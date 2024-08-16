/*     */ package com.name.util;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Timer;
/*     */ import java.util.TimerTask;
/*     */ import java.util.UUID;
/*     */ import java.util.logging.Level;
/*     */ import java.util.zip.GZIPOutputStream;
/*     */ import javax.net.ssl.HttpsURLConnection;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.plugin.ServicePriority;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ import org.json.simple.JSONArray;
/*     */ import org.json.simple.JSONObject;
/*     */ 
/*     */ public class Metrics {
/*     */   public static final int B_STATS_VERSION = 1;
/*     */   
/*     */   static {
/*  29 */     String defaultPackage = new String(new byte[] { 111, 114, 103, 46, 98, 115, 116, 97, 116, 115 });
/*  30 */     String examplePackage = new String(new byte[] { 121, 111, 117, 114, 46, 112, 97, 99, 107, 97, 103, 101 });
/*     */     
/*  32 */     if (Metrics.class.getPackage().getName().equals(defaultPackage) || Metrics.class.getPackage().getName().equals(examplePackage))
/*  33 */       throw new IllegalStateException("bStats Metrics class has not been relocated correctly!"); 
/*     */   }
/*     */   private static final String URL = "https://bStats.org/submitData/bukkit"; private static boolean logFailedRequests;
/*     */   private static String serverUUID;
/*     */   private final JavaPlugin plugin;
/*  38 */   private final List<CustomChart> charts = new ArrayList<>();
/*     */ 
/*     */   
/*     */   public Metrics(JavaPlugin plugin) {
/*  42 */     if (plugin == null) {
/*  43 */       throw new IllegalArgumentException("Plugin cannot be null!");
/*     */     }
/*  45 */     this.plugin = plugin;
/*     */ 
/*     */     
/*  48 */     File bStatsFolder = new File(plugin.getDataFolder().getParentFile(), "bStats");
/*  49 */     File configFile = new File(bStatsFolder, "config.yml");
/*  50 */     YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
/*     */ 
/*     */     
/*  53 */     if (!config.isSet("serverUuid")) {
/*     */ 
/*     */       
/*  56 */       config.addDefault("enabled", Boolean.valueOf(true));
/*     */       
/*  58 */       config.addDefault("serverUuid", UUID.randomUUID().toString());
/*     */       
/*  60 */       config.addDefault("logFailedRequests", Boolean.valueOf(false));
/*     */ 
/*     */       
/*  63 */       config.options().header("bStats collects some data for plugin authors like how many servers are using their plugins.\nTo honor their work, you should not disable it.\nThis has nearly no effect on the server performance!\nCheck out https://bStats.org/ to learn more :)")
/*     */ 
/*     */         
/*  66 */         .copyDefaults(true);
/*     */       try {
/*  68 */         config.save(configFile);
/*  69 */       } catch (IOException iOException) {}
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  74 */     serverUUID = config.getString("serverUuid");
/*  75 */     logFailedRequests = config.getBoolean("logFailedRequests", false);
/*  76 */     if (config.getBoolean("enabled", true)) {
/*  77 */       boolean found = false;
/*     */       
/*  79 */       for (Class<?> service : (Iterable<Class<?>>)Bukkit.getServicesManager().getKnownServices()) {
/*     */         try {
/*  81 */           service.getField("B_STATS_VERSION");
/*  82 */           found = true;
/*     */           break;
/*  84 */         } catch (NoSuchFieldException noSuchFieldException) {}
/*     */       } 
/*     */ 
/*     */       
/*  88 */       Bukkit.getServicesManager().register(Metrics.class, this, (Plugin)plugin, ServicePriority.Normal);
/*  89 */       if (!found) {
/*  90 */         startSubmitting();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void sendData(JSONObject data) throws Exception {
/*  96 */     if (data == null) {
/*  97 */       throw new IllegalArgumentException("Data cannot be null!");
/*     */     }
/*  99 */     if (Bukkit.isPrimaryThread()) {
/* 100 */       throw new IllegalAccessException("This method must not be called from the main thread!");
/*     */     }
/* 102 */     HttpsURLConnection connection = (HttpsURLConnection)(new URL("https://bStats.org/submitData/bukkit")).openConnection();
/*     */ 
/*     */     
/* 105 */     byte[] compressedData = compress(data.toString());
/*     */ 
/*     */     
/* 108 */     connection.setRequestMethod("POST");
/* 109 */     connection.addRequestProperty("Accept", "application/json");
/* 110 */     connection.addRequestProperty("Connection", "close");
/* 111 */     connection.addRequestProperty("Content-Encoding", "gzip");
/* 112 */     connection.addRequestProperty("Content-Length", String.valueOf(compressedData.length));
/* 113 */     connection.setRequestProperty("Content-Type", "application/json");
/* 114 */     connection.setRequestProperty("User-Agent", "MC-Server/1");
/*     */ 
/*     */     
/* 117 */     connection.setDoOutput(true);
/* 118 */     DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
/* 119 */     outputStream.write(compressedData);
/* 120 */     outputStream.flush();
/* 121 */     outputStream.close();
/*     */     
/* 123 */     connection.getInputStream().close();
/*     */   }
/*     */   
/*     */   private static byte[] compress(String str) throws IOException {
/* 127 */     if (str == null) {
/* 128 */       return null;
/*     */     }
/* 130 */     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
/* 131 */     GZIPOutputStream gzip = new GZIPOutputStream(outputStream);
/* 132 */     gzip.write(str.getBytes("UTF-8"));
/* 133 */     gzip.close();
/* 134 */     return outputStream.toByteArray();
/*     */   }
/*     */   
/*     */   public void addCustomChart(CustomChart chart) {
/* 138 */     if (chart == null) {
/* 139 */       throw new IllegalArgumentException("Chart cannot be null!");
/*     */     }
/* 141 */     this.charts.add(chart);
/*     */   }
/*     */   
/*     */   private void startSubmitting() {
/* 145 */     final Timer timer = new Timer(true);
/* 146 */     timer.scheduleAtFixedRate(new TimerTask() {
/*     */           public void run() {
/* 148 */             if (!Metrics.this.plugin.isEnabled()) {
/* 149 */               timer.cancel();
/*     */               
/*     */               return;
/*     */             } 
/*     */             
/* 154 */             Bukkit.getScheduler().runTask((Plugin)Metrics.this.plugin, new Runnable() {
/*     */                   public void run() {
/* 156 */                     Metrics.this.submitData();
/*     */                   }
/*     */                 });
/*     */           }
/*     */         }300000L, 1800000L);
/*     */   }
/*     */   
/*     */   public JSONObject getPluginData() {
/* 164 */     JSONObject data = new JSONObject();
/*     */     
/* 166 */     String pluginName = this.plugin.getDescription().getName();
/* 167 */     String pluginVersion = this.plugin.getDescription().getVersion();
/*     */     
/* 169 */     data.put("pluginName", pluginName);
/* 170 */     data.put("pluginVersion", pluginVersion);
/* 171 */     JSONArray customCharts = new JSONArray();
/* 172 */     for (CustomChart customChart : this.charts) {
/*     */       
/* 174 */       JSONObject chart = customChart.getRequestJsonObject();
/* 175 */       if (chart == null) {
/*     */         continue;
/*     */       }
/* 178 */       customCharts.add(chart);
/*     */     } 
/* 180 */     data.put("customCharts", customCharts);
/*     */     
/* 182 */     return data;
/*     */   }
/*     */   
/*     */   private JSONObject getServerData() {
/* 186 */     int playerAmount = Bukkit.getOnlinePlayers().size();
/* 187 */     int onlineMode = Bukkit.getOnlineMode() ? 1 : 0;
/* 188 */     String bukkitVersion = Bukkit.getVersion();
/* 189 */     bukkitVersion = bukkitVersion.substring(bukkitVersion.indexOf("MC: ") + 4, bukkitVersion.length() - 1);
/*     */ 
/*     */     
/* 192 */     String javaVersion = System.getProperty("java.version");
/* 193 */     String osName = System.getProperty("os.name");
/* 194 */     String osArch = System.getProperty("os.arch");
/* 195 */     String osVersion = System.getProperty("os.version");
/* 196 */     int coreCount = Runtime.getRuntime().availableProcessors();
/*     */     
/* 198 */     JSONObject data = new JSONObject();
/*     */     
/* 200 */     data.put("serverUUID", serverUUID);
/*     */     
/* 202 */     data.put("playerAmount", Integer.valueOf(playerAmount));
/* 203 */     data.put("onlineMode", Integer.valueOf(onlineMode));
/* 204 */     data.put("bukkitVersion", bukkitVersion);
/*     */     
/* 206 */     data.put("javaVersion", javaVersion);
/* 207 */     data.put("osName", osName);
/* 208 */     data.put("osArch", osArch);
/* 209 */     data.put("osVersion", osVersion);
/* 210 */     data.put("coreCount", Integer.valueOf(coreCount));
/*     */     
/* 212 */     return data;
/*     */   }
/*     */   
/*     */   private void submitData() {
/* 216 */     final JSONObject data = getServerData();
/*     */     
/* 218 */     JSONArray pluginData = new JSONArray();
/*     */     
/* 220 */     for (Class<?> service : (Iterable<Class<?>>)Bukkit.getServicesManager().getKnownServices()) {
/*     */       try {
/* 222 */         service.getField("B_STATS_VERSION");
/* 223 */       } catch (NoSuchFieldException ignored) {
/*     */         continue;
/*     */       } 
/*     */       
/*     */       try {
/* 228 */         pluginData.add(service.getMethod("getPluginData", new Class[0]).invoke(Bukkit.getServicesManager().load(service), new Object[0]));
/* 229 */       } catch (NoSuchMethodException|IllegalAccessException|java.lang.reflect.InvocationTargetException noSuchMethodException) {}
/*     */     } 
/*     */ 
/*     */     
/* 233 */     data.put("plugins", pluginData);
/*     */ 
/*     */     
/* 236 */     (new Thread(new Runnable() {
/*     */           public void run() {
/*     */             try {
/* 239 */               Metrics.sendData(data);
/* 240 */             } catch (Exception e) {
/*     */               
/* 242 */               if (Metrics.logFailedRequests) {
/* 243 */                 Metrics.this.plugin.getLogger().log(Level.WARNING, "Could not submit plugin stats of " + Metrics.this.plugin.getName(), e);
/*     */               }
/*     */             } 
/*     */           }
/* 247 */         })).start();
/*     */   }
/*     */   
/*     */   public enum Country
/*     */   {
/* 252 */     AUTO_DETECT("AUTO", "Auto Detected"),
/*     */     
/* 254 */     ANDORRA("AD", "Andorra"),
/* 255 */     UNITED_ARAB_EMIRATES("AE", "United Arab Emirates"),
/* 256 */     AFGHANISTAN("AF", "Afghanistan"),
/* 257 */     ANTIGUA_AND_BARBUDA("AG", "Antigua and Barbuda"),
/* 258 */     ANGUILLA("AI", "Anguilla"),
/* 259 */     ALBANIA("AL", "Albania"),
/* 260 */     ARMENIA("AM", "Armenia"),
/* 261 */     NETHERLANDS_ANTILLES("AN", "Netherlands Antilles"),
/* 262 */     ANGOLA("AO", "Angola"),
/* 263 */     ANTARCTICA("AQ", "Antarctica"),
/* 264 */     ARGENTINA("AR", "Argentina"),
/* 265 */     AMERICAN_SAMOA("AS", "American Samoa"),
/* 266 */     AUSTRIA("AT", "Austria"),
/* 267 */     AUSTRALIA("AU", "Australia"),
/* 268 */     ARUBA("AW", "Aruba"),
/* 269 */     ALAND_ISLANDS("AX", "Åland Islands"),
/* 270 */     AZERBAIJAN("AZ", "Azerbaijan"),
/* 271 */     BOSNIA_AND_HERZEGOVINA("BA", "Bosnia and Herzegovina"),
/* 272 */     BARBADOS("BB", "Barbados"),
/* 273 */     BANGLADESH("BD", "Bangladesh"),
/* 274 */     BELGIUM("BE", "Belgium"),
/* 275 */     BURKINA_FASO("BF", "Burkina Faso"),
/* 276 */     BULGARIA("BG", "Bulgaria"),
/* 277 */     BAHRAIN("BH", "Bahrain"),
/* 278 */     BURUNDI("BI", "Burundi"),
/* 279 */     BENIN("BJ", "Benin"),
/* 280 */     SAINT_BARTHELEMY("BL", "Saint Barthélemy"),
/* 281 */     BERMUDA("BM", "Bermuda"),
/* 282 */     BRUNEI("BN", "Brunei"),
/* 283 */     BOLIVIA("BO", "Bolivia"),
/* 284 */     BONAIRE_SINT_EUSTATIUS_AND_SABA("BQ", "Bonaire, Sint Eustatius and Saba"),
/* 285 */     BRAZIL("BR", "Brazil"),
/* 286 */     BAHAMAS("BS", "Bahamas"),
/* 287 */     BHUTAN("BT", "Bhutan"),
/* 288 */     BOUVET_ISLAND("BV", "Bouvet Island"),
/* 289 */     BOTSWANA("BW", "Botswana"),
/* 290 */     BELARUS("BY", "Belarus"),
/* 291 */     BELIZE("BZ", "Belize"),
/* 292 */     CANADA("CA", "Canada"),
/* 293 */     COCOS_ISLANDS("CC", "Cocos Islands"),
/* 294 */     THE_DEMOCRATIC_REPUBLIC_OF_CONGO("CD", "The Democratic Republic Of Congo"),
/* 295 */     CENTRAL_AFRICAN_REPUBLIC("CF", "Central African Republic"),
/* 296 */     CONGO("CG", "Congo"),
/* 297 */     SWITZERLAND("CH", "Switzerland"),
/* 298 */     COTE_D_IVOIRE("CI", "Côte d'Ivoire"),
/* 299 */     COOK_ISLANDS("CK", "Cook Islands"),
/* 300 */     CHILE("CL", "Chile"),
/* 301 */     CAMEROON("CM", "Cameroon"),
/* 302 */     CHINA("CN", "China"),
/* 303 */     COLOMBIA("CO", "Colombia"),
/* 304 */     COSTA_RICA("CR", "Costa Rica"),
/* 305 */     CUBA("CU", "Cuba"),
/* 306 */     CAPE_VERDE("CV", "Cape Verde"),
/* 307 */     CURACAO("CW", "Curaçao"),
/* 308 */     CHRISTMAS_ISLAND("CX", "Christmas Island"),
/* 309 */     CYPRUS("CY", "Cyprus"),
/* 310 */     CZECH_REPUBLIC("CZ", "Czech Republic"),
/* 311 */     GERMANY("DE", "Germany"),
/* 312 */     DJIBOUTI("DJ", "Djibouti"),
/* 313 */     DENMARK("DK", "Denmark"),
/* 314 */     DOMINICA("DM", "Dominica"),
/* 315 */     DOMINICAN_REPUBLIC("DO", "Dominican Republic"),
/* 316 */     ALGERIA("DZ", "Algeria"),
/* 317 */     ECUADOR("EC", "Ecuador"),
/* 318 */     ESTONIA("EE", "Estonia"),
/* 319 */     EGYPT("EG", "Egypt"),
/* 320 */     WESTERN_SAHARA("EH", "Western Sahara"),
/* 321 */     ERITREA("ER", "Eritrea"),
/* 322 */     SPAIN("ES", "Spain"),
/* 323 */     ETHIOPIA("ET", "Ethiopia"),
/* 324 */     FINLAND("FI", "Finland"),
/* 325 */     FIJI("FJ", "Fiji"),
/* 326 */     FALKLAND_ISLANDS("FK", "Falkland Islands"),
/* 327 */     MICRONESIA("FM", "Micronesia"),
/* 328 */     FAROE_ISLANDS("FO", "Faroe Islands"),
/* 329 */     FRANCE("FR", "France"),
/* 330 */     GABON("GA", "Gabon"),
/* 331 */     UNITED_KINGDOM("GB", "United Kingdom"),
/* 332 */     GRENADA("GD", "Grenada"),
/* 333 */     GEORGIA("GE", "Georgia"),
/* 334 */     FRENCH_GUIANA("GF", "French Guiana"),
/* 335 */     GUERNSEY("GG", "Guernsey"),
/* 336 */     GHANA("GH", "Ghana"),
/* 337 */     GIBRALTAR("GI", "Gibraltar"),
/* 338 */     GREENLAND("GL", "Greenland"),
/* 339 */     GAMBIA("GM", "Gambia"),
/* 340 */     GUINEA("GN", "Guinea"),
/* 341 */     GUADELOUPE("GP", "Guadeloupe"),
/* 342 */     EQUATORIAL_GUINEA("GQ", "Equatorial Guinea"),
/* 343 */     GREECE("GR", "Greece"),
/* 344 */     SOUTH_GEORGIA_AND_THE_SOUTH_SANDWICH_ISLANDS("GS", "South Georgia And The South Sandwich Islands"),
/* 345 */     GUATEMALA("GT", "Guatemala"),
/* 346 */     GUAM("GU", "Guam"),
/* 347 */     GUINEA_BISSAU("GW", "Guinea-Bissau"),
/* 348 */     GUYANA("GY", "Guyana"),
/* 349 */     HONG_KONG("HK", "Hong Kong"),
/* 350 */     HEARD_ISLAND_AND_MCDONALD_ISLANDS("HM", "Heard Island And McDonald Islands"),
/* 351 */     HONDURAS("HN", "Honduras"),
/* 352 */     CROATIA("HR", "Croatia"),
/* 353 */     HAITI("HT", "Haiti"),
/* 354 */     HUNGARY("HU", "Hungary"),
/* 355 */     INDONESIA("ID", "Indonesia"),
/* 356 */     IRELAND("IE", "Ireland"),
/* 357 */     ISRAEL("IL", "Israel"),
/* 358 */     ISLE_OF_MAN("IM", "Isle Of Man"),
/* 359 */     INDIA("IN", "India"),
/* 360 */     BRITISH_INDIAN_OCEAN_TERRITORY("IO", "British Indian Ocean Territory"),
/* 361 */     IRAQ("IQ", "Iraq"),
/* 362 */     IRAN("IR", "Iran"),
/* 363 */     ICELAND("IS", "Iceland"),
/* 364 */     ITALY("IT", "Italy"),
/* 365 */     JERSEY("JE", "Jersey"),
/* 366 */     JAMAICA("JM", "Jamaica"),
/* 367 */     JORDAN("JO", "Jordan"),
/* 368 */     JAPAN("JP", "Japan"),
/* 369 */     KENYA("KE", "Kenya"),
/* 370 */     KYRGYZSTAN("KG", "Kyrgyzstan"),
/* 371 */     CAMBODIA("KH", "Cambodia"),
/* 372 */     KIRIBATI("KI", "Kiribati"),
/* 373 */     COMOROS("KM", "Comoros"),
/* 374 */     SAINT_KITTS_AND_NEVIS("KN", "Saint Kitts And Nevis"),
/* 375 */     NORTH_KOREA("KP", "North Korea"),
/* 376 */     SOUTH_KOREA("KR", "South Korea"),
/* 377 */     KUWAIT("KW", "Kuwait"),
/* 378 */     CAYMAN_ISLANDS("KY", "Cayman Islands"),
/* 379 */     KAZAKHSTAN("KZ", "Kazakhstan"),
/* 380 */     LAOS("LA", "Laos"),
/* 381 */     LEBANON("LB", "Lebanon"),
/* 382 */     SAINT_LUCIA("LC", "Saint Lucia"),
/* 383 */     LIECHTENSTEIN("LI", "Liechtenstein"),
/* 384 */     SRI_LANKA("LK", "Sri Lanka"),
/* 385 */     LIBERIA("LR", "Liberia"),
/* 386 */     LESOTHO("LS", "Lesotho"),
/* 387 */     LITHUANIA("LT", "Lithuania"),
/* 388 */     LUXEMBOURG("LU", "Luxembourg"),
/* 389 */     LATVIA("LV", "Latvia"),
/* 390 */     LIBYA("LY", "Libya"),
/* 391 */     MOROCCO("MA", "Morocco"),
/* 392 */     MONACO("MC", "Monaco"),
/* 393 */     MOLDOVA("MD", "Moldova"),
/* 394 */     MONTENEGRO("ME", "Montenegro"),
/* 395 */     SAINT_MARTIN("MF", "Saint Martin"),
/* 396 */     MADAGASCAR("MG", "Madagascar"),
/* 397 */     MARSHALL_ISLANDS("MH", "Marshall Islands"),
/* 398 */     MACEDONIA("MK", "Macedonia"),
/* 399 */     MALI("ML", "Mali"),
/* 400 */     MYANMAR("MM", "Myanmar"),
/* 401 */     MONGOLIA("MN", "Mongolia"),
/* 402 */     MACAO("MO", "Macao"),
/* 403 */     NORTHERN_MARIANA_ISLANDS("MP", "Northern Mariana Islands"),
/* 404 */     MARTINIQUE("MQ", "Martinique"),
/* 405 */     MAURITANIA("MR", "Mauritania"),
/* 406 */     MONTSERRAT("MS", "Montserrat"),
/* 407 */     MALTA("MT", "Malta"),
/* 408 */     MAURITIUS("MU", "Mauritius"),
/* 409 */     MALDIVES("MV", "Maldives"),
/* 410 */     MALAWI("MW", "Malawi"),
/* 411 */     MEXICO("MX", "Mexico"),
/* 412 */     MALAYSIA("MY", "Malaysia"),
/* 413 */     MOZAMBIQUE("MZ", "Mozambique"),
/* 414 */     NAMIBIA("NA", "Namibia"),
/* 415 */     NEW_CALEDONIA("NC", "New Caledonia"),
/* 416 */     NIGER("NE", "Niger"),
/* 417 */     NORFOLK_ISLAND("NF", "Norfolk Island"),
/* 418 */     NIGERIA("NG", "Nigeria"),
/* 419 */     NICARAGUA("NI", "Nicaragua"),
/* 420 */     NETHERLANDS("NL", "Netherlands"),
/* 421 */     NORWAY("NO", "Norway"),
/* 422 */     NEPAL("NP", "Nepal"),
/* 423 */     NAURU("NR", "Nauru"),
/* 424 */     NIUE("NU", "Niue"),
/* 425 */     NEW_ZEALAND("NZ", "New Zealand"),
/* 426 */     OMAN("OM", "Oman"),
/* 427 */     PANAMA("PA", "Panama"),
/* 428 */     PERU("PE", "Peru"),
/* 429 */     FRENCH_POLYNESIA("PF", "French Polynesia"),
/* 430 */     PAPUA_NEW_GUINEA("PG", "Papua New Guinea"),
/* 431 */     PHILIPPINES("PH", "Philippines"),
/* 432 */     PAKISTAN("PK", "Pakistan"),
/* 433 */     POLAND("PL", "Poland"),
/* 434 */     SAINT_PIERRE_AND_MIQUELON("PM", "Saint Pierre And Miquelon"),
/* 435 */     PITCAIRN("PN", "Pitcairn"),
/* 436 */     PUERTO_RICO("PR", "Puerto Rico"),
/* 437 */     PALESTINE("PS", "Palestine"),
/* 438 */     PORTUGAL("PT", "Portugal"),
/* 439 */     PALAU("PW", "Palau"),
/* 440 */     PARAGUAY("PY", "Paraguay"),
/* 441 */     QATAR("QA", "Qatar"),
/* 442 */     REUNION("RE", "Reunion"),
/* 443 */     ROMANIA("RO", "Romania"),
/* 444 */     SERBIA("RS", "Serbia"),
/* 445 */     RUSSIA("RU", "Russia"),
/* 446 */     RWANDA("RW", "Rwanda"),
/* 447 */     SAUDI_ARABIA("SA", "Saudi Arabia"),
/* 448 */     SOLOMON_ISLANDS("SB", "Solomon Islands"),
/* 449 */     SEYCHELLES("SC", "Seychelles"),
/* 450 */     SUDAN("SD", "Sudan"),
/* 451 */     SWEDEN("SE", "Sweden"),
/* 452 */     SINGAPORE("SG", "Singapore"),
/* 453 */     SAINT_HELENA("SH", "Saint Helena"),
/* 454 */     SLOVENIA("SI", "Slovenia"),
/* 455 */     SVALBARD_AND_JAN_MAYEN("SJ", "Svalbard And Jan Mayen"),
/* 456 */     SLOVAKIA("SK", "Slovakia"),
/* 457 */     SIERRA_LEONE("SL", "Sierra Leone"),
/* 458 */     SAN_MARINO("SM", "San Marino"),
/* 459 */     SENEGAL("SN", "Senegal"),
/* 460 */     SOMALIA("SO", "Somalia"),
/* 461 */     SURINAME("SR", "Suriname"),
/* 462 */     SOUTH_SUDAN("SS", "South Sudan"),
/* 463 */     SAO_TOME_AND_PRINCIPE("ST", "Sao Tome And Principe"),
/* 464 */     EL_SALVADOR("SV", "El Salvador"),
/* 465 */     SINT_MAARTEN_DUTCH_PART("SX", "Sint Maarten (Dutch part)"),
/* 466 */     SYRIA("SY", "Syria"),
/* 467 */     SWAZILAND("SZ", "Swaziland"),
/* 468 */     TURKS_AND_CAICOS_ISLANDS("TC", "Turks And Caicos Islands"),
/* 469 */     CHAD("TD", "Chad"),
/* 470 */     FRENCH_SOUTHERN_TERRITORIES("TF", "French Southern Territories"),
/* 471 */     TOGO("TG", "Togo"),
/* 472 */     THAILAND("TH", "Thailand"),
/* 473 */     TAJIKISTAN("TJ", "Tajikistan"),
/* 474 */     TOKELAU("TK", "Tokelau"),
/* 475 */     TIMOR_LESTE("TL", "Timor-Leste"),
/* 476 */     TURKMENISTAN("TM", "Turkmenistan"),
/* 477 */     TUNISIA("TN", "Tunisia"),
/* 478 */     TONGA("TO", "Tonga"),
/* 479 */     TURKEY("TR", "Turkey"),
/* 480 */     TRINIDAD_AND_TOBAGO("TT", "Trinidad and Tobago"),
/* 481 */     TUVALU("TV", "Tuvalu"),
/* 482 */     TAIWAN("TW", "Taiwan"),
/* 483 */     TANZANIA("TZ", "Tanzania"),
/* 484 */     UKRAINE("UA", "Ukraine"),
/* 485 */     UGANDA("UG", "Uganda"),
/* 486 */     UNITED_STATES_MINOR_OUTLYING_ISLANDS("UM", "United States Minor Outlying Islands"),
/* 487 */     UNITED_STATES("US", "United States"),
/* 488 */     URUGUAY("UY", "Uruguay"),
/* 489 */     UZBEKISTAN("UZ", "Uzbekistan"),
/* 490 */     VATICAN("VA", "Vatican"),
/* 491 */     SAINT_VINCENT_AND_THE_GRENADINES("VC", "Saint Vincent And The Grenadines"),
/* 492 */     VENEZUELA("VE", "Venezuela"),
/* 493 */     BRITISH_VIRGIN_ISLANDS("VG", "British Virgin Islands"),
/* 494 */     U_S__VIRGIN_ISLANDS("VI", "U.S. Virgin Islands"),
/* 495 */     VIETNAM("VN", "Vietnam"),
/* 496 */     VANUATU("VU", "Vanuatu"),
/* 497 */     WALLIS_AND_FUTUNA("WF", "Wallis And Futuna"),
/* 498 */     SAMOA("WS", "Samoa"),
/* 499 */     YEMEN("YE", "Yemen"),
/* 500 */     MAYOTTE("YT", "Mayotte"),
/* 501 */     SOUTH_AFRICA("ZA", "South Africa"),
/* 502 */     ZAMBIA("ZM", "Zambia"),
/* 503 */     ZIMBABWE("ZW", "Zimbabwe");
/*     */     
/*     */     private String isoTag;
/*     */     private String name;
/*     */     
/*     */     Country(String isoTag, String name) {
/* 509 */       this.isoTag = isoTag;
/* 510 */       this.name = name;
/*     */     }
/*     */     
/*     */     public static Country byIsoTag(String isoTag) {
/* 514 */       for (Country country : values()) {
/* 515 */         if (country.getCountryIsoTag().equals(isoTag)) {
/* 516 */           return country;
/*     */         }
/*     */       } 
/* 519 */       return null;
/*     */     }
/*     */     
/*     */     public static Country byLocale(Locale locale) {
/* 523 */       return byIsoTag(locale.getCountry());
/*     */     }
/*     */     
/*     */     public String getCountryName() {
/* 527 */       return this.name;
/*     */     }
/*     */     
/*     */     public String getCountryIsoTag() {
/* 531 */       return this.isoTag;
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class CustomChart
/*     */   {
/*     */     protected final String chartId;
/*     */     
/*     */     public CustomChart(String chartId) {
/* 540 */       if (chartId == null || chartId.isEmpty()) {
/* 541 */         throw new IllegalArgumentException("ChartId cannot be null or empty!");
/*     */       }
/* 543 */       this.chartId = chartId;
/*     */     }
/*     */     
/*     */     protected JSONObject getRequestJsonObject() {
/* 547 */       JSONObject chart = new JSONObject();
/* 548 */       chart.put("chartId", this.chartId);
/*     */       try {
/* 550 */         JSONObject data = getChartData();
/* 551 */         if (data == null) {
/* 552 */           return null;
/*     */         }
/* 554 */         chart.put("data", data);
/* 555 */       } catch (Throwable t) {
/* 556 */         if (Metrics.logFailedRequests) {
/* 557 */           Bukkit.getLogger().log(Level.WARNING, "Failed to get data for custom chart with id " + this.chartId, t);
/*     */         }
/* 559 */         return null;
/*     */       } 
/* 561 */       return chart;
/*     */     }
/*     */     
/*     */     protected abstract JSONObject getChartData();
/*     */   }
/*     */   
/*     */   public static abstract class SimplePie
/*     */     extends CustomChart
/*     */   {
/*     */     public SimplePie(String chartId) {
/* 571 */       super(chartId);
/*     */     }
/*     */ 
/*     */     
/*     */     public abstract String getValue();
/*     */ 
/*     */     
/*     */     protected JSONObject getChartData() {
/* 579 */       JSONObject data = new JSONObject();
/* 580 */       String value = getValue();
/* 581 */       if (value == null || value.isEmpty()) {
/* 582 */         return null;
/*     */       }
/* 584 */       data.put("value", value);
/* 585 */       return data;
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class AdvancedPie
/*     */     extends CustomChart {
/*     */     public AdvancedPie(String chartId) {
/* 592 */       super(chartId);
/*     */     }
/*     */ 
/*     */     
/*     */     public abstract HashMap<String, Integer> getValues(HashMap<String, Integer> param1HashMap);
/*     */ 
/*     */     
/*     */     protected JSONObject getChartData() {
/* 600 */       JSONObject data = new JSONObject();
/* 601 */       JSONObject values = new JSONObject();
/* 602 */       HashMap<String, Integer> map = getValues(new HashMap<>());
/* 603 */       if (map == null || map.isEmpty()) {
/* 604 */         return null;
/*     */       }
/* 606 */       boolean allSkipped = true;
/* 607 */       for (Map.Entry<String, Integer> entry : map.entrySet()) {
/* 608 */         if (((Integer)entry.getValue()).intValue() == 0) {
/*     */           continue;
/*     */         }
/* 611 */         allSkipped = false;
/* 612 */         values.put(entry.getKey(), entry.getValue());
/*     */       } 
/* 614 */       if (allSkipped) {
/* 615 */         return null;
/*     */       }
/* 617 */       data.put("values", values);
/* 618 */       return data;
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class SingleLineChart
/*     */     extends CustomChart {
/*     */     public SingleLineChart(String chartId) {
/* 625 */       super(chartId);
/*     */     }
/*     */ 
/*     */     
/*     */     public abstract int getValue();
/*     */ 
/*     */     
/*     */     protected JSONObject getChartData() {
/* 633 */       JSONObject data = new JSONObject();
/* 634 */       int value = getValue();
/* 635 */       if (value == 0) {
/* 636 */         return null;
/*     */       }
/* 638 */       data.put("value", Integer.valueOf(value));
/* 639 */       return data;
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class MultiLineChart
/*     */     extends CustomChart {
/*     */     public MultiLineChart(String chartId) {
/* 646 */       super(chartId);
/*     */     }
/*     */ 
/*     */     
/*     */     public abstract HashMap<String, Integer> getValues(HashMap<String, Integer> param1HashMap);
/*     */ 
/*     */     
/*     */     protected JSONObject getChartData() {
/* 654 */       JSONObject data = new JSONObject();
/* 655 */       JSONObject values = new JSONObject();
/* 656 */       HashMap<String, Integer> map = getValues(new HashMap<>());
/* 657 */       if (map == null || map.isEmpty()) {
/* 658 */         return null;
/*     */       }
/* 660 */       boolean allSkipped = true;
/* 661 */       for (Map.Entry<String, Integer> entry : map.entrySet()) {
/* 662 */         if (((Integer)entry.getValue()).intValue() == 0) {
/*     */           continue;
/*     */         }
/* 665 */         allSkipped = false;
/* 666 */         values.put(entry.getKey(), entry.getValue());
/*     */       } 
/* 668 */       if (allSkipped) {
/* 669 */         return null;
/*     */       }
/* 671 */       data.put("values", values);
/* 672 */       return data;
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class SimpleBarChart
/*     */     extends CustomChart {
/*     */     public SimpleBarChart(String chartId) {
/* 679 */       super(chartId);
/*     */     }
/*     */ 
/*     */     
/*     */     public abstract HashMap<String, Integer> getValues(HashMap<String, Integer> param1HashMap);
/*     */ 
/*     */     
/*     */     protected JSONObject getChartData() {
/* 687 */       JSONObject data = new JSONObject();
/* 688 */       JSONObject values = new JSONObject();
/* 689 */       HashMap<String, Integer> map = getValues(new HashMap<>());
/* 690 */       if (map == null || map.isEmpty()) {
/* 691 */         return null;
/*     */       }
/* 693 */       for (Map.Entry<String, Integer> entry : map.entrySet()) {
/* 694 */         JSONArray categoryValues = new JSONArray();
/* 695 */         categoryValues.add(entry.getValue());
/* 696 */         values.put(entry.getKey(), categoryValues);
/*     */       } 
/* 698 */       data.put("values", values);
/* 699 */       return data;
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class AdvancedBarChart
/*     */     extends CustomChart {
/*     */     public AdvancedBarChart(String chartId) {
/* 706 */       super(chartId);
/*     */     }
/*     */ 
/*     */     
/*     */     public abstract HashMap<String, int[]> getValues(HashMap<String, int[]> param1HashMap);
/*     */ 
/*     */     
/*     */     protected JSONObject getChartData() {
/* 714 */       JSONObject data = new JSONObject();
/* 715 */       JSONObject values = new JSONObject();
/* 716 */       HashMap<String, int[]> map = getValues((HashMap)new HashMap<>());
/* 717 */       if (map == null || map.isEmpty()) {
/* 718 */         return null;
/*     */       }
/* 720 */       boolean allSkipped = true;
/* 721 */       for (Map.Entry<String, int[]> entry : map.entrySet()) {
/* 722 */         if (((int[])entry.getValue()).length == 0) {
/*     */           continue;
/*     */         }
/* 725 */         allSkipped = false;
/* 726 */         JSONArray categoryValues = new JSONArray();
/* 727 */         for (int categoryValue : (int[])entry.getValue()) {
/* 728 */           categoryValues.add(Integer.valueOf(categoryValue));
/*     */         }
/* 730 */         values.put(entry.getKey(), categoryValues);
/*     */       } 
/* 732 */       if (allSkipped) {
/* 733 */         return null;
/*     */       }
/* 735 */       data.put("values", values);
/* 736 */       return data;
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class SimpleMapChart
/*     */     extends CustomChart {
/*     */     public SimpleMapChart(String chartId) {
/* 743 */       super(chartId);
/*     */     }
/*     */ 
/*     */     
/*     */     public abstract Metrics.Country getValue();
/*     */ 
/*     */     
/*     */     protected JSONObject getChartData() {
/* 751 */       JSONObject data = new JSONObject();
/* 752 */       Metrics.Country value = getValue();
/*     */       
/* 754 */       if (value == null) {
/* 755 */         return null;
/*     */       }
/* 757 */       data.put("value", value.getCountryIsoTag());
/* 758 */       return data;
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class AdvancedMapChart
/*     */     extends CustomChart {
/*     */     public AdvancedMapChart(String chartId) {
/* 765 */       super(chartId);
/*     */     }
/*     */ 
/*     */     
/*     */     public abstract HashMap<Metrics.Country, Integer> getValues(HashMap<Metrics.Country, Integer> param1HashMap);
/*     */ 
/*     */     
/*     */     protected JSONObject getChartData() {
/* 773 */       JSONObject data = new JSONObject();
/* 774 */       JSONObject values = new JSONObject();
/* 775 */       HashMap<Metrics.Country, Integer> map = getValues(new HashMap<>());
/* 776 */       if (map == null || map.isEmpty()) {
/* 777 */         return null;
/*     */       }
/* 779 */       boolean allSkipped = true;
/* 780 */       for (Map.Entry<Metrics.Country, Integer> entry : map.entrySet()) {
/* 781 */         if (((Integer)entry.getValue()).intValue() == 0) {
/*     */           continue;
/*     */         }
/* 784 */         allSkipped = false;
/* 785 */         values.put(((Metrics.Country)entry.getKey()).getCountryIsoTag(), entry.getValue());
/*     */       } 
/* 787 */       if (allSkipped) {
/* 788 */         return null;
/*     */       }
/* 790 */       data.put("values", values);
/* 791 */       return data;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\cheen\Downloads\BetterMobAI-2.11 alpha.jar!\com\nam\\util\Metrics.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
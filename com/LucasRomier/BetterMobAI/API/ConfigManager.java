/*     */ package com.LucasRomier.BetterMobAI.API;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.List;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ 
/*     */ public class ConfigManager {
/*     */   public ConfigManager(JavaPlugin plugin) {
/*  17 */     this.plugin = plugin;
/*     */   }
/*     */   private JavaPlugin plugin;
/*     */   
/*     */   public SimpleConfig getNewConfig(String filePath, String[] header) {
/*  22 */     File file = getConfigFile(filePath);
/*     */     
/*  24 */     assert file != null;
/*  25 */     if (!file.exists()) {
/*  26 */       prepareFile(filePath);
/*     */       
/*  28 */       if (header != null && header.length != 0) {
/*  29 */         setHeader(file, header);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  34 */     return new SimpleConfig(file, getCommentsNum(file), this.plugin);
/*     */   }
/*     */ 
/*     */   
/*     */   public SimpleConfig getNewConfig(String filePath) {
/*  39 */     return getNewConfig(filePath, null);
/*     */   }
/*     */ 
/*     */   
/*     */   private File getConfigFile(String file) {
/*     */     File configFile;
/*  45 */     if (file.isEmpty()) {
/*  46 */       return null;
/*     */     }
/*     */ 
/*     */     
/*  50 */     if (file.contains("/")) {
/*     */       
/*  52 */       if (file.startsWith("/")) {
/*  53 */         configFile = new File(this.plugin.getDataFolder() + file.replace("/", File.separator));
/*     */       } else {
/*  55 */         configFile = new File(this.plugin.getDataFolder() + File.separator + file.replace("/", File.separator));
/*     */       } 
/*     */     } else {
/*     */       
/*  59 */       configFile = new File(this.plugin.getDataFolder(), file);
/*     */     } 
/*     */     
/*  62 */     return configFile;
/*     */   }
/*     */ 
/*     */   
/*     */   public void prepareFile(String filePath, String resource) {
/*  67 */     File file = getConfigFile(filePath);
/*     */     
/*  69 */     assert file != null;
/*  70 */     if (file.exists()) {
/*     */       return;
/*     */     }
/*     */     
/*     */     try {
/*  75 */       if (file.getParentFile().mkdirs() && 
/*  76 */         file.createNewFile() && 
/*  77 */         resource != null && !resource.isEmpty()) {
/*  78 */         copyResource(this.plugin.getResource(resource), file);
/*     */       
/*     */       }
/*     */     }
/*  82 */     catch (IOException e) {
/*  83 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void prepareFile(String filePath) {
/*  89 */     prepareFile(filePath, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeader(File file, String[] header) {
/*  94 */     if (!file.exists()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 100 */       StringBuilder config = new StringBuilder();
/* 101 */       BufferedReader reader = new BufferedReader(new FileReader(file));
/*     */       String currentLine;
/* 103 */       while ((currentLine = reader.readLine()) != null) {
/* 104 */         config.append(currentLine).append("\n");
/*     */       }
/*     */       
/* 107 */       reader.close();
/* 108 */       config.append("# +----------------------------------------------------+ #\n");
/*     */       
/* 110 */       for (String line : header) {
/*     */         
/* 112 */         if (line.length() <= 50) {
/*     */ 
/*     */           
/* 115 */           int lenght = (50 - line.length()) / 2;
/* 116 */           StringBuilder finalLine = new StringBuilder(line);
/*     */           
/* 118 */           for (int i = 0; i < lenght; i++) {
/* 119 */             finalLine.append(" ");
/* 120 */             finalLine.reverse();
/* 121 */             finalLine.append(" ");
/* 122 */             finalLine.reverse();
/*     */           } 
/*     */           
/* 125 */           if (line.length() % 2 != 0) {
/* 126 */             finalLine.append(" ");
/*     */           }
/*     */           
/* 129 */           config.append("# < ").append(finalLine.toString()).append(" > #\n");
/*     */         } 
/*     */       } 
/*     */       
/* 133 */       config.append("# +----------------------------------------------------+ #");
/*     */       
/* 135 */       BufferedWriter writer = new BufferedWriter(new FileWriter(file));
/* 136 */       writer.write(prepareConfigString(config.toString()));
/* 137 */       writer.flush();
/* 138 */       writer.close();
/* 139 */     } catch (IOException e) {
/* 140 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int getCommentsNum(File file) {
/* 146 */     if (!file.exists()) {
/* 147 */       return 0;
/*     */     }
/*     */     
/*     */     try {
/* 151 */       int comments = 0;
/*     */ 
/*     */       
/* 154 */       BufferedReader reader = new BufferedReader(new FileReader(file));
/*     */       String currentLine;
/* 156 */       while ((currentLine = reader.readLine()) != null) {
/*     */         
/* 158 */         if (currentLine.startsWith("#")) {
/* 159 */           comments++;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 164 */       reader.close();
/* 165 */       return comments;
/* 166 */     } catch (IOException e) {
/* 167 */       e.printStackTrace();
/* 168 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private String prepareConfigString(String configString) {
/* 174 */     int lastLine = 0;
/* 175 */     int headerLine = 0;
/*     */     
/* 177 */     String[] lines = configString.split("\n");
/* 178 */     StringBuilder config = new StringBuilder();
/*     */     
/* 180 */     for (String line : lines) {
/*     */       
/* 182 */       if (line.startsWith(getPluginName() + "_COMMENT")) {
/* 183 */         String comment = "#" + line.trim().substring(line.indexOf(":") + 1);
/*     */         
/* 185 */         if (comment.startsWith("# +-")) {
/* 186 */           if (headerLine == 0) {
/* 187 */             config.append(comment).append("\n");
/*     */             
/* 189 */             lastLine = 0;
/* 190 */             headerLine = 1;
/*     */           } else {
/* 192 */             config.append(comment).append("\n\n");
/*     */             
/* 194 */             lastLine = 0;
/* 195 */             headerLine = 0;
/*     */           } 
/*     */         } else {
/*     */           String normalComment;
/*     */ 
/*     */           
/* 201 */           if (comment.startsWith("# ' ")) {
/* 202 */             normalComment = comment.substring(0, comment.length() - 1).replaceFirst("# ' ", "# ");
/*     */           } else {
/* 204 */             normalComment = comment;
/*     */           } 
/*     */           
/* 207 */           if (lastLine == 0) {
/* 208 */             config.append(normalComment).append("\n");
/*     */           } else {
/* 210 */             config.append("\n").append(normalComment).append("\n");
/*     */           } 
/*     */           
/* 213 */           lastLine = 0;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 218 */         config.append(line).append("\n");
/* 219 */         lastLine = 1;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 224 */     return config.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveConfig(String configString, File file) {
/* 229 */     String configuration = prepareConfigString(configString);
/*     */     
/*     */     try {
/* 232 */       BufferedWriter writer = new BufferedWriter(new FileWriter(file));
/* 233 */       writer.write(configuration);
/* 234 */       writer.flush();
/* 235 */       writer.close();
/* 236 */     } catch (IOException e) {
/* 237 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPluginName() {
/* 243 */     return this.plugin.getDescription().getName();
/*     */   }
/*     */ 
/*     */   
/*     */   private void copyResource(InputStream resource, File file) {
/*     */     try {
/* 249 */       OutputStream out = new FileOutputStream(file);
/*     */ 
/*     */       
/* 252 */       byte[] buf = new byte[1024];
/*     */       int lenght;
/* 254 */       while ((lenght = resource.read(buf)) > 0) {
/* 255 */         out.write(buf, 0, lenght);
/*     */       }
/*     */       
/* 258 */       out.close();
/* 259 */       resource.close();
/* 260 */     } catch (Exception e) {
/* 261 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class SimpleConfig
/*     */   {
/*     */     private int comments;
/*     */     private ConfigManager manager;
/*     */     private File file;
/*     */     private FileConfiguration config;
/*     */     
/*     */     public SimpleConfig(File configFile, int comments, JavaPlugin plugin) {
/* 273 */       this.comments = comments;
/* 274 */       this.manager = new ConfigManager(plugin);
/*     */       
/* 276 */       this.file = configFile;
/* 277 */       this.config = (FileConfiguration)YamlConfiguration.loadConfiguration(configFile);
/*     */     }
/*     */     
/*     */     public Object get(String path) {
/* 281 */       return this.config.get(path);
/*     */     }
/*     */     
/*     */     public Object get(String path, Object def) {
/* 285 */       return this.config.get(path, def);
/*     */     }
/*     */     
/*     */     public String getString(String path) {
/* 289 */       return this.config.getString(path);
/*     */     }
/*     */     
/*     */     public String getString(String path, String def) {
/* 293 */       return this.config.getString(path, def);
/*     */     }
/*     */     
/*     */     public int getInt(String path) {
/* 297 */       return this.config.getInt(path);
/*     */     }
/*     */     
/*     */     public int getInt(String path, int def) {
/* 301 */       return this.config.getInt(path, def);
/*     */     }
/*     */     
/*     */     public boolean getBoolean(String path) {
/* 305 */       return this.config.getBoolean(path);
/*     */     }
/*     */     
/*     */     public boolean getBoolean(String path, boolean def) {
/* 309 */       return this.config.getBoolean(path, def);
/*     */     }
/*     */     
/*     */     public void createSection(String path) {
/* 313 */       this.config.createSection(path);
/*     */     }
/*     */     
/*     */     public ConfigurationSection getConfigurationSection(String path) {
/* 317 */       return this.config.getConfigurationSection(path);
/*     */     }
/*     */     
/*     */     public double getDouble(String path) {
/* 321 */       return this.config.getDouble(path);
/*     */     }
/*     */     
/*     */     public double getDouble(String path, double def) {
/* 325 */       return this.config.getDouble(path, def);
/*     */     }
/*     */     
/*     */     public List<?> getList(String path) {
/* 329 */       return this.config.getList(path);
/*     */     }
/*     */     
/*     */     public List<?> getList(String path, List<?> def) {
/* 333 */       return this.config.getList(path, def);
/*     */     }
/*     */     
/*     */     public List<String> getStringList(String path) {
/* 337 */       return this.config.getStringList(path);
/*     */     }
/*     */     
/*     */     public boolean contains(String path) {
/* 341 */       return this.config.contains(path);
/*     */     }
/*     */     
/*     */     public void removeKey(String path) {
/* 345 */       this.config.set(path, null);
/*     */     }
/*     */     
/*     */     public void set(String path, Object value) {
/* 349 */       this.config.set(path, value);
/*     */     }
/*     */     
/*     */     public void set(String path, Object value, String comment) {
/* 353 */       if (!this.config.contains(path)) {
/* 354 */         this.config.set(this.manager.getPluginName() + "_COMMENT_" + this.comments, " " + comment);
/* 355 */         this.comments++;
/*     */       } 
/*     */       
/* 358 */       this.config.set(path, value);
/*     */     }
/*     */ 
/*     */     
/*     */     public void set(String path, Object value, String[] comment) {
/* 363 */       for (String comm : comment) {
/*     */         
/* 365 */         if (!this.config.contains(path)) {
/* 366 */           this.config.set(this.manager.getPluginName() + "_COMMENT_" + this.comments, " " + comm);
/* 367 */           this.comments++;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 372 */       this.config.set(path, value);
/*     */     }
/*     */ 
/*     */     
/*     */     public void setHeader(String[] header) {
/* 377 */       this.manager.setHeader(this.file, header);
/* 378 */       this.comments = header.length + 2;
/* 379 */       reloadConfig();
/*     */     }
/*     */     
/*     */     public void reloadConfig() {
/* 383 */       this.config = (FileConfiguration)YamlConfiguration.loadConfiguration(this.file);
/*     */     }
/*     */     
/*     */     public void saveConfig() {
/* 387 */       String config = this.config.saveToString();
/* 388 */       this.manager.saveConfig(config, this.file);
/*     */     }
/*     */ 
/*     */     
/*     */     public Set<String> getKeys() {
/* 393 */       return this.config.getKeys(false);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\cheen\Downloads\BetterMobAI-2.11 alpha.jar!\com\LucasRomier\BetterMobAI\API\ConfigManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
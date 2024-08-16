/*     */ package com.LucasRomier.BetterMobAI.API;
/*     */ 
/*     */ import com.LucasRomier.BetterMobAI.MobAI;
/*     */ import java.util.ArrayList;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ 
/*     */ public class Settings {
/*  11 */   public ConfigManager.SimpleConfig configuration = (new ConfigManager((JavaPlugin)MobAI.getInstance())).getNewConfig("Settings.yml");
/*     */   
/*     */   public Settings() {
/*  14 */     if (!this.configuration.contains("VersionTracking")) {
/*  15 */       this.configuration.set("VersionTracking", Boolean.TRUE, "Set this to true/false to enable/disable version tracking");
/*     */     }
/*     */     
/*  18 */     if (!this.configuration.contains("Worlds")) {
/*  19 */       ArrayList<String> list = new ArrayList<>();
/*  20 */       for (World world : Bukkit.getWorlds()) {
/*  21 */         list.add(world.getName());
/*     */       }
/*  23 */       this.configuration.set("Worlds", list, new String[] { "Add all the worlds affected by Better MobAI here", "Remove worlds from here to disable the Better MobAI on these" });
/*     */     } 
/*     */ 
/*     */     
/*  27 */     if (!this.configuration.contains("BetterMobs")) {
/*  28 */       ArrayList<String> list = new ArrayList<>();
/*  29 */       list.add(EntityType.BLAZE.toString());
/*  30 */       list.add(EntityType.CAVE_SPIDER.toString());
/*  31 */       list.add(EntityType.CREEPER.toString());
/*  32 */       list.add(EntityType.ENDERMAN.toString());
/*  33 */       list.add(EntityType.GHAST.toString());
/*  34 */       list.add(EntityType.GUARDIAN.toString());
/*  35 */       list.add(EntityType.PIG_ZOMBIE.toString());
/*  36 */       list.add(EntityType.SKELETON.toString());
/*  37 */       list.add(EntityType.SPIDER.toString());
/*  38 */       list.add(EntityType.WITCH.toString());
/*  39 */       list.add(EntityType.ZOMBIE.toString());
/*  40 */       list.add(EntityType.GIANT.toString());
/*  41 */       this.configuration.set("BetterMobs", list, new String[] { "Add all the mobs affected by Better MobAI here", "Remove mobs from here to disable the Better MobAI on these" });
/*     */     } 
/*     */ 
/*     */     
/*  45 */     if (!this.configuration.contains("CaveSpider")) {
/*  46 */       this.configuration.set("CaveSpider.Health", Integer.valueOf(12), "Edit the cave spider parameters here");
/*  47 */       this.configuration.set("CaveSpider.TrackingSpeed", Integer.valueOf(1));
/*     */       
/*  49 */       this.configuration.set("CaveSpider.NormalAttack.Speed", Double.valueOf(1.3D));
/*  50 */       this.configuration.set("CaveSpider.NormalAttack.Damage", Integer.valueOf(10));
/*  51 */       this.configuration.set("CaveSpider.NormalAttack.NextAttackDelay", Integer.valueOf(5));
/*     */       
/*  53 */       this.configuration.set("CaveSpider.PoisonAttack.Disable", Boolean.FALSE);
/*  54 */       this.configuration.set("CaveSpider.PoisonAttack.Speed", Double.valueOf(1.3D));
/*  55 */       this.configuration.set("CaveSpider.PoisonAttack.Damage", Integer.valueOf(8));
/*  56 */       this.configuration.set("CaveSpider.PoisonAttack.PoisoningTime", Integer.valueOf(7));
/*  57 */       this.configuration.set("CaveSpider.PoisonAttack.NextAttackDelay", Integer.valueOf(5));
/*     */       
/*  59 */       this.configuration.set("CaveSpider.SkyAttack.Disable", Boolean.FALSE);
/*  60 */       this.configuration.set("CaveSpider.SkyAttack.Speed", Double.valueOf(1.3D));
/*  61 */       this.configuration.set("CaveSpider.SkyAttack.Damage", Integer.valueOf(13));
/*  62 */       this.configuration.set("CaveSpider.SkyAttack.NextAttackDelay", Integer.valueOf(5));
/*     */       
/*  64 */       this.configuration.set("CaveSpider.WebAttack.Disable", Boolean.FALSE);
/*  65 */       this.configuration.set("CaveSpider.WebAttack.Speed", Double.valueOf(1.3D));
/*  66 */       this.configuration.set("CaveSpider.WebAttack.NextAttackDelay", Integer.valueOf(5));
/*     */     } 
/*  68 */     if (!this.configuration.contains("Spider")) {
/*  69 */       this.configuration.set("Spider.Health", Integer.valueOf(16), "Edit the spider parameters here");
/*  70 */       this.configuration.set("Spider.TrackingSpeed", Integer.valueOf(1));
/*     */       
/*  72 */       this.configuration.set("Spider.NormalAttack.Speed", Double.valueOf(1.3D));
/*  73 */       this.configuration.set("Spider.NormalAttack.Damage", Integer.valueOf(10));
/*  74 */       this.configuration.set("Spider.NormalAttack.NextAttackDelay", Integer.valueOf(5));
/*     */       
/*  76 */       this.configuration.set("Spider.PoisonAttack.Disable", Boolean.FALSE);
/*  77 */       this.configuration.set("Spider.PoisonAttack.Speed", Double.valueOf(1.3D));
/*  78 */       this.configuration.set("Spider.PoisonAttack.Damage", Integer.valueOf(8));
/*  79 */       this.configuration.set("Spider.PoisonAttack.PoisoningTime", Integer.valueOf(7));
/*  80 */       this.configuration.set("Spider.PoisonAttack.NextAttackDelay", Integer.valueOf(5));
/*     */       
/*  82 */       this.configuration.set("Spider.SkyAttack.Disable", Boolean.FALSE);
/*  83 */       this.configuration.set("Spider.SkyAttack.Speed", Double.valueOf(1.3D));
/*  84 */       this.configuration.set("Spider.SkyAttack.Damage", Integer.valueOf(13));
/*  85 */       this.configuration.set("Spider.SkyAttack.NextAttackDelay", Integer.valueOf(5));
/*     */       
/*  87 */       this.configuration.set("Spider.WebAttack.Disable", Boolean.FALSE);
/*  88 */       this.configuration.set("Spider.WebAttack.Speed", Double.valueOf(1.3D));
/*  89 */       this.configuration.set("Spider.WebAttack.NextAttackDelay", Integer.valueOf(5));
/*     */     } 
/*  91 */     if (!this.configuration.contains("Creeper")) {
/*  92 */       this.configuration.set("Creeper.Health", Integer.valueOf(20), "Edit the creeper parameters here");
/*  93 */       this.configuration.set("Creeper.TrackingSpeed", Integer.valueOf(1));
/*     */       
/*  95 */       this.configuration.set("Creeper.NormalAttack.Speed", Double.valueOf(1.3D));
/*     */       
/*  97 */       this.configuration.set("Creeper.ImplosionExplosionAttack.Disable", Boolean.FALSE);
/*  98 */       this.configuration.set("Creeper.ImplosionExplosionAttack.Speed", Double.valueOf(1.3D));
/*  99 */       this.configuration.set("Creeper.ImplosionExplosionAttack.ImplosionRadius", Integer.valueOf(10));
/*     */       
/* 101 */       this.configuration.set("Creeper.ChargedCreeperAttack.Disable", Boolean.FALSE);
/* 102 */       this.configuration.set("Creeper.ChargedCreeperAttack.Speed", Double.valueOf(1.3D));
/*     */       
/* 104 */       this.configuration.set("Creeper.ChargedSuperCreeperAttack.Disable", Boolean.FALSE);
/* 105 */       this.configuration.set("Creeper.ChargedSuperCreeperAttack.Speed", Double.valueOf(1.3D));
/*     */     } 
/* 107 */     if (!this.configuration.contains("Enderman")) {
/* 108 */       this.configuration.set("Enderman.Health", Integer.valueOf(40), "Edit the enderman parameters here");
/* 109 */       this.configuration.set("Enderman.TrackingSpeed", Integer.valueOf(1));
/*     */       
/* 111 */       this.configuration.set("Enderman.NormalAttack.Speed", Integer.valueOf(1));
/* 112 */       this.configuration.set("Enderman.NormalAttack.Damage", Integer.valueOf(7));
/* 113 */       this.configuration.set("Enderman.NormalAttack.ConfusionTime", Integer.valueOf(4));
/* 114 */       this.configuration.set("Enderman.NormalAttack.NextAttackDelay", Integer.valueOf(5));
/*     */       
/* 116 */       this.configuration.set("Enderman.JumperAttack.Disable", Boolean.FALSE);
/* 117 */       this.configuration.set("Enderman.JumperAttack.Speed", Integer.valueOf(1));
/* 118 */       this.configuration.set("Enderman.JumperAttack.Damage", Integer.valueOf(10));
/* 119 */       this.configuration.set("Enderman.JumperAttack.ConfusionTime", Integer.valueOf(6));
/* 120 */       this.configuration.set("Enderman.JumperAttack.NextAttackDelay", Integer.valueOf(5));
/*     */     } 
/* 122 */     if (!this.configuration.contains("Skeleton")) {
/* 123 */       this.configuration.set("Skeleton.Health", Integer.valueOf(20), "Edit the skeleton parameters here");
/* 124 */       this.configuration.set("Skeleton.TrackingSpeed", Integer.valueOf(1));
/*     */       
/* 126 */       this.configuration.set("Skeleton.NormalAttack.Speed", Double.valueOf(1.3D));
/* 127 */       this.configuration.set("Skeleton.NormalAttack.NextAttackDelay", Integer.valueOf(3));
/*     */       
/* 129 */       this.configuration.set("Skeleton.BurningArrowAttack.Disable", Boolean.FALSE);
/* 130 */       this.configuration.set("Skeleton.BurningArrowAttack.Speed", Double.valueOf(1.3D));
/* 131 */       this.configuration.set("Skeleton.BurningArrowAttack.NextAttackDelay", Integer.valueOf(3));
/*     */       
/* 133 */       this.configuration.set("Skeleton.PoisonArrowAttack.Disable", Boolean.FALSE);
/* 134 */       this.configuration.set("Skeleton.PoisonArrowAttack.Speed", Double.valueOf(1.3D));
/* 135 */       this.configuration.set("Skeleton.PoisonArrowAttack.NextAttackDelay", Integer.valueOf(3));
/*     */       
/* 137 */       this.configuration.set("Skeleton.NailingArrowAttack.Disable", Boolean.FALSE);
/* 138 */       this.configuration.set("Skeleton.NailingArrowAttack.Speed", Double.valueOf(1.3D));
/* 139 */       this.configuration.set("Skeleton.NailingArrowAttack.NextAttackDelay", Integer.valueOf(3));
/*     */       
/* 141 */       this.configuration.set("Skeleton.GetARide.Disable", Boolean.FALSE);
/* 142 */       this.configuration.set("Skeleton.GetARide.Radius", Integer.valueOf(10));
/*     */       
/* 144 */       this.configuration.set("Skeleton.ArrowRain.Disable", Boolean.FALSE);
/* 145 */       this.configuration.set("Skeleton.ArrowRain.Speed", Double.valueOf(1.3D));
/* 146 */       this.configuration.set("Skeleton.ArrowRain.Arrows", Integer.valueOf(6));
/* 147 */       this.configuration.set("Skeleton.ArrowRain.NextAttackDelay", Integer.valueOf(3));
/*     */     } 
/* 149 */     if (!this.configuration.contains("Zombie")) {
/* 150 */       this.configuration.set("Zombie.Health", Integer.valueOf(20), "Edit the zombie parameters here");
/* 151 */       this.configuration.set("Zombie.TrackingSpeed", Integer.valueOf(1));
/*     */       
/* 153 */       this.configuration.set("Zombie.NormalAttack.Speed", Integer.valueOf(1));
/* 154 */       this.configuration.set("Zombie.NormalAttack.Damage", Integer.valueOf(6));
/* 155 */       this.configuration.set("Zombie.NormalAttack.NextAttackDelay", Integer.valueOf(3));
/*     */       
/* 157 */       this.configuration.set("Zombie.BloodRushAttack.Disable", Boolean.FALSE);
/* 158 */       this.configuration.set("Zombie.BloodRushAttack.Speed", Double.valueOf(1.5D));
/* 159 */       this.configuration.set("Zombie.BloodRushAttack.Damage", Integer.valueOf(8));
/* 160 */       this.configuration.set("Zombie.BloodRushAttack.NextAttackDelay", Integer.valueOf(3));
/*     */       
/* 162 */       this.configuration.set("Zombie.MinionsAttack.Disable", Boolean.FALSE);
/* 163 */       this.configuration.set("Zombie.MinionsAttack.Speed", Integer.valueOf(1));
/*     */       
/* 165 */       this.configuration.set("Zombie.VampireAttack.Disable", Boolean.FALSE);
/* 166 */       this.configuration.set("Zombie.VampireAttack.Speed", Integer.valueOf(1));
/* 167 */       this.configuration.set("Zombie.VampireAttack.Damage", Integer.valueOf(8));
/* 168 */       this.configuration.set("Zombie.VampireAttack.NextAttackDelay", Integer.valueOf(5));
/*     */     } 
/* 170 */     if (!this.configuration.contains("Witch")) {
/* 171 */       this.configuration.set("Witch.Health", Integer.valueOf(26), "Edit the witch parameters here");
/* 172 */       this.configuration.set("Witch.TrackingSpeed", Integer.valueOf(1));
/*     */       
/* 174 */       this.configuration.set("Witch.NormalAttack.Speed", Double.valueOf(1.3D));
/* 175 */       this.configuration.set("Witch.NormalAttack.NextAttackDelay", Integer.valueOf(5));
/*     */       
/* 177 */       this.configuration.set("Witch.BlackMagicAttack.Disable", Boolean.FALSE);
/* 178 */       this.configuration.set("Witch.BlackMagicAttack.Speed", Double.valueOf(1.3D));
/* 179 */       this.configuration.set("Witch.BlackMagicAttack.EffectLength", Integer.valueOf(7));
/* 180 */       this.configuration.set("Witch.BlackMagicAttack.NextAttackDelay", Integer.valueOf(5));
/*     */       
/* 182 */       this.configuration.set("Witch.FireAttack.Disable", Boolean.FALSE);
/* 183 */       this.configuration.set("Witch.FireAttack.Speed", Double.valueOf(1.3D));
/* 184 */       this.configuration.set("Witch.FireAttack.EffectLength", Integer.valueOf(7));
/* 185 */       this.configuration.set("Witch.FireAttack.NextAttackDelay", Integer.valueOf(5));
/*     */       
/* 187 */       this.configuration.set("Witch.FireCircleAttack.Disable", Boolean.FALSE);
/* 188 */       this.configuration.set("Witch.FireCircleAttack.Speed", Double.valueOf(1.3D));
/* 189 */       this.configuration.set("Witch.FireCircleAttack.Radius", Integer.valueOf(2));
/* 190 */       this.configuration.set("Witch.FireCircleAttack.NextAttackDelay", Integer.valueOf(5));
/*     */       
/* 192 */       this.configuration.set("Witch.LavaAttack.Disable", Boolean.FALSE);
/* 193 */       this.configuration.set("Witch.LavaAttack.Speed", Double.valueOf(1.3D));
/* 194 */       this.configuration.set("Witch.LavaAttack.NextAttackDelay", Integer.valueOf(5));
/*     */       
/* 196 */       this.configuration.set("Witch.PoisonAttack.Disable", Boolean.FALSE);
/* 197 */       this.configuration.set("Witch.PoisonAttack.Speed", Double.valueOf(1.3D));
/* 198 */       this.configuration.set("Witch.PoisonAttack.EffectLength", Integer.valueOf(7));
/* 199 */       this.configuration.set("Witch.PoisonAttack.NextAttackDelay", Integer.valueOf(5));
/*     */       
/* 201 */       this.configuration.set("Witch.SlownessAttack.Disable", Boolean.FALSE);
/* 202 */       this.configuration.set("Witch.SlownessAttack.Speed", Double.valueOf(1.3D));
/* 203 */       this.configuration.set("Witch.SlownessAttack.EffectLength", Integer.valueOf(5));
/* 204 */       this.configuration.set("Witch.SlownessAttack.NextAttackDelay", Integer.valueOf(5));
/*     */       
/* 206 */       this.configuration.set("Witch.EndermitesAttack.Disable", Boolean.FALSE);
/* 207 */       this.configuration.set("Witch.EndermitesAttack.Speed", Double.valueOf(1.3D));
/* 208 */       this.configuration.set("Witch.EndermitesAttack.NextAttackDelay", Integer.valueOf(5));
/*     */     } 
/* 210 */     if (!this.configuration.contains("Guardian")) {
/* 211 */       this.configuration.set("Guardian.Normal.Health", Integer.valueOf(30), "Edit the guardian parameters here");
/* 212 */       this.configuration.set("Guardian.Elder.Health", Integer.valueOf(80));
/* 213 */       this.configuration.set("Guardian.TrackingSpeed", Double.valueOf(0.5D));
/*     */       
/* 215 */       this.configuration.set("Guardian.NormalAttack.Speed", Double.valueOf(0.5D));
/* 216 */       this.configuration.set("Guardian.NormalAttack.NextAttackDelay", Integer.valueOf(5));
/*     */       
/* 218 */       this.configuration.set("Guardian.InstantAttack.Disable", Boolean.FALSE);
/* 219 */       this.configuration.set("Guardian.InstantAttack.Speed", Double.valueOf(0.5D));
/* 220 */       this.configuration.set("Guardian.InstantAttack.NextAttackDelay", Integer.valueOf(5));
/*     */       
/* 222 */       this.configuration.set("Guardian.NailingVibesAttack.Disable", Boolean.FALSE);
/* 223 */       this.configuration.set("Guardian.NailingVibesAttack.Speed", Double.valueOf(0.5D));
/* 224 */       this.configuration.set("Guardian.NailingVibesAttack.EffectLength", Integer.valueOf(4));
/* 225 */       this.configuration.set("Guardian.NailingVibesAttack.NextAttackDelay", Integer.valueOf(5));
/*     */       
/* 227 */       this.configuration.set("Guardian.SuffocationVibesAttack.Disable", Boolean.FALSE);
/* 228 */       this.configuration.set("Guardian.SuffocationVibesAttack.Speed", Double.valueOf(0.5D));
/* 229 */       this.configuration.set("Guardian.SuffocationVibesAttack.NextAttackDelay", Integer.valueOf(5));
/*     */     } 
/* 231 */     if (!this.configuration.contains("PigmanZombie")) {
/* 232 */       this.configuration.set("PigmanZombie.Health", Integer.valueOf(20), "Edit the pigman zombie parameters here");
/* 233 */       this.configuration.set("PigmanZombie.TrackingSpeed", Integer.valueOf(1));
/*     */       
/* 235 */       this.configuration.set("PigmanZombie.NormalAttack.Speed", Integer.valueOf(1));
/* 236 */       this.configuration.set("PigmanZombie.NormalAttack.Damage", Integer.valueOf(6));
/* 237 */       this.configuration.set("PigmanZombie.NormalAttack.NextAttackDelay", Integer.valueOf(3));
/*     */       
/* 239 */       this.configuration.set("PigmanZombie.LightningAttack.Disable", Boolean.FALSE);
/* 240 */       this.configuration.set("PigmanZombie.LightningAttack.Speed", Integer.valueOf(1));
/* 241 */       this.configuration.set("PigmanZombie.LightningAttack.Damage", Integer.valueOf(6));
/* 242 */       this.configuration.set("PigmanZombie.LightningAttack.NextAttackDelay", Integer.valueOf(3));
/*     */       
/* 244 */       this.configuration.set("PigmanZombie.SwordAttack.Disable", Boolean.FALSE);
/* 245 */       this.configuration.set("PigmanZombie.SwordAttack.Speed", Integer.valueOf(1));
/* 246 */       this.configuration.set("PigmanZombie.SwordAttack.Damage", Integer.valueOf(8));
/* 247 */       this.configuration.set("PigmanZombie.SwordAttack.NextAttackDelay", Integer.valueOf(3));
/*     */       
/* 249 */       this.configuration.set("PigmanZombie.ThrowSwordAttack.Disable", Boolean.FALSE);
/* 250 */       this.configuration.set("PigmanZombie.ThrowSwordAttack.Damage", Integer.valueOf(8));
/* 251 */       this.configuration.set("PigmanZombie.ThrowSwordAttack.NextAttackDelay", Integer.valueOf(5));
/*     */     } 
/* 253 */     if (!this.configuration.contains("WitherSkeleton")) {
/* 254 */       this.configuration.set("WitherSkeleton.Health", Integer.valueOf(20), "Edit the wither skeleton parameters here");
/* 255 */       this.configuration.set("WitherSkeleton.TrackingSpeed", Integer.valueOf(1));
/*     */       
/* 257 */       this.configuration.set("WitherSkeleton.NormalAttack.Speed", Integer.valueOf(1));
/* 258 */       this.configuration.set("WitherSkeleton.NormalAttack.Damage", Integer.valueOf(8));
/* 259 */       this.configuration.set("WitherSkeleton.NormalAttack.NextAttackDelay", Integer.valueOf(3));
/*     */       
/* 261 */       this.configuration.set("WitherSkeleton.WitherSkullAttack.Disable", Boolean.FALSE);
/* 262 */       this.configuration.set("WitherSkeleton.WitherSkullAttack.Speed", Integer.valueOf(1));
/* 263 */       this.configuration.set("WitherSkeleton.WitherSkullAttack.NextAttackDelay", Integer.valueOf(3));
/*     */       
/* 265 */       this.configuration.set("WitherSkeleton.SwordAttack.Disable", Boolean.FALSE);
/* 266 */       this.configuration.set("WitherSkeleton.SwordAttack.Speed", Integer.valueOf(1));
/* 267 */       this.configuration.set("WitherSkeleton.SwordAttack.Damage", Integer.valueOf(10));
/* 268 */       this.configuration.set("WitherSkeleton.SwordAttack.NextAttackDelay", Integer.valueOf(3));
/*     */       
/* 270 */       this.configuration.set("WitherSkeleton.ThrowSwordAttack.Disable", Boolean.FALSE);
/* 271 */       this.configuration.set("WitherSkeleton.ThrowSwordAttack.Damage", Integer.valueOf(10));
/* 272 */       this.configuration.set("WitherSkeleton.ThrowSwordAttack.NextAttackDelay", Integer.valueOf(5));
/*     */     } 
/* 274 */     if (!this.configuration.contains("Ghast")) {
/* 275 */       this.configuration.set("Ghast.Health", Integer.valueOf(10), "Edit the ghast parameters here");
/* 276 */       this.configuration.set("Ghast.TrackingSpeed", Double.valueOf(0.5D));
/*     */       
/* 278 */       this.configuration.set("Ghast.NormalAttack.Speed", Double.valueOf(0.5D));
/* 279 */       this.configuration.set("Ghast.NormalAttack.NextAttackDelay", Integer.valueOf(5));
/*     */       
/* 281 */       this.configuration.set("Ghast.HighSpeedAttack.Disable", Boolean.FALSE);
/* 282 */       this.configuration.set("Ghast.HighSpeedAttack.Speed", Double.valueOf(0.5D));
/* 283 */       this.configuration.set("Ghast.HighSpeedAttack.NextAttackDelay", Integer.valueOf(5));
/*     */       
/* 285 */       this.configuration.set("Ghast.MultiAttack.Disable", Boolean.FALSE);
/* 286 */       this.configuration.set("Ghast.MultiAttack.Speed", Double.valueOf(0.5D));
/* 287 */       this.configuration.set("Ghast.MultiAttack.NextAttackDelay", Integer.valueOf(5));
/*     */     } 
/* 289 */     if (!this.configuration.contains("Blaze")) {
/* 290 */       this.configuration.set("Blaze.Health", Integer.valueOf(10), "Edit the blaze parameters here");
/* 291 */       this.configuration.set("Blaze.TrackingSpeed", Integer.valueOf(1));
/*     */       
/* 293 */       this.configuration.set("Blaze.NormalAttack.Speed", Integer.valueOf(1));
/* 294 */       this.configuration.set("Blaze.NormalAttack.NextAttackDelay", Integer.valueOf(5));
/*     */       
/* 296 */       this.configuration.set("Blaze.HeatAttack.Disable", Boolean.FALSE);
/* 297 */       this.configuration.set("Blaze.HeatAttack.Speed", Integer.valueOf(1));
/* 298 */       this.configuration.set("Blaze.HeatAttack.EffectLength", Integer.valueOf(5));
/* 299 */       this.configuration.set("Blaze.HeatAttack.NextAttackDelay", Integer.valueOf(5));
/*     */       
/* 301 */       this.configuration.set("Blaze.SmokeAttack.Disable", Boolean.FALSE);
/* 302 */       this.configuration.set("Blaze.SmokeAttack.Speed", Integer.valueOf(1));
/* 303 */       this.configuration.set("Blaze.SmokeAttack.EffectLength", Integer.valueOf(5));
/* 304 */       this.configuration.set("Blaze.SmokeAttack.NextAttackDelay", Integer.valueOf(5));
/*     */     } 
/* 306 */     if (!this.configuration.contains("Giant")) {
/* 307 */       this.configuration.set("Giant.Health", Integer.valueOf(200), "Edit the giant parameters here");
/* 308 */       this.configuration.set("Giant.TrackingSpeed", Double.valueOf(0.5D));
/* 309 */       this.configuration.set("Giant.MiniZombieHealth", Integer.valueOf(20));
/*     */       
/* 311 */       this.configuration.set("Giant.ThrowMiniZombie.Disable", Boolean.FALSE);
/* 312 */       this.configuration.set("Giant.ThrowMiniZombie.Speed", Double.valueOf(0.5D));
/* 313 */       this.configuration.set("Giant.ThrowMiniZombie.NextAttackDelay", Integer.valueOf(5));
/*     */       
/* 315 */       this.configuration.set("Giant.ThrowBoulder.Disable", Boolean.FALSE);
/* 316 */       this.configuration.set("Giant.ThrowBoulder.Speed", Double.valueOf(0.5D));
/* 317 */       this.configuration.set("Giant.ThrowBoulder.Damage", Integer.valueOf(14));
/* 318 */       this.configuration.set("Giant.ThrowBoulder.DamageRadius", Integer.valueOf(6));
/* 319 */       this.configuration.set("Giant.ThrowBoulder.NextAttackDelay", Integer.valueOf(5));
/*     */       
/* 321 */       this.configuration.set("Giant.Earthquake.Disable", Boolean.FALSE);
/* 322 */       this.configuration.set("Giant.Earthquake.Speed", Double.valueOf(0.5D));
/* 323 */       this.configuration.set("Giant.Earthquake.Damage", Integer.valueOf(8));
/* 324 */       this.configuration.set("Giant.Earthquake.Radius", Integer.valueOf(10));
/* 325 */       this.configuration.set("Giant.Earthquake.NextAttackDelay", Integer.valueOf(8));
/*     */       
/* 327 */       this.configuration.set("Giant.LightningStriker.Disable", Boolean.FALSE);
/* 328 */       this.configuration.set("Giant.LightningStriker.Speed", Double.valueOf(0.5D));
/* 329 */       this.configuration.set("Giant.LightningStriker.DamageRadius", Integer.valueOf(10));
/* 330 */       this.configuration.set("Giant.LightningStriker.NextAttackDelay", Integer.valueOf(8));
/*     */     } 
/* 332 */     this.configuration.saveConfig();
/*     */   }
/*     */ }


/* Location:              C:\Users\cheen\Downloads\BetterMobAI-2.11 alpha.jar!\com\LucasRomier\BetterMobAI\API\Settings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package com.LucasRomier.BetterMobAI.Mobs.Entity.Overworld;
/*     */ import com.LucasRomier.BetterMobAI.MobAI;
/*     */ import com.LucasRomier.BetterMobAI.Mobs.Attacks.SkeletonAttack;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.entity.Arrow;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.projectiles.ProjectileSource;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ public class BetterSkeleton extends BetterMob {
/*     */   public static Map<String, String> lastArrow;
/*     */   private int scheduler;
/*     */   
/*     */   public BetterSkeleton(Skeleton skeleton) {
/*  19 */     super((LivingEntity)skeleton);
/*     */   }
/*     */   
/*     */   public void normalAttack(final Player player) {
/*  23 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/*  24 */       setBusy(true);
/*  25 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/*  26 */             boolean b = BetterSkeleton.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Skeleton.NormalAttack.Speed"), 10.0D);
/*     */             
/*     */             public void run() {
/*  29 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/*  30 */                 !player.isOnline() || BetterSkeleton.this.entity.isDead()) {
/*  31 */                 BetterSkeleton.this.setBusy(false);
/*  32 */                 Bukkit.getScheduler().cancelTask(BetterSkeleton.this.scheduler);
/*     */                 return;
/*     */               } 
/*  35 */               if (!this.b) {
/*  36 */                 this.b = BetterSkeleton.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Skeleton.NormalAttack.Speed"), 10.0D);
/*     */               } else {
/*     */                 
/*  39 */                 BetterSkeleton.this.track(BetterSkeleton.this.entity.getLocation(), 0.0F, 0.0D);
/*  40 */                 Arrow arrow = (Arrow)BetterSkeleton.this.entity.getWorld().spawnEntity(BetterSkeleton.this.entity.getEyeLocation().clone().add(0.0D, 1.0D, 0.0D), EntityType.ARROW);
/*  41 */                 Vector vector = player.getLocation().subtract(BetterSkeleton.this.entity.getEyeLocation().clone().add(0.0D, 1.0D, 0.0D)).toVector();
/*  42 */                 arrow.setShooter((ProjectileSource)BetterSkeleton.this.entity);
/*  43 */                 BetterSkeleton.lastArrow.put(player.getUniqueId().toString(), "None");
/*  44 */                 arrow.setVelocity(vector.multiply(1));
/*  45 */                 BetterSkeleton.this.randomAttack(player, MobAI.settings.configuration.getInt("Skeleton.NormalAttack.NextAttackDelay"));
/*  46 */                 Bukkit.getScheduler().cancelTask(BetterSkeleton.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void burningArrowAttack(final Player player) {
/*  54 */     if (MobAI.settings.configuration.getBoolean("Skeleton.BurningArrowAttack.Disable")) {
/*  55 */       normalAttack(player);
/*     */       return;
/*     */     } 
/*  58 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/*  59 */       setBusy(true);
/*  60 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/*  61 */             boolean b = BetterSkeleton.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Skeleton.BurningArrowAttack.Speed"), 10.0D);
/*     */             
/*     */             public void run() {
/*  64 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/*  65 */                 !player.isOnline() || BetterSkeleton.this.entity.isDead()) {
/*  66 */                 BetterSkeleton.this.setBusy(false);
/*  67 */                 Bukkit.getScheduler().cancelTask(BetterSkeleton.this.scheduler);
/*     */                 return;
/*     */               } 
/*  70 */               if (!this.b) {
/*  71 */                 this.b = BetterSkeleton.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Skeleton.BurningArrowAttack.Speed"), 10.0D);
/*     */               } else {
/*     */                 
/*  74 */                 BetterSkeleton.this.track(BetterSkeleton.this.entity.getLocation(), 0.0F, 0.0D);
/*  75 */                 Arrow arrow = (Arrow)BetterSkeleton.this.entity.getWorld().spawnEntity(BetterSkeleton.this.entity.getEyeLocation().clone().add(0.0D, 1.0D, 0.0D), EntityType.ARROW);
/*  76 */                 Vector vector = player.getLocation().subtract(BetterSkeleton.this.entity.getEyeLocation().clone().add(0.0D, 1.0D, 0.0D)).toVector();
/*  77 */                 arrow.setFireTicks(200);
/*  78 */                 arrow.setShooter((ProjectileSource)BetterSkeleton.this.entity);
/*  79 */                 BetterSkeleton.lastArrow.put(player.getUniqueId().toString(), "Burning");
/*  80 */                 arrow.setVelocity(vector.multiply(1));
/*  81 */                 BetterSkeleton.this.randomAttack(player, MobAI.settings.configuration.getInt("Skeleton.BurningArrowAttack.NextAttackDelay"));
/*  82 */                 Bukkit.getScheduler().cancelTask(BetterSkeleton.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void poisonedArrowAttack(final Player player) {
/*  90 */     if (MobAI.settings.configuration.getBoolean("Skeleton.PoisonArrowAttack.Disable")) {
/*  91 */       normalAttack(player);
/*     */       return;
/*     */     } 
/*  94 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/*  95 */       setBusy(true);
/*  96 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/*  97 */             boolean b = BetterSkeleton.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Skeleton.PoisonArrowAttack.Speed"), 10.0D);
/*     */             
/*     */             public void run() {
/* 100 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/* 101 */                 !player.isOnline() || BetterSkeleton.this.entity.isDead()) {
/* 102 */                 BetterSkeleton.this.setBusy(false);
/* 103 */                 Bukkit.getScheduler().cancelTask(BetterSkeleton.this.scheduler);
/*     */                 return;
/*     */               } 
/* 106 */               if (!this.b) {
/* 107 */                 this.b = BetterSkeleton.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Skeleton.PoisonArrowAttack.Speed"), 10.0D);
/*     */               } else {
/*     */                 
/* 110 */                 BetterSkeleton.this.track(BetterSkeleton.this.entity.getLocation(), 0.0F, 0.0D);
/* 111 */                 Arrow arrow = (Arrow)BetterSkeleton.this.entity.getWorld().spawnEntity(BetterSkeleton.this.entity.getEyeLocation().clone().add(0.0D, 1.0D, 0.0D), EntityType.ARROW);
/* 112 */                 Vector vector = player.getLocation().subtract(BetterSkeleton.this.entity.getEyeLocation().clone().add(0.0D, 1.0D, 0.0D)).toVector();
/* 113 */                 BetterSkeleton.lastArrow.put(player.getUniqueId().toString(), "Poisoned");
/* 114 */                 arrow.setShooter((ProjectileSource)BetterSkeleton.this.entity);
/* 115 */                 arrow.setVelocity(vector.multiply(1));
/* 116 */                 BetterSkeleton.this.randomAttack(player, MobAI.settings.configuration.getInt("Skeleton.PoisonArrowAttack.NextAttackDelay"));
/* 117 */                 Bukkit.getScheduler().cancelTask(BetterSkeleton.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void nailingArrowAttack(final Player player) {
/* 125 */     if (MobAI.settings.configuration.getBoolean("Skeleton.NailingArrowAttack.Disable")) {
/* 126 */       normalAttack(player);
/*     */       return;
/*     */     } 
/* 129 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/* 130 */       setBusy(true);
/* 131 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 132 */             boolean b = BetterSkeleton.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Skeleton.NailingArrowAttack.Speed"), 10.0D);
/*     */             
/*     */             public void run() {
/* 135 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/* 136 */                 !player.isOnline() || BetterSkeleton.this.entity.isDead()) {
/* 137 */                 BetterSkeleton.this.setBusy(false);
/* 138 */                 Bukkit.getScheduler().cancelTask(BetterSkeleton.this.scheduler);
/*     */                 return;
/*     */               } 
/* 141 */               if (!this.b) {
/* 142 */                 this.b = BetterSkeleton.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Skeleton.NailingArrowAttack.Speed"), 10.0D);
/*     */               } else {
/*     */                 
/* 145 */                 BetterSkeleton.this.track(BetterSkeleton.this.entity.getLocation(), 0.0F, 0.0D);
/* 146 */                 Arrow arrow = (Arrow)BetterSkeleton.this.entity.getWorld().spawnEntity(BetterSkeleton.this.entity.getEyeLocation().clone().add(0.0D, 1.0D, 0.0D), EntityType.ARROW);
/* 147 */                 Vector vector = player.getLocation().subtract(BetterSkeleton.this.entity.getEyeLocation().clone().add(0.0D, 1.0D, 0.0D)).toVector();
/* 148 */                 BetterSkeleton.lastArrow.put(player.getUniqueId().toString(), "Nailing");
/* 149 */                 arrow.setShooter((ProjectileSource)BetterSkeleton.this.entity);
/* 150 */                 arrow.setVelocity(vector.multiply(1));
/* 151 */                 BetterSkeleton.this.randomAttack(player, MobAI.settings.configuration.getInt("Skeleton.NailingArrowAttack.NextAttackDelay"));
/* 152 */                 Bukkit.getScheduler().cancelTask(BetterSkeleton.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void getARide() {
/* 161 */     if (MobAI.settings.configuration.getBoolean("Skeleton.GetARide.Disable")) {
/*     */       return;
/*     */     }
/* 164 */     int j = MobAI.settings.configuration.getInt("Skeleton.GetARide.Radius");
/* 165 */     for (Entity e : this.entity.getNearbyEntities(j, j, j)) {
/* 166 */       if (e.getType().equals(EntityType.SPIDER)) {
/* 167 */         if (e instanceof LivingEntity) {
/* 168 */           LivingEntity livingEntity = (LivingEntity)e;
/* 169 */           if (livingEntity.getPassenger() == null) {
/* 170 */             livingEntity.addPassenger((Entity)this.entity);
/* 171 */             BetterSpider spider = new BetterSpider((Spider)e);
/* 172 */             spider.setBusy(true);
/*     */             break;
/*     */           } 
/*     */         } 
/*     */         continue;
/*     */       } 
/* 178 */       if (e.getType().equals(EntityType.CAVE_SPIDER) && e instanceof LivingEntity) {
/*     */         
/* 180 */         LivingEntity livingEntity = (LivingEntity)e;
/* 181 */         if (livingEntity.getPassenger() == null) {
/* 182 */           livingEntity.addPassenger((Entity)this.entity);
/* 183 */           BetterCaveSpider spider = new BetterCaveSpider((CaveSpider)e);
/* 184 */           spider.setBusy(true);
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void arrowRainAttack(final Player player) {
/* 193 */     if (MobAI.settings.configuration.getBoolean("Skeleton.ArrowRain.Disable")) {
/* 194 */       normalAttack(player);
/*     */       return;
/*     */     } 
/* 197 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/* 198 */       setBusy(true);
/* 199 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 200 */             boolean b = BetterSkeleton.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Skeleton.ArrowRain.Speed"), 10.0D);
/*     */             
/*     */             public void run() {
/* 203 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/* 204 */                 !player.isOnline() || BetterSkeleton.this.entity.isDead()) {
/* 205 */                 BetterSkeleton.this.setBusy(false);
/* 206 */                 Bukkit.getScheduler().cancelTask(BetterSkeleton.this.scheduler);
/*     */                 return;
/*     */               } 
/* 209 */               if (!this.b) {
/* 210 */                 this.b = BetterSkeleton.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Skeleton.ArrowRain.Speed"), 10.0D);
/*     */               } else {
/*     */                 
/* 213 */                 BetterSkeleton.this.track(BetterSkeleton.this.entity.getLocation(), 0.0F, 0.0D);
/* 214 */                 BetterSkeleton.lastArrow.put(player.getUniqueId().toString(), "None");
/* 215 */                 for (int i = 0; i < MobAI.settings.configuration.getInt("Skeleton.ArrowRain.Arrows"); i++) {
/* 216 */                   Vector vector; Arrow arrow = (Arrow)BetterSkeleton.this.entity.getWorld().spawnEntity(BetterSkeleton.this.entity.getEyeLocation().clone().add(0.0D, 1.0D, 0.0D), EntityType.ARROW);
/*     */                   
/* 218 */                   switch (i) {
/*     */                     case 0:
/* 220 */                       vector = player.getLocation().subtract(BetterSkeleton.this.entity.getEyeLocation().clone().add(0.3D, 1.0D, 0.0D)).toVector();
/*     */                       break;
/*     */                     case 1:
/* 223 */                       vector = player.getLocation().subtract(BetterSkeleton.this.entity.getEyeLocation().clone().add(0.3D, 1.0D, 0.3D)).toVector();
/*     */                       break;
/*     */                     case 2:
/* 226 */                       vector = player.getLocation().subtract(BetterSkeleton.this.entity.getEyeLocation().clone().add(0.0D, 1.0D, 0.0D)).toVector();
/*     */                       break;
/*     */                     case 3:
/* 229 */                       vector = player.getLocation().subtract(BetterSkeleton.this.entity.getEyeLocation().clone().add(-0.3D, 1.0D, 0.0D)).toVector();
/*     */                       break;
/*     */                     case 4:
/* 232 */                       vector = player.getLocation().subtract(BetterSkeleton.this.entity.getEyeLocation().clone().add(-0.3D, 1.0D, -0.3D)).toVector();
/*     */                       break;
/*     */                     case 5:
/* 235 */                       vector = player.getLocation().subtract(BetterSkeleton.this.entity.getEyeLocation().clone().add(-0.3D, 1.0D, 0.3D)).toVector();
/*     */                       break;
/*     */                     case 6:
/* 238 */                       vector = player.getLocation().subtract(BetterSkeleton.this.entity.getEyeLocation().clone().add(0.3D, 1.0D, -0.3D)).toVector();
/*     */                       break;
/*     */                     default:
/* 241 */                       vector = player.getLocation().subtract(BetterSkeleton.this.entity.getEyeLocation().clone().add(0.0D, 1.0D, 0.0D)).toVector();
/*     */                       break;
/*     */                   } 
/* 244 */                   arrow.setShooter((ProjectileSource)BetterSkeleton.this.entity);
/* 245 */                   arrow.setVelocity(vector.multiply(1));
/*     */                 } 
/* 247 */                 BetterSkeleton.this.randomAttack(player, MobAI.settings.configuration.getInt("Skeleton.ArrowRain.NextAttackDelay"));
/* 248 */                 Bukkit.getScheduler().cancelTask(BetterSkeleton.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void trackAndKill(final Player player) {
/* 257 */     if (!isBusy()) {
/* 258 */       setBusy(true);
/* 259 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 260 */             boolean b = BetterSkeleton.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Skeleton.TrackingSpeed"), 15.0D);
/*     */             
/*     */             public void run() {
/* 263 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/* 264 */                 !player.isOnline() || BetterSkeleton.this.entity.isDead()) {
/* 265 */                 BetterSkeleton.this.setBusy(false);
/* 266 */                 Bukkit.getScheduler().cancelTask(BetterSkeleton.this.scheduler);
/*     */                 return;
/*     */               } 
/* 269 */               if (!this.b) {
/* 270 */                 this.b = BetterSkeleton.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Skeleton.TrackingSpeed"), 15.0D);
/*     */               } else {
/* 272 */                 BetterSkeleton.this.track(BetterSkeleton.this.entity.getLocation(), 0.0F, 0.0D);
/* 273 */                 BetterSkeleton.this.setBusy(false);
/* 274 */                 BetterSkeleton.this.randomAttack(player, 0);
/* 275 */                 Bukkit.getScheduler().cancelTask(BetterSkeleton.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void randomAttack(Player player, int delay) {
/* 284 */     setBusy(true);
/* 285 */     getARide();
/* 286 */     Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)MobAI.getInstance(), () -> { SkeletonAttack attack = SkeletonAttack.values()[this.random.nextInt((SkeletonAttack.values()).length)]; switch (attack) { case NORMAL_ATTACK: normalAttack(player); return;case ARROW_RAIN_ATTACK: if (this.random.nextInt(10) < 4) arrowRainAttack(player);  return;case BURNING_ARROW_ATTACK: burningArrowAttack(player); return;case NAILING_ARROW_ATTACK: nailingArrowAttack(player); return;case POISONED_ARROW_ATTACK: poisonedArrowAttack(player); return; }  normalAttack(player); }(20 * delay));
/*     */   }
/*     */ }


/* Location:              C:\Users\cheen\Downloads\BetterMobAI-2.11 alpha.jar!\com\LucasRomier\BetterMobAI\Mobs\Entity\Overworld\BetterSkeleton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
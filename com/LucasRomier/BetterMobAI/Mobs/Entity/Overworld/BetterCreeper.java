/*     */ package com.LucasRomier.BetterMobAI.Mobs.Entity.Overworld;
/*     */ import com.LucasRomier.BetterMobAI.MobAI;
/*     */ import com.LucasRomier.BetterMobAI.Mobs.Attacks.CreeperAttack;
/*     */ import java.util.Random;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.entity.Creeper;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ public class BetterCreeper extends BetterMob {
/*     */   private int scheduler;
/*     */   
/*     */   public BetterCreeper(Creeper creeper) {
/*  19 */     super((LivingEntity)creeper);
/*     */   }
/*     */   
/*     */   public void normalAttack(final Player player) {
/*  23 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/*  24 */       setBusy(true);
/*  25 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/*  26 */             boolean b = BetterCreeper.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Creeper.NormalAttack.Speed"), 4.0D);
/*     */             
/*     */             public void run() {
/*  29 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/*  30 */                 !player.isOnline() || BetterCreeper.this.entity.isDead()) {
/*  31 */                 BetterCreeper.this.setBusy(false);
/*  32 */                 Bukkit.getScheduler().cancelTask(BetterCreeper.this.scheduler);
/*     */                 return;
/*     */               } 
/*  35 */               if (!this.b) {
/*  36 */                 this.b = BetterCreeper.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Creeper.NormalAttack.Speed"), 4.0D);
/*     */               } else {
/*  38 */                 Bukkit.getScheduler().cancelTask(BetterCreeper.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void implosionExplosionAttack(final Player player) {
/*  46 */     if (MobAI.settings.configuration.getBoolean("Creeper.ImplosionExplosionAttack.Disable")) {
/*  47 */       normalAttack(player);
/*     */       return;
/*     */     } 
/*  50 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/*  51 */       setBusy(true);
/*  52 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/*  53 */             boolean b = BetterCreeper.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Creeper.ImplosionExplosionAttack.Speed"), 4.0D);
/*     */             
/*     */             public void run() {
/*  56 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/*  57 */                 !player.isOnline() || BetterCreeper.this.entity.isDead()) {
/*  58 */                 BetterCreeper.this.setBusy(false);
/*  59 */                 Bukkit.getScheduler().cancelTask(BetterCreeper.this.scheduler);
/*     */                 return;
/*     */               } 
/*  62 */               if (!this.b) {
/*  63 */                 this.b = BetterCreeper.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Creeper.ImplosionExplosionAttack.Speed"), 4.0D);
/*     */               } else {
/*  65 */                 int j = MobAI.settings.configuration.getInt("Creeper.ImplosionExplosionAttack.ImplosionRadius");
/*  66 */                 for (Entity e : BetterCreeper.this.entity.getNearbyEntities(j, j, j)) {
/*  67 */                   if (e instanceof LivingEntity) {
/*  68 */                     Vector vector = BetterCreeper.this.entity.getLocation().subtract(e.getLocation()).toVector();
/*  69 */                     e.setVelocity(vector);
/*     */                   } 
/*     */                 } 
/*  72 */                 Bukkit.getScheduler().cancelTask(BetterCreeper.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void chargedCreeperAttack(final Player player) {
/*  80 */     if (MobAI.settings.configuration.getBoolean("Creeper.ChargedCreeperAttack.Disable")) {
/*  81 */       normalAttack(player);
/*     */       return;
/*     */     } 
/*  84 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/*  85 */       setBusy(true);
/*  86 */       this.entity.getWorld().strikeLightning(this.entity.getLocation());
/*  87 */       ((Creeper)this.entity).setPowered(true);
/*  88 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/*  89 */             boolean b = BetterCreeper.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Creeper.ChargedCreeperAttack.Speed"), 4.0D);
/*     */             
/*     */             public void run() {
/*  92 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/*  93 */                 !player.isOnline() || BetterCreeper.this.entity.isDead()) {
/*  94 */                 BetterCreeper.this.setBusy(false);
/*  95 */                 ((Creeper)BetterCreeper.this.entity).setPowered(false);
/*  96 */                 Bukkit.getScheduler().cancelTask(BetterCreeper.this.scheduler);
/*     */                 return;
/*     */               } 
/*  99 */               if (!this.b) {
/* 100 */                 this.b = BetterCreeper.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Creeper.ChargedCreeperAttack.Speed"), 4.0D);
/*     */               } else {
/* 102 */                 Bukkit.getScheduler().cancelTask(BetterCreeper.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void chargedSuperCreeperAttack(final Player player) {
/* 110 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/* 111 */       setBusy(true);
/* 112 */       this.entity.getWorld().strikeLightning(this.entity.getLocation());
/* 113 */       ((Creeper)this.entity).setPowered(true);
/* 114 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 115 */             boolean b = BetterCreeper.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Creeper.ChargedSuperCreeperAttack.Speed"), 4.0D);
/*     */             
/*     */             public void run() {
/* 118 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/* 119 */                 !player.isOnline() || BetterCreeper.this.entity.isDead()) {
/* 120 */                 BetterCreeper.this.setBusy(false);
/* 121 */                 ((Creeper)BetterCreeper.this.entity).setPowered(false);
/* 122 */                 Bukkit.getScheduler().cancelTask(BetterCreeper.this.scheduler);
/*     */                 return;
/*     */               } 
/* 125 */               if (!this.b) {
/* 126 */                 this.b = BetterCreeper.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Creeper.ChargedSuperCreeperAttack.Speed"), 4.0D);
/*     */               } else {
/* 128 */                 for (int i = 0; i < BetterCreeper.this.random.nextInt(3) + 3; i++) {
/*     */                   
/* 130 */                   Location location = BetterCreeper.this.entity.getLocation().clone().add((BetterCreeper.this.random.nextInt(6) - 3), 0.0D, ((new Random()).nextInt(6) - 3));
/* 131 */                   Creeper creeper = (Creeper)BetterCreeper.this.entity.getWorld().spawnEntity(location, EntityType.CREEPER);
/* 132 */                   (new BetterCreeper(creeper)).normalAttack(player);
/*     */                 } 
/* 134 */                 Bukkit.getScheduler().cancelTask(BetterCreeper.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void trackAndKill(final Player player) {
/* 143 */     if (!isBusy()) {
/* 144 */       setBusy(true);
/* 145 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 146 */             boolean b = BetterCreeper.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Creeper.TrackingSpeed"), 7.0D);
/*     */             
/*     */             public void run() {
/* 149 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/* 150 */                 !player.isOnline() || BetterCreeper.this.entity.isDead()) {
/* 151 */                 BetterCreeper.this.setBusy(false);
/* 152 */                 Bukkit.getScheduler().cancelTask(BetterCreeper.this.scheduler);
/*     */                 return;
/*     */               } 
/* 155 */               if (!this.b) {
/* 156 */                 this.b = BetterCreeper.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Creeper.TrackingSpeed"), 7.0D);
/*     */               } else {
/* 158 */                 BetterCreeper.this.track(BetterCreeper.this.entity.getLocation(), 0.0F, 0.0D);
/* 159 */                 BetterCreeper.this.setBusy(false);
/* 160 */                 BetterCreeper.this.randomAttack(player, 0);
/* 161 */                 Bukkit.getScheduler().cancelTask(BetterCreeper.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void randomAttack(Player player, int delay) {
/* 170 */     setBusy(true);
/* 171 */     CreeperAttack attack = CreeperAttack.values()[this.random.nextInt((CreeperAttack.values()).length)];
/* 172 */     Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)MobAI.getInstance(), () -> { switch (attack) { case NORMAL_ATTACK: normalAttack(player); return;case CHARGED_CREEPER_ATTACK: chargedCreeperAttack(player); return;case CHARGED_SUPER_CREEPER_ATTACK: chargedSuperCreeperAttack(player); return;case IMPLOSION_EXPLOSION_ATTACK: implosionExplosionAttack(player); return; }  normalAttack(player); }(20 * delay));
/*     */   }
/*     */ }


/* Location:              C:\Users\cheen\Downloads\BetterMobAI-2.11 alpha.jar!\com\LucasRomier\BetterMobAI\Mobs\Entity\Overworld\BetterCreeper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
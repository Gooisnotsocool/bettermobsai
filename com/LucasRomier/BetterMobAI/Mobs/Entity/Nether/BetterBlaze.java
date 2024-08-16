/*     */ package com.LucasRomier.BetterMobAI.Mobs.Entity.Nether;
/*     */ 
/*     */ import com.LucasRomier.BetterMobAI.MobAI;
/*     */ import com.LucasRomier.BetterMobAI.Mobs.Attacks.BlazeAttack;
/*     */ import com.LucasRomier.BetterMobAI.Mobs.BetterMob;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.craftbukkit.v1_15_R1.entity.CraftBlaze;
/*     */ import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
/*     */ import org.bukkit.entity.Blaze;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ 
/*     */ public class BetterBlaze
/*     */   extends BetterMob {
/*     */   private int scheduler;
/*     */   
/*     */   public BetterBlaze(Blaze blaze) {
/*  22 */     super((LivingEntity)blaze);
/*     */   }
/*     */   
/*     */   public void normalAttack(final Player player) {
/*  26 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/*  27 */       setBusy(true);
/*  28 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/*  29 */             boolean b = BetterBlaze.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Blaze.NormalAttack.Speed"), 10.0D);
/*     */             
/*     */             public void run() {
/*  32 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/*  33 */                 !player.isOnline() || BetterBlaze.this.entity.isDead()) {
/*  34 */                 BetterBlaze.this.setBusy(false);
/*  35 */                 Bukkit.getScheduler().cancelTask(BetterBlaze.this.scheduler);
/*     */                 return;
/*     */               } 
/*  38 */               if (!this.b) {
/*  39 */                 this.b = BetterBlaze.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Blaze.NormalAttack.Speed"), 10.0D);
/*     */               } else {
/*  41 */                 if (!player.isDead() && player.isOnline()) {
/*  42 */                   ((CraftBlaze)BetterBlaze.this.entity).getHandle().a(((CraftPlayer)player).getHandle());
/*  43 */                   BetterBlaze.this.randomAttack(player, MobAI.settings.configuration.getInt("Blaze.NormalAttack.NextAttackDelay"));
/*     */                 } else {
/*  45 */                   BetterBlaze.this.setBusy(false);
/*     */                 } 
/*  47 */                 Bukkit.getScheduler().cancelTask(BetterBlaze.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void heatAttack(final Player player) {
/*  55 */     if (MobAI.settings.configuration.getBoolean("Blaze.HeatAttack.Disable")) {
/*  56 */       normalAttack(player);
/*     */       return;
/*     */     } 
/*  59 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/*  60 */       setBusy(true);
/*  61 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/*  62 */             boolean b = BetterBlaze.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Blaze.HeatAttack.Speed"), 5.0D);
/*     */             
/*     */             public void run() {
/*  65 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/*  66 */                 !player.isOnline() || BetterBlaze.this.entity.isDead()) {
/*  67 */                 BetterBlaze.this.setBusy(false);
/*  68 */                 Bukkit.getScheduler().cancelTask(BetterBlaze.this.scheduler);
/*     */                 return;
/*     */               } 
/*  71 */               if (!this.b) {
/*  72 */                 this.b = BetterBlaze.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Blaze.HeatAttack.Speed"), 5.0D);
/*     */               } else {
/*  74 */                 if (!player.isDead() && player.isOnline()) {
/*  75 */                   BetterBlaze.this.entity.setFireTicks(20 * MobAI.settings.configuration.getInt("Blaze.HeatAttack.EffectLength"));
/*  76 */                   player.setFireTicks(20 * MobAI.settings.configuration.getInt("Blaze.HeatAttack.EffectLength"));
/*  77 */                   BetterBlaze.this.randomAttack(player, MobAI.settings.configuration.getInt("Blaze.HeatAttack.NextAttackDelay"));
/*     */                 } else {
/*  79 */                   BetterBlaze.this.setBusy(false);
/*     */                 } 
/*  81 */                 Bukkit.getScheduler().cancelTask(BetterBlaze.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void smokeAttack(final Player player) {
/*  89 */     if (MobAI.settings.configuration.getBoolean("Blaze.SmokeAttack.Disable")) {
/*  90 */       normalAttack(player);
/*     */       return;
/*     */     } 
/*  93 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/*  94 */       setBusy(true);
/*  95 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/*  96 */             boolean b = BetterBlaze.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Blaze.SmokeAttack.Speed"), 5.0D);
/*     */             
/*     */             public void run() {
/*  99 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/* 100 */                 !player.isOnline() || BetterBlaze.this.entity.isDead()) {
/* 101 */                 BetterBlaze.this.setBusy(false);
/* 102 */                 Bukkit.getScheduler().cancelTask(BetterBlaze.this.scheduler);
/*     */                 return;
/*     */               } 
/* 105 */               if (!this.b) {
/* 106 */                 this.b = BetterBlaze.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Blaze.SmokeAttack.Speed"), 5.0D);
/*     */               } else {
/* 108 */                 if (!player.isDead() && player.isOnline()) {
/* 109 */                   player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * MobAI.settings.configuration.getInt("Blaze.SmokeAttack.EffectLength"), 5));
/* 110 */                   BetterBlaze.this.randomAttack(player, MobAI.settings.configuration.getInt("Blaze.SmokeAttack.NextAttackDelay"));
/*     */                 } else {
/* 112 */                   BetterBlaze.this.setBusy(false);
/*     */                 } 
/* 114 */                 Bukkit.getScheduler().cancelTask(BetterBlaze.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void trackAndKill(final Player player) {
/* 123 */     if (!isBusy()) {
/* 124 */       setBusy(true);
/* 125 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 126 */             boolean b = BetterBlaze.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Blaze.TrackingSpeed"), 15.0D);
/*     */             
/*     */             public void run() {
/* 129 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/* 130 */                 !player.isOnline() || BetterBlaze.this.entity.isDead()) {
/* 131 */                 BetterBlaze.this.setBusy(false);
/* 132 */                 Bukkit.getScheduler().cancelTask(BetterBlaze.this.scheduler);
/*     */                 return;
/*     */               } 
/* 135 */               if (!this.b) {
/* 136 */                 this.b = BetterBlaze.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Blaze.TrackingSpeed"), 15.0D);
/*     */               } else {
/* 138 */                 BetterBlaze.this.track(BetterBlaze.this.entity.getLocation(), 0.0F, 0.0D);
/* 139 */                 BetterBlaze.this.setBusy(false);
/* 140 */                 BetterBlaze.this.randomAttack(player, 0);
/* 141 */                 Bukkit.getScheduler().cancelTask(BetterBlaze.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void randomAttack(Player player, int delay) {
/* 150 */     setBusy(true);
/* 151 */     BlazeAttack attack = BlazeAttack.values()[this.random.nextInt((BlazeAttack.values()).length)];
/* 152 */     Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)MobAI.getInstance(), () -> { switch (attack) { case NORMAL_ATTACK: normalAttack(player); return;case HEAT_ATTACK: heatAttack(player); return;case SMOKE_ATTACK: smokeAttack(player); return; }  normalAttack(player); }(20 * delay));
/*     */   }
/*     */ }


/* Location:              C:\Users\cheen\Downloads\BetterMobAI-2.11 alpha.jar!\com\LucasRomier\BetterMobAI\Mobs\Entity\Nether\BetterBlaze.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package com.LucasRomier.BetterMobAI.Mobs.Entity.Water;
/*     */ import com.LucasRomier.BetterMobAI.MobAI;
/*     */ import com.LucasRomier.BetterMobAI.Mobs.Attacks.GuardianAttack;
/*     */ import net.minecraft.server.v1_15_R1.Entity;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.craftbukkit.v1_15_R1.entity.CraftGuardian;
/*     */ import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
/*     */ import org.bukkit.entity.Guardian;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ 
/*     */ public class BetterGuardian extends BetterMob {
/*     */   private int scheduler;
/*     */   
/*     */   public BetterGuardian(Guardian guardian) {
/*  20 */     super((LivingEntity)guardian);
/*     */   }
/*     */   
/*     */   public void normalAttack(final Player player) {
/*  24 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/*  25 */       setBusy(true);
/*  26 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/*  27 */             boolean b = BetterGuardian.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Guardian.NormalAttack.Speed"), 10.0D);
/*     */             
/*     */             public void run() {
/*  30 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/*  31 */                 !player.isOnline() || BetterGuardian.this.entity.isDead()) {
/*  32 */                 BetterGuardian.this.setBusy(false);
/*  33 */                 Bukkit.getScheduler().cancelTask(BetterGuardian.this.scheduler);
/*     */                 return;
/*     */               } 
/*  36 */               if (!this.b) {
/*  37 */                 this.b = BetterGuardian.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Guardian.NormalAttack.Speed"), 10.0D);
/*     */               } else {
/*  39 */                 if (!player.isDead() && player.isOnline()) {
/*  40 */                   ((CraftGuardian)BetterGuardian.this.entity).getHandle().a(((CraftPlayer)player).getHandle());
/*  41 */                   BetterGuardian.this.randomAttack(player, MobAI.settings.configuration.getInt("Guardian.NormalAttack.NextAttackDelay"));
/*     */                 } else {
/*  43 */                   BetterGuardian.this.setBusy(false);
/*     */                 } 
/*  45 */                 Bukkit.getScheduler().cancelTask(BetterGuardian.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void instantLaserAttack(final Player player) {
/*  53 */     if (MobAI.settings.configuration.getBoolean("Guardian.InstantAttack.Disable")) {
/*  54 */       normalAttack(player);
/*     */       return;
/*     */     } 
/*  57 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/*  58 */       setBusy(true);
/*  59 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/*  60 */             boolean b = BetterGuardian.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Guardian.InstantAttack.Speed"), 10.0D);
/*     */             
/*     */             public void run() {
/*  63 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/*  64 */                 !player.isOnline() || BetterGuardian.this.entity.isDead()) {
/*  65 */                 BetterGuardian.this.setBusy(false);
/*  66 */                 Bukkit.getScheduler().cancelTask(BetterGuardian.this.scheduler);
/*     */                 return;
/*     */               } 
/*  69 */               if (!this.b) {
/*  70 */                 this.b = BetterGuardian.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Guardian.InstantAttack.Speed"), 10.0D);
/*     */               } else {
/*  72 */                 if (!player.isDead() && player.isOnline()) {
/*  73 */                   ((CraftGuardian)BetterGuardian.this.entity).getHandle().a((Entity)((CraftPlayer)player).getHandle(), 0.0F, 0.0F);
/*  74 */                   BetterGuardian.this.randomAttack(player, MobAI.settings.configuration.getInt("Guardian.InstantAttack.NextAttackDelay"));
/*     */                 } else {
/*  76 */                   BetterGuardian.this.setBusy(false);
/*     */                 } 
/*  78 */                 Bukkit.getScheduler().cancelTask(BetterGuardian.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void suffocationAttack(final Player player) {
/*  86 */     if (MobAI.settings.configuration.getBoolean("Guardian.SuffocationVibesAttack.Disable")) {
/*  87 */       normalAttack(player);
/*     */       return;
/*     */     } 
/*  90 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/*  91 */       setBusy(true);
/*  92 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/*  93 */             boolean b = BetterGuardian.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Guardian.SuffocationVibesAttack.Speed"), 10.0D);
/*     */             
/*     */             public void run() {
/*  96 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/*  97 */                 !player.isOnline() || BetterGuardian.this.entity.isDead()) {
/*  98 */                 BetterGuardian.this.setBusy(false);
/*  99 */                 Bukkit.getScheduler().cancelTask(BetterGuardian.this.scheduler);
/*     */                 return;
/*     */               } 
/* 102 */               if (!this.b) {
/* 103 */                 this.b = BetterGuardian.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Guardian.SuffocationVibesAttack.Speed"), 10.0D);
/*     */               } else {
/* 105 */                 if (!player.isDead() && player.isOnline()) {
/* 106 */                   player.setRemainingAir(0);
/* 107 */                   BetterGuardian.this.randomAttack(player, MobAI.settings.configuration.getInt("Guardian.SuffocationVibesAttack.NextAttackDelay"));
/*     */                 } else {
/* 109 */                   BetterGuardian.this.setBusy(false);
/*     */                 } 
/* 111 */                 Bukkit.getScheduler().cancelTask(BetterGuardian.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void nailingAttack(final Player player) {
/* 119 */     if (MobAI.settings.configuration.getBoolean("Guardian.NailingVibesAttack.Disable")) {
/* 120 */       normalAttack(player);
/*     */       return;
/*     */     } 
/* 123 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/* 124 */       setBusy(true);
/* 125 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 126 */             boolean b = BetterGuardian.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Guardian.NailingVibesAttack.Speed"), 10.0D);
/*     */             
/*     */             public void run() {
/* 129 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/* 130 */                 !player.isOnline() || BetterGuardian.this.entity.isDead()) {
/* 131 */                 BetterGuardian.this.setBusy(false);
/* 132 */                 Bukkit.getScheduler().cancelTask(BetterGuardian.this.scheduler);
/*     */                 return;
/*     */               } 
/* 135 */               if (!this.b) {
/* 136 */                 this.b = BetterGuardian.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Guardian.NailingVibesAttack.Speed"), 10.0D);
/*     */               } else {
/* 138 */                 if (!player.isDead() && player.isOnline()) {
/* 139 */                   player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * MobAI.settings.configuration.getInt("Guardian.NailingVibesAttack.EffectLength"), 4));
/* 140 */                   BetterGuardian.this.randomAttack(player, MobAI.settings.configuration.getInt("Guardian.NailingVibesAttack.NextAttackDelay"));
/*     */                 } else {
/* 142 */                   BetterGuardian.this.setBusy(false);
/*     */                 } 
/* 144 */                 Bukkit.getScheduler().cancelTask(BetterGuardian.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void trackAndKill(final Player player) {
/* 153 */     if (!isBusy()) {
/* 154 */       setBusy(true);
/* 155 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 156 */             boolean b = BetterGuardian.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Guardian.TrackingSpeed"), 10.0D);
/*     */             
/*     */             public void run() {
/* 159 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/* 160 */                 !player.isOnline() || BetterGuardian.this.entity.isDead()) {
/* 161 */                 BetterGuardian.this.setBusy(false);
/* 162 */                 Bukkit.getScheduler().cancelTask(BetterGuardian.this.scheduler);
/*     */                 return;
/*     */               } 
/* 165 */               if (!this.b) {
/* 166 */                 this.b = BetterGuardian.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Guardian.TrackingSpeed"), 10.0D);
/*     */               } else {
/* 168 */                 BetterGuardian.this.track(BetterGuardian.this.entity.getLocation(), 0.0F, 0.0D);
/* 169 */                 BetterGuardian.this.setBusy(false);
/* 170 */                 BetterGuardian.this.randomAttack(player, 0);
/* 171 */                 Bukkit.getScheduler().cancelTask(BetterGuardian.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void randomAttack(Player player, int delay) {
/* 180 */     setBusy(true);
/* 181 */     GuardianAttack attack = GuardianAttack.values()[this.random.nextInt((GuardianAttack.values()).length)];
/* 182 */     Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)MobAI.getInstance(), () -> { switch (attack) { case NORMAL_ATTACK: normalAttack(player); return;case INSTANT_LASER_ATTACK: instantLaserAttack(player); return;case NAILING_VIBES_ATTACK: suffocationAttack(player); return;case SUFFOCATION_VIBES_ATTACK: nailingAttack(player); return; }  normalAttack(player); }(20 * delay));
/*     */   }
/*     */ }


/* Location:              C:\Users\cheen\Downloads\BetterMobAI-2.11 alpha.jar!\com\LucasRomier\BetterMobAI\Mobs\Entity\Water\BetterGuardian.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
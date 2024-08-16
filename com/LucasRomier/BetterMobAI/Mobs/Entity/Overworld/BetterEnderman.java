/*     */ package com.LucasRomier.BetterMobAI.Mobs.Entity.Overworld;
/*     */ import com.LucasRomier.BetterMobAI.MobAI;
/*     */ import com.LucasRomier.BetterMobAI.Mobs.Attacks.EndermanAttack;
/*     */ import com.LucasRomier.BetterMobAI.Mobs.BetterMob;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.entity.Enderman;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ 
/*     */ public class BetterEnderman extends BetterMob {
/*     */   private int scheduler;
/*     */   private int secondary;
/*     */   
/*     */   public BetterEnderman(Enderman enderman) {
/*  21 */     super((LivingEntity)enderman);
/*     */   }
/*     */   
/*     */   public void normalAttack(final Player player) {
/*  25 */     final Location location = this.entity.getLocation();
/*  26 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/*  27 */       setBusy(true);
/*  28 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/*  29 */             boolean b = BetterEnderman.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Enderman.NormalAttack.Speed"), 10.0D);
/*     */             
/*     */             public void run() {
/*  32 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/*  33 */                 !player.isOnline() || BetterEnderman.this.entity.isDead()) {
/*  34 */                 BetterEnderman.this.setBusy(false);
/*  35 */                 Bukkit.getScheduler().cancelTask(BetterEnderman.this.scheduler);
/*     */                 return;
/*     */               } 
/*  38 */               if (!this.b) {
/*  39 */                 this.b = BetterEnderman.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Enderman.NormalAttack.Speed"), 10.0D);
/*     */               } else {
/*  41 */                 BetterEnderman.this.track(BetterEnderman.this.entity.getLocation(), 1.0F, 1.0D);
/*  42 */                 BetterEnderman.this.entity.teleport(player.getLocation().clone().add(0.5D, 0.0D, 0.5D));
/*  43 */                 player.damage(MobAI.settings.configuration.getDouble("Enderman.NormalAttack.Damage"));
/*  44 */                 player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 20 * MobAI.settings.configuration.getInt("Enderman.NormalAttack.ConfusionTime"), 1));
/*  45 */                 BetterEnderman.this.entity.teleport(location);
/*  46 */                 BetterEnderman.this.randomAttack(player, MobAI.settings.configuration.getInt("Enderman.NormalAttack.NextAttackDelay"));
/*  47 */                 Bukkit.getScheduler().cancelTask(BetterEnderman.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void jumperAttack(final Player player) {
/*  55 */     if (MobAI.settings.configuration.getBoolean("Creeper.JumperAttack.Disable")) {
/*  56 */       normalAttack(player);
/*     */       return;
/*     */     } 
/*  59 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/*  60 */       setBusy(true);
/*  61 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/*  62 */             boolean b = BetterEnderman.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Enderman.JumperAttack.Speed"), 15.0D);
/*     */             
/*     */             public void run() {
/*  65 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/*  66 */                 !player.isOnline() || BetterEnderman.this.entity.isDead()) {
/*  67 */                 BetterEnderman.this.setBusy(false);
/*  68 */                 Bukkit.getScheduler().cancelTask(BetterEnderman.this.scheduler);
/*     */                 return;
/*     */               } 
/*  71 */               if (!this.b) {
/*  72 */                 this.b = BetterEnderman.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Enderman.JumperAttack.Speed"), 15.0D);
/*     */               } else {
/*  74 */                 BetterEnderman.this.track(BetterEnderman.this.entity.getLocation(), 1.0F, 1.0D);
/*  75 */                 BetterEnderman.this.jumpTowardsPlayer(player);
/*  76 */                 Bukkit.getScheduler().cancelTask(BetterEnderman.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void jumpTowardsPlayer(final Player player) {
/*  84 */     final Location location = this.entity.getLocation();
/*  85 */     this.secondary = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/*  86 */           double x = BetterEnderman.this.entity.getLocation().getX();
/*  87 */           double z = BetterEnderman.this.entity.getLocation().getZ();
/*  88 */           int i = 0;
/*     */           
/*     */           public void run() {
/*  91 */             if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/*  92 */               !player.isOnline() || BetterEnderman.this.entity.isDead()) {
/*  93 */               BetterEnderman.this.setBusy(false);
/*  94 */               Bukkit.getScheduler().cancelTask(BetterEnderman.this.scheduler);
/*     */               return;
/*     */             } 
/*  97 */             double xPortion = player.getLocation().getX() - BetterEnderman.this.entity.getLocation().getX() / this.i;
/*  98 */             double zPortion = player.getLocation().getZ() - BetterEnderman.this.entity.getLocation().getZ() / this.i;
/*  99 */             if (this.i < 6) {
/* 100 */               BetterEnderman.this.entity.teleport(new Location(player.getWorld(), this.x + xPortion, player.getLocation().getY(), this.z + zPortion));
/* 101 */               BetterEnderman.this.entity.getWorld().playSound(BetterEnderman.this.entity.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0F, 1.0F);
/* 102 */               this.i++;
/*     */             } else {
/* 104 */               BetterEnderman.this.entity.teleport(player.getLocation().clone().add(0.5D, 0.0D, 0.5D));
/* 105 */               player.damage(MobAI.settings.configuration.getDouble("Enderman.JumperAttack.Damage"));
/* 106 */               player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 20 * MobAI.settings.configuration.getInt("Enderman.JumperAttack.Damage"), 1));
/* 107 */               BetterEnderman.this.entity.teleport(location);
/* 108 */               BetterEnderman.this.randomAttack(player, MobAI.settings.configuration.getInt("Enderman.JumperAttack.NextAttackDelay"));
/* 109 */               Bukkit.getScheduler().cancelTask(BetterEnderman.this.secondary);
/*     */             } 
/*     */           }
/*     */         }0L, 5L);
/*     */   }
/*     */ 
/*     */   
/*     */   public void trackAndKill(final Player player) {
/* 117 */     if (!isBusy()) {
/* 118 */       setBusy(true);
/* 119 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 120 */             boolean b = BetterEnderman.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Enderman.TrackingSpeed"), 15.0D);
/*     */             
/*     */             public void run() {
/* 123 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/* 124 */                 !player.isOnline() || BetterEnderman.this.entity.isDead()) {
/* 125 */                 BetterEnderman.this.setBusy(false);
/* 126 */                 Bukkit.getScheduler().cancelTask(BetterEnderman.this.scheduler);
/*     */                 return;
/*     */               } 
/* 129 */               if (!this.b) {
/* 130 */                 this.b = BetterEnderman.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Enderman.TrackingSpeed"), 15.0D);
/*     */               } else {
/* 132 */                 BetterEnderman.this.track(BetterEnderman.this.entity.getLocation(), 0.0F, 0.0D);
/* 133 */                 BetterEnderman.this.setBusy(false);
/* 134 */                 BetterEnderman.this.randomAttack(player, 0);
/* 135 */                 Bukkit.getScheduler().cancelTask(BetterEnderman.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void randomAttack(Player player, int delay) {
/* 144 */     setBusy(true);
/* 145 */     EndermanAttack attack = EndermanAttack.values()[this.random.nextInt((EndermanAttack.values()).length)];
/* 146 */     Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)MobAI.getInstance(), () -> { switch (attack) { case NORMAL_ATTACK: normalAttack(player); return;case JUMPER_ATTACK: jumperAttack(player); return; }  normalAttack(player); }(20 * delay));
/*     */   }
/*     */ }


/* Location:              C:\Users\cheen\Downloads\BetterMobAI-2.11 alpha.jar!\com\LucasRomier\BetterMobAI\Mobs\Entity\Overworld\BetterEnderman.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
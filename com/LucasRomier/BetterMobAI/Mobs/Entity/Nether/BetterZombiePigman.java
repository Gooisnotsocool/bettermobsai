/*     */ package com.LucasRomier.BetterMobAI.Mobs.Entity.Nether;
/*     */ import com.LucasRomier.BetterMobAI.MobAI;
/*     */ import com.LucasRomier.BetterMobAI.Mobs.Attacks.ZombiePigmanAttack;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Item;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.PigZombie;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ public class BetterZombiePigman extends BetterMob {
/*     */   private int scheduler;
/*     */   private int secondary;
/*     */   
/*     */   public BetterZombiePigman(PigZombie pigZombie) {
/*  19 */     super((LivingEntity)pigZombie);
/*     */   }
/*     */   
/*     */   public void normalAttack(final Player player) {
/*  23 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/*  24 */       setBusy(true);
/*  25 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/*  26 */             boolean b = BetterZombiePigman.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("PigmanZombie.NormalAttack.Speed"), 1.0D);
/*     */             
/*     */             public void run() {
/*  29 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/*  30 */                 !player.isOnline() || BetterZombiePigman.this.entity.isDead()) {
/*  31 */                 BetterZombiePigman.this.setBusy(false);
/*  32 */                 Bukkit.getScheduler().cancelTask(BetterZombiePigman.this.scheduler);
/*     */                 return;
/*     */               } 
/*  35 */               if (!this.b) {
/*  36 */                 this.b = BetterZombiePigman.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("PigmanZombie.NormalAttack.Speed"), 1.0D);
/*     */               } else {
/*  38 */                 if (!player.isDead() && player.isOnline()) {
/*  39 */                   player.damage(MobAI.settings.configuration.getDouble("PigmanZombie.NormalAttack.Damage"));
/*  40 */                   BetterZombiePigman.this.randomAttack(player, MobAI.settings.configuration.getInt("PigmanZombie.NormalAttack.NextAttackDelay"));
/*     */                 } else {
/*  42 */                   BetterZombiePigman.this.setBusy(false);
/*     */                 } 
/*  44 */                 Bukkit.getScheduler().cancelTask(BetterZombiePigman.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void lightningAttack(final Player player) {
/*  52 */     if (MobAI.settings.configuration.getBoolean("PigmanZombie.LightningAttack.Disable")) {
/*  53 */       normalAttack(player);
/*     */       return;
/*     */     } 
/*  56 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/*  57 */       setBusy(true);
/*  58 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/*  59 */             boolean b = BetterZombiePigman.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("PigmanZombie.LightningAttack.Speed"), 1.0D);
/*     */             
/*     */             public void run() {
/*  62 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/*  63 */                 !player.isOnline() || BetterZombiePigman.this.entity.isDead()) {
/*  64 */                 BetterZombiePigman.this.setBusy(false);
/*  65 */                 Bukkit.getScheduler().cancelTask(BetterZombiePigman.this.scheduler);
/*     */                 return;
/*     */               } 
/*  68 */               if (!this.b) {
/*  69 */                 this.b = BetterZombiePigman.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("PigmanZombie.LightningAttack.Speed"), 1.0D);
/*     */               } else {
/*  71 */                 if (!player.isDead() && player.isOnline()) {
/*  72 */                   BetterZombiePigman.this.entity.getWorld().strikeLightning(BetterZombiePigman.this.entity.getLocation());
/*  73 */                   player.damage(MobAI.settings.configuration.getDouble("PigmanZombie.LightningAttack.Damage"));
/*  74 */                   BetterZombiePigman.this.randomAttack(player, MobAI.settings.configuration.getInt("PigmanZombie.LightningAttack.NextAttackDelay"));
/*     */                 } else {
/*  76 */                   BetterZombiePigman.this.setBusy(false);
/*     */                 } 
/*  78 */                 Bukkit.getScheduler().cancelTask(BetterZombiePigman.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void swordAttack(final Player player) {
/*  86 */     if (MobAI.settings.configuration.getBoolean("PigmanZombie.SwordAttack.Disable")) {
/*  87 */       normalAttack(player);
/*     */       return;
/*     */     } 
/*  90 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/*  91 */       setBusy(true);
/*  92 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/*  93 */             boolean b = BetterZombiePigman.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("PigmanZombie.SwordAttack.Speed"), 10.0D);
/*     */             
/*     */             public void run() {
/*  96 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/*  97 */                 !player.isOnline() || BetterZombiePigman.this.entity.isDead()) {
/*  98 */                 BetterZombiePigman.this.setBusy(false);
/*  99 */                 Bukkit.getScheduler().cancelTask(BetterZombiePigman.this.scheduler);
/*     */                 return;
/*     */               } 
/* 102 */               if (!this.b) {
/* 103 */                 this.b = BetterZombiePigman.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("PigmanZombie.SwordAttack.Speed"), 10.0D);
/*     */               } else {
/* 105 */                 if (!player.isDead() && player.isOnline()) {
/* 106 */                   BetterZombiePigman.this.throwSword(player);
/*     */                 } else {
/* 108 */                   BetterZombiePigman.this.setBusy(false);
/*     */                 } 
/* 110 */                 Bukkit.getScheduler().cancelTask(BetterZombiePigman.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void throwSword(Player player) {
/* 118 */     if (MobAI.settings.configuration.getBoolean("PigmanZombie.ThrowSwordAttack.Disable")) {
/* 119 */       normalAttack(player);
/*     */       return;
/*     */     } 
/* 122 */     Item item = this.entity.getWorld().dropItem(this.entity.getEyeLocation().clone().add(0.0D, 0.2D, 0.0D), new ItemStack(Material.GOLDEN_SWORD));
/* 123 */     Vector vector = player.getLocation().subtract(this.entity.getLocation()).toVector();
/* 124 */     item.setVelocity(vector.multiply(0.5D));
/* 125 */     this.secondary = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), () -> { if (item.isOnGround()) { item.remove(); Bukkit.getScheduler().cancelTask(this.secondary); return; }  for (Entity entity : item.getNearbyEntities(0.2D, 0.2D, 0.2D)) { if (entity.getType().equals(EntityType.PLAYER)) { item.remove(); player.damage(MobAI.settings.configuration.getDouble("PigmanZombie.ThrowSwordAttack.Damage")); randomAttack(player, MobAI.settings.configuration.getInt("PigmanZombie.ThrowSwordAttack.NextAttackDelay")); Bukkit.getScheduler().cancelTask(this.secondary); break; }  }  }0L, 1L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void trackAndKill(final Player player) {
/* 145 */     if (!isBusy()) {
/* 146 */       setBusy(true);
/* 147 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 148 */             boolean b = BetterZombiePigman.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("PigmanZombie.TrackingSpeed"), 10.0D);
/*     */             
/*     */             public void run() {
/* 151 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/* 152 */                 !player.isOnline() || BetterZombiePigman.this.entity.isDead()) {
/* 153 */                 BetterZombiePigman.this.setBusy(false);
/* 154 */                 Bukkit.getScheduler().cancelTask(BetterZombiePigman.this.scheduler);
/*     */                 return;
/*     */               } 
/* 157 */               if (!this.b) {
/* 158 */                 this.b = BetterZombiePigman.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("PigmanZombie.TrackingSpeed"), 10.0D);
/*     */               } else {
/* 160 */                 BetterZombiePigman.this.track(BetterZombiePigman.this.entity.getLocation(), 0.0F, 0.0D);
/* 161 */                 BetterZombiePigman.this.setBusy(false);
/* 162 */                 BetterZombiePigman.this.randomAttack(player, 0);
/* 163 */                 Bukkit.getScheduler().cancelTask(BetterZombiePigman.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void randomAttack(Player player, int delay) {
/* 172 */     setBusy(true);
/* 173 */     ZombiePigmanAttack attack = ZombiePigmanAttack.values()[this.random.nextInt((ZombiePigmanAttack.values()).length)];
/* 174 */     Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)MobAI.getInstance(), () -> { switch (attack) { case NORMAL_ATTACK: normalAttack(player); return;case LIGHTNING_ATTACK: lightningAttack(player); return;case SWORD_THROW_ATTACK: swordAttack(player); return; }  normalAttack(player); }(20 * delay));
/*     */   }
/*     */ }


/* Location:              C:\Users\cheen\Downloads\BetterMobAI-2.11 alpha.jar!\com\LucasRomier\BetterMobAI\Mobs\Entity\Nether\BetterZombiePigman.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
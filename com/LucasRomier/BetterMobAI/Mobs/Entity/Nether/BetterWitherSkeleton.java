/*     */ package com.LucasRomier.BetterMobAI.Mobs.Entity.Nether;
/*     */ import com.LucasRomier.BetterMobAI.MobAI;
/*     */ import com.LucasRomier.BetterMobAI.Mobs.Attacks.WitherSkeletonAttack;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.Item;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.WitherSkull;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ public class BetterWitherSkeleton extends BetterMob {
/*     */   private int scheduler;
/*     */   private int secondary;
/*     */   
/*     */   public BetterWitherSkeleton(Skeleton skeleton) {
/*  22 */     super((LivingEntity)skeleton);
/*     */   }
/*     */   
/*     */   public void normalAttack(final Player player) {
/*  26 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/*  27 */       setBusy(true);
/*  28 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/*  29 */             boolean b = BetterWitherSkeleton.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("WitherSkeleton.NormalAttack.Speed"), 1.0D);
/*     */             
/*     */             public void run() {
/*  32 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/*  33 */                 !player.isOnline() || BetterWitherSkeleton.this.entity.isDead()) {
/*  34 */                 BetterWitherSkeleton.this.setBusy(false);
/*  35 */                 Bukkit.getScheduler().cancelTask(BetterWitherSkeleton.this.scheduler);
/*     */                 return;
/*     */               } 
/*  38 */               if (!this.b) {
/*  39 */                 this.b = BetterWitherSkeleton.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("WitherSkeleton.NormalAttack.Speed"), 1.0D);
/*     */               } else {
/*  41 */                 if (!player.isDead() && player.isOnline()) {
/*  42 */                   player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 0));
/*  43 */                   player.damage(MobAI.settings.configuration.getDouble("WitherSkeleton.NormalAttack.Damage"));
/*  44 */                   BetterWitherSkeleton.this.randomAttack(player, MobAI.settings.configuration.getInt("WitherSkeleton.NormalAttack.NextAttackDelay"));
/*     */                 } else {
/*  46 */                   BetterWitherSkeleton.this.setBusy(false);
/*     */                 } 
/*  48 */                 Bukkit.getScheduler().cancelTask(BetterWitherSkeleton.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void swordAttack(final Player player) {
/*  56 */     if (MobAI.settings.configuration.getBoolean("WitherSkeleton.SwordAttack.Disable")) {
/*  57 */       normalAttack(player);
/*     */       return;
/*     */     } 
/*  60 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/*  61 */       setBusy(true);
/*  62 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/*  63 */             boolean b = BetterWitherSkeleton.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("WitherSkeleton.SwordAttack.Speed"), 1.0D);
/*     */             
/*     */             public void run() {
/*  66 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/*  67 */                 !player.isOnline() || BetterWitherSkeleton.this.entity.isDead()) {
/*  68 */                 BetterWitherSkeleton.this.setBusy(false);
/*  69 */                 Bukkit.getScheduler().cancelTask(BetterWitherSkeleton.this.scheduler);
/*     */                 return;
/*     */               } 
/*  72 */               if (!this.b) {
/*  73 */                 this.b = BetterWitherSkeleton.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("WitherSkeleton.SwordAttack.Speed"), 1.0D);
/*     */               } else {
/*  75 */                 if (!player.isDead() && player.isOnline()) {
/*  76 */                   BetterWitherSkeleton.this.throwSword(player);
/*     */                 } else {
/*  78 */                   BetterWitherSkeleton.this.setBusy(false);
/*     */                 } 
/*  80 */                 Bukkit.getScheduler().cancelTask(BetterWitherSkeleton.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void throwSword(Player player) {
/*  88 */     if (MobAI.settings.configuration.getBoolean("WitherSkeleton.ThrowSwordAttack.Disable")) {
/*  89 */       normalAttack(player);
/*     */       return;
/*     */     } 
/*  92 */     Item item = this.entity.getWorld().dropItem(this.entity.getEyeLocation().clone().add(0.0D, 0.2D, 0.0D), new ItemStack(Material.GOLDEN_SWORD));
/*  93 */     Vector vector = player.getLocation().subtract(this.entity.getLocation()).toVector();
/*  94 */     item.setVelocity(vector.multiply(0.5D));
/*  95 */     this.secondary = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), () -> { if (item.isOnGround()) { item.remove(); Bukkit.getScheduler().cancelTask(this.secondary); return; }  for (Entity entity : item.getNearbyEntities(0.2D, 0.2D, 0.2D)) { if (entity.getType().equals(EntityType.PLAYER)) { item.remove(); player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 0)); player.damage(MobAI.settings.configuration.getDouble("WitherSkeleton.ThrowSwordAttack.Damage")); randomAttack(player, MobAI.settings.configuration.getInt("WitherSkeleton.ThrowSwordAttack.NextAttackDelay")); Bukkit.getScheduler().cancelTask(this.secondary); break; }  }  }0L, 1L);
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
/*     */   public void witherSkullAttack(final Player player) {
/* 115 */     if (MobAI.settings.configuration.getBoolean("WitherSkeleton.WitherSkullAttack.Disable")) {
/* 116 */       normalAttack(player);
/*     */       return;
/*     */     } 
/* 119 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/* 120 */       setBusy(true);
/* 121 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 122 */             boolean b = BetterWitherSkeleton.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("WitherSkeleton.WitherSkullAttack.Speed"), 10.0D);
/*     */             
/*     */             public void run() {
/* 125 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/* 126 */                 !player.isOnline() || BetterWitherSkeleton.this.entity.isDead()) {
/* 127 */                 BetterWitherSkeleton.this.setBusy(false);
/* 128 */                 Bukkit.getScheduler().cancelTask(BetterWitherSkeleton.this.scheduler);
/*     */                 return;
/*     */               } 
/* 131 */               if (!this.b) {
/* 132 */                 this.b = BetterWitherSkeleton.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("WitherSkeleton.WitherSkullAttack.Speed"), 10.0D);
/*     */               } else {
/* 134 */                 if (!player.isDead() && player.isOnline()) {
/* 135 */                   Vector vector = player.getLocation().subtract(BetterWitherSkeleton.this.entity.getLocation()).toVector();
/* 136 */                   WitherSkull skull = (WitherSkull)BetterWitherSkeleton.this.entity.getWorld().spawnEntity(BetterWitherSkeleton.this.entity.getEyeLocation().clone().add(0.0D, 0.2D, 0.0D), EntityType.WITHER_SKULL);
/* 137 */                   skull.setGlowing(true);
/* 138 */                   skull.setDirection(vector);
/*     */                 } else {
/* 140 */                   BetterWitherSkeleton.this.setBusy(false);
/*     */                 } 
/* 142 */                 Bukkit.getScheduler().cancelTask(BetterWitherSkeleton.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void trackAndKill(final Player player) {
/* 151 */     if (!isBusy()) {
/* 152 */       setBusy(true);
/* 153 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 154 */             boolean b = BetterWitherSkeleton.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("WitherSkeleton.TrackingSpeed"), 10.0D);
/*     */             
/*     */             public void run() {
/* 157 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/* 158 */                 !player.isOnline() || BetterWitherSkeleton.this.entity.isDead()) {
/* 159 */                 BetterWitherSkeleton.this.setBusy(false);
/* 160 */                 Bukkit.getScheduler().cancelTask(BetterWitherSkeleton.this.scheduler);
/*     */                 return;
/*     */               } 
/* 163 */               if (!this.b) {
/* 164 */                 this.b = BetterWitherSkeleton.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("WitherSkeleton.TrackingSpeed"), 10.0D);
/*     */               } else {
/* 166 */                 BetterWitherSkeleton.this.track(BetterWitherSkeleton.this.entity.getLocation(), 0.0F, 0.0D);
/* 167 */                 BetterWitherSkeleton.this.setBusy(false);
/* 168 */                 BetterWitherSkeleton.this.randomAttack(player, 0);
/* 169 */                 Bukkit.getScheduler().cancelTask(BetterWitherSkeleton.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void randomAttack(Player player, int delay) {
/* 178 */     setBusy(true);
/* 179 */     WitherSkeletonAttack attack = WitherSkeletonAttack.values()[this.random.nextInt((WitherSkeletonAttack.values()).length)];
/* 180 */     Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)MobAI.getInstance(), () -> { switch (attack) { case NORMAL_ATTACK: normalAttack(player); return;case WITHER_SKULL_ATTACK: witherSkullAttack(player); return;case SWORD_THROW_ATTACK: swordAttack(player); return; }  normalAttack(player); }(20 * delay));
/*     */   }
/*     */ }


/* Location:              C:\Users\cheen\Downloads\BetterMobAI-2.11 alpha.jar!\com\LucasRomier\BetterMobAI\Mobs\Entity\Nether\BetterWitherSkeleton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
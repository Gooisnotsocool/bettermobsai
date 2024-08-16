/*     */ package com.LucasRomier.BetterMobAI.Mobs.Entity.Overworld;
/*     */ import com.LucasRomier.BetterMobAI.MobAI;
/*     */ import com.LucasRomier.BetterMobAI.Mobs.Attacks.SpiderAttack;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
/*     */ import org.bukkit.craftbukkit.v1_15_R1.entity.CraftSpider;
/*     */ import org.bukkit.entity.CaveSpider;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Snowball;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ 
/*     */ public class BetterCaveSpider extends BetterMob {
/*     */   private int scheduler;
/*     */   
/*     */   public BetterCaveSpider(CaveSpider spider) {
/*  20 */     super((LivingEntity)spider);
/*     */   }
/*     */   
/*     */   public void normalAttack(final Player player) {
/*  24 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/*  25 */       setBusy(true);
/*  26 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/*  27 */             boolean b = BetterCaveSpider.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("CaveSpider.NormalAttack.Speed"), 3.0D);
/*     */             
/*     */             public void run() {
/*  30 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/*  31 */                 !player.isOnline() || BetterCaveSpider.this.entity.isDead()) {
/*  32 */                 BetterCaveSpider.this.setBusy(false);
/*  33 */                 Bukkit.getScheduler().cancelTask(BetterCaveSpider.this.scheduler);
/*     */                 return;
/*     */               } 
/*  36 */               if (!this.b) {
/*  37 */                 this.b = BetterCaveSpider.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("CaveSpider.NormalAttack.Speed"), 3.0D);
/*     */               } else {
/*  39 */                 BetterCaveSpider.this.track(BetterCaveSpider.this.entity.getLocation(), 0.0F, 0.0D);
/*  40 */                 ((CraftSpider)BetterCaveSpider.this.entity).getHandle().a(((CraftPlayer)player).getHandle());
/*  41 */                 player.damage(MobAI.settings.configuration.getDouble("CaveSpider.NormalAttack.Damage"));
/*  42 */                 BetterCaveSpider.this.randomAttack(player, MobAI.settings.configuration.getInt("CaveSpider.NormalAttack.NextAttackDelay"));
/*  43 */                 Bukkit.getScheduler().cancelTask(BetterCaveSpider.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void poisonAttack(final Player player) {
/*  51 */     if (MobAI.settings.configuration.getBoolean("CaveSpider.PoisonAttack.Disable")) {
/*  52 */       normalAttack(player);
/*     */       return;
/*     */     } 
/*  55 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/*  56 */       setBusy(true);
/*  57 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/*  58 */             boolean b = BetterCaveSpider.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("CaveSpider.PoisonAttack.Speed"), 3.0D);
/*     */             
/*     */             public void run() {
/*  61 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/*  62 */                 !player.isOnline() || BetterCaveSpider.this.entity.isDead()) {
/*  63 */                 BetterCaveSpider.this.setBusy(false);
/*  64 */                 Bukkit.getScheduler().cancelTask(BetterCaveSpider.this.scheduler);
/*     */                 return;
/*     */               } 
/*  67 */               if (!this.b) {
/*  68 */                 this.b = BetterCaveSpider.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("CaveSpider.PoisonAttack.Speed"), 3.0D);
/*     */               } else {
/*  70 */                 BetterCaveSpider.this.track(BetterCaveSpider.this.entity.getLocation(), 0.0F, 0.0D);
/*  71 */                 ((CraftSpider)BetterCaveSpider.this.entity).getHandle().a(((CraftPlayer)player).getHandle());
/*  72 */                 player.damage(MobAI.settings.configuration.getDouble("CaveSpider.PoisonAttack.Damage"));
/*  73 */                 player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20 * MobAI.settings.configuration.getInt("CaveSpider.PoisonAttack.PoisoningTime"), 1));
/*  74 */                 BetterCaveSpider.this.randomAttack(player, MobAI.settings.configuration.getInt("CaveSpider.PoisonAttack.NextAttackDelay"));
/*  75 */                 Bukkit.getScheduler().cancelTask(BetterCaveSpider.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void skyAttack(final Player player) {
/*  83 */     if (MobAI.settings.configuration.getBoolean("CaveSpider.SkyAttack.Disable")) {
/*  84 */       normalAttack(player);
/*     */       return;
/*     */     } 
/*  87 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/*  88 */       setBusy(true);
/*  89 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/*  90 */             boolean b = BetterCaveSpider.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("CaveSpider.SkyAttack.Speed"), 3.0D);
/*     */             
/*     */             public void run() {
/*  93 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/*  94 */                 !player.isOnline() || BetterCaveSpider.this.entity.isDead()) {
/*  95 */                 BetterCaveSpider.this.setBusy(false);
/*  96 */                 Bukkit.getScheduler().cancelTask(BetterCaveSpider.this.scheduler);
/*     */                 return;
/*     */               } 
/*  99 */               if (!this.b) {
/* 100 */                 this.b = BetterCaveSpider.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("CaveSpider.SkyAttack.Speed"), 3.0D);
/*     */               } else {
/* 102 */                 BetterCaveSpider.this.track(BetterCaveSpider.this.entity.getLocation(), 0.0F, 0.0D);
/* 103 */                 BetterCaveSpider.this.entity.teleport(BetterCaveSpider.this.entity.getLocation().clone().add(0.0D, 20.0D, 0.0D));
/* 104 */                 BetterCaveSpider.this.entity.setNoDamageTicks(10);
/* 105 */                 ((CraftSpider)BetterCaveSpider.this.entity).getHandle().a(((CraftPlayer)player).getHandle());
/* 106 */                 player.damage(MobAI.settings.configuration.getDouble("CaveSpider.SkyAttack.Damage"));
/* 107 */                 BetterCaveSpider.this.randomAttack(player, MobAI.settings.configuration.getInt("CaveSpider.SkyAttack.NextAttackDelay"));
/* 108 */                 Bukkit.getScheduler().cancelTask(BetterCaveSpider.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void webAttack(final Player player) {
/* 116 */     if (MobAI.settings.configuration.getBoolean("CaveSpider.WebAttack.Disable")) {
/* 117 */       normalAttack(player);
/*     */       return;
/*     */     } 
/* 120 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/* 121 */       setBusy(true);
/* 122 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 123 */             boolean b = BetterCaveSpider.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("CaveSpider.WebAttack.Speed"), 3.0D);
/*     */             
/*     */             public void run() {
/* 126 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/* 127 */                 !player.isOnline() || BetterCaveSpider.this.entity.isDead()) {
/* 128 */                 BetterCaveSpider.this.setBusy(false);
/* 129 */                 Bukkit.getScheduler().cancelTask(BetterCaveSpider.this.scheduler);
/*     */                 return;
/*     */               } 
/* 132 */               if (!this.b) {
/* 133 */                 this.b = BetterCaveSpider.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("CaveSpider.WebAttack.Speed"), 3.0D);
/*     */               } else {
/* 135 */                 BetterCaveSpider.this.track(BetterCaveSpider.this.entity.getLocation(), 0.0F, 0.0D);
/* 136 */                 Snowball snowball = (Snowball)BetterCaveSpider.this.entity.getWorld().spawnEntity(BetterCaveSpider.this.entity.getLocation().clone().add(0.0D, 0.2D, 0.0D), EntityType.SNOWBALL);
/* 137 */                 snowball.setVelocity(BetterCaveSpider.this.entity.getEyeLocation().clone().add(0.0D, 1.0D, 0.0D).subtract(player.getLocation().clone()).toVector().multiply(3));
/* 138 */                 player.getLocation().clone().add(0.0D, 1.0D, 0.0D).getBlock().setType(Material.COBWEB);
/* 139 */                 BetterCaveSpider.this.randomAttack(player, MobAI.settings.configuration.getInt("CaveSpider.WebAttack.NextAttackDelay"));
/* 140 */                 Bukkit.getScheduler().cancelTask(BetterCaveSpider.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void trackAndKill(final Player player) {
/* 149 */     if (!isBusy()) {
/* 150 */       setBusy(true);
/* 151 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 152 */             boolean b = BetterCaveSpider.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("CaveSpider.TrackingSpeed"), 10.0D);
/*     */             
/*     */             public void run() {
/* 155 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/* 156 */                 !player.isOnline() || BetterCaveSpider.this.entity.isDead()) {
/* 157 */                 BetterCaveSpider.this.setBusy(false);
/* 158 */                 Bukkit.getScheduler().cancelTask(BetterCaveSpider.this.scheduler);
/*     */                 return;
/*     */               } 
/* 161 */               if (!this.b) {
/* 162 */                 this.b = BetterCaveSpider.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("CaveSpider.TrackingSpeed"), 10.0D);
/*     */               } else {
/* 164 */                 BetterCaveSpider.this.track(BetterCaveSpider.this.entity.getLocation(), 0.0F, 0.0D);
/* 165 */                 BetterCaveSpider.this.setBusy(false);
/* 166 */                 BetterCaveSpider.this.randomAttack(player, 0);
/* 167 */                 Bukkit.getScheduler().cancelTask(BetterCaveSpider.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void randomAttack(Player player, int delay) {
/* 176 */     setBusy(true);
/* 177 */     Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)MobAI.getInstance(), () -> { SpiderAttack attack = SpiderAttack.values()[this.random.nextInt((SpiderAttack.values()).length)]; switch (attack) { case NORMAL_ATTACK: normalAttack(player); return;case POISON_ATTACK: poisonAttack(player); return;case SKY_ATTACK: skyAttack(player); return;case WEB_ATTACK: webAttack(player); return; }  normalAttack(player); }(20 * delay));
/*     */   }
/*     */ }


/* Location:              C:\Users\cheen\Downloads\BetterMobAI-2.11 alpha.jar!\com\LucasRomier\BetterMobAI\Mobs\Entity\Overworld\BetterCaveSpider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
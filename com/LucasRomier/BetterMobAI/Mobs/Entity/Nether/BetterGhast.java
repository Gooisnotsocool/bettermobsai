/*     */ package com.LucasRomier.BetterMobAI.Mobs.Entity.Nether;
/*     */ 
/*     */ import com.LucasRomier.BetterMobAI.MobAI;
/*     */ import com.LucasRomier.BetterMobAI.Mobs.Attacks.GhastAttack;
/*     */ import com.LucasRomier.BetterMobAI.Mobs.BetterMob;
/*     */ import net.minecraft.server.v1_15_R1.Entity;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.craftbukkit.v1_15_R1.entity.CraftGhast;
/*     */ import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
/*     */ import org.bukkit.entity.Ghast;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ public class BetterGhast
/*     */   extends BetterMob {
/*     */   private int scheduler;
/*     */   
/*     */   public BetterGhast(Ghast ghast) {
/*  22 */     super((LivingEntity)ghast);
/*     */   }
/*     */ 
/*     */   
/*     */   public void moveTo(Location location, float speed) {
/*  27 */     ((CraftGhast)this.entity).getHandle().getNavigation().a(location.getX(), location.getY() + 1.0D, location.getZ(), speed);
/*     */   }
/*     */   
/*     */   public void normalAttack(final Player player) {
/*  31 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/*  32 */       setBusy(true);
/*  33 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/*  34 */             boolean b = BetterGhast.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Ghast.NormalAttack.Speed"), 20.0D);
/*     */             
/*     */             public void run() {
/*  37 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/*  38 */                 !player.isOnline() || BetterGhast.this.entity.isDead()) {
/*  39 */                 BetterGhast.this.setBusy(false);
/*  40 */                 Bukkit.getScheduler().cancelTask(BetterGhast.this.scheduler);
/*     */                 return;
/*     */               } 
/*  43 */               if (!this.b) {
/*  44 */                 this.b = BetterGhast.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Ghast.NormalAttack.Speed"), 20.0D);
/*     */               } else {
/*  46 */                 if (!player.isDead() && player.isOnline()) {
/*  47 */                   ((CraftGhast)BetterGhast.this.entity).getHandle().a(((CraftPlayer)player).getHandle());
/*  48 */                   BetterGhast.this.randomAttack(player, MobAI.settings.configuration.getInt("Ghast.NormalAttack.NextAttackDelay"));
/*     */                 } else {
/*  50 */                   BetterGhast.this.setBusy(false);
/*     */                 } 
/*  52 */                 Bukkit.getScheduler().cancelTask(BetterGhast.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void highSpeedAttack(final Player player) {
/*  60 */     if (MobAI.settings.configuration.getBoolean("Ghast.HighSpeedAttack.Disable")) {
/*  61 */       normalAttack(player);
/*     */       return;
/*     */     } 
/*  64 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/*  65 */       setBusy(true);
/*  66 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/*  67 */             boolean b = BetterGhast.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Ghast.HighSpeedAttack.Speed"), 20.0D);
/*     */             
/*     */             public void run() {
/*  70 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/*  71 */                 !player.isOnline() || BetterGhast.this.entity.isDead()) {
/*  72 */                 BetterGhast.this.setBusy(false);
/*  73 */                 Bukkit.getScheduler().cancelTask(BetterGhast.this.scheduler);
/*     */                 return;
/*     */               } 
/*  76 */               if (!this.b) {
/*  77 */                 this.b = BetterGhast.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Ghast.HighSpeedAttack.Speed"), 20.0D);
/*     */               } else {
/*  79 */                 if (!player.isDead() && player.isOnline()) {
/*  80 */                   ((CraftGhast)BetterGhast.this.entity).getHandle().a((Entity)((CraftPlayer)player).getHandle(), 1.0F, 1.0F);
/*  81 */                   BetterGhast.this.randomAttack(player, MobAI.settings.configuration.getInt("Ghast.HighSpeedAttack.NextAttackDelay"));
/*     */                 } else {
/*  83 */                   BetterGhast.this.setBusy(false);
/*     */                 } 
/*  85 */                 Bukkit.getScheduler().cancelTask(BetterGhast.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void multiAttack(final Player player) {
/*  93 */     if (MobAI.settings.configuration.getBoolean("Ghast.MultiAttack.Disable")) {
/*  94 */       normalAttack(player);
/*     */       return;
/*     */     } 
/*  97 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/*  98 */       setBusy(true);
/*  99 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 100 */             boolean b = BetterGhast.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Ghast.MultiAttack.Speed"), 20.0D);
/*     */             
/*     */             public void run() {
/* 103 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/* 104 */                 !player.isOnline() || BetterGhast.this.entity.isDead()) {
/* 105 */                 BetterGhast.this.setBusy(false);
/* 106 */                 Bukkit.getScheduler().cancelTask(BetterGhast.this.scheduler);
/*     */                 return;
/*     */               } 
/* 109 */               if (!this.b) {
/* 110 */                 this.b = BetterGhast.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Ghast.MultiAttack.Speed"), 20.0D);
/*     */               } else {
/* 112 */                 if (!player.isDead() && player.isOnline()) {
/* 113 */                   for (int i = 0; i < 4; i++) {
/* 114 */                     ((CraftGhast)BetterGhast.this.entity).getHandle().a(((CraftPlayer)player).getHandle());
/*     */                   }
/* 116 */                   BetterGhast.this.randomAttack(player, MobAI.settings.configuration.getInt("Ghast.MultiAttack.NextAttackDelay"));
/*     */                 } else {
/* 118 */                   BetterGhast.this.setBusy(false);
/*     */                 } 
/* 120 */                 Bukkit.getScheduler().cancelTask(BetterGhast.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void trackAndKill(final Player player) {
/* 129 */     if (!isBusy()) {
/* 130 */       setBusy(true);
/* 131 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 132 */             boolean b = BetterGhast.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Ghast.TrackingSpeed"), 30.0D);
/*     */             
/*     */             public void run() {
/* 135 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/* 136 */                 !player.isOnline() || BetterGhast.this.entity.isDead()) {
/* 137 */                 BetterGhast.this.setBusy(false);
/* 138 */                 Bukkit.getScheduler().cancelTask(BetterGhast.this.scheduler);
/*     */                 return;
/*     */               } 
/* 141 */               if (!this.b) {
/* 142 */                 this.b = BetterGhast.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Ghast.TrackingSpeed"), 30.0D);
/*     */               } else {
/* 144 */                 BetterGhast.this.track(BetterGhast.this.entity.getLocation(), 0.0F, 0.0D);
/* 145 */                 BetterGhast.this.setBusy(false);
/* 146 */                 BetterGhast.this.randomAttack(player, 0);
/* 147 */                 Bukkit.getScheduler().cancelTask(BetterGhast.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void randomAttack(Player player, int delay) {
/* 156 */     setBusy(true);
/* 157 */     GhastAttack attack = GhastAttack.values()[this.random.nextInt((GhastAttack.values()).length)];
/* 158 */     Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)MobAI.getInstance(), () -> { switch (attack) { case NORMAL_ATTACK: normalAttack(player); return;case HIGH_SPEED_FIRE_BALL_ATTACK: highSpeedAttack(player); return;case MULTY_FIRE_BALL_ATTACK: multiAttack(player); return; }  normalAttack(player); }(20 * delay));
/*     */   }
/*     */ }


/* Location:              C:\Users\cheen\Downloads\BetterMobAI-2.11 alpha.jar!\com\LucasRomier\BetterMobAI\Mobs\Entity\Nether\BetterGhast.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
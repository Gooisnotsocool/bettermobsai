/*     */ package com.LucasRomier.BetterMobAI.Mobs.Entity.Overworld;
/*     */ import com.LucasRomier.BetterMobAI.MobAI;
/*     */ import com.LucasRomier.BetterMobAI.Mobs.Attacks.ZombieAttack;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Particle;
/*     */ import org.bukkit.entity.Bat;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Zombie;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ 
/*     */ public class BetterZombie extends BetterMob {
/*     */   private int scheduler;
/*     */   
/*     */   public BetterZombie(Zombie zombie) {
/*  22 */     super((LivingEntity)zombie);
/*     */   }
/*     */   
/*     */   public void normalAttack(final Player player) {
/*  26 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/*  27 */       setBusy(true);
/*  28 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/*  29 */             boolean b = BetterZombie.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Zombie.NormalAttack.Speed"), 1.0D);
/*     */             
/*     */             public void run() {
/*  32 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/*  33 */                 !player.isOnline() || BetterZombie.this.entity.isDead()) {
/*  34 */                 BetterZombie.this.setBusy(false);
/*  35 */                 Bukkit.getScheduler().cancelTask(BetterZombie.this.scheduler);
/*     */                 return;
/*     */               } 
/*  38 */               if (!this.b) {
/*  39 */                 this.b = BetterZombie.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Zombie.NormalAttack.Speed"), 1.0D);
/*     */               } else {
/*  41 */                 if (!player.isDead() && player.isOnline()) {
/*     */                   
/*  43 */                   player.damage(MobAI.settings.configuration.getDouble("Zombie.NormalAttack.Damage"));
/*  44 */                   BetterZombie.this.randomAttack(player, MobAI.settings.configuration.getInt("Zombie.NormalAttack.NextAttackDelay"));
/*     */                 } else {
/*  46 */                   BetterZombie.this.setBusy(false);
/*     */                 } 
/*  48 */                 Bukkit.getScheduler().cancelTask(BetterZombie.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void bloodRushAttack(final Player player) {
/*  56 */     if (MobAI.settings.configuration.getBoolean("Zombie.BloodRushAttack.Disable")) {
/*  57 */       normalAttack(player);
/*     */       return;
/*     */     } 
/*  60 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/*  61 */       setBusy(true);
/*  62 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/*  63 */             boolean b = BetterZombie.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Zombie.BloodRushAttack.Speed"), 1.0D);
/*  64 */             double i = 0.0D;
/*     */             
/*     */             public void run() {
/*  67 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/*  68 */                 !player.isOnline() || BetterZombie.this.entity.isDead()) {
/*  69 */                 BetterZombie.this.setBusy(false);
/*  70 */                 Bukkit.getScheduler().cancelTask(BetterZombie.this.scheduler);
/*     */                 return;
/*     */               } 
/*  73 */               if (!this.b) {
/*  74 */                 this.b = BetterZombie.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Zombie.BloodRushAttack.Speed"), 1.0D);
/*  75 */                 this.i += 0.19634954084936207D;
/*  76 */                 for (double j = 0.0D; j <= 6.283185307179586D; j += 0.19634954084936207D) {
/*  77 */                   for (int k = 0; k <= 1; k++) {
/*  78 */                     double x = 0.3D * (6.283185307179586D - j) * 0.5D * Math.cos(j + this.i + k * Math.PI);
/*  79 */                     double y = 0.5D * j;
/*  80 */                     double z = 0.3D * (6.283185307179586D - j) * 0.5D * Math.sin(j + this.i + k * Math.PI);
/*  81 */                     Location location = BetterZombie.this.entity.getLocation().clone().add(x, y, z);
/*  82 */                     Particle.DustOptions dustOptions = new Particle.DustOptions(Color.RED, 1.0F);
/*  83 */                     ((World)Objects.<World>requireNonNull(location.getWorld())).spawnParticle(Particle.REDSTONE, location, 0, 0.0D, 0.0D, 1.0D, dustOptions);
/*     */                   } 
/*     */                 } 
/*     */               } else {
/*  87 */                 if (!player.isDead() && player.isOnline()) {
/*     */                   
/*  89 */                   player.damage(MobAI.settings.configuration.getDouble("Zombie.BloodRushAttack.Damage"));
/*  90 */                   BetterZombie.this.randomAttack(player, MobAI.settings.configuration.getInt("Zombie.BloodRushAttack.NextAttackDelay"));
/*     */                 } else {
/*  92 */                   BetterZombie.this.setBusy(false);
/*     */                 } 
/*  94 */                 Bukkit.getScheduler().cancelTask(BetterZombie.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 2L);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void minionsAttack(final Player player) {
/* 102 */     if (MobAI.settings.configuration.getBoolean("Zombie.MinionsAttack.Disable")) {
/* 103 */       normalAttack(player);
/*     */       return;
/*     */     } 
/* 106 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/* 107 */       setBusy(true);
/* 108 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 109 */             boolean b = BetterZombie.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Zombie.MinionsAttack.Speed"), 10.0D);
/*     */             
/*     */             public void run() {
/* 112 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/* 113 */                 !player.isOnline() || BetterZombie.this.entity.isDead()) {
/* 114 */                 BetterZombie.this.setBusy(false);
/* 115 */                 Bukkit.getScheduler().cancelTask(BetterZombie.this.scheduler);
/*     */                 return;
/*     */               } 
/* 118 */               if (!this.b) {
/* 119 */                 this.b = BetterZombie.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Zombie.MinionsAttack.Speed"), 10.0D);
/*     */               } else {
/* 121 */                 if (!player.isDead() && player.isOnline()) {
/*     */                   
/* 123 */                   for (int i = 0; i < BetterZombie.this.random.nextInt(3) + 3; i++) {
/*     */                     
/* 125 */                     Location location = BetterZombie.this.entity.getLocation().clone().add((BetterZombie.this.random.nextInt(6) - 3), 0.0D, ((new Random()).nextInt(6) - 3));
/* 126 */                     Zombie zombie = (Zombie)BetterZombie.this.entity.getWorld().spawnEntity(location, EntityType.ZOMBIE);
/* 127 */                     ((CraftZombie)zombie).getHandle().setBaby(true);
/*     */                   } 
/* 129 */                   BetterZombie.this.setBusy(false);
/* 130 */                   BetterZombie.this.entity.remove();
/*     */                 } else {
/* 132 */                   BetterZombie.this.setBusy(false);
/*     */                 } 
/* 134 */                 Bukkit.getScheduler().cancelTask(BetterZombie.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void vampireAttack(final Player player) {
/* 142 */     if (MobAI.settings.configuration.getBoolean("Zombie.VampireAttack.Disable")) {
/* 143 */       normalAttack(player);
/*     */       return;
/*     */     } 
/* 146 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/* 147 */       setBusy(true);
/* 148 */       final List<Bat> bats = new ArrayList<>();
/* 149 */       for (int i = 0; i < 5; i++) {
/*     */         
/* 151 */         Location location = this.entity.getLocation().clone().add((this.random.nextInt(6) - 3), 0.0D, ((new Random()).nextInt(6) - 3));
/* 152 */         Bat bat = (Bat)this.entity.getWorld().spawnEntity(location, EntityType.BAT);
/* 153 */         bat.setNoDamageTicks(400);
/* 154 */         bats.add(bat);
/*     */       } 
/* 156 */       this.entity.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100000, 1, true));
/* 157 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 158 */             boolean b = BetterZombie.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Zombie.VampireAttack.Speed"), 1.0D);
/*     */             
/*     */             public void run() {
/* 161 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/* 162 */                 !player.isOnline() || BetterZombie.this.entity.isDead()) {
/* 163 */                 BetterZombie.this.setBusy(false);
/* 164 */                 BetterZombie.this.entity.removePotionEffect(PotionEffectType.INVISIBILITY);
/* 165 */                 Bukkit.getScheduler().cancelTask(BetterZombie.this.scheduler);
/*     */                 return;
/*     */               } 
/* 168 */               if (!this.b) {
/* 169 */                 this.b = BetterZombie.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Zombie.VampireAttack.Speed"), 1.0D);
/*     */               } else {
/* 171 */                 BetterZombie.this.entity.removePotionEffect(PotionEffectType.INVISIBILITY);
/* 172 */                 for (int i = 0; i < 5; i++) {
/* 173 */                   ((Bat)bats.get(i)).damage(100000.0D);
/*     */                   
/* 175 */                   Location location = BetterZombie.this.entity.getLocation().clone().add((BetterZombie.this.random.nextInt(6) - 3), 0.0D, ((new Random()).nextInt(6) - 3));
/* 176 */                   Bat bat = (Bat)BetterZombie.this.entity.getWorld().spawnEntity(location, EntityType.BAT);
/* 177 */                   bat.setNoDamageTicks(400);
/*     */                 } 
/* 179 */                 if (!player.isDead() && player.isOnline()) {
/*     */                   
/* 181 */                   player.damage(MobAI.settings.configuration.getDouble("Zombie.VampireAttack.Damage"));
/* 182 */                   BetterZombie.this.randomAttack(player, MobAI.settings.configuration.getInt("Zombie.VampireAttack.NextAttackDelay"));
/*     */                 } else {
/* 184 */                   BetterZombie.this.setBusy(false);
/*     */                 } 
/* 186 */                 Bukkit.getScheduler().cancelTask(BetterZombie.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void trackAndKill(final Player player) {
/* 195 */     if (!isBusy()) {
/* 196 */       setBusy(true);
/* 197 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 198 */             boolean b = BetterZombie.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Zombie.TrackingSpeed"), 10.0D);
/*     */             
/*     */             public void run() {
/* 201 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/* 202 */                 !player.isOnline() || BetterZombie.this.entity.isDead()) {
/* 203 */                 BetterZombie.this.setBusy(false);
/* 204 */                 Bukkit.getScheduler().cancelTask(BetterZombie.this.scheduler);
/*     */                 return;
/*     */               } 
/* 207 */               if (!this.b) {
/* 208 */                 this.b = BetterZombie.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Zombie.TrackingSpeed"), 10.0D);
/*     */               } else {
/* 210 */                 BetterZombie.this.track(BetterZombie.this.entity.getLocation(), 0.0F, 0.0D);
/* 211 */                 BetterZombie.this.setBusy(false);
/* 212 */                 BetterZombie.this.randomAttack(player, 0);
/* 213 */                 Bukkit.getScheduler().cancelTask(BetterZombie.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void randomAttack(Player player, int delay) {
/* 222 */     setBusy(true);
/* 223 */     ZombieAttack attack = ZombieAttack.values()[this.random.nextInt((ZombieAttack.values()).length)];
/* 224 */     Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)MobAI.getInstance(), () -> { switch (attack) { case NORMAL_ATTACK: normalAttack(player); return;case BLOOD_RUSH_ATTACK: bloodRushAttack(player); return;case MINIONS_ATTACK: minionsAttack(player); return;case VAMPIRE_ATTACK: vampireAttack(player); return; }  normalAttack(player); }(20 * delay));
/*     */   }
/*     */ }


/* Location:              C:\Users\cheen\Downloads\BetterMobAI-2.11 alpha.jar!\com\LucasRomier\BetterMobAI\Mobs\Entity\Overworld\BetterZombie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
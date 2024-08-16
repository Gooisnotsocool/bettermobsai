/*     */ package com.LucasRomier.BetterMobAI.Mobs.Entity.Overworld;
/*     */ import com.LucasRomier.BetterMobAI.MobAI;
/*     */ import com.LucasRomier.BetterMobAI.Mobs.Attacks.WitchAttack;
/*     */ import java.util.Objects;
/*     */ import java.util.Random;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Particle;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ 
/*     */ public class BetterWitch extends BetterMob {
/*     */   private int scheduler;
/*     */   private int secondary;
/*     */   
/*     */   public BetterWitch(Witch witch) {
/*  23 */     super((LivingEntity)witch);
/*     */   }
/*     */ 
/*     */   
/*     */   public void normalAttack(final Player player) {
/*  28 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/*  29 */       setBusy(true);
/*  30 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/*  31 */             boolean b = BetterWitch.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Witch.NormalAttack.Speed"), 15.0D);
/*     */             
/*     */             public void run() {
/*  34 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/*  35 */                 !player.isOnline() || BetterWitch.this.entity.isDead()) {
/*  36 */                 BetterWitch.this.setBusy(false);
/*  37 */                 Bukkit.getScheduler().cancelTask(BetterWitch.this.scheduler);
/*     */                 return;
/*     */               } 
/*  40 */               if (!this.b) {
/*  41 */                 this.b = BetterWitch.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Witch.NormalAttack.Speed"), 15.0D);
/*     */               } else {
/*  43 */                 BetterWitch.this.track(BetterWitch.this.entity.getLocation(), 0.0F, 0.0D);
/*  44 */                 BetterWitch.this.particleNormalAttack(player);
/*  45 */                 Bukkit.getScheduler().cancelTask(BetterWitch.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void particleNormalAttack(final Player player) {
/*  53 */     this.entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20000, 100, true));
/*  54 */     final double health = this.entity.getHealth();
/*  55 */     this.secondary = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/*  56 */           double i = 0.0D;
/*  57 */           double r = 1.0D;
/*  58 */           int j = 0;
/*     */           
/*     */           public void run() {
/*  61 */             if (BetterWitch.this.entity.getHealth() < health || player.isDead() || !player.isOnline()) {
/*  62 */               BetterWitch.this.entity.removePotionEffect(PotionEffectType.SLOW);
/*  63 */               BetterWitch.this.randomAttack(player, 0);
/*  64 */               Bukkit.getScheduler().cancelTask(BetterWitch.this.secondary);
/*  65 */             } else if (this.j < 20) {
/*  66 */               if (this.i > 3.0D) this.i = 0.0D;
/*     */               
/*  68 */               this.i += 0.19634954084936207D;
/*  69 */               double x = this.r * Math.cos(this.i);
/*  70 */               double y = 0.5D * this.i;
/*  71 */               double z = this.r * Math.sin(this.i);
/*  72 */               Location loc1 = BetterWitch.this.entity.getLocation().clone().add(x, y, z);
/*  73 */               Location loc2 = BetterWitch.this.entity.getLocation().clone().add(-x, y, -z);
/*  74 */               Location loc3 = BetterWitch.this.entity.getLocation().clone().add(-x, y, z);
/*  75 */               Location loc4 = BetterWitch.this.entity.getLocation().clone().add(x, y, -z);
/*  76 */               BetterWitch.this.entity.getWorld().spawnParticle(Particle.MOB_APPEARANCE, loc1, 0, 0.0D, 0.0D, 0.0D, 1.0D);
/*  77 */               BetterWitch.this.entity.getWorld().spawnParticle(Particle.MOB_APPEARANCE, loc2, 0, 0.0D, 0.0D, 0.0D, 1.0D);
/*  78 */               BetterWitch.this.entity.getWorld().spawnParticle(Particle.MOB_APPEARANCE, loc3, 0, 0.0D, 0.0D, 0.0D, 1.0D);
/*  79 */               BetterWitch.this.entity.getWorld().spawnParticle(Particle.MOB_APPEARANCE, loc4, 0, 0.0D, 0.0D, 0.0D, 1.0D);
/*  80 */               this.j++;
/*     */             } else {
/*  82 */               BetterWitch.this.entity.removePotionEffect(PotionEffectType.SLOW);
/*  83 */               player.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 1, 1));
/*  84 */               BetterWitch.this.track(BetterWitch.this.entity.getLocation(), 0.0F, 0.0D);
/*  85 */               BetterWitch.this.randomAttack(player, MobAI.settings.configuration.getInt("Witch.NormalAttack.NextAttackDelay"));
/*  86 */               Bukkit.getScheduler().cancelTask(BetterWitch.this.secondary);
/*     */             } 
/*     */           }
/*     */         }0L, 2L);
/*     */   }
/*     */ 
/*     */   
/*     */   public void blackMagicAttack(final Player player) {
/*  94 */     if (MobAI.settings.configuration.getBoolean("Witch.BlackMagicAttack.Disable")) {
/*  95 */       normalAttack(player);
/*     */       return;
/*     */     } 
/*  98 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/*  99 */       setBusy(true);
/* 100 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 101 */             boolean b = BetterWitch.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Witch.BlackMagicAttack.Speed"), 15.0D);
/*     */             
/*     */             public void run() {
/* 104 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/* 105 */                 !player.isOnline() || BetterWitch.this.entity.isDead()) {
/* 106 */                 BetterWitch.this.setBusy(false);
/* 107 */                 Bukkit.getScheduler().cancelTask(BetterWitch.this.scheduler);
/*     */                 return;
/*     */               } 
/* 110 */               if (!this.b) {
/* 111 */                 this.b = BetterWitch.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Witch.BlackMagicAttack.Speed"), 15.0D);
/*     */               } else {
/* 113 */                 BetterWitch.this.track(BetterWitch.this.entity.getLocation(), 0.0F, 0.0D);
/* 114 */                 BetterWitch.this.particleBlackMagicAttack(player);
/* 115 */                 Bukkit.getScheduler().cancelTask(BetterWitch.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void particleBlackMagicAttack(final Player player) {
/* 123 */     this.entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20000, 100, true));
/* 124 */     final double health = this.entity.getHealth();
/* 125 */     this.secondary = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 126 */           double i = 0.0D;
/* 127 */           double r = 1.0D;
/* 128 */           int j = 0;
/*     */           
/*     */           public void run() {
/* 131 */             if (BetterWitch.this.entity.getHealth() < health || player.isDead() || !player.isOnline()) {
/* 132 */               BetterWitch.this.entity.removePotionEffect(PotionEffectType.SLOW);
/* 133 */               BetterWitch.this.randomAttack(player, 0);
/* 134 */               Bukkit.getScheduler().cancelTask(BetterWitch.this.secondary);
/* 135 */             } else if (this.j < 20) {
/* 136 */               if (this.i > 3.0D) this.i = 0.0D;
/*     */               
/* 138 */               this.i += 0.19634954084936207D;
/* 139 */               double x = this.r * Math.cos(this.i);
/* 140 */               double y = 0.5D * this.i;
/* 141 */               double z = this.r * Math.sin(this.i);
/* 142 */               Location loc1 = BetterWitch.this.entity.getLocation().clone().add(x, y, z);
/* 143 */               Location loc2 = BetterWitch.this.entity.getLocation().clone().add(-x, y, -z);
/* 144 */               Location loc3 = BetterWitch.this.entity.getLocation().clone().add(-x, y, z);
/* 145 */               Location loc4 = BetterWitch.this.entity.getLocation().clone().add(x, y, -z);
/* 146 */               BetterWitch.this.entity.getWorld().spawnParticle(Particle.PORTAL, loc1, 0, 0.0D, 0.0D, 0.0D, 10.0D);
/* 147 */               BetterWitch.this.entity.getWorld().spawnParticle(Particle.PORTAL, loc2, 0, 0.0D, 0.0D, 0.0D, 10.0D);
/* 148 */               BetterWitch.this.entity.getWorld().spawnParticle(Particle.PORTAL, loc3, 0, 0.0D, 0.0D, 0.0D, 10.0D);
/* 149 */               BetterWitch.this.entity.getWorld().spawnParticle(Particle.PORTAL, loc4, 0, 0.0D, 0.0D, 0.0D, 10.0D);
/* 150 */               this.j++;
/*     */             } else {
/* 152 */               BetterWitch.this.entity.removePotionEffect(PotionEffectType.SLOW);
/* 153 */               player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 20 * MobAI.settings.configuration.getInt("Witch.BlackMagicAttack.EffectLength"), 1));
/* 154 */               BetterWitch.this.track(BetterWitch.this.entity.getLocation(), 0.0F, 0.0D);
/* 155 */               BetterWitch.this.randomAttack(player, MobAI.settings.configuration.getInt("Witch.BlackMagicAttack.NextAttackDelay"));
/* 156 */               Bukkit.getScheduler().cancelTask(BetterWitch.this.secondary);
/*     */             } 
/*     */           }
/*     */         }0L, 2L);
/*     */   }
/*     */ 
/*     */   
/*     */   public void burnPlayerAttack(final Player player) {
/* 164 */     if (MobAI.settings.configuration.getBoolean("Witch.FireAttack.Disable")) {
/* 165 */       normalAttack(player);
/*     */       return;
/*     */     } 
/* 168 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/* 169 */       setBusy(true);
/* 170 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 171 */             boolean b = BetterWitch.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Witch.FireAttack.Speed"), 15.0D);
/*     */             
/*     */             public void run() {
/* 174 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/* 175 */                 !player.isOnline() || BetterWitch.this.entity.isDead()) {
/* 176 */                 BetterWitch.this.setBusy(false);
/* 177 */                 Bukkit.getScheduler().cancelTask(BetterWitch.this.scheduler);
/*     */                 return;
/*     */               } 
/* 180 */               if (!this.b) {
/* 181 */                 this.b = BetterWitch.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Witch.FireAttack.Speed"), 15.0D);
/*     */               } else {
/* 183 */                 BetterWitch.this.track(BetterWitch.this.entity.getLocation(), 0.0F, 0.0D);
/* 184 */                 BetterWitch.this.particleBurnAttack(player);
/* 185 */                 Bukkit.getScheduler().cancelTask(BetterWitch.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void particleBurnAttack(final Player player) {
/* 193 */     this.entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20000, 100, true));
/* 194 */     final double health = this.entity.getHealth();
/* 195 */     this.secondary = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 196 */           double i = 0.0D;
/* 197 */           double r = 1.0D;
/* 198 */           int j = 0;
/*     */           
/*     */           public void run() {
/* 201 */             if (BetterWitch.this.entity.getHealth() < health || player.isDead() || !player.isOnline()) {
/* 202 */               BetterWitch.this.entity.removePotionEffect(PotionEffectType.SLOW);
/* 203 */               BetterWitch.this.randomAttack(player, 0);
/* 204 */               Bukkit.getScheduler().cancelTask(BetterWitch.this.secondary);
/* 205 */             } else if (this.j < 20) {
/* 206 */               if (this.i > 3.0D) this.i = 0.0D;
/*     */               
/* 208 */               this.i += 0.19634954084936207D;
/* 209 */               double x = this.r * Math.cos(this.i);
/* 210 */               double y = 0.5D * this.i;
/* 211 */               double z = this.r * Math.sin(this.i);
/* 212 */               Location loc1 = BetterWitch.this.entity.getLocation().clone().add(x, y, z);
/* 213 */               Location loc2 = BetterWitch.this.entity.getLocation().clone().add(-x, y, -z);
/* 214 */               Location loc3 = BetterWitch.this.entity.getLocation().clone().add(-x, y, z);
/* 215 */               Location loc4 = BetterWitch.this.entity.getLocation().clone().add(x, y, -z);
/* 216 */               BetterWitch.this.entity.getWorld().spawnParticle(Particle.SMOKE_LARGE, loc1, 0, 0.0D, 0.0D, 0.0D, 5.0D);
/* 217 */               BetterWitch.this.entity.getWorld().spawnParticle(Particle.SMOKE_LARGE, loc2, 0, 0.0D, 0.0D, 0.0D, 5.0D);
/* 218 */               BetterWitch.this.entity.getWorld().spawnParticle(Particle.SMOKE_LARGE, loc3, 0, 0.0D, 0.0D, 0.0D, 5.0D);
/* 219 */               BetterWitch.this.entity.getWorld().spawnParticle(Particle.SMOKE_LARGE, loc4, 0, 0.0D, 0.0D, 0.0D, 5.0D);
/* 220 */               this.j++;
/*     */             } else {
/* 222 */               BetterWitch.this.entity.removePotionEffect(PotionEffectType.SLOW);
/* 223 */               player.setFireTicks(10 * MobAI.settings.configuration.getInt("Witch.FireAttack.EffectLength"));
/* 224 */               BetterWitch.this.track(BetterWitch.this.entity.getLocation(), 0.0F, 0.0D);
/* 225 */               BetterWitch.this.randomAttack(player, MobAI.settings.configuration.getInt("Witch.FireAttack.NextAttackDelay"));
/* 226 */               Bukkit.getScheduler().cancelTask(BetterWitch.this.secondary);
/*     */             } 
/*     */           }
/*     */         }0L, 2L);
/*     */   }
/*     */ 
/*     */   
/*     */   public void fireCircleAttack(final Player player) {
/* 234 */     if (MobAI.settings.configuration.getBoolean("Witch.FireCircleAttack.Disable")) {
/* 235 */       normalAttack(player);
/*     */       return;
/*     */     } 
/* 238 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/* 239 */       setBusy(true);
/* 240 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 241 */             boolean b = BetterWitch.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Witch.FireCircleAttack.Speed"), 15.0D);
/*     */             
/*     */             public void run() {
/* 244 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/* 245 */                 !player.isOnline() || BetterWitch.this.entity.isDead()) {
/* 246 */                 BetterWitch.this.setBusy(false);
/* 247 */                 Bukkit.getScheduler().cancelTask(BetterWitch.this.scheduler);
/*     */                 return;
/*     */               } 
/* 250 */               if (!this.b) {
/* 251 */                 this.b = BetterWitch.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Witch.FireCircleAttack.Speed"), 15.0D);
/*     */               } else {
/* 253 */                 BetterWitch.this.track(BetterWitch.this.entity.getLocation(), 0.0F, 0.0D);
/* 254 */                 BetterWitch.this.particleFireCircleAttack(player);
/* 255 */                 Bukkit.getScheduler().cancelTask(BetterWitch.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void particleFireCircleAttack(final Player player) {
/* 263 */     this.entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20000, 100, true));
/* 264 */     final double health = this.entity.getHealth();
/* 265 */     this.secondary = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 266 */           double i = 0.0D;
/* 267 */           double r = 1.0D;
/* 268 */           int j = 0;
/*     */           
/*     */           public void run() {
/* 271 */             if (BetterWitch.this.entity.getHealth() < health || player.isDead() || !player.isOnline()) {
/* 272 */               BetterWitch.this.entity.removePotionEffect(PotionEffectType.SLOW);
/* 273 */               BetterWitch.this.randomAttack(player, 0);
/* 274 */               Bukkit.getScheduler().cancelTask(BetterWitch.this.secondary);
/* 275 */             } else if (this.j < 20) {
/* 276 */               if (this.i > 3.0D) this.i = 0.0D;
/*     */               
/* 278 */               this.i += 0.19634954084936207D;
/* 279 */               double x = this.r * Math.cos(this.i);
/* 280 */               double y = 0.5D * this.i;
/* 281 */               double z = this.r * Math.sin(this.i);
/* 282 */               Location loc1 = BetterWitch.this.entity.getLocation().clone().add(x, y, z);
/* 283 */               Location loc2 = BetterWitch.this.entity.getLocation().clone().add(-x, y, -z);
/* 284 */               Location loc3 = BetterWitch.this.entity.getLocation().clone().add(-x, y, z);
/* 285 */               Location loc4 = BetterWitch.this.entity.getLocation().clone().add(x, y, -z);
/* 286 */               BetterWitch.this.entity.getWorld().spawnParticle(Particle.FLAME, loc1, 0, 0.0D, 0.0D, 0.0D, 1.0D);
/* 287 */               BetterWitch.this.entity.getWorld().spawnParticle(Particle.FLAME, loc2, 0, 0.0D, 0.0D, 0.0D, 1.0D);
/* 288 */               BetterWitch.this.entity.getWorld().spawnParticle(Particle.FLAME, loc3, 0, 0.0D, 0.0D, 0.0D, 1.0D);
/* 289 */               BetterWitch.this.entity.getWorld().spawnParticle(Particle.FLAME, loc4, 0, 0.0D, 0.0D, 0.0D, 1.0D);
/* 290 */               this.j++;
/*     */             } else {
/* 292 */               BetterWitch.this.entity.removePotionEffect(PotionEffectType.SLOW);
/*     */               
/* 294 */               for (int i = 4; i < 4 + MobAI.settings.configuration.getInt("Witch.FireCircleAttack.Radius"); i++) {
/* 295 */                 for (int x = player.getLocation().getBlockX() - i; x < player.getLocation().getBlockX() + i; x++) {
/* 296 */                   ((World)Objects.<World>requireNonNull(player.getLocation().getWorld())).getHighestBlockAt(x, player.getLocation().getBlockZ() - i).setType(Material.FIRE);
/* 297 */                   player.getLocation().getWorld().getHighestBlockAt(x, player.getLocation().getBlockZ() + i).setType(Material.FIRE);
/*     */                 } 
/* 299 */                 for (int z = player.getLocation().getBlockZ() - i; z < player.getLocation().getBlockZ() + i; z++) {
/* 300 */                   ((World)Objects.<World>requireNonNull(player.getLocation().getWorld())).getHighestBlockAt(player.getLocation().getBlockX() - i, z).setType(Material.FIRE);
/* 301 */                   player.getLocation().getWorld().getHighestBlockAt(player.getLocation().getBlockX() + i, z).setType(Material.FIRE);
/*     */                 } 
/*     */               } 
/*     */               
/* 305 */               BetterWitch.this.randomAttack(player, MobAI.settings.configuration.getInt("Witch.FireCircleAttack.NextAttackDelay"));
/* 306 */               Bukkit.getScheduler().cancelTask(BetterWitch.this.secondary);
/*     */             } 
/*     */           }
/*     */         }0L, 2L);
/*     */   }
/*     */ 
/*     */   
/*     */   public void lavaAttack(final Player player) {
/* 314 */     if (MobAI.settings.configuration.getBoolean("Witch.LavaAttack.Disable")) {
/* 315 */       normalAttack(player);
/*     */       return;
/*     */     } 
/* 318 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/* 319 */       setBusy(true);
/* 320 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 321 */             boolean b = BetterWitch.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Witch.LavaAttack.Speed"), 15.0D);
/*     */             
/*     */             public void run() {
/* 324 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/* 325 */                 !player.isOnline() || BetterWitch.this.entity.isDead()) {
/* 326 */                 BetterWitch.this.setBusy(false);
/* 327 */                 Bukkit.getScheduler().cancelTask(BetterWitch.this.scheduler);
/*     */                 return;
/*     */               } 
/* 330 */               if (!this.b) {
/* 331 */                 this.b = BetterWitch.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Witch.LavaAttack.Speed"), 15.0D);
/*     */               } else {
/* 333 */                 BetterWitch.this.track(BetterWitch.this.entity.getLocation(), 0.0F, 0.0D);
/* 334 */                 BetterWitch.this.particleLavaAttack(player);
/* 335 */                 Bukkit.getScheduler().cancelTask(BetterWitch.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void particleLavaAttack(final Player player) {
/* 343 */     this.entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20000, 100, true));
/* 344 */     final double health = this.entity.getHealth();
/* 345 */     this.secondary = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 346 */           double i = 0.0D;
/* 347 */           double r = 1.0D;
/* 348 */           int j = 0;
/*     */           
/*     */           public void run() {
/* 351 */             if (BetterWitch.this.entity.getHealth() < health || player.isDead() || !player.isOnline()) {
/* 352 */               BetterWitch.this.entity.removePotionEffect(PotionEffectType.SLOW);
/* 353 */               BetterWitch.this.randomAttack(player, 0);
/* 354 */               Bukkit.getScheduler().cancelTask(BetterWitch.this.secondary);
/* 355 */             } else if (this.j < 20) {
/* 356 */               if (this.i > 3.0D) this.i = 0.0D;
/*     */               
/* 358 */               this.i += 0.19634954084936207D;
/* 359 */               double x = this.r * Math.cos(this.i);
/* 360 */               double y = 0.5D * this.i;
/* 361 */               double z = this.r * Math.sin(this.i);
/* 362 */               Location loc1 = BetterWitch.this.entity.getLocation().clone().add(x, y, z);
/* 363 */               Location loc2 = BetterWitch.this.entity.getLocation().clone().add(-x, y, -z);
/* 364 */               Location loc3 = BetterWitch.this.entity.getLocation().clone().add(-x, y, z);
/* 365 */               Location loc4 = BetterWitch.this.entity.getLocation().clone().add(x, y, -z);
/* 366 */               BetterWitch.this.entity.getWorld().spawnParticle(Particle.DRIP_LAVA, loc1, 0, 0.0D, 0.0D, 0.0D, 1.0D);
/* 367 */               BetterWitch.this.entity.getWorld().spawnParticle(Particle.DRIP_LAVA, loc2, 0, 0.0D, 0.0D, 0.0D, 1.0D);
/* 368 */               BetterWitch.this.entity.getWorld().spawnParticle(Particle.DRIP_LAVA, loc3, 0, 0.0D, 0.0D, 0.0D, 1.0D);
/* 369 */               BetterWitch.this.entity.getWorld().spawnParticle(Particle.DRIP_LAVA, loc4, 0, 0.0D, 0.0D, 0.0D, 1.0D);
/* 370 */               this.j++;
/*     */             } else {
/* 372 */               BetterWitch.this.entity.removePotionEffect(PotionEffectType.SLOW);
/*     */               
/* 374 */               Material material = player.getEyeLocation().getBlock().getType();
/* 375 */               player.getEyeLocation().getBlock().setType(Material.LAVA);
/* 376 */               player.getEyeLocation().getBlock().setType(material);
/* 377 */               player.setFireTicks(50);
/*     */               
/* 379 */               BetterWitch.this.track(BetterWitch.this.entity.getLocation(), 0.0F, 0.0D);
/* 380 */               BetterWitch.this.randomAttack(player, MobAI.settings.configuration.getInt("Witch.LavaAttack.NextAttackDelay"));
/* 381 */               Bukkit.getScheduler().cancelTask(BetterWitch.this.secondary);
/*     */             } 
/*     */           }
/*     */         }0L, 2L);
/*     */   }
/*     */ 
/*     */   
/*     */   public void poisonAttack(final Player player) {
/* 389 */     if (MobAI.settings.configuration.getBoolean("Witch.PoisonAttack.Disable")) {
/* 390 */       normalAttack(player);
/*     */       return;
/*     */     } 
/* 393 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/* 394 */       setBusy(true);
/* 395 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 396 */             boolean b = BetterWitch.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Witch.PoisonAttack.Speed"), 15.0D);
/*     */             
/*     */             public void run() {
/* 399 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/* 400 */                 !player.isOnline() || BetterWitch.this.entity.isDead()) {
/* 401 */                 BetterWitch.this.setBusy(false);
/* 402 */                 Bukkit.getScheduler().cancelTask(BetterWitch.this.scheduler);
/*     */                 return;
/*     */               } 
/* 405 */               if (!this.b) {
/* 406 */                 this.b = BetterWitch.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Witch.PoisonAttack.Speed"), 15.0D);
/*     */               } else {
/* 408 */                 BetterWitch.this.track(BetterWitch.this.entity.getLocation(), 0.0F, 0.0D);
/* 409 */                 BetterWitch.this.particlePoisonAttack(player);
/* 410 */                 Bukkit.getScheduler().cancelTask(BetterWitch.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void particlePoisonAttack(final Player player) {
/* 418 */     this.entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20000, 100, true));
/* 419 */     final double health = this.entity.getHealth();
/* 420 */     this.secondary = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 421 */           double i = 0.39269908169872414D;
/* 422 */           int j = 0;
/*     */           
/*     */           public void run() {
/* 425 */             if (BetterWitch.this.entity.getHealth() < health || player.isDead() || !player.isOnline()) {
/* 426 */               BetterWitch.this.entity.removePotionEffect(PotionEffectType.SLOW);
/* 427 */               BetterWitch.this.randomAttack(player, 0);
/* 428 */               Bukkit.getScheduler().cancelTask(BetterWitch.this.secondary);
/* 429 */             } else if (this.j < 20) {
/* 430 */               this.i += 0.3141592653589793D;
/*     */               
/* 432 */               for (double alpha = 0.0D; alpha <= 6.283185307179586D; alpha += 0.09817477042468103D) {
/* 433 */                 double x = this.i * Math.cos(alpha);
/* 434 */                 double y = 2.0D * Math.exp(-0.1D * this.i) * Math.sin(this.i) + 1.5D;
/* 435 */                 double z = this.i * Math.sin(alpha);
/*     */                 
/* 437 */                 Location loc1 = BetterWitch.this.entity.getLocation().clone().add(x, y, z);
/* 438 */                 BetterWitch.this.entity.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, loc1, 0, 0.0D, 0.0D, 0.0D, 1.0D);
/*     */               } 
/* 440 */               this.j++;
/*     */             } else {
/* 442 */               BetterWitch.this.entity.removePotionEffect(PotionEffectType.SLOW);
/* 443 */               player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20 * MobAI.settings.configuration.getInt("Witch.PoisonAttack.EffectLength"), 1));
/* 444 */               BetterWitch.this.track(BetterWitch.this.entity.getLocation(), 0.0F, 0.0D);
/* 445 */               BetterWitch.this.randomAttack(player, MobAI.settings.configuration.getInt("Witch.PoisonAttack.NextAttackDelay"));
/* 446 */               Bukkit.getScheduler().cancelTask(BetterWitch.this.secondary);
/*     */             } 
/*     */           }
/*     */         }0L, 2L);
/*     */   }
/*     */ 
/*     */   
/*     */   public void slownessAttack(final Player player) {
/* 454 */     if (MobAI.settings.configuration.getBoolean("Witch.SlownessAttack.Disable")) {
/* 455 */       normalAttack(player);
/*     */       return;
/*     */     } 
/* 458 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/* 459 */       setBusy(true);
/* 460 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 461 */             boolean b = BetterWitch.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Witch.SlownessAttack.Speed"), 15.0D);
/*     */             
/*     */             public void run() {
/* 464 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/* 465 */                 !player.isOnline() || BetterWitch.this.entity.isDead()) {
/* 466 */                 BetterWitch.this.setBusy(false);
/* 467 */                 Bukkit.getScheduler().cancelTask(BetterWitch.this.scheduler);
/*     */                 return;
/*     */               } 
/* 470 */               if (!this.b) {
/* 471 */                 this.b = BetterWitch.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Witch.SlownessAttack.Speed"), 15.0D);
/*     */               } else {
/* 473 */                 BetterWitch.this.track(BetterWitch.this.entity.getLocation(), 0.0F, 0.0D);
/* 474 */                 BetterWitch.this.particleSlownessAttack(player);
/* 475 */                 Bukkit.getScheduler().cancelTask(BetterWitch.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void particleSlownessAttack(final Player player) {
/* 483 */     this.entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20000, 100, true));
/* 484 */     final double health = this.entity.getHealth();
/* 485 */     this.secondary = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 486 */           double i = 0.39269908169872414D;
/* 487 */           int j = 0;
/*     */           
/*     */           public void run() {
/* 490 */             if (BetterWitch.this.entity.getHealth() < health || player.isDead() || !player.isOnline()) {
/* 491 */               BetterWitch.this.entity.removePotionEffect(PotionEffectType.SLOW);
/* 492 */               BetterWitch.this.randomAttack(player, 0);
/* 493 */               Bukkit.getScheduler().cancelTask(BetterWitch.this.secondary);
/* 494 */             } else if (this.j < 20) {
/* 495 */               this.i += 0.3141592653589793D;
/*     */               
/* 497 */               for (double alpha = 0.0D; alpha <= 6.283185307179586D; alpha += 0.39269908169872414D) {
/* 498 */                 double x = this.i * Math.cos(alpha);
/* 499 */                 double y = 2.0D * Math.exp(-0.1D * this.i) * Math.sin(this.i) + 1.5D;
/* 500 */                 double z = this.i * Math.sin(alpha);
/*     */                 
/* 502 */                 Location loc1 = BetterWitch.this.entity.getLocation().clone().add(x, y, z);
/* 503 */                 BetterWitch.this.entity.getWorld().spawnParticle(Particle.SNOW_SHOVEL, loc1, 0, 0.0D, 0.0D, 0.0D, 1.0D);
/*     */               } 
/* 505 */               this.j++;
/*     */             } else {
/* 507 */               BetterWitch.this.entity.removePotionEffect(PotionEffectType.SLOW);
/* 508 */               player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * MobAI.settings.configuration.getInt("Witch.SlownessAttack.EffectLength"), 5));
/* 509 */               BetterWitch.this.track(BetterWitch.this.entity.getLocation(), 0.0F, 0.0D);
/* 510 */               BetterWitch.this.randomAttack(player, MobAI.settings.configuration.getInt("Witch.SlownessAttack.NextAttackDelay"));
/* 511 */               Bukkit.getScheduler().cancelTask(BetterWitch.this.secondary);
/*     */             } 
/*     */           }
/*     */         }0L, 2L);
/*     */   }
/*     */ 
/*     */   
/*     */   public void endermitesAttack(final Player player) {
/* 519 */     if (MobAI.settings.configuration.getBoolean("Witch.EndermitesAttack.Disable")) {
/* 520 */       normalAttack(player);
/*     */       return;
/*     */     } 
/* 523 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/* 524 */       setBusy(true);
/* 525 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 526 */             boolean b = BetterWitch.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Witch.EndermitesAttack.Speed"), 15.0D);
/*     */             
/*     */             public void run() {
/* 529 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/* 530 */                 !player.isOnline() || BetterWitch.this.entity.isDead()) {
/* 531 */                 BetterWitch.this.setBusy(false);
/* 532 */                 Bukkit.getScheduler().cancelTask(BetterWitch.this.scheduler);
/*     */                 return;
/*     */               } 
/* 535 */               if (!this.b) {
/* 536 */                 this.b = BetterWitch.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Witch.EndermitesAttack.Speed"), 15.0D);
/*     */               } else {
/* 538 */                 BetterWitch.this.track(BetterWitch.this.entity.getLocation(), 0.0F, 0.0D);
/* 539 */                 BetterWitch.this.endermitesAttackParticle(player);
/* 540 */                 Bukkit.getScheduler().cancelTask(BetterWitch.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void endermitesAttackParticle(final Player player) {
/* 548 */     this.entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20000, 100, true));
/* 549 */     final double health = this.entity.getHealth();
/* 550 */     this.secondary = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 551 */           double i = 0.0D;
/* 552 */           double r = 1.0D;
/* 553 */           int j = 0;
/*     */           
/*     */           public void run() {
/* 556 */             if (BetterWitch.this.entity.getHealth() < health || player.isDead() || !player.isOnline()) {
/* 557 */               BetterWitch.this.entity.removePotionEffect(PotionEffectType.SLOW);
/* 558 */               BetterWitch.this.randomAttack(player, 0);
/* 559 */               Bukkit.getScheduler().cancelTask(BetterWitch.this.secondary);
/* 560 */             } else if (this.j < 20) {
/* 561 */               if (this.i > 3.0D) this.i = 0.0D;
/*     */               
/* 563 */               this.i += 0.19634954084936207D;
/* 564 */               double x = this.r * Math.cos(this.i);
/* 565 */               double y = 0.5D * this.i;
/* 566 */               double z = this.r * Math.sin(this.i);
/* 567 */               Location loc1 = BetterWitch.this.entity.getLocation().clone().add(x, y, z);
/* 568 */               Location loc2 = BetterWitch.this.entity.getLocation().clone().add(-x, y, -z);
/* 569 */               Location loc3 = BetterWitch.this.entity.getLocation().clone().add(-x, y, z);
/* 570 */               Location loc4 = BetterWitch.this.entity.getLocation().clone().add(x, y, -z);
/* 571 */               BetterWitch.this.entity.getWorld().spawnParticle(Particle.CLOUD, loc1, 0, 0.0D, 0.0D, 0.0D, 1.0D);
/* 572 */               BetterWitch.this.entity.getWorld().spawnParticle(Particle.CLOUD, loc2, 0, 0.0D, 0.0D, 0.0D, 1.0D);
/* 573 */               BetterWitch.this.entity.getWorld().spawnParticle(Particle.CLOUD, loc3, 0, 0.0D, 0.0D, 0.0D, 1.0D);
/* 574 */               BetterWitch.this.entity.getWorld().spawnParticle(Particle.CLOUD, loc4, 0, 0.0D, 0.0D, 0.0D, 1.0D);
/* 575 */               this.j++;
/*     */             } else {
/* 577 */               BetterWitch.this.entity.removePotionEffect(PotionEffectType.SLOW);
/* 578 */               for (int i = 0; i < BetterWitch.this.random.nextInt(3) + 3; i++) {
/*     */                 
/* 580 */                 Location location = BetterWitch.this.entity.getLocation().clone().add((BetterWitch.this.random.nextInt(6) - 3), 0.0D, ((new Random()).nextInt(6) - 3));
/* 581 */                 BetterWitch.this.entity.getWorld().spawnEntity(location, EntityType.ENDERMITE);
/*     */               } 
/* 583 */               BetterWitch.this.randomAttack(player, MobAI.settings.configuration.getInt("Witch.EndermitesAttack.NextAttackDelay"));
/* 584 */               Bukkit.getScheduler().cancelTask(BetterWitch.this.secondary);
/*     */             } 
/*     */           }
/*     */         }0L, 2L);
/*     */   }
/*     */ 
/*     */   
/*     */   public void healEffect(final Player player) {
/* 592 */     this.entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20000, 100, true));
/* 593 */     final double health = this.entity.getHealth();
/* 594 */     this.secondary = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 595 */           double i = 0.0D;
/* 596 */           double r = 1.0D;
/* 597 */           int j = 0;
/*     */ 
/*     */           
/*     */           public void run() {
/* 601 */             if (BetterWitch.this.entity.getHealth() < health || player.isDead() || !player.isOnline()) {
/* 602 */               BetterWitch.this.entity.removePotionEffect(PotionEffectType.SLOW);
/* 603 */               BetterWitch.this.randomAttack(player, 0);
/* 604 */               Bukkit.getScheduler().cancelTask(BetterWitch.this.secondary);
/* 605 */             } else if (this.j < 20) {
/* 606 */               if (this.i > 3.0D) this.i = 0.0D;
/*     */               
/* 608 */               this.i += 0.19634954084936207D;
/* 609 */               double x = this.r * Math.cos(this.i);
/* 610 */               double y = 0.5D * this.i;
/* 611 */               double z = this.r * Math.sin(this.i);
/* 612 */               Location loc1 = BetterWitch.this.entity.getLocation().clone().add(x, y, z);
/* 613 */               Location loc2 = BetterWitch.this.entity.getLocation().clone().add(-x, y, -z);
/* 614 */               Location loc3 = BetterWitch.this.entity.getLocation().clone().add(-x, y, z);
/* 615 */               Location loc4 = BetterWitch.this.entity.getLocation().clone().add(x, y, -z);
/* 616 */               BetterWitch.this.entity.getWorld().spawnParticle(Particle.HEART, loc1, 0, 0.0D, 0.0D, 0.0D, 1.0D);
/* 617 */               BetterWitch.this.entity.getWorld().spawnParticle(Particle.HEART, loc2, 0, 0.0D, 0.0D, 0.0D, 1.0D);
/* 618 */               BetterWitch.this.entity.getWorld().spawnParticle(Particle.HEART, loc3, 0, 0.0D, 0.0D, 0.0D, 1.0D);
/* 619 */               BetterWitch.this.entity.getWorld().spawnParticle(Particle.HEART, loc4, 0, 0.0D, 0.0D, 0.0D, 1.0D);
/* 620 */               this.j++;
/*     */             } else {
/* 622 */               BetterWitch.this.entity.removePotionEffect(PotionEffectType.SLOW);
/* 623 */               BetterWitch.this.entity.setHealth(BetterWitch.this.entity.getMaxHealth());
/* 624 */               Bukkit.getScheduler().cancelTask(BetterWitch.this.secondary);
/*     */             } 
/*     */           }
/*     */         }0L, 2L);
/*     */   }
/*     */ 
/*     */   
/*     */   public void trackAndKill(final Player player) {
/* 632 */     if (!isBusy()) {
/* 633 */       setBusy(true);
/* 634 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 635 */             boolean b = BetterWitch.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Witch.TrackingSpeed"), 20.0D);
/*     */             
/*     */             public void run() {
/* 638 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/* 639 */                 !player.isOnline() || BetterWitch.this.entity.isDead()) {
/* 640 */                 BetterWitch.this.setBusy(false);
/* 641 */                 Bukkit.getScheduler().cancelTask(BetterWitch.this.scheduler);
/*     */                 return;
/*     */               } 
/* 644 */               if (!this.b) {
/* 645 */                 this.b = BetterWitch.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Witch.TrackingSpeed"), 20.0D);
/*     */               } else {
/* 647 */                 BetterWitch.this.track(BetterWitch.this.entity.getLocation(), 0.0F, 0.0D);
/* 648 */                 BetterWitch.this.setBusy(false);
/* 649 */                 BetterWitch.this.randomAttack(player, 0);
/* 650 */                 Bukkit.getScheduler().cancelTask(BetterWitch.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void randomAttack(Player player, int delay) {
/* 659 */     if (!player.isDead() && player.isOnline()) {
/* 660 */       setBusy(true);
/* 661 */       Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)MobAI.getInstance(), () -> { ((EntityEquipment)Objects.<EntityEquipment>requireNonNull(this.entity.getEquipment())).setItemInHand(new ItemStack(Material.BLAZE_ROD)); WitchAttack attack = WitchAttack.values()[this.random.nextInt((WitchAttack.values()).length)]; switch (attack) { case NORMAL_ATTACK: normalAttack(player); return;case BLACK_MAGIC_ATTACK: blackMagicAttack(player); return;case BURN_PLAYER_ATTACK: burnPlayerAttack(player); return;case ENDERMITES_ATTACK: endermitesAttack(player); return;case FIRE_CIRCLE_ATTACK: fireCircleAttack(player); return;case LAVA_ATTACK: lavaAttack(player); return;case POISON_ATTACK: poisonAttack(player); return;case SLOWNESS_ATTACK: slownessAttack(player); return;case HEAL_EFFECT: if (this.entity.getHealth() < this.entity.getMaxHealth()) { healEffect(player); } else { normalAttack(player); }  return; }  normalAttack(player); }(20 * delay));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\cheen\Downloads\BetterMobAI-2.11 alpha.jar!\com\LucasRomier\BetterMobAI\Mobs\Entity\Overworld\BetterWitch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
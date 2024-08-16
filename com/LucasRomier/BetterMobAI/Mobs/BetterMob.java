/*     */ package com.LucasRomier.BetterMobAI.Mobs;
/*     */ import com.LucasRomier.BetterMobAI.MobAI;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Particle;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ public class BetterMob implements Reflection {
/*     */   public static Map<UUID, Boolean> busyEntities;
/*     */   public static Map<UUID, Boolean> protectedEntities;
/*     */   
/*     */   public BetterMob(LivingEntity entity) {
/*  23 */     this.entity = entity;
/*  24 */     this.random = new Random();
/*     */   }
/*     */   protected LivingEntity entity; protected Random random; private int protect;
/*     */   public static void setBusy(Entity entity, boolean busy) {
/*  28 */     busyEntities.put(entity.getUniqueId(), Boolean.valueOf(busy));
/*     */   }
/*     */   
/*     */   public static boolean isBusy(Entity entity) {
/*  32 */     if (busyEntities.containsKey(entity.getUniqueId())) {
/*  33 */       return ((Boolean)busyEntities.get(entity.getUniqueId())).booleanValue();
/*     */     }
/*  35 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isBusy() {
/*  39 */     if (busyEntities.containsKey(this.entity.getUniqueId())) {
/*  40 */       return ((Boolean)busyEntities.get(this.entity.getUniqueId())).booleanValue();
/*     */     }
/*  42 */     return false;
/*     */   }
/*     */   
/*     */   public void setBusy(boolean busy) {
/*  46 */     busyEntities.put(this.entity.getUniqueId(), Boolean.valueOf(busy));
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
/*     */ 
/*     */   
/*     */   public void moveTo(Location location, float speed) {
/*  68 */     ((CraftCreature)this.entity).getHandle().getNavigation().a(location.getX(), location.getY() + 1.0D, location.getZ(), speed);
/*     */   }
/*     */   
/*     */   public boolean track(Location location, float speed, double distance) {
/*  72 */     if (!this.entity.isDead()) {
/*  73 */       moveTo(location, speed);
/*  74 */       double distance2D = Math.sqrt(Math.pow(location.getX() - this.entity.getLocation().getX(), 2.0D) + 
/*  75 */           Math.pow(location.getZ() - this.entity.getLocation().getZ(), 2.0D));
/*  76 */       return (distance2D <= distance);
/*     */     } 
/*     */     
/*  79 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void trackAndKill(Player player) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void randomAttack(Player player, int delay) {}
/*     */ 
/*     */   
/*     */   public void protect() {
/*  92 */     if (protectedEntities.containsKey(this.entity.getUniqueId())) {
/*  93 */       if (!((Boolean)protectedEntities.get(this.entity.getUniqueId())).booleanValue()) {
/*  94 */         protectedEntities.put(this.entity.getUniqueId(), Boolean.TRUE);
/*  95 */         doProtection();
/*     */       } 
/*     */     } else {
/*  98 */       protectedEntities.put(this.entity.getUniqueId(), Boolean.TRUE);
/*  99 */       doProtection();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void doProtection() {
/* 104 */     boolean b = false;
/* 105 */     for (String s : MobAI.settings.configuration.getStringList("BetterMobs")) {
/* 106 */       if (s.equals(this.entity.getType().toString())) {
/* 107 */         b = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 111 */     boolean world = false;
/* 112 */     for (String s : MobAI.settings.configuration.getStringList("Worlds")) {
/* 113 */       if (this.entity.getLocation().getWorld() != null && s.equals(this.entity.getLocation().getWorld().getName())) {
/* 114 */         world = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 118 */     if (world && b) {
/* 119 */       final List<BetterMob> entities = new ArrayList<>();
/* 120 */       final Map<UUID, double[]> offset = (Map)new HashMap<>();
/*     */       
/* 122 */       for (Entity e : this.entity.getNearbyEntities(10.0D, 10.0D, 10.0D)) {
/* 123 */         if (e instanceof LivingEntity) {
/* 124 */           b = false;
/* 125 */           for (String s : MobAI.settings.configuration.getStringList("BetterMobs")) {
/* 126 */             if (s.equals(this.entity.getType().toString())) {
/* 127 */               b = true;
/*     */               break;
/*     */             } 
/*     */           } 
/* 131 */           if (b && (e
/* 132 */             .getType().equals(EntityType.ZOMBIE) || e.getType().equals(EntityType.BLAZE) || e
/* 133 */             .getType().equals(EntityType.PIG_ZOMBIE))) {
/* 134 */             BetterMob betterMob = new BetterMob((LivingEntity)e);
/* 135 */             if (!betterMob.isBusy() && entities.size() <= 15) {
/* 136 */               betterMob.setBusy(true);
/* 137 */               entities.add(betterMob);
/* 138 */               offset.put(e.getUniqueId(), null);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 145 */       this.protect = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 146 */             double p = 0.0D;
/* 147 */             double r = 5.0D;
/* 148 */             int k = 0;
/*     */             
/*     */             public void run() {
/* 151 */               if (entities.size() > 2) {
/* 152 */                 for (int i = 0; i < entities.size(); i++) {
/* 153 */                   double x1 = 3.0D * Math.cos(((i + 1 + this.k) * 2) * Math.PI / entities.size());
/* 154 */                   double x3 = 3.0D * Math.sin(((i + 1 + this.k) * 2) * Math.PI / entities.size());
/* 155 */                   offset.remove(((BetterMob)entities.get(i)).entity.getUniqueId());
/* 156 */                   offset.put(((BetterMob)entities.get(i)).entity.getUniqueId(), new double[] { x1, x3 });
/*     */                 } 
/*     */               }
/*     */               
/* 160 */               if (entities.size() > 2) {
/* 161 */                 this.k++;
/* 162 */                 if (this.k > entities.size()) this.k = 0;
/*     */               
/*     */               } 
/* 165 */               if (!BetterMob.this.entity.isDead() && entities.size() > 2) {
/* 166 */                 this.p += 0.3141592653589793D;
/*     */                 
/* 168 */                 for (double theta = 0.0D; theta < 6.283185307179586D; theta += 0.07853981633974483D) {
/* 169 */                   double x = this.r * Math.cos(theta) * Math.sin(this.p);
/* 170 */                   double y = this.r * Math.cos(this.p) + 1.5D;
/* 171 */                   double z = this.r * Math.sin(theta) * Math.sin(this.p);
/* 172 */                   BetterMob.this.entity.getWorld().spawnParticle(Particle.FLAME, BetterMob.this.entity
/* 173 */                       .getLocation().clone().add(x, y, z), 0, 0.0D, 0.0D, 0.0D, 1.0D);
/*     */                 } 
/*     */                 
/* 176 */                 Iterator<BetterMob> iterator = entities.iterator();
/* 177 */                 while (iterator.hasNext()) {
/* 178 */                   BetterMob betterMob = iterator.next();
/* 179 */                   if (betterMob.entity.isDead()) {
/* 180 */                     iterator.remove();
/* 181 */                     offset.remove(betterMob.entity.getUniqueId());
/*     */                     continue;
/*     */                   } 
/* 184 */                   double x1 = ((double[])offset.get(betterMob.entity.getUniqueId()))[0];
/* 185 */                   double x3 = ((double[])offset.get(betterMob.entity.getUniqueId()))[1];
/* 186 */                   Location location = BetterMob.this.entity.getLocation().clone().add(x1, 0.0D, x3);
/* 187 */                   if (location.getWorld() != null) {
/* 188 */                     location = location.getWorld().getHighestBlockAt(location).getLocation();
/* 189 */                     if (!betterMob.track(location, 2.0F, 8.0D)) {
/* 190 */                       betterMob.entity.teleport(location);
/*     */                     }
/* 192 */                     for (Entity e : betterMob.entity.getNearbyEntities(0.7D, 0.7D, 0.7D)) {
/* 193 */                       if (e.getType().equals(EntityType.PLAYER)) {
/* 194 */                         ((Player)e).damage(8.0D);
/*     */                       }
/*     */                     } 
/*     */                   } 
/*     */                 } 
/*     */               } else {
/* 200 */                 BetterMob.protectedEntities.put(BetterMob.this.entity.getUniqueId(), Boolean.FALSE);
/* 201 */                 Bukkit.getScheduler().cancelTask(BetterMob.this.protect);
/*     */               } 
/*     */             }
/*     */           }0L, 4L);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\cheen\Downloads\BetterMobAI-2.11 alpha.jar!\com\LucasRomier\BetterMobAI\Mobs\BetterMob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
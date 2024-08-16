/*    */ package com.LucasRomier.BetterMobAI.Events;
/*    */ import com.LucasRomier.BetterMobAI.MobAI;
/*    */ import com.LucasRomier.BetterMobAI.Mobs.Entity.Overworld.BetterSkeleton;
/*    */ import com.LucasRomier.BetterMobAI.Mobs.Entity.Overworld.BetterWitch;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.craftbukkit.v1_15_R1.entity.CraftSkeleton;
/*    */ import org.bukkit.craftbukkit.v1_15_R1.entity.CraftZombie;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Projectile;
/*    */ import org.bukkit.entity.Skeleton;
/*    */ import org.bukkit.entity.Witch;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ import org.bukkit.projectiles.ProjectileSource;
/*    */ 
/*    */ public class EntityDamageByEntity implements Listener {
/*    */   @EventHandler(priority = EventPriority.HIGHEST)
/*    */   public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
/* 25 */     Entity damager = event.getDamager();
/* 26 */     Entity damaged = event.getEntity();
/*    */     
/* 28 */     boolean b = false;
/* 29 */     for (String s : MobAI.settings.configuration.getStringList("BetterMobs")) {
/* 30 */       if (s.equals(damager.getType().toString()) || damager.getType().equals(EntityType.PLAYER)) {
/* 31 */         b = true; break;
/*    */       } 
/* 33 */       if (damager instanceof Projectile && (
/* 34 */         (Projectile)damager).getShooter() != null && (
/* 35 */         (Projectile)damager).getShooter() instanceof LivingEntity && (
/* 36 */         (LivingEntity)((Projectile)damager).getShooter()).getType().equals(EntityType.PLAYER)) {
/* 37 */         b = true;
/*    */ 
/*    */         
/*    */         break;
/*    */       } 
/*    */     } 
/*    */     
/* 44 */     boolean world = false;
/* 45 */     for (String s : MobAI.settings.configuration.getStringList("Worlds")) {
/* 46 */       if (s.equals(((World)Objects.<World>requireNonNull(damager.getLocation().getWorld())).getName())) {
/* 47 */         world = true;
/*    */         
/*    */         break;
/*    */       } 
/*    */     } 
/* 52 */     if (b && world) {
/* 53 */       if (damaged.getType().equals(EntityType.WITCH)) {
/* 54 */         BetterWitch betterWitch = new BetterWitch((Witch)damaged);
/* 55 */         betterWitch.protect();
/*    */       } 
/*    */       
/* 58 */       if (damager.getType().equals(EntityType.ZOMBIE)) {
/* 59 */         if (!((CraftZombie)damager).getHandle().isBaby()) {
/* 60 */           event.setCancelled(true);
/*    */         }
/* 62 */       } else if (damager.getType().equals(EntityType.ARROW)) {
/* 63 */         ProjectileSource source = ((Projectile)damager).getShooter();
/* 64 */         if (source instanceof LivingEntity && damaged.getType().equals(EntityType.PLAYER)) {
/* 65 */           event.setDamage(event.getDamage() / 2.0D);
/* 66 */           if (BetterSkeleton.lastArrow.containsKey(damaged.getUniqueId().toString())) {
/* 67 */             if (((String)BetterSkeleton.lastArrow.get(damaged.getUniqueId().toString())).equalsIgnoreCase("Poisoned")) {
/* 68 */               ((LivingEntity)damaged).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 0));
/* 69 */             } else if (((String)BetterSkeleton.lastArrow.get(damaged.getUniqueId().toString())).equalsIgnoreCase("Nailing")) {
/* 70 */               ((LivingEntity)damaged).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 5));
/* 71 */             } else if (((String)BetterSkeleton.lastArrow.get(damaged.getUniqueId().toString())).equalsIgnoreCase("Burning")) {
/* 72 */               damaged.setFireTicks(100);
/*    */             }
/*    */           
/*    */           }
/*    */         } 
/* 77 */       } else if (damager.getType().equals(EntityType.SPIDER) || damager.getType().equals(EntityType.CAVE_SPIDER)) {
/* 78 */         event.setCancelled(true);
/* 79 */       } else if (damager.getType().equals(EntityType.ENDERMAN)) {
/* 80 */         event.setCancelled(true);
/* 81 */       } else if (damager.getType().equals(EntityType.WITCH)) {
/* 82 */         event.setCancelled(true);
/* 83 */       } else if (damager.getType().equals(EntityType.PIG_ZOMBIE)) {
/* 84 */         event.setCancelled(true);
/* 85 */       } else if (damager.getType().equals(EntityType.SKELETON)) {
/* 86 */         if (((CraftSkeleton)damager).getSkeletonType() == Skeleton.SkeletonType.WITHER) {
/* 87 */           event.setCancelled(true);
/*    */         }
/* 89 */       } else if (damager.getType().equals(EntityType.ENDER_DRAGON)) {
/* 90 */         event.setCancelled(true);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\cheen\Downloads\BetterMobAI-2.11 alpha.jar!\com\LucasRomier\BetterMobAI\Events\EntityDamageByEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
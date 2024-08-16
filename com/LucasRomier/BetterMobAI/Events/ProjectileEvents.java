/*    */ package com.LucasRomier.BetterMobAI.Events;
/*    */ import com.LucasRomier.BetterMobAI.MobAI;
/*    */ import java.util.Objects;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Projectile;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.ProjectileLaunchEvent;
/*    */ 
/*    */ public class ProjectileEvents implements Listener {
/*    */   @EventHandler(priority = EventPriority.HIGHEST)
/*    */   public void onProjectileLaunch(ProjectileLaunchEvent event) {
/* 16 */     Projectile projectile = event.getEntity();
/*    */     
/* 18 */     boolean b = false;
/* 19 */     for (String s : MobAI.settings.configuration.getStringList("BetterMobs")) {
/* 20 */       if (s.equals(projectile.getType().toString())) {
/* 21 */         b = true;
/*    */         break;
/*    */       } 
/*    */     } 
/* 25 */     boolean world = false;
/* 26 */     for (String s : MobAI.settings.configuration.getStringList("Worlds")) {
/* 27 */       if (s.equals(((World)Objects.<World>requireNonNull(projectile.getLocation().getWorld())).getName())) {
/* 28 */         world = true;
/*    */         
/*    */         break;
/*    */       } 
/*    */     } 
/* 33 */     if (world && b)
/* 34 */       if (projectile.getType().equals(EntityType.SKELETON)) {
/* 35 */         event.setCancelled(true);
/* 36 */       } else if (projectile.getType().equals(EntityType.GHAST)) {
/* 37 */         event.setCancelled(true);
/* 38 */       } else if (projectile.getType().equals(EntityType.WITCH)) {
/* 39 */         event.setCancelled(true);
/* 40 */       } else if (projectile.getType().equals(EntityType.SPLASH_POTION)) {
/* 41 */         if (projectile.getShooter() instanceof LivingEntity) {
/* 42 */           LivingEntity e = (LivingEntity)projectile.getShooter();
/* 43 */           assert e != null;
/* 44 */           if (!e.getType().equals(EntityType.PLAYER)) {
/* 45 */             event.setCancelled(true);
/*    */           }
/*    */         } 
/* 48 */       } else if (projectile.getType().equals(EntityType.ARROW)) {
/* 49 */         if (projectile.getShooter() instanceof LivingEntity) {
/* 50 */           LivingEntity e = (LivingEntity)projectile.getShooter();
/* 51 */           assert e != null;
/* 52 */           if (!e.getType().equals(EntityType.PLAYER)) {
/* 53 */             event.setCancelled(true);
/*    */           }
/*    */         } 
/* 56 */       } else if (projectile.getType().equals(EntityType.FIREBALL) && projectile.getShooter() instanceof LivingEntity) {
/* 57 */         LivingEntity e = (LivingEntity)projectile.getShooter();
/* 58 */         assert e != null;
/* 59 */         if (!e.getType().equals(EntityType.PLAYER) && e
/* 60 */           .getType().equals(EntityType.GHAST))
/* 61 */           event.setCancelled(true); 
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\cheen\Downloads\BetterMobAI-2.11 alpha.jar!\com\LucasRomier\BetterMobAI\Events\ProjectileEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
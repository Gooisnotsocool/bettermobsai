/*    */ package com.LucasRomier.BetterMobAI.Events;
/*    */ 
/*    */ import com.LucasRomier.BetterMobAI.MobAI;
/*    */ import java.util.Objects;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.PotionSplashEvent;
/*    */ 
/*    */ public class PotionSplash
/*    */   implements Listener {
/*    */   @EventHandler(priority = EventPriority.HIGHEST)
/*    */   public void onPotionSplash(PotionSplashEvent event) {
/* 17 */     if (event.getPotion().getShooter() != null && event
/* 18 */       .getPotion().getShooter() instanceof Entity) {
/* 19 */       boolean b = false;
/* 20 */       for (String s : MobAI.settings.configuration.getStringList("BetterMobs")) {
/* 21 */         if (s.equals(((Entity)event.getPotion().getShooter()).getType().toString())) {
/* 22 */           b = true;
/*    */           break;
/*    */         } 
/*    */       } 
/* 26 */       boolean world = false;
/* 27 */       for (String s : MobAI.settings.configuration.getStringList("Worlds")) {
/* 28 */         if (s.equals(((World)Objects.<World>requireNonNull(((Entity)event.getPotion().getShooter()).getLocation().getWorld())).getName())) {
/* 29 */           world = true;
/*    */           break;
/*    */         } 
/*    */       } 
/* 33 */       if (b && world && !((Entity)event.getPotion().getShooter()).getType().equals(EntityType.PLAYER))
/* 34 */         event.setCancelled(true); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\cheen\Downloads\BetterMobAI-2.11 alpha.jar!\com\LucasRomier\BetterMobAI\Events\PotionSplash.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
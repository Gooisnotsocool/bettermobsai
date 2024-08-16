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
/*    */ import org.bukkit.event.entity.ExplosionPrimeEvent;
/*    */ 
/*    */ public class ExplosionEvents
/*    */   implements Listener {
/*    */   @EventHandler(priority = EventPriority.HIGHEST)
/*    */   public void onEntityFuse(ExplosionPrimeEvent event) {
/* 17 */     Entity entity = event.getEntity();
/*    */     
/* 19 */     boolean b = false;
/* 20 */     for (String s : MobAI.settings.configuration.getStringList("BetterMobs")) {
/* 21 */       if (s.equals(entity.getType().toString())) {
/* 22 */         b = true;
/*    */         break;
/*    */       } 
/*    */     } 
/* 26 */     boolean world = false;
/* 27 */     for (String s : MobAI.settings.configuration.getStringList("Worlds")) {
/* 28 */       if (s.equals(((World)Objects.<World>requireNonNull(entity.getLocation().getWorld())).getName())) {
/* 29 */         world = true;
/*    */         
/*    */         break;
/*    */       } 
/*    */     } 
/* 34 */     if (b && world && entity.getType().equals(EntityType.CREEPER))
/* 35 */       event.setCancelled(false); 
/*    */   }
/*    */ }


/* Location:              C:\Users\cheen\Downloads\BetterMobAI-2.11 alpha.jar!\com\LucasRomier\BetterMobAI\Events\ExplosionEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
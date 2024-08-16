/*    */ package com.LucasRomier.BetterMobAI.Events;
/*    */ 
/*    */ import com.LucasRomier.BetterMobAI.MobAI;
/*    */ import java.util.Objects;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.EntityTeleportEvent;
/*    */ 
/*    */ public class TeleportEvents
/*    */   implements Listener {
/*    */   @EventHandler
/*    */   public void onEntityTeleport(EntityTeleportEvent event) {
/* 16 */     Entity entity = event.getEntity();
/* 17 */     boolean b = false;
/* 18 */     for (String s : MobAI.settings.configuration.getStringList("BetterMobs")) {
/* 19 */       if (s.equals(entity.getType().toString())) {
/* 20 */         b = true;
/*    */         break;
/*    */       } 
/*    */     } 
/* 24 */     boolean world = false;
/* 25 */     for (String s : MobAI.settings.configuration.getStringList("Worlds")) {
/* 26 */       if (s.equals(((World)Objects.<World>requireNonNull(entity.getLocation().getWorld())).getName())) {
/* 27 */         world = true;
/*    */         break;
/*    */       } 
/*    */     } 
/* 31 */     if (b && world && entity
/* 32 */       .getType().equals(EntityType.ENDERMAN))
/* 33 */       event.setCancelled(true); 
/*    */   }
/*    */ }


/* Location:              C:\Users\cheen\Downloads\BetterMobAI-2.11 alpha.jar!\com\LucasRomier\BetterMobAI\Events\TeleportEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
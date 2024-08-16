/*    */ package com.LucasRomier.BetterMobAI.Events;
/*    */ 
/*    */ import com.LucasRomier.BetterMobAI.API.PacketReader;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerJoinEvent;
/*    */ 
/*    */ public class PlayerJoin
/*    */   implements Listener {
/*    */   @EventHandler
/*    */   public void onPlayerJoin(PlayerJoinEvent event) {
/* 12 */     PacketReader reader = new PacketReader(event.getPlayer());
/* 13 */     reader.inject();
/*    */   }
/*    */ }


/* Location:              C:\Users\cheen\Downloads\BetterMobAI-2.11 alpha.jar!\com\LucasRomier\BetterMobAI\Events\PlayerJoin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
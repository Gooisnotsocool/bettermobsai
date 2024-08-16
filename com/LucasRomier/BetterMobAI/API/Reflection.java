/*    */ package com.LucasRomier.BetterMobAI.API;
/*    */ 
/*    */ import java.lang.reflect.Field;
/*    */ import net.minecraft.server.v1_15_R1.Packet;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ public interface Reflection
/*    */ {
/*    */   static Object getClassFieldObject(String name, Class<?> clazz, Object object) {
/*    */     try {
/* 14 */       Field field = clazz.getDeclaredField(name);
/* 15 */       field.setAccessible(true);
/* 16 */       return field.get(object);
/* 17 */     } catch (Exception e) {
/* 18 */       e.printStackTrace();
/*    */       
/* 20 */       return null;
/*    */     } 
/*    */   }
/*    */   
/*    */   default void setValue(Object object, String name, Object value) {
/*    */     try {
/* 26 */       Field field = object.getClass().getDeclaredField(name);
/* 27 */       field.setAccessible(true);
/* 28 */       field.set(object, value);
/* 29 */     } catch (Exception e) {
/* 30 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   default Object getValue(Object object, String name) {
/*    */     try {
/* 36 */       Field field = object.getClass().getDeclaredField(name);
/* 37 */       field.setAccessible(true);
/* 38 */       return field.get(object);
/* 39 */     } catch (Exception e) {
/* 40 */       e.printStackTrace();
/*    */       
/* 42 */       return null;
/*    */     } 
/*    */   }
/*    */   
/*    */   default void sendPacket(Packet<?> packet, Player player) {
/* 47 */     (((CraftPlayer)player).getHandle()).playerConnection.sendPacket(packet);
/*    */   }
/*    */   
/*    */   default void sendPacket(Packet<?> packet) {
/* 51 */     for (Player p : Bukkit.getOnlinePlayers())
/* 52 */       sendPacket(packet, p); 
/*    */   }
/*    */ }


/* Location:              C:\Users\cheen\Downloads\BetterMobAI-2.11 alpha.jar!\com\LucasRomier\BetterMobAI\API\Reflection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
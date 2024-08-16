/*    */ package com.LucasRomier.BetterMobAI.API;
/*    */ 
/*    */ import io.netty.channel.Channel;
/*    */ import io.netty.channel.ChannelHandler;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.handler.codec.MessageToMessageDecoder;
/*    */ import java.lang.reflect.Field;
/*    */ import java.util.List;
/*    */ import net.minecraft.server.v1_15_R1.Packet;
/*    */ import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketReader
/*    */ {
/*    */   private Player player;
/*    */   private Channel channel;
/*    */   
/*    */   public PacketReader(Player player) {
/* 22 */     this.player = player;
/*    */   }
/*    */   
/*    */   public void inject() {
/* 26 */     CraftPlayer cp = (CraftPlayer)this.player.getPlayer();
/* 27 */     assert cp != null;
/* 28 */     this.channel = (cp.getHandle()).playerConnection.networkManager.channel;
/* 29 */     this.channel.pipeline().addAfter("decoder", "PacketInjector", (ChannelHandler)new MessageToMessageDecoder<Packet<?>>() {
/*    */           protected void decode(ChannelHandlerContext context, Packet<?> packet, List<Object> list) {
/* 31 */             list.add(packet);
/*    */           }
/*    */         });
/*    */   }
/*    */   
/*    */   public void unInject() {
/* 37 */     if (this.channel.pipeline().get("PacketInjector") != null) {
/* 38 */       this.channel.pipeline().remove("PacketInjector");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void setValue(Object object, String name, Object value) {
/*    */     try {
/* 45 */       Field field = object.getClass().getDeclaredField(name);
/* 46 */       field.setAccessible(true);
/* 47 */       field.set(object, value);
/* 48 */     } catch (Exception e) {
/* 49 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   public Object getValue(Object object, String name) {
/*    */     try {
/* 55 */       Field field = object.getClass().getDeclaredField(name);
/* 56 */       field.setAccessible(true);
/* 57 */       return field.get(object);
/* 58 */     } catch (Exception e) {
/* 59 */       e.printStackTrace();
/*    */       
/* 61 */       return null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\cheen\Downloads\BetterMobAI-2.11 alpha.jar!\com\LucasRomier\BetterMobAI\API\PacketReader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
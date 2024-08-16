/*    */ package com.LucasRomier.BetterMobAI.Events;
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.craftbukkit.v1_15_R1.entity.CraftSkeleton;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerMoveEvent;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ public class PlayerMove implements Listener {
/*    */   @EventHandler
/*    */   public void onPlayerMove(PlayerMoveEvent event) {
/* 17 */     Player player = event.getPlayer();
/*    */     
/* 19 */     if (player.getGameMode().equals(GameMode.ADVENTURE) && player.getGameMode().equals(GameMode.SURVIVAL))
/* 20 */       for (Entity entity : player.getNearbyEntities(10.0D, 10.0D, 10.0D)) {
/* 21 */         double distance2D = Math.sqrt(Math.pow(player.getLocation().getX() - entity.getLocation().getX(), 2.0D) + 
/* 22 */             Math.pow(player.getLocation().getZ() - entity.getLocation().getZ(), 2.0D));
/* 23 */         Vector vector = entity.getLocation().subtract(player.getLocation()).toVector().setY(-1);
/* 24 */         vector = vector.multiply(0.02D * Math.sqrt(Math.abs(10.0D - distance2D)));
/* 25 */         vector.setY(-9.81D);
/*    */         
/* 27 */         if (entity.getType().equals(EntityType.SKELETON)) {
/* 28 */           if (((CraftSkeleton)entity).getSkeletonType() == Skeleton.SkeletonType.NORMAL && !((LivingEntity)entity).hasPotionEffect(PotionEffectType.SLOW))
/* 29 */             entity.setVelocity(vector); 
/*    */           continue;
/*    */         } 
/* 32 */         if (entity.getType().equals(EntityType.WITCH) && !((LivingEntity)entity).hasPotionEffect(PotionEffectType.SLOW))
/* 33 */           entity.setVelocity(vector); 
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\cheen\Downloads\BetterMobAI-2.11 alpha.jar!\com\LucasRomier\BetterMobAI\Events\PlayerMove.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
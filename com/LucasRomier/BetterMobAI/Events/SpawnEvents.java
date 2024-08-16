/*    */ package com.LucasRomier.BetterMobAI.Events;
/*    */ 
/*    */ import com.LucasRomier.BetterMobAI.MobAI;
/*    */ import com.LucasRomier.BetterMobAI.Mobs.Entity.Bosses.BetterGiant;
/*    */ import java.util.Objects;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.craftbukkit.v1_15_R1.entity.CraftLivingEntity;
/*    */ import org.bukkit.craftbukkit.v1_15_R1.entity.CraftSkeleton;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Giant;
/*    */ import org.bukkit.entity.Guardian;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Skeleton;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*    */ 
/*    */ public class SpawnEvents implements Listener {
/*    */   @EventHandler
/*    */   public void onCreatureSpawn(CreatureSpawnEvent event) {
/* 21 */     LivingEntity entity = event.getEntity();
/* 22 */     boolean b = false;
/* 23 */     for (String s : MobAI.settings.configuration.getStringList("BetterMobs")) {
/* 24 */       if (s.equals(entity.getType().toString())) {
/* 25 */         b = true;
/*    */         break;
/*    */       } 
/*    */     } 
/* 29 */     boolean world = false;
/* 30 */     for (String s : MobAI.settings.configuration.getStringList("Worlds")) {
/* 31 */       if (s.equals(((World)Objects.<World>requireNonNull(entity.getLocation().getWorld())).getName())) {
/* 32 */         world = true;
/*    */         break;
/*    */       } 
/*    */     } 
/* 36 */     if (b && world)
/* 37 */       switch (entity.getType()) {
/*    */         case CAVE_SPIDER:
/* 39 */           ((CraftLivingEntity)entity).setMaxHealth(MobAI.settings.configuration.getDouble("CaveSpider.Health"));
/* 40 */           entity.setHealth(MobAI.settings.configuration.getDouble("CaveSpider.Health"));
/*    */           break;
/*    */         case SPIDER:
/* 43 */           ((CraftLivingEntity)entity).setMaxHealth(MobAI.settings.configuration.getDouble("Spider.Health"));
/* 44 */           entity.setHealth(MobAI.settings.configuration.getDouble("Spider.Health"));
/*    */           break;
/*    */         case CREEPER:
/* 47 */           ((CraftLivingEntity)entity).setMaxHealth(MobAI.settings.configuration.getDouble("Creeper.Health"));
/* 48 */           entity.setHealth(MobAI.settings.configuration.getDouble("Creeper.Health"));
/*    */           break;
/*    */         case ENDERMAN:
/* 51 */           ((CraftLivingEntity)entity).setMaxHealth(MobAI.settings.configuration.getDouble("Enderman.Health"));
/* 52 */           entity.setHealth(MobAI.settings.configuration.getDouble("Enderman.Health"));
/*    */           break;
/*    */         case SKELETON:
/* 55 */           if (((CraftSkeleton)entity).getSkeletonType() == Skeleton.SkeletonType.WITHER) {
/* 56 */             ((CraftLivingEntity)entity).setMaxHealth(MobAI.settings.configuration.getDouble("WitherSkeleton.Health"));
/* 57 */             entity.setHealth(MobAI.settings.configuration.getDouble("WitherSkeleton.Health"));
/*    */             break;
/*    */           } 
/* 60 */           ((CraftLivingEntity)entity).setMaxHealth(MobAI.settings.configuration.getDouble("Skeleton.Health"));
/* 61 */           entity.setHealth(MobAI.settings.configuration.getDouble("Skeleton.Health"));
/*    */           break;
/*    */         
/*    */         case ZOMBIE:
/* 65 */           ((CraftLivingEntity)entity).setMaxHealth(MobAI.settings.configuration.getDouble("Zombie.Health"));
/* 66 */           entity.setHealth(MobAI.settings.configuration.getDouble("Zombie.Health"));
/*    */           break;
/*    */         case WITCH:
/* 69 */           ((CraftLivingEntity)entity).setMaxHealth(MobAI.settings.configuration.getDouble("Witch.Health"));
/* 70 */           entity.setHealth(MobAI.settings.configuration.getDouble("Witch.Health"));
/*    */           break;
/*    */         case GUARDIAN:
/* 73 */           if (((Guardian)entity).isElder()) {
/* 74 */             ((CraftLivingEntity)entity).setMaxHealth(MobAI.settings.configuration.getDouble("Guardian.Elder.Health"));
/* 75 */             entity.setHealth(MobAI.settings.configuration.getDouble("Guardian.Elder.Health"));
/*    */             break;
/*    */           } 
/* 78 */           ((CraftLivingEntity)entity).setMaxHealth(MobAI.settings.configuration.getDouble("Guardian.Normal.Health"));
/* 79 */           entity.setHealth(MobAI.settings.configuration.getDouble("Guardian.Normal.Health"));
/*    */           break;
/*    */         
/*    */         case PIG_ZOMBIE:
/* 83 */           ((CraftLivingEntity)entity).setMaxHealth(MobAI.settings.configuration.getDouble("PigmanZombie.Health"));
/* 84 */           entity.setHealth(MobAI.settings.configuration.getDouble("PigmanZombie.Health"));
/*    */           break;
/*    */         case GHAST:
/* 87 */           ((CraftLivingEntity)entity).setMaxHealth(MobAI.settings.configuration.getDouble("Ghast.Health"));
/* 88 */           entity.setHealth(MobAI.settings.configuration.getDouble("Ghast.Health"));
/*    */           break;
/*    */         case BLAZE:
/* 91 */           ((CraftLivingEntity)entity).setMaxHealth(MobAI.settings.configuration.getDouble("Blaze.Health"));
/* 92 */           entity.setHealth(MobAI.settings.configuration.getDouble("Blaze.Health"));
/*    */           break;
/*    */         case GIANT:
/* 95 */           new BetterGiant((Giant)entity);
/*    */           break;
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\cheen\Downloads\BetterMobAI-2.11 alpha.jar!\com\LucasRomier\BetterMobAI\Events\SpawnEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
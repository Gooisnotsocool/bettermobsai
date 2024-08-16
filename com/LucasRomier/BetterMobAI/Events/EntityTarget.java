/*     */ package com.LucasRomier.BetterMobAI.Events;
/*     */ import com.LucasRomier.BetterMobAI.MobAI;
/*     */ import com.LucasRomier.BetterMobAI.Mobs.Entity.Nether.BetterBlaze;
/*     */ import com.LucasRomier.BetterMobAI.Mobs.Entity.Nether.BetterGhast;
/*     */ import com.LucasRomier.BetterMobAI.Mobs.Entity.Nether.BetterWitherSkeleton;
/*     */ import com.LucasRomier.BetterMobAI.Mobs.Entity.Nether.BetterZombiePigman;
/*     */ import com.LucasRomier.BetterMobAI.Mobs.Entity.Overworld.BetterCaveSpider;
/*     */ import com.LucasRomier.BetterMobAI.Mobs.Entity.Overworld.BetterCreeper;
/*     */ import com.LucasRomier.BetterMobAI.Mobs.Entity.Overworld.BetterEnderman;
/*     */ import com.LucasRomier.BetterMobAI.Mobs.Entity.Overworld.BetterSkeleton;
/*     */ import com.LucasRomier.BetterMobAI.Mobs.Entity.Overworld.BetterSpider;
/*     */ import com.LucasRomier.BetterMobAI.Mobs.Entity.Overworld.BetterWitch;
/*     */ import com.LucasRomier.BetterMobAI.Mobs.Entity.Overworld.BetterZombie;
/*     */ import com.LucasRomier.BetterMobAI.Mobs.Entity.Water.BetterGuardian;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Skeleton;
/*     */ import org.bukkit.event.entity.EntityTargetEvent;
/*     */ 
/*     */ public class EntityTarget implements Listener {
/*     */   @EventHandler(priority = EventPriority.HIGHEST)
/*     */   public void onEntityTarget(EntityTargetEvent event) {
/*  25 */     Entity entity = event.getEntity();
/*  26 */     Entity target = event.getTarget();
/*     */     
/*  28 */     if (target != null && target.getType().equals(EntityType.PLAYER)) {
/*  29 */       boolean b = false;
/*  30 */       for (String s : MobAI.settings.configuration.getStringList("BetterMobs")) {
/*  31 */         if (s.equals(entity.getType().toString())) {
/*  32 */           b = true;
/*     */           break;
/*     */         } 
/*     */       } 
/*  36 */       boolean world = false;
/*  37 */       for (String s : MobAI.settings.configuration.getStringList("Worlds")) {
/*  38 */         if (s.equals(((World)Objects.<World>requireNonNull(entity.getLocation().getWorld())).getName())) {
/*  39 */           world = true;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*  44 */       if (b && world) {
/*     */         BetterCreeper creeper;
/*     */         BetterSkeleton skeleton;
/*     */         BetterWitch witch;
/*     */         BetterSpider spider;
/*     */         BetterCaveSpider caveSpider;
/*     */         BetterEnderman enderman;
/*     */         BetterGuardian guardian;
/*     */         BetterZombiePigman pigman;
/*     */         BetterGhast ghast;
/*     */         BetterBlaze blaze;
/*  55 */         switch (entity.getType()) {
/*     */           case ZOMBIE:
/*  57 */             if (!((CraftZombie)entity).getHandle().isBaby()) {
/*  58 */               BetterZombie zombie = new BetterZombie((Zombie)entity);
/*  59 */               zombie.trackAndKill((Player)target);
/*     */             } 
/*     */             break;
/*     */           case CREEPER:
/*  63 */             creeper = new BetterCreeper((Creeper)entity);
/*  64 */             creeper.trackAndKill((Player)target);
/*     */             break;
/*     */           case SKELETON:
/*  67 */             if (((CraftSkeleton)entity).getSkeletonType() == Skeleton.SkeletonType.WITHER) {
/*  68 */               BetterWitherSkeleton witherSkeleton = new BetterWitherSkeleton((Skeleton)entity);
/*  69 */               witherSkeleton.trackAndKill((Player)target);
/*     */               break;
/*     */             } 
/*  72 */             skeleton = new BetterSkeleton((Skeleton)entity);
/*  73 */             skeleton.getARide();
/*  74 */             skeleton.trackAndKill((Player)target);
/*     */             break;
/*     */           
/*     */           case WITCH:
/*  78 */             witch = new BetterWitch((Witch)entity);
/*  79 */             witch.trackAndKill((Player)target);
/*     */             break;
/*     */           case SPIDER:
/*  82 */             spider = new BetterSpider((Spider)entity);
/*  83 */             spider.trackAndKill((Player)target);
/*     */             break;
/*     */           case CAVE_SPIDER:
/*  86 */             caveSpider = new BetterCaveSpider((CaveSpider)entity);
/*  87 */             caveSpider.trackAndKill((Player)target);
/*     */             break;
/*     */           case ENDERMAN:
/*  90 */             enderman = new BetterEnderman((Enderman)entity);
/*  91 */             enderman.trackAndKill((Player)target);
/*     */             break;
/*     */           case GUARDIAN:
/*  94 */             guardian = new BetterGuardian((Guardian)entity);
/*  95 */             guardian.trackAndKill((Player)target);
/*     */             break;
/*     */           case PIG_ZOMBIE:
/*  98 */             pigman = new BetterZombiePigman((PigZombie)entity);
/*  99 */             pigman.trackAndKill((Player)target);
/*     */             break;
/*     */           case GHAST:
/* 102 */             ghast = new BetterGhast((Ghast)entity);
/* 103 */             ghast.trackAndKill((Player)target);
/*     */             break;
/*     */           case BLAZE:
/* 106 */             blaze = new BetterBlaze((Blaze)entity);
/* 107 */             blaze.trackAndKill((Player)target);
/*     */             break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\cheen\Downloads\BetterMobAI-2.11 alpha.jar!\com\LucasRomier\BetterMobAI\Events\EntityTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
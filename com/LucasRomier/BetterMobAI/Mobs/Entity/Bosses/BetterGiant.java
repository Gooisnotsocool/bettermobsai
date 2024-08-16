/*     */ package com.LucasRomier.BetterMobAI.Mobs.Entity.Bosses;
/*     */ import com.LucasRomier.BetterMobAI.API.Reflection;
/*     */ import com.LucasRomier.BetterMobAI.MobAI;
/*     */ import com.LucasRomier.BetterMobAI.Mobs.Attacks.GiantAttack;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.server.v1_15_R1.EntityInsentient;
/*     */ import net.minecraft.server.v1_15_R1.EntityMonster;
/*     */ import net.minecraft.server.v1_15_R1.PacketPlayOutAnimation;
/*     */ import net.minecraft.server.v1_15_R1.PathfinderGoal;
/*     */ import net.minecraft.server.v1_15_R1.PathfinderGoalSelector;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.FallingBlock;
/*     */ import org.bukkit.entity.Giant;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Zombie;
/*     */ import org.bukkit.material.MaterialData;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ public class BetterGiant extends BetterMob {
/*  30 */   private static List<BetterGiant> betterGiants = new ArrayList<>();
/*     */   private List<Zombie> zombies;
/*     */   private int protect;
/*     */   private int scheduler;
/*     */   private int boulder;
/*     */   private int main;
/*     */   private EntityGiantZombie entityGiantZombie;
/*     */   private GiantAttack nextAttack;
/*     */   private int cyclesToWait;
/*     */   
/*     */   public BetterGiant(Giant giant) {
/*  41 */     super((LivingEntity)giant);
/*  42 */     this.entityGiantZombie = ((CraftGiant)this.entity).getHandle();
/*  43 */     clearGoals();
/*  44 */     defaultZombieGoals();
/*     */     
/*  46 */     this.cyclesToWait = 0;
/*  47 */     mainLoop();
/*     */     
/*  49 */     this.zombies = new ArrayList<>();
/*  50 */     doProtection();
/*     */     
/*  52 */     betterGiants.add(this);
/*     */   }
/*     */   
/*     */   public static void init() {
/*  56 */     for (World world : Bukkit.getWorlds()) {
/*  57 */       for (Entity entity : world.getEntities()) {
/*  58 */         if (entity.getType().equals(EntityType.GIANT)) {
/*  59 */           new BetterGiant((Giant)entity);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void stop() {
/*  66 */     for (BetterGiant betterGiant : betterGiants) {
/*  67 */       for (Iterator<Zombie> iterator = betterGiant.zombies.iterator(); iterator.hasNext(); ) {
/*  68 */         ((Zombie)iterator.next()).remove();
/*  69 */         iterator.remove();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void mainLoop() {
/*  75 */     this.main = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() { public void run() { // Byte code:
/*     */             //   0: aload_0
/*     */             //   1: getfield this$0 : Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;
/*     */             //   4: invokestatic access$000 : (Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;)Lorg/bukkit/entity/LivingEntity;
/*     */             //   7: invokeinterface isDead : ()Z
/*     */             //   12: ifeq -> 118
/*     */             //   15: aload_0
/*     */             //   16: getfield this$0 : Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;
/*     */             //   19: invokestatic access$100 : (Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;)Ljava/util/List;
/*     */             //   22: invokeinterface iterator : ()Ljava/util/Iterator;
/*     */             //   27: astore_1
/*     */             //   28: aload_1
/*     */             //   29: invokeinterface hasNext : ()Z
/*     */             //   34: ifeq -> 103
/*     */             //   37: aload_1
/*     */             //   38: invokeinterface next : ()Ljava/lang/Object;
/*     */             //   43: checkcast org/bukkit/entity/Zombie
/*     */             //   46: invokeinterface remove : ()V
/*     */             //   51: aload_1
/*     */             //   52: invokeinterface remove : ()V
/*     */             //   57: aload_0
/*     */             //   58: getfield this$0 : Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;
/*     */             //   61: invokestatic access$300 : (Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;)Lorg/bukkit/entity/LivingEntity;
/*     */             //   64: invokeinterface getWorld : ()Lorg/bukkit/World;
/*     */             //   69: aload_0
/*     */             //   70: getfield this$0 : Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;
/*     */             //   73: invokestatic access$200 : (Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;)Lorg/bukkit/entity/LivingEntity;
/*     */             //   76: invokeinterface getLocation : ()Lorg/bukkit/Location;
/*     */             //   81: getstatic org/bukkit/entity/EntityType.ZOMBIE : Lorg/bukkit/entity/EntityType;
/*     */             //   84: invokeinterface spawnEntity : (Lorg/bukkit/Location;Lorg/bukkit/entity/EntityType;)Lorg/bukkit/entity/Entity;
/*     */             //   89: checkcast org/bukkit/entity/Zombie
/*     */             //   92: astore_2
/*     */             //   93: aload_2
/*     */             //   94: iconst_1
/*     */             //   95: invokeinterface setBaby : (Z)V
/*     */             //   100: goto -> 28
/*     */             //   103: invokestatic getScheduler : ()Lorg/bukkit/scheduler/BukkitScheduler;
/*     */             //   106: aload_0
/*     */             //   107: getfield this$0 : Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;
/*     */             //   110: invokestatic access$400 : (Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;)I
/*     */             //   113: invokeinterface cancelTask : (I)V
/*     */             //   118: aload_0
/*     */             //   119: getfield this$0 : Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;
/*     */             //   122: invokestatic access$500 : (Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;)Ljava/util/List;
/*     */             //   125: invokeinterface size : ()I
/*     */             //   130: ifle -> 435
/*     */             //   133: aload_0
/*     */             //   134: getfield this$0 : Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;
/*     */             //   137: invokestatic access$600 : (Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;)Lcom/LucasRomier/BetterMobAI/Mobs/Attacks/GiantAttack;
/*     */             //   140: ifnull -> 287
/*     */             //   143: getstatic com/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant$7.$SwitchMap$com$LucasRomier$BetterMobAI$Mobs$Attacks$GiantAttack : [I
/*     */             //   146: aload_0
/*     */             //   147: getfield this$0 : Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;
/*     */             //   150: invokestatic access$600 : (Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;)Lcom/LucasRomier/BetterMobAI/Mobs/Attacks/GiantAttack;
/*     */             //   153: invokevirtual ordinal : ()I
/*     */             //   156: iaload
/*     */             //   157: tableswitch default -> 225, 1 -> 188, 2 -> 198, 3 -> 208, 4 -> 218
/*     */             //   188: aload_0
/*     */             //   189: getfield this$0 : Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;
/*     */             //   192: invokestatic access$700 : (Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;)V
/*     */             //   195: goto -> 225
/*     */             //   198: aload_0
/*     */             //   199: getfield this$0 : Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;
/*     */             //   202: invokestatic access$800 : (Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;)V
/*     */             //   205: goto -> 225
/*     */             //   208: aload_0
/*     */             //   209: getfield this$0 : Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;
/*     */             //   212: invokestatic access$900 : (Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;)V
/*     */             //   215: goto -> 225
/*     */             //   218: aload_0
/*     */             //   219: getfield this$0 : Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;
/*     */             //   222: invokestatic access$1000 : (Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;)V
/*     */             //   225: aload_0
/*     */             //   226: getfield this$0 : Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;
/*     */             //   229: getstatic com/LucasRomier/BetterMobAI/MobAI.settings : Lcom/LucasRomier/BetterMobAI/API/Settings;
/*     */             //   232: getfield configuration : Lcom/LucasRomier/BetterMobAI/API/ConfigManager$SimpleConfig;
/*     */             //   235: new java/lang/StringBuilder
/*     */             //   238: dup
/*     */             //   239: invokespecial <init> : ()V
/*     */             //   242: ldc 'Giant.'
/*     */             //   244: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */             //   247: aload_0
/*     */             //   248: getfield this$0 : Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;
/*     */             //   251: invokestatic access$600 : (Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;)Lcom/LucasRomier/BetterMobAI/Mobs/Attacks/GiantAttack;
/*     */             //   254: invokevirtual getName : ()Ljava/lang/String;
/*     */             //   257: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */             //   260: ldc '.NextAttackDelay'
/*     */             //   262: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */             //   265: invokevirtual toString : ()Ljava/lang/String;
/*     */             //   268: invokevirtual getInt : (Ljava/lang/String;)I
/*     */             //   271: invokestatic access$1102 : (Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;I)I
/*     */             //   274: pop
/*     */             //   275: aload_0
/*     */             //   276: getfield this$0 : Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;
/*     */             //   279: aconst_null
/*     */             //   280: invokestatic access$602 : (Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;Lcom/LucasRomier/BetterMobAI/Mobs/Attacks/GiantAttack;)Lcom/LucasRomier/BetterMobAI/Mobs/Attacks/GiantAttack;
/*     */             //   283: pop
/*     */             //   284: goto -> 435
/*     */             //   287: aload_0
/*     */             //   288: getfield this$0 : Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;
/*     */             //   291: invokevirtual isBusy : ()Z
/*     */             //   294: ifne -> 435
/*     */             //   297: aload_0
/*     */             //   298: getfield this$0 : Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;
/*     */             //   301: invokestatic access$1100 : (Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;)I
/*     */             //   304: ifgt -> 317
/*     */             //   307: aload_0
/*     */             //   308: getfield this$0 : Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;
/*     */             //   311: invokestatic access$1200 : (Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;)V
/*     */             //   314: goto -> 435
/*     */             //   317: aload_0
/*     */             //   318: getfield this$0 : Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;
/*     */             //   321: invokestatic access$1300 : (Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;)Lorg/bukkit/entity/LivingEntity;
/*     */             //   324: ldc2_w 5.0
/*     */             //   327: ldc2_w 5.0
/*     */             //   330: ldc2_w 5.0
/*     */             //   333: invokeinterface getNearbyEntities : (DDD)Ljava/util/List;
/*     */             //   338: invokeinterface iterator : ()Ljava/util/Iterator;
/*     */             //   343: astore_1
/*     */             //   344: aload_1
/*     */             //   345: invokeinterface hasNext : ()Z
/*     */             //   350: ifeq -> 427
/*     */             //   353: aload_1
/*     */             //   354: invokeinterface next : ()Ljava/lang/Object;
/*     */             //   359: checkcast org/bukkit/entity/Entity
/*     */             //   362: astore_2
/*     */             //   363: aload_2
/*     */             //   364: invokeinterface getType : ()Lorg/bukkit/entity/EntityType;
/*     */             //   369: getstatic org/bukkit/entity/EntityType.PLAYER : Lorg/bukkit/entity/EntityType;
/*     */             //   372: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */             //   375: ifeq -> 424
/*     */             //   378: aload_0
/*     */             //   379: getfield this$0 : Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;
/*     */             //   382: invokestatic access$1400 : (Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;)Lorg/bukkit/entity/LivingEntity;
/*     */             //   385: invokeinterface getLocation : ()Lorg/bukkit/Location;
/*     */             //   390: invokevirtual toVector : ()Lorg/bukkit/util/Vector;
/*     */             //   393: aload_2
/*     */             //   394: invokeinterface getLocation : ()Lorg/bukkit/Location;
/*     */             //   399: invokevirtual toVector : ()Lorg/bukkit/util/Vector;
/*     */             //   402: invokevirtual subtract : (Lorg/bukkit/util/Vector;)Lorg/bukkit/util/Vector;
/*     */             //   405: invokevirtual normalize : ()Lorg/bukkit/util/Vector;
/*     */             //   408: astore_3
/*     */             //   409: aload_2
/*     */             //   410: aload_3
/*     */             //   411: iconst_m1
/*     */             //   412: invokevirtual multiply : (I)Lorg/bukkit/util/Vector;
/*     */             //   415: iconst_1
/*     */             //   416: invokevirtual setY : (I)Lorg/bukkit/util/Vector;
/*     */             //   419: invokeinterface setVelocity : (Lorg/bukkit/util/Vector;)V
/*     */             //   424: goto -> 344
/*     */             //   427: aload_0
/*     */             //   428: getfield this$0 : Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;
/*     */             //   431: invokestatic access$1110 : (Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant;)I
/*     */             //   434: pop
/*     */             //   435: return
/*     */             // Line number table:
/*     */             //   Java source line number -> byte code offset
/*     */             //   #78	-> 0
/*     */             //   #79	-> 15
/*     */             //   #80	-> 28
/*     */             //   #81	-> 37
/*     */             //   #82	-> 51
/*     */             //   #83	-> 57
/*     */             //   #84	-> 93
/*     */             //   #85	-> 100
/*     */             //   #86	-> 103
/*     */             //   #88	-> 118
/*     */             //   #89	-> 143
/*     */             //   #91	-> 188
/*     */             //   #92	-> 195
/*     */             //   #94	-> 198
/*     */             //   #95	-> 205
/*     */             //   #97	-> 208
/*     */             //   #98	-> 215
/*     */             //   #100	-> 218
/*     */             //   #103	-> 225
/*     */             //   #104	-> 275
/*     */             //   #105	-> 287
/*     */             //   #106	-> 297
/*     */             //   #107	-> 307
/*     */             //   #109	-> 317
/*     */             //   #110	-> 363
/*     */             //   #111	-> 378
/*     */             //   #112	-> 409
/*     */             //   #114	-> 424
/*     */             //   #115	-> 427
/*     */             //   #118	-> 435
/*     */             // Local variable table:
/*     */             //   start	length	slot	name	descriptor
/*     */             //   93	7	2	zombie	Lorg/bukkit/entity/Zombie;
/*     */             //   28	90	1	iterator	Ljava/util/Iterator;
/*     */             //   409	15	3	vector	Lorg/bukkit/util/Vector;
/*     */             //   363	61	2	e	Lorg/bukkit/entity/Entity;
/*     */             //   0	436	0	this	Lcom/LucasRomier/BetterMobAI/Mobs/Entity/Bosses/BetterGiant$1;
/*     */             // Local variable type table:
/*     */             //   start	length	slot	name	signature
/*  75 */             //   28	90	1	iterator	Ljava/util/Iterator<Lorg/bukkit/entity/Zombie;>; } }, 0L, 20L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void randomAttack() {
/* 123 */     this.nextAttack = GiantAttack.values()[this.random.nextInt((GiantAttack.values()).length)];
/* 124 */     while (this.zombies.size() <= 0 && this.nextAttack.equals(GiantAttack.THROW_MINI_ZOMBIE)) {
/* 125 */       this.nextAttack = GiantAttack.values()[this.random.nextInt((GiantAttack.values()).length)];
/*     */     }
/*     */   }
/*     */   
/*     */   private void earthquake() {
/* 130 */     final Player player = nearest();
/* 131 */     if (player == null)
/*     */       return; 
/* 133 */     if (MobAI.settings.configuration.getBoolean("Giant.Earthquake.Disable")) {
/*     */       return;
/*     */     }
/*     */     
/* 137 */     final double radius = MobAI.settings.configuration.getDouble("Giant.Earthquake.Radius");
/*     */     
/* 139 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/* 140 */       setBusy(true);
/* 141 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 142 */             boolean b = BetterGiant.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Giant.Earthquake.Speed"), 30.0D);
/*     */             boolean handling = false;
/*     */             
/*     */             public void run() {
/* 146 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/* 147 */                 !player.isOnline() || BetterGiant.this.entity.isDead()) {
/* 148 */                 BetterGiant.this.setBusy(false);
/* 149 */                 Bukkit.getScheduler().cancelTask(BetterGiant.this.scheduler);
/*     */                 return;
/*     */               } 
/* 152 */               if (!this.b) {
/* 153 */                 this.b = BetterGiant.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Giant.Earthquake.Speed"), 30.0D);
/*     */               } else {
/* 155 */                 BetterGiant.this.track(BetterGiant.this.entity.getLocation(), 0.0F, 0.0D);
/* 156 */                 if (!this.handling) {
/* 157 */                   BetterGiant.this.entity.setNoDamageTicks(80);
/* 158 */                   BetterGiant.this.entity.setVelocity(new Vector(0, 2, 0));
/* 159 */                   this.handling = true;
/*     */                 } 
/*     */                 
/* 162 */                 if (BetterGiant.this.entity.isOnGround() && this.handling) {
/* 163 */                   for (Entity e : BetterGiant.this.entity.getNearbyEntities(radius, radius, radius)) {
/* 164 */                     if (e.getType().equals(EntityType.PLAYER)) {
/* 165 */                       Vector vector = BetterGiant.this.entity.getLocation().toVector().subtract(e.getLocation().toVector()).normalize();
/* 166 */                       e.setVelocity(vector.multiply(-1).setY(1));
/* 167 */                       ((Player)e).damage(MobAI.settings.configuration.getDouble("Giant.Earthquake.Damage"));
/*     */                     } 
/*     */                   } 
/* 170 */                   BetterGiant.this.setBusy(false);
/* 171 */                   Bukkit.getScheduler().cancelTask(BetterGiant.this.scheduler);
/*     */                 } 
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void lightningStriker() {
/* 180 */     final Player player = nearest();
/* 181 */     if (player == null)
/*     */       return; 
/* 183 */     if (MobAI.settings.configuration.getBoolean("Giant.LightningStriker.Disable")) {
/*     */       return;
/*     */     }
/*     */     
/* 187 */     final double radius = MobAI.settings.configuration.getDouble("Giant.LightningStriker.Radius");
/*     */     
/* 189 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/* 190 */       setBusy(true);
/* 191 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 192 */             boolean b = BetterGiant.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Giant.LightningStriker.Speed"), 30.0D);
/*     */             
/*     */             public void run() {
/* 195 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/* 196 */                 !player.isOnline() || BetterGiant.this.entity.isDead()) {
/* 197 */                 BetterGiant.this.setBusy(false);
/* 198 */                 Bukkit.getScheduler().cancelTask(BetterGiant.this.scheduler);
/*     */                 return;
/*     */               } 
/* 201 */               if (!this.b) {
/* 202 */                 this.b = BetterGiant.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Giant.LightningStriker.Speed"), 30.0D);
/*     */               } else {
/* 204 */                 BetterGiant.this.track(BetterGiant.this.entity.getLocation(), 0.0F, 0.0D);
/* 205 */                 for (Entity e : BetterGiant.this.entity.getNearbyEntities(radius, radius, radius)) {
/* 206 */                   if (e.getType().equals(EntityType.PLAYER)) {
/* 207 */                     e.getWorld().strikeLightning(e.getLocation());
/*     */                   }
/*     */                 } 
/* 210 */                 BetterGiant.this.setBusy(false);
/* 211 */                 Bukkit.getScheduler().cancelTask(BetterGiant.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void throwBoulder() {
/* 219 */     final Player player = nearest();
/* 220 */     if (player == null)
/*     */       return; 
/* 222 */     if (MobAI.settings.configuration.getBoolean("Giant.ThrowBoulder.Disable")) {
/*     */       return;
/*     */     }
/*     */     
/* 226 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/* 227 */       setBusy(true);
/* 228 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 229 */             boolean b = BetterGiant.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Giant.ThrowBoulder.Speed"), 50.0D);
/*     */             
/*     */             public void run() {
/* 232 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/* 233 */                 !player.isOnline() || BetterGiant.this.entity.isDead()) {
/* 234 */                 BetterGiant.this.setBusy(false);
/* 235 */                 Bukkit.getScheduler().cancelTask(BetterGiant.this.scheduler);
/*     */                 return;
/*     */               } 
/* 238 */               if (!this.b) {
/* 239 */                 this.b = BetterGiant.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Giant.ThrowBoulder.Speed"), 50.0D);
/* 240 */                 Location location = BetterGiant.this.entity.getLocation();
/* 241 */                 location.setYaw(player.getLocation().getYaw() * -1.0F);
/* 242 */                 BetterGiant.this.entity.teleport(location);
/*     */               } else {
/* 244 */                 BetterGiant.this.track(BetterGiant.this.entity.getLocation(), 0.0F, 0.0D);
/* 245 */                 Location location = BetterGiant.this.entity.getLocation();
/* 246 */                 location.setYaw(player.getLocation().getYaw() * -1.0F);
/* 247 */                 BetterGiant.this.entity.teleport(location);
/*     */                 
/* 249 */                 Location inFront = BetterGiant.this.inFront().add(0.0D, 7.0D, 0.0D);
/* 250 */                 FallingBlock block = BetterGiant.this.entity.getWorld().spawnFallingBlock(inFront, new MaterialData(Material.COBBLESTONE));
/* 251 */                 double factor = BetterGiant.this.distance2D(player.getLocation()) / 50.0D * -1.5D;
/* 252 */                 Vector vector = inFront.toVector().subtract(player.getLocation().toVector()).normalize();
/* 253 */                 block.setVelocity(vector.multiply(factor).setY(1));
/* 254 */                 BetterGiant.this.trackBoulder(block);
/* 255 */                 BetterGiant.this.throwArm();
/*     */                 
/* 257 */                 BetterGiant.this.setBusy(false);
/* 258 */                 Bukkit.getScheduler().cancelTask(BetterGiant.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */   
/*     */   private double distance2D(Location location) {
/* 266 */     return Math.sqrt(Math.pow(this.entity.getLocation().getX() - location.getX(), 2.0D) + Math.pow(this.entity.getLocation().getZ() - location.getZ(), 2.0D));
/*     */   }
/*     */ 
/*     */   
/*     */   private void trackBoulder(FallingBlock paramFallingBlock) {
/* 271 */     this.boulder = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), () -> { if (paramFallingBlock.isOnGround()) { paramFallingBlock.remove(); double radius = MobAI.settings.configuration.getDouble("Giant.ThrowBoulder.DamageRadius"); for (Entity e : paramFallingBlock.getNearbyEntities(radius, radius, radius)) { if (e.getType().equals(EntityType.PLAYER)) ((LivingEntity)e).damage(MobAI.settings.configuration.getDouble("Giant.ThrowBoulder.Damage"));  }  MaterialData fallingDustData = new MaterialData(Material.COBBLESTONE); paramFallingBlock.getWorld().spawnParticle(Particle.BLOCK_DUST, paramFallingBlock.getLocation(), 40, fallingDustData); paramFallingBlock.getWorld().playSound(paramFallingBlock.getLocation(), Sound.BLOCK_STONE_BREAK, 4.0F, 4.0F); paramFallingBlock.getLocation().getBlock().setType(Material.AIR); Bukkit.getScheduler().cancelTask(this.boulder); }  }0L, 5L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void throwMiniZombie() {
/* 290 */     final Player player = nearest();
/* 291 */     if (player == null)
/*     */       return; 
/* 293 */     if (MobAI.settings.configuration.getBoolean("Giant.ThrowMiniZombie.Disable")) {
/*     */       return;
/*     */     }
/*     */     
/* 297 */     if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
/* 298 */       setBusy(true);
/* 299 */       this.scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 300 */             boolean b = BetterGiant.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Giant.ThrowMiniZombie.Speed"), 50.0D);
/*     */             
/*     */             public void run() {
/* 303 */               if (BetterGiant.this.zombies.size() <= 0) {
/* 304 */                 BetterGiant.this.setBusy(false);
/* 305 */                 Bukkit.getScheduler().cancelTask(BetterGiant.this.scheduler);
/*     */                 
/*     */                 return;
/*     */               } 
/* 309 */               if ((!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) || 
/* 310 */                 !player.isOnline() || BetterGiant.this.entity.isDead()) {
/* 311 */                 BetterGiant.this.setBusy(false);
/* 312 */                 Bukkit.getScheduler().cancelTask(BetterGiant.this.scheduler);
/*     */                 return;
/*     */               } 
/* 315 */               if (!this.b) {
/* 316 */                 this.b = BetterGiant.this.track(player.getLocation(), (float)MobAI.settings.configuration.getDouble("Giant.ThrowMiniZombie.Speed"), 50.0D);
/* 317 */                 Location location = BetterGiant.this.entity.getLocation();
/* 318 */                 location.setYaw(player.getLocation().getYaw() * -1.0F);
/* 319 */                 BetterGiant.this.entity.teleport(location);
/*     */               } else {
/* 321 */                 BetterGiant.this.track(BetterGiant.this.entity.getLocation(), 0.0F, 0.0D);
/* 322 */                 Location location = BetterGiant.this.entity.getLocation();
/* 323 */                 location.setYaw(player.getLocation().getYaw() * -1.0F);
/* 324 */                 BetterGiant.this.entity.teleport(location);
/*     */                 
/* 326 */                 ((Zombie)BetterGiant.this.zombies.get(0)).remove();
/* 327 */                 BetterGiant.this.zombies.remove(0);
/* 328 */                 Location inFront = BetterGiant.this.inFront().add(0.0D, 7.0D, 0.0D);
/* 329 */                 Zombie zombie = (Zombie)BetterGiant.this.entity.getWorld().spawnEntity(inFront, EntityType.ZOMBIE);
/* 330 */                 BetterGiant.this.throwArm();
/* 331 */                 double factor = BetterGiant.this.distance2D(player.getLocation()) / 50.0D * -1.5D;
/* 332 */                 Vector vector = inFront.toVector().subtract(player.getLocation().toVector()).normalize();
/* 333 */                 zombie.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 60, 1));
/* 334 */                 zombie.setVelocity(vector.multiply(factor).setY(1));
/* 335 */                 zombie.setBaby(true);
/*     */                 
/* 337 */                 BetterGiant.this.setBusy(false);
/* 338 */                 Bukkit.getScheduler().cancelTask(BetterGiant.this.scheduler);
/*     */               } 
/*     */             }
/*     */           }0L, 5L);
/*     */     } 
/*     */   }
/*     */   
/*     */   private Location inFront() {
/* 346 */     Vector vec = this.entity.getLocation().toVector();
/* 347 */     Vector dir = this.entity.getLocation().getDirection();
/* 348 */     vec = vec.add(dir.multiply(3.0D));
/* 349 */     return vec.toLocation(this.entity.getWorld());
/*     */   }
/*     */   
/*     */   private void throwArm() {
/* 353 */     PacketPlayOutAnimation packetPlayOutAnimation = new PacketPlayOutAnimation((Entity)this.entityGiantZombie, 0);
/* 354 */     sendPacket((Packet)packetPlayOutAnimation);
/*     */   }
/*     */   
/*     */   private List<Player> nearby() {
/* 358 */     List<Player> list = new ArrayList<>();
/* 359 */     for (Entity entity : this.entity.getNearbyEntities(100.0D, 100.0D, 100.0D)) {
/* 360 */       if (entity.getType().equals(EntityType.PLAYER)) {
/* 361 */         Player player = (Player)entity;
/* 362 */         if (player.getGameMode().equals(GameMode.ADVENTURE) || player.getGameMode().equals(GameMode.SURVIVAL)) list.add(player); 
/*     */       } 
/*     */     } 
/* 365 */     return list;
/*     */   }
/*     */   
/*     */   private Player nearest() {
/* 369 */     double distance = Double.MAX_VALUE;
/* 370 */     Player player = null;
/* 371 */     for (Player p : nearby()) {
/* 372 */       double dist = this.entity.getLocation().distance(p.getLocation());
/* 373 */       if (dist < distance) {
/* 374 */         distance = dist;
/* 375 */         player = p;
/*     */       } 
/*     */     } 
/* 378 */     return player;
/*     */   }
/*     */   
/*     */   private void defaultZombieGoals() {
/* 382 */     this.entityGiantZombie.goalSelector.a(7, (PathfinderGoal)new PathfinderGoalRandomStrollLand((EntityCreature)this.entityGiantZombie, 0.5D));
/* 383 */     this.entityGiantZombie.goalSelector.a(8, (PathfinderGoal)new PathfinderGoalLookAtPlayer((EntityInsentient)this.entityGiantZombie, EntityHuman.class, 8.0F));
/* 384 */     this.entityGiantZombie.goalSelector.a(8, (PathfinderGoal)new PathfinderGoalRandomLookaround((EntityInsentient)this.entityGiantZombie));
/*     */   }
/*     */   
/*     */   private void clearGoals() {
/* 388 */     HashSet<?> goalD = (HashSet)Reflection.getClassFieldObject("d", PathfinderGoalSelector.class, this.entityGiantZombie.goalSelector);
/* 389 */     assert goalD != null;
/* 390 */     goalD.clear();
/* 391 */     HashSet<?> targetD = (HashSet)Reflection.getClassFieldObject("d", PathfinderGoalSelector.class, this.entityGiantZombie.targetSelector);
/* 392 */     assert targetD != null;
/* 393 */     targetD.clear();
/*     */   }
/*     */   
/*     */   private void clearGoals(Entity entity) {
/* 397 */     EntityMonster e = (EntityMonster)((CraftEntity)entity).getHandle();
/*     */     
/* 399 */     HashSet<?> goalD = (HashSet)Reflection.getClassFieldObject("d", PathfinderGoalSelector.class, e.goalSelector);
/* 400 */     assert goalD != null;
/* 401 */     goalD.clear();
/* 402 */     HashSet<?> targetD = (HashSet)Reflection.getClassFieldObject("d", PathfinderGoalSelector.class, e.targetSelector);
/* 403 */     assert targetD != null;
/* 404 */     targetD.clear();
/*     */   }
/*     */   
/*     */   public void doProtection() {
/* 408 */     for (int i = 0; i < 15.0D; i++) {
/* 409 */       double x1 = this.entity.getLocation().getX() + 5.0D * Math.cos(Math.toRadians((36 * i)));
/* 410 */       double x3 = this.entity.getLocation().getZ() + 5.0D * Math.sin(Math.toRadians((36 * i)));
/* 411 */       Location location = new Location(this.entity.getWorld(), x1, this.entity.getLocation().getY() + 8.0D, x3);
/*     */       
/* 413 */       Zombie zombie = (Zombie)this.entity.getWorld().spawnEntity(location, EntityType.ZOMBIE);
/* 414 */       clearGoals((Entity)zombie);
/* 415 */       zombie.setVelocity(new Vector(0, 0, 0));
/* 416 */       zombie.setAI(false);
/* 417 */       zombie.setBaby(true);
/* 418 */       ((CraftLivingEntity)zombie).setMaxHealth(MobAI.settings.configuration.getDouble("Giant.MiniZombieHealth"));
/* 419 */       zombie.setHealth(MobAI.settings.configuration.getDouble("Giant.MiniZombieHealth"));
/*     */       
/* 421 */       this.zombies.add(zombie);
/*     */     } 
/*     */ 
/*     */     
/* 425 */     this.protect = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MobAI.getInstance(), new Runnable() {
/* 426 */           double angle = 0.0D;
/*     */           
/*     */           public void run() {
/* 429 */             if (this.angle > 360.0D) this.angle -= 360.0D;
/*     */             
/* 431 */             if (!BetterGiant.this.entity.isDead() && BetterGiant.this.zombies.size() > 2) {
/* 432 */               double pos = 0.0D;
/* 433 */               double angleSize = 360.0D / BetterGiant.this.zombies.size();
/*     */               
/* 435 */               for (Iterator<Zombie> iterator = BetterGiant.this.zombies.iterator(); iterator.hasNext(); ) {
/* 436 */                 Zombie zombie = iterator.next();
/* 437 */                 if (zombie.isDead()) {
/* 438 */                   iterator.remove();
/*     */                 } else {
/* 440 */                   double x1 = BetterGiant.this.entity.getLocation().getX() + 5.0D * Math.cos(Math.toRadians(angleSize * pos + this.angle));
/* 441 */                   double x3 = BetterGiant.this.entity.getLocation().getZ() + 5.0D * Math.sin(Math.toRadians(angleSize * pos + this.angle));
/* 442 */                   Location location = new Location(BetterGiant.this.entity.getWorld(), x1, BetterGiant.this.entity.getLocation().getY() + 8.0D, x3);
/* 443 */                   zombie.teleport(location);
/*     */                 } 
/* 445 */                 pos++;
/*     */               } 
/*     */             } else {
/* 448 */               for (Iterator<Zombie> iterator = BetterGiant.this.zombies.iterator(); iterator.hasNext(); ) {
/* 449 */                 ((Zombie)iterator.next()).remove();
/* 450 */                 iterator.remove();
/* 451 */                 Zombie zombie = (Zombie)BetterGiant.this.entity.getWorld().spawnEntity(BetterGiant.this.entity.getLocation(), EntityType.ZOMBIE);
/* 452 */                 zombie.setBaby(true);
/*     */               } 
/* 454 */               Bukkit.getScheduler().cancelTask(BetterGiant.this.protect);
/*     */             } 
/* 456 */             this.angle += 4.0D;
/*     */           }
/*     */         }0L, 2L);
/*     */   }
/*     */ }


/* Location:              C:\Users\cheen\Downloads\BetterMobAI-2.11 alpha.jar!\com\LucasRomier\BetterMobAI\Mobs\Entity\Bosses\BetterGiant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
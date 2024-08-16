/*    */ package com.LucasRomier.BetterMobAI;
/*    */ import com.LucasRomier.BetterMobAI.API.Settings;
/*    */ import com.LucasRomier.BetterMobAI.API.VersionTracker;
/*    */ import com.LucasRomier.BetterMobAI.Events.EntityDamageByEntity;
/*    */ import com.LucasRomier.BetterMobAI.Events.ExplosionEvents;
/*    */ import com.LucasRomier.BetterMobAI.Events.PlayerMove;
/*    */ import com.LucasRomier.BetterMobAI.Events.SpawnEvents;
/*    */ import com.LucasRomier.BetterMobAI.Events.TeleportEvents;
/*    */ import com.LucasRomier.BetterMobAI.Mobs.BetterMob;
/*    */ import com.LucasRomier.BetterMobAI.Mobs.Entity.Bosses.BetterGiant;
/*    */ import com.name.util.Metrics;
/*    */ import java.util.HashMap;
/*    */ import javax.annotation.Nonnull;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ 
/*    */ public class MobAI extends JavaPlugin {
/*    */   public static Settings settings;
/*    */   
/*    */   public static MobAI getInstance() {
/* 26 */     return instance;
/*    */   }
/*    */   private static MobAI instance;
/*    */   public void onEnable() {
/* 30 */     instance = this;
/* 31 */     settings = new Settings();
/*    */     
/* 33 */     PluginManager manager = Bukkit.getPluginManager();
/* 34 */     manager.registerEvents((Listener)new EntityTarget(), (Plugin)this);
/* 35 */     manager.registerEvents((Listener)new EntityDamageByEntity(), (Plugin)this);
/* 36 */     manager.registerEvents((Listener)new ExplosionEvents(), (Plugin)this);
/* 37 */     manager.registerEvents((Listener)new ProjectileEvents(), (Plugin)this);
/* 38 */     manager.registerEvents((Listener)new PotionSplash(), (Plugin)this);
/* 39 */     manager.registerEvents((Listener)new TeleportEvents(), (Plugin)this);
/* 40 */     manager.registerEvents((Listener)new SpawnEvents(), (Plugin)this);
/* 41 */     manager.registerEvents((Listener)new PlayerMove(), (Plugin)this);
/* 42 */     manager.registerEvents((Listener)new PlayerJoin(), (Plugin)this);
/*    */     
/* 44 */     BetterSkeleton.lastArrow = new HashMap<>();
/* 45 */     BetterMob.busyEntities = new HashMap<>();
/* 46 */     BetterMob.protectedEntities = new HashMap<>();
/*    */     
/* 48 */     System.out.println("[" + getDescription().getName() + "] Current worlds: " + settings.configuration.getStringList("Worlds"));
/* 49 */     System.out.println("[" + getDescription().getName() + "] Current mobs: " + settings.configuration.getStringList("BetterMobs"));
/*    */     
/* 51 */     initMetrics();
/* 52 */     new VersionTracker();
/*    */     
/* 54 */     BetterGiant.init();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onDisable() {
/* 59 */     BetterGiant.stop();
/*    */   }
/*    */   
/*    */   public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String label, @Nonnull String[] args) {
/* 63 */     if (command.getName().equalsIgnoreCase("BetterMobAI")) {
/* 64 */       if (args.length > 0) {
/* 65 */         if (args[0].equalsIgnoreCase("reload")) {
/* 66 */           if (!(sender instanceof org.bukkit.entity.Player) || sender.hasPermission("BetterMobAI.reload")) {
/*    */             
/* 68 */             sender.sendMessage(ChatColor.DARK_GREEN + "Better MobAI reloaded!");
/* 69 */             settings = new Settings();
/* 70 */           } else if (!sender.hasPermission("BetterMobAI.reload")) {
/* 71 */             sender.sendMessage(ChatColor.DARK_RED + "You do not have the permission!");
/*    */           } 
/*    */         }
/*    */       } else {
/* 75 */         sender.sendMessage(ChatColor.DARK_RED + ">>Better MobAI<<");
/* 76 */         sender.sendMessage(ChatColor.DARK_GREEN + "Version: " + getDescription().getVersion());
/* 77 */         sender.sendMessage(ChatColor.DARK_GREEN + "Authors: " + getDescription().getAuthors());
/* 78 */         sender.sendMessage(ChatColor.DARK_GREEN + "Website: " + getDescription().getWebsite());
/*    */       } 
/*    */     }
/* 81 */     return false;
/*    */   }
/*    */   
/*    */   private void initMetrics() {
/* 85 */     Metrics metrics = new Metrics(this);
/* 86 */     metrics.addCustomChart((Metrics.CustomChart)new Metrics.AdvancedPie("used_plugins") {
/*    */           public HashMap<String, Integer> getValues(HashMap<String, Integer> hashMap) {
/* 88 */             for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
/* 89 */               hashMap.put(plugin.getName(), Integer.valueOf(1));
/*    */             }
/* 91 */             return hashMap;
/*    */           }
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\cheen\Downloads\BetterMobAI-2.11 alpha.jar!\com\LucasRomier\BetterMobAI\MobAI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
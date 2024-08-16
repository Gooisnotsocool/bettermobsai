/*    */ package com.LucasRomier.BetterMobAI.API;
/*    */ 
/*    */ import com.LucasRomier.BetterMobAI.MobAI;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.net.MalformedURLException;
/*    */ import java.net.URL;
/*    */ import java.net.URLConnection;
/*    */ import java.util.Scanner;
/*    */ 
/*    */ 
/*    */ public class VersionTracker
/*    */ {
/*    */   public VersionTracker() {
/* 15 */     if (MobAI.settings.configuration.getBoolean("VersionTracking")) {
/*    */       URL url; InputStream inputStream;
/*    */       try {
/* 18 */         url = new URL("https://raw.githubusercontent.com/LucasRomier/BetterMobAI/master/src/main/resources/plugin.yml");
/* 19 */       } catch (MalformedURLException e) {
/*    */         return;
/*    */       } 
/*    */       
/*    */       try {
/* 24 */         URLConnection connection = url.openConnection();
/* 25 */         connection.setUseCaches(false);
/* 26 */         inputStream = connection.getInputStream();
/* 27 */       } catch (IOException e) {
/* 28 */         System.err.println("/============================================================\\");
/* 29 */         System.err.println("|[Better MobAI] Could not retrieve last Better MobAI version:|");
/* 30 */         System.err.println("|[Better MobAI] You are not connected to the internet!       |");
/* 31 */         System.err.println("\\============================================================/");
/*    */         return;
/*    */       } 
/*    */       try {
/* 35 */         String version = MobAI.getInstance().getDescription().getVersion().replace("version: ", "");
/*    */         
/* 37 */         Scanner scanner = new Scanner(inputStream, "UTF-8");
/* 38 */         scanner.useDelimiter("\\n");
/* 39 */         boolean found = false;
/* 40 */         while (scanner.hasNext()) {
/* 41 */           String line = scanner.nextLine();
/* 42 */           if (line.contains("version: ")) {
/* 43 */             found = true;
/* 44 */             String latest = line.replace("version: ", "").replace("\"", "");
/*    */             
/* 46 */             if (!version.equals(latest)) {
/* 47 */               System.out.println("/============================================================================================================\\");
/* 48 */               System.out.println("|[Better MobAI] There is a new version of Better MobAI available!                                            |");
/* 49 */               System.out.println("|[Better MobAI] Current version: " + version + "                                                                            ".substring(0, 76 - version.length()) + "|");
/* 50 */               System.out.println("|[Better MobAI] Latest version: " + latest + "                                                                             ".substring(0, 77 - latest.length()) + "|");
/* 51 */               System.out.println("|[Better MobAI] Download now: http://dev.bukkit.org/bukkit-plugins/better-mob-ai/#w-installation-and-updating|");
/* 52 */               System.out.println("\\============================================================================================================/");
/*    */               break;
/*    */             } 
/* 55 */             System.out.println("/==================================================\\");
/* 56 */             System.out.println("|[Better MobAI] Better MobAI is up to date!        |");
/* 57 */             System.out.println("|[Better MobAI] Current version: " + version + "!" + "         ".substring(0, 17 - version.length()) + "|");
/* 58 */             System.out.println("\\==================================================/");
/*    */             
/*    */             break;
/*    */           } 
/*    */         } 
/* 63 */         scanner.close();
/* 64 */         inputStream.close();
/* 65 */         if (!found) {
/* 66 */           System.err.println("/==================================================================\\");
/* 67 */           System.err.println("|[Better MobAI] Could not retrieve last Better MobAI version:      |");
/* 68 */           System.err.println("|[Better MobAI] Our team of highly trained monkey is working on it!|");
/* 69 */           System.err.println("\\==================================================================/");
/*    */         } 
/* 71 */       } catch (IOException e) {
/* 72 */         e.printStackTrace();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\cheen\Downloads\BetterMobAI-2.11 alpha.jar!\com\LucasRomier\BetterMobAI\API\VersionTracker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
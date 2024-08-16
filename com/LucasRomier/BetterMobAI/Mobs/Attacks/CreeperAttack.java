/*    */ package com.LucasRomier.BetterMobAI.Mobs.Attacks;
/*    */ 
/*    */ public enum CreeperAttack {
/*  4 */   NORMAL_ATTACK("NORMAL_ATTACK"),
/*  5 */   CHARGED_CREEPER_ATTACK("CHARGED_CREEPER_ATTACK"),
/*  6 */   CHARGED_SUPER_CREEPER_ATTACK("CHARGED_SUPER_CREEPER_ATTACK"),
/*  7 */   IMPLOSION_EXPLOSION_ATTACK("IMPLOSION_EXPLOSION_ATTACK");
/*    */   
/*    */   private final String name;
/*    */   
/*    */   CreeperAttack(String name) {
/* 12 */     this.name = name;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 17 */     return this.name;
/*    */   }
/*    */ }


/* Location:              C:\Users\cheen\Downloads\BetterMobAI-2.11 alpha.jar!\com\LucasRomier\BetterMobAI\Mobs\Attacks\CreeperAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
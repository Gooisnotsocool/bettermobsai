/*    */ package com.LucasRomier.BetterMobAI.Mobs.Attacks;
/*    */ 
/*    */ public enum SpiderAttack {
/*  4 */   NORMAL_ATTACK("NORMAL_ATTACK"),
/*  5 */   SKY_ATTACK("SKY_ATTACK"),
/*  6 */   POISON_ATTACK("POISON_ATTACK"),
/*  7 */   WEB_ATTACK("WEB_ATTACK");
/*    */   
/*    */   private final String name;
/*    */   
/*    */   SpiderAttack(String name) {
/* 12 */     this.name = name;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 17 */     return this.name;
/*    */   }
/*    */ }


/* Location:              C:\Users\cheen\Downloads\BetterMobAI-2.11 alpha.jar!\com\LucasRomier\BetterMobAI\Mobs\Attacks\SpiderAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package com.LucasRomier.BetterMobAI.Mobs.Attacks;
/*    */ 
/*    */ public enum SkeletonAttack {
/*  4 */   NORMAL_ATTACK("NORMAL_ATTACK"),
/*  5 */   ARROW_RAIN_ATTACK("ARROW_RAIN_ATTACK"),
/*  6 */   BURNING_ARROW_ATTACK("BURNING_ARROW_ATTACK"),
/*  7 */   POISONED_ARROW_ATTACK("POISONED_ARROW_ATTACK"),
/*  8 */   NAILING_ARROW_ATTACK("NAILING_ARROW_ATTACK");
/*    */   
/*    */   private final String name;
/*    */   
/*    */   SkeletonAttack(String name) {
/* 13 */     this.name = name;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 18 */     return this.name;
/*    */   }
/*    */ }


/* Location:              C:\Users\cheen\Downloads\BetterMobAI-2.11 alpha.jar!\com\LucasRomier\BetterMobAI\Mobs\Attacks\SkeletonAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
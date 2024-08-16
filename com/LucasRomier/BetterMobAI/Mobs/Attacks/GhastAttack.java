/*    */ package com.LucasRomier.BetterMobAI.Mobs.Attacks;
/*    */ 
/*    */ public enum GhastAttack {
/*  4 */   NORMAL_ATTACK("NORMAL_ATTACK"),
/*  5 */   HIGH_SPEED_FIRE_BALL_ATTACK("HIGH_SPEED_FIRE_BALL_ATTACK"),
/*  6 */   MULTY_FIRE_BALL_ATTACK("MULTY_FIRE_BALL_ATTACK");
/*    */   
/*    */   private final String name;
/*    */   
/*    */   GhastAttack(String name) {
/* 11 */     this.name = name;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 16 */     return this.name;
/*    */   }
/*    */ }


/* Location:              C:\Users\cheen\Downloads\BetterMobAI-2.11 alpha.jar!\com\LucasRomier\BetterMobAI\Mobs\Attacks\GhastAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
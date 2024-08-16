/*    */ package com.LucasRomier.BetterMobAI.Mobs.Attacks;
/*    */ 
/*    */ public enum GuardianAttack {
/*  4 */   NORMAL_ATTACK("NORMAL_ATTACK"),
/*  5 */   SUFFOCATION_VIBES_ATTACK("SUFFOCATION_VIBES_ATTACK"),
/*  6 */   NAILING_VIBES_ATTACK("NAILING_VIBES_ATTACK"),
/*  7 */   INSTANT_LASER_ATTACK("INSTANT_LASER_ATTACK");
/*    */   
/*    */   private final String name;
/*    */   
/*    */   GuardianAttack(String name) {
/* 12 */     this.name = name;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 17 */     return this.name;
/*    */   }
/*    */ }


/* Location:              C:\Users\cheen\Downloads\BetterMobAI-2.11 alpha.jar!\com\LucasRomier\BetterMobAI\Mobs\Attacks\GuardianAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
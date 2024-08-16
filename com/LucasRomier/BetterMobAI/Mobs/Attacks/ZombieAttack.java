/*    */ package com.LucasRomier.BetterMobAI.Mobs.Attacks;
/*    */ 
/*    */ public enum ZombieAttack {
/*  4 */   NORMAL_ATTACK("NORMAL_ATTACK"),
/*  5 */   BLOOD_RUSH_ATTACK("BLOOD_RUSH_ATTACK"),
/*  6 */   VAMPIRE_ATTACK("VAMPIRE_ATTACK"),
/*  7 */   MINIONS_ATTACK("MINIONS_ATTACK");
/*    */   
/*    */   private final String name;
/*    */   
/*    */   ZombieAttack(String name) {
/* 12 */     this.name = name;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 17 */     return this.name;
/*    */   }
/*    */ }


/* Location:              C:\Users\cheen\Downloads\BetterMobAI-2.11 alpha.jar!\com\LucasRomier\BetterMobAI\Mobs\Attacks\ZombieAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
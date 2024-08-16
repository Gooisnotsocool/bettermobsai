/*    */ package com.LucasRomier.BetterMobAI.Mobs.Attacks;
/*    */ 
/*    */ public enum WitchAttack {
/*  4 */   NORMAL_ATTACK("NORMAL_ATTACK"),
/*  5 */   SLOWNESS_ATTACK("SLOWNESS_POTION_ATTACK"),
/*  6 */   POISON_ATTACK("POISON_POTION_ATTACK"),
/*  7 */   FIRE_CIRCLE_ATTACK("FIRE_CIRCLE_ATTACK"),
/*  8 */   BURN_PLAYER_ATTACK("BURN_PLAYER_ATTACK"),
/*  9 */   LAVA_ATTACK("LAVA_ATTACK"),
/* 10 */   BLACK_MAGIC_ATTACK("BLACK_MAGIC_ATTACK"),
/* 11 */   HEAL_EFFECT("HEAL_EFFECT"),
/* 12 */   ENDERMITES_ATTACK("ENDERMITES_ATTACK");
/*    */   
/*    */   private final String name;
/*    */   
/*    */   WitchAttack(String name) {
/* 17 */     this.name = name;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 22 */     return this.name;
/*    */   }
/*    */ }


/* Location:              C:\Users\cheen\Downloads\BetterMobAI-2.11 alpha.jar!\com\LucasRomier\BetterMobAI\Mobs\Attacks\WitchAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
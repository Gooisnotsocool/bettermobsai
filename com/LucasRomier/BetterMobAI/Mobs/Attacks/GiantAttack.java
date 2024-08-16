/*    */ package com.LucasRomier.BetterMobAI.Mobs.Attacks;
/*    */ 
/*    */ public enum GiantAttack {
/*  4 */   THROW_MINI_ZOMBIE("ThrowMiniZombie"),
/*  5 */   THROW_BOULDER("ThrowBoulder"),
/*  6 */   EARTHQUAKE("Earthquake"),
/*  7 */   LIGHTNING_STRIKER("LightningStriker");
/*    */   private String name;
/*    */   
/*    */   GiantAttack(String name) {
/* 11 */     this.name = name;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 15 */     return this.name;
/*    */   }
/*    */ }


/* Location:              C:\Users\cheen\Downloads\BetterMobAI-2.11 alpha.jar!\com\LucasRomier\BetterMobAI\Mobs\Attacks\GiantAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
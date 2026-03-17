package com.narxoz.rpg;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.decorator.BasicAttack;
import com.narxoz.rpg.decorator.CriticalFocusDecorator;
import com.narxoz.rpg.decorator.FireRuneDecorator;
import com.narxoz.rpg.decorator.PoisonCoatingDecorator;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.facade.AdventureResult;
import com.narxoz.rpg.facade.DungeonFacade;
import com.narxoz.rpg.hero.HeroProfile;

public class Main {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║     HOMEWORK 5: DECORATOR + FACADE PATTERNS    ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");

        System.out.println("🎯 PART 1: DECORATOR PATTERN - Runtime Attack Upgrades");
        System.out.println("==================================================");
        
        AttackAction basicStrike = new BasicAttack("Strike", 15);
        System.out.println("🔹 BASE ATTACK:");
        System.out.println("  Name: " + basicStrike.getActionName());
        System.out.println("  Damage: " + basicStrike.getDamage());
        System.out.println("  Effects: " + basicStrike.getEffectSummary());
        
        System.out.println("\n🔸 DECORATED ATTACKS (Runtime combinations):");
        
        AttackAction fireStrike = new FireRuneDecorator(basicStrike);
        System.out.println("  1. " + fireStrike.getActionName());
        System.out.println("     Damage: " + fireStrike.getDamage());
        System.out.println("     Effects: " + fireStrike.getEffectSummary());
        
        AttackAction poisonStrike = new PoisonCoatingDecorator(basicStrike);
        System.out.println("  2. " + poisonStrike.getActionName());
        System.out.println("     Damage: " + poisonStrike.getDamage());
        System.out.println("     Effects: " + poisonStrike.getEffectSummary());
        
        AttackAction criticalStrike = new CriticalFocusDecorator(basicStrike);
        System.out.println("  3. " + criticalStrike.getActionName());
        System.out.println("     Damage: " + criticalStrike.getDamage());
        System.out.println("     Effects: " + criticalStrike.getEffectSummary());
        
        System.out.println("\n🔸 STACKED DECORATORS (Order matters!):");
        
        AttackAction firePoison = new FireRuneDecorator(
                                   new PoisonCoatingDecorator(basicStrike));
        System.out.println("  Fire then Poison:");
        System.out.println("    " + firePoison.getActionName());
        System.out.println("    Damage: " + firePoison.getDamage());
        System.out.println("    Effects: " + firePoison.getEffectSummary());
        
        AttackAction poisonFire = new PoisonCoatingDecorator(
                                   new FireRuneDecorator(basicStrike));
        System.out.println("  Poison then Fire:");
        System.out.println("    " + poisonFire.getActionName());
        System.out.println("    Damage: " + poisonFire.getDamage());
        System.out.println("    Effects: " + poisonFire.getEffectSummary());
        
        AttackAction ultimateStrike = new CriticalFocusDecorator(
                                      new FireRuneDecorator(
                                      new PoisonCoatingDecorator(basicStrike)));
        System.out.println("\n  ULTIMATE (All three):");
        System.out.println("    " + ultimateStrike.getActionName());
        System.out.println("    Damage: " + ultimateStrike.getDamage());
        System.out.println("    Effects: " + ultimateStrike.getEffectSummary());
        
        System.out.println("\n✅ Decorator Pattern Verified: Runtime stacking without class explosion");
        
        System.out.println("\n\n🏰 PART 2: FACADE PATTERN - Simplified Dungeon Flow");
        System.out.println("==================================================");
        
        HeroProfile arthas = new HeroProfile("Arthas (Death Knight)", 180);
        BossEnemy illidan = new BossEnemy("Illidan Stormrage", 250, 25, "Demonic Rage");
        
        System.out.println("Characters Created:");
        System.out.println("  Hero: " + arthas.getStatus());
        System.out.println("  Boss: " + illidan.getStatus());
        
        System.out.println("\n\n⚔️ PART 3: Testing Different Attack Combinations");
        System.out.println("==================================================");
        
        AttackAction[] attacks = {
            basicStrike,
            fireStrike,
            poisonStrike,
            criticalStrike,
            firePoison,
            ultimateStrike
        };
        
        String[] attackNames = {
            "Basic Strike",
            "Fire Strike",
            "Poison Strike",
            "Critical Strike",
            "Fire+Poison Strike",
            "Ultimate Strike"
        };
        
        for (int i = 0; i < attacks.length; i++) {
            System.out.println("\n📊 Test " + (i+1) + ": " + attackNames[i]);
            System.out.println("  Damage: " + attacks[i].getDamage());
            System.out.println("  Effects: " + attacks[i].getEffectSummary());
        }
        
        System.out.println("\n\n🎮 PART 4: Full Dungeon Run (Using Facade)");
        System.out.println("==================================================");
        
        DungeonFacade facade = new DungeonFacade().setRandomSeed(12345L);
        
        AdventureResult result = facade.runAdventure(arthas, illidan, ultimateStrike);
        
        System.out.println("\n" + result);
        
        System.out.println("\n\n🔄 PART 5: Second Run with Different Attack");
        System.out.println("==================================================");
        
        HeroProfile sylvanas = new HeroProfile("Sylvanas Windrunner", 160);
        
        AttackAction poisonCritical = new PoisonCoatingDecorator(
                                      new CriticalFocusDecorator(
                                      new BasicAttack("Arrow Shot", 12)));
        
        AdventureResult result2 = facade.runAdventure(sylvanas, illidan, poisonCritical);
        System.out.println("\n" + result2);
        
        System.out.println("\n\n🔍 PART 6: Architecture Verification");
        System.out.println("==================================================");
        System.out.println("✓ DECORATOR PATTERN:");
        System.out.println("  - AttackAction interface defines component contract");
        System.out.println("  - BasicAttack is concrete component");
        System.out.println("  - ActionDecorator is base decorator");
        System.out.println("  - 3 concrete decorators: FireRune, PoisonCoating, CriticalFocus");
        System.out.println("  - Runtime stacking: " + ultimateStrike.getActionName());
        System.out.println("  - No class explosion: 3 decorators = 7 possible combinations");
        
        System.out.println("\n✓ FACADE PATTERN:");
        System.out.println("  - DungeonFacade provides simple runAdventure() method");
        System.out.println("  - 3 subsystems: PreparationService, BattleService, RewardService");
        System.out.println("  - Main.java only calls facade, not subsystems");
        System.out.println("  - Complex dungeon flow hidden from client");
        
        System.out.println("\n✓ INTEGRATION:");
        System.out.println("  - Hero and Boss interact through facade");
        System.out.println("  - Decorated attacks used in battle");
        System.out.println("  - Full dungeon run completed successfully");
        System.out.println("  - Clear winner, rounds, and reward output");
        
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║            DEMO COMPLETE - SUCCESS             ║");
        System.out.println("╚════════════════════════════════════════════════╝");
    }
}
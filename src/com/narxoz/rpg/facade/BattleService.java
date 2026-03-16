package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.decorator.PoisonCoatingDecorator;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

import java.util.Random;

public class BattleService {
    private Random random = new Random(1L);
    private static final int MAX_ROUNDS = 20;

    public BattleService setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public AdventureResult battle(HeroProfile hero, BossEnemy boss, AttackAction action) {
        AdventureResult result = new AdventureResult();
        
        result.addLine("⚔️ BATTLE START: " + hero.getName() + " vs " + boss.getName());
        result.addLine("Hero uses: " + action.getActionName() + " - " + action.getEffectSummary());
        
        int round = 1;
        boolean heroTurn = random.nextBoolean(); // Random who attacks first
        result.addLine("First attack: " + (heroTurn ? hero.getName() : boss.getName()));
        
        while (hero.isAlive() && boss.isAlive() && round <= MAX_ROUNDS) {
            result.addLine("\n--- ROUND " + round + " ---");
            
            if (heroTurn) {
                // Hero attacks
                int heroDamage = action.getDamage();
                result.addLine(hero.getName() + " attacks with " + action.getActionName() + 
                              " dealing " + heroDamage + " damage!");
                boss.takeDamage(heroDamage);
                result.addDamageDealt(heroDamage);
                result.addLine(boss.getName() + " health: " + boss.getHealth() + "/" + boss.getMaxHealth());
                
                // Apply poison DOT if applicable
                if (action instanceof PoisonCoatingDecorator) {
                    int poisonDamage = ((PoisonCoatingDecorator) action).getPoisonDotDamage();
                    boss.takeDamage(poisonDamage);
                    result.addLine("💀 Poison deals " + poisonDamage + " additional damage!");
                    result.addDamageDealt(poisonDamage);
                }
            } else {
                // Boss attacks
                int bossDamage = calculateBossDamage(boss, round);
                result.addLine(boss.getName() + " attacks with " + boss.getSpecialAbility() + 
                              " dealing " + bossDamage + " damage!");
                hero.takeDamage(bossDamage);
                result.addDamageTaken(bossDamage);
                result.addLine(hero.getName() + " health: " + hero.getHealth() + "/" + hero.getMaxHealth());
            }
            
            // Check if battle ended
            if (!boss.isAlive()) {
                result.addLine("\n🎉 " + boss.getName() + " has been defeated!");
                result.setWinner(hero.getName());
                result.setRounds(round);
                break;
            }
            if (!hero.isAlive()) {
                result.addLine("\n💔 " + hero.getName() + " has been defeated!");
                result.setWinner(boss.getName());
                result.setRounds(round);
                break;
            }
            
            heroTurn = !heroTurn; // Switch turns
            round++;
        }
        
        if (round > MAX_ROUNDS) {
            result.addLine("\n⏰ Battle reached maximum rounds! It's a draw!");
            result.setWinner("Draw");
            result.setRounds(MAX_ROUNDS);
        }
        
        result.addLine("\n⚔️ BATTLE END");
        return result;
    }
    
    private int calculateBossDamage(BossEnemy boss, int round) {
        int baseDamage = boss.getAttackPower();
        
        double enrageMultiplier = 1.0 + (round * 0.05);
        
        double variation = 0.9 + (random.nextDouble() * 0.2);
        
        return (int)(baseDamage * enrageMultiplier * variation);
    }
}
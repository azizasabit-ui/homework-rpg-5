package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

public class PreparationService {
    
    public String prepare(HeroProfile hero, BossEnemy boss, AttackAction action) {
        if (hero == null || boss == null || action == null) {
            return "❌ Preparation failed: Invalid parameters";
        }
        
        StringBuilder prepLog = new StringBuilder();
        prepLog.append("📋 PREPARATION PHASE:\n");
        prepLog.append("  Hero: ").append(hero.getStatus()).append("\n");
        prepLog.append("  Boss: ").append(boss.getStatus()).append("\n");
        prepLog.append("  Attack: ").append(action.getActionName()).append("\n");
        prepLog.append("  Attack Details: ").append(action.getEffectSummary()).append("\n");
        
        if (hero.getHealth() < hero.getMaxHealth() * 0.3) {
            prepLog.append("  ⚠️  Warning: Hero is critically wounded!\n");
        }
        
        if (boss.getHealth() > hero.getMaxHealth() * 2) {
            prepLog.append("  ⚠️  Warning: Boss has overwhelming health advantage!\n");
        }
        
        prepLog.append("  ✅ Preparation complete. Entering dungeon...\n");
        
        return prepLog.toString();
    }
}
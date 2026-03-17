package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

public class DungeonFacade {
    private final PreparationService preparationService = new PreparationService();
    private final BattleService battleService = new BattleService();
    private final RewardService rewardService = new RewardService();

    public DungeonFacade setRandomSeed(long seed) {
        battleService.setRandomSeed(seed);
        rewardService.setRandomSeed(seed);
        return this;
    }

    public AdventureResult runAdventure(HeroProfile hero, BossEnemy boss, AttackAction action) {
        System.out.println("\n🏰 DUNGEON ADVENTURE START");
        System.out.println("==========================");
        
        String preparationSummary = preparationService.prepare(hero, boss, action);
        AdventureResult result = new AdventureResult();
        result.addLine(preparationSummary);
        
        AdventureResult battleResult = battleService.battle(hero, boss, action);
        
        result.setWinner(battleResult.getWinner());
        result.setRounds(battleResult.getRounds());
        result.setTotalDamageDealt(battleResult.getTotalDamageDealt());
        result.setTotalDamageTaken(battleResult.getTotalDamageTaken());
        
        for (String line : battleResult.getLog()) {
            result.addLine(line);
        }
        
        String reward = rewardService.determineReward(result);
        result.setReward(reward);
        result.addLine("\n💰 REWARD: " + reward);
        
        result.addLine("\n==========================");
        result.addLine("🏰 DUNGEON ADVENTURE END");
        
        return result;
    }
}
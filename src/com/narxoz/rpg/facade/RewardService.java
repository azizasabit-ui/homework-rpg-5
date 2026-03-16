package com.narxoz.rpg.facade;

import java.util.Random;

public class RewardService {
    private Random random = new Random(1L);
    
    public RewardService setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }
    
    public String determineReward(AdventureResult battleResult) {
        if (battleResult == null) {
            return "No reward (invalid battle)";
        }
        
        String winner = battleResult.getWinner();
        int rounds = battleResult.getRounds();
        int damageDealt = battleResult.getTotalDamageDealt();
        
        if (winner == null || winner.equals("Draw")) {
            return "Participation trophy: 50 gold";
        }
        
        if (winner.contains("Hero") || winner.contains("Arthas") || winner.contains("Sylvanas")) {
           
            StringBuilder reward = new StringBuilder("Victory reward: ");
            
            int gold = 100;
            String item = "";
            
            if (rounds <= 5) {
                gold += 50;
                reward.append("Quick victory bonus! ");
            }
            
            if (damageDealt > 200) {
                gold += 75;
                reward.append("High damage bonus! ");
            }
            
            if (random.nextInt(100) < 20) {
                String[] rareItems = {"Dragon's Tooth", "Phoenix Feather", "Ancient Relic", "Legendary Sword"};
                item = rareItems[random.nextInt(rareItems.length)];
                reward.append("Rare item found: ").append(item).append("! ");
            } else {
                String[] commonItems = {"Health Potion", "Mana Crystal", "Iron Ore", "Leather Scraps"};
                item = commonItems[random.nextInt(commonItems.length)];
                reward.append("Item found: ").append(item).append(". ");
            }
            
            reward.append(gold).append(" gold");
            return reward.toString();
        } else {
            return "Defeat reward: 25 gold (better luck next time)";
        }
    }
}
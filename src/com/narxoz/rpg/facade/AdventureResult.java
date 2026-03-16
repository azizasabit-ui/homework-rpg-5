package com.narxoz.rpg.facade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdventureResult {
    private String winner;
    private int rounds;
    private String reward;
    private final List<String> log = new ArrayList<>();
    private int totalDamageDealt;
    private int totalDamageTaken;

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public void addLine(String line) {
        log.add(line);
        System.out.println(line);
    }

    public List<String> getLog() {
        return Collections.unmodifiableList(log);
    }
    
    public int getTotalDamageDealt() {
        return totalDamageDealt;
    }

    public void setTotalDamageDealt(int totalDamageDealt) {
        this.totalDamageDealt = totalDamageDealt;
    }

    public int getTotalDamageTaken() {
        return totalDamageTaken;
    }

    public void setTotalDamageTaken(int totalDamageTaken) {
        this.totalDamageTaken = totalDamageTaken;
    }
    
    public void addDamageDealt(int damage) {
        this.totalDamageDealt += damage;
    }
    
    public void addDamageTaken(int damage) {
        this.totalDamageTaken += damage;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n=== ADVENTURE RESULT ===\n");
        sb.append("Winner: ").append(winner).append("\n");
        sb.append("Rounds: ").append(rounds).append("\n");
        sb.append("Reward: ").append(reward).append("\n");
        sb.append("Damage Dealt: ").append(totalDamageDealt).append("\n");
        sb.append("Damage Taken: ").append(totalDamageTaken).append("\n");
        sb.append("Battle Log:\n");
        for (String line : log) {
            sb.append("  ").append(line).append("\n");
        }
        return sb.toString();
    }
}
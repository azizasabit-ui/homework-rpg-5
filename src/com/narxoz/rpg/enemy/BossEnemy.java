package com.narxoz.rpg.enemy;

public class BossEnemy {
    private final String name;
    private int health;
    private final int maxHealth;
    private final int attackPower;
    private final String specialAbility;

    public BossEnemy(String name, int health, int attackPower, String specialAbility) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.attackPower = attackPower;
        this.specialAbility = specialAbility;
    }
    
    public BossEnemy(String name, int health, int attackPower) {
        this(name, health, attackPower, "None");
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }
    
    public int getMaxHealth() {
        return maxHealth;
    }

    public int getAttackPower() {
        return attackPower;
    }
    
    public String getSpecialAbility() {
        return specialAbility;
    }

    public void takeDamage(int amount) {
        health = Math.max(0, health - amount);
    }

    public boolean isAlive() {
        return health > 0;
    }
    
    public String getStatus() {
        return name + " [HP: " + health + "/" + maxHealth + ", Ability: " + specialAbility + "]";
    }
}

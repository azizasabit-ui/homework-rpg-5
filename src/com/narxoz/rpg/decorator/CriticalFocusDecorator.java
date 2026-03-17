package com.narxoz.rpg.decorator;

import java.util.Random;

public class CriticalFocusDecorator extends ActionDecorator {
    private static final int CRITICAL_CHANCE = 20; // 20%
    private static final double CRITICAL_MULTIPLIER = 2.0;
    private static final String CRITICAL_EFFECT = "Critical Strike";
    private Random random;

    public CriticalFocusDecorator(AttackAction wrappedAction) {
        super(wrappedAction);
        this.random = new Random();
    }
    
    public CriticalFocusDecorator(AttackAction wrappedAction, long seed) {
        super(wrappedAction);
        this.random = new Random(seed);
    }

    @Override
    public String getActionName() {
        return "Precise " + super.getActionName();
    }

    @Override
    public int getDamage() {
        int baseDamage = super.getDamage();
        if (isCritical()) {
            return (int)(baseDamage * CRITICAL_MULTIPLIER);
        }
        return baseDamage;
    }

    @Override
    public String getEffectSummary() {
        return super.getEffectSummary() + " + " + CRITICAL_EFFECT + 
               " (" + CRITICAL_CHANCE + "% chance for " + CRITICAL_MULTIPLIER + "x damage)";
    }
    
    private boolean isCritical() {
        return random.nextInt(100) < CRITICAL_CHANCE;
    }
    
    public void setSeed(long seed) {
        this.random = new Random(seed);
    }
}
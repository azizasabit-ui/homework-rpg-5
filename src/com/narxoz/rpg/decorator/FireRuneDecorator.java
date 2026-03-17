package com.narxoz.rpg.decorator;

public class FireRuneDecorator extends ActionDecorator {
    private static final int FIRE_DAMAGE_BONUS = 8;
    private static final String FIRE_EFFECT = "Burning";

    public FireRuneDecorator(AttackAction wrappedAction) {
        super(wrappedAction);
    }

    @Override
    public String getActionName() {
        return "Flaming " + super.getActionName();
    }

    @Override
    public int getDamage() {
        return super.getDamage() + FIRE_DAMAGE_BONUS;
    }

    @Override
    public String getEffectSummary() {
        return super.getEffectSummary() + " + " + FIRE_EFFECT + " (+" + FIRE_DAMAGE_BONUS + " fire damage)";
    }
}
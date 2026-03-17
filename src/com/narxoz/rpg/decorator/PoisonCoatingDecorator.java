package com.narxoz.rpg.decorator;

public class PoisonCoatingDecorator extends ActionDecorator {
    private static final int POISON_DAMAGE_BONUS = 5;
    private static final int POISON_DOT_DAMAGE = 3;
    private static final String POISON_EFFECT = "Toxic";

    public PoisonCoatingDecorator(AttackAction wrappedAction) {
        super(wrappedAction);
    }

    @Override
    public String getActionName() {
        return "Toxic " + super.getActionName();
    }

    @Override
    public int getDamage() {
        return super.getDamage() + POISON_DAMAGE_BONUS;
    }

    @Override
    public String getEffectSummary() {
        return super.getEffectSummary() + " + " + POISON_EFFECT + 
               " (+" + POISON_DAMAGE_BONUS + " initial, +" + POISON_DOT_DAMAGE + " over time)";
    }
    
    public int getPoisonDotDamage() {
        return POISON_DOT_DAMAGE;
    }
}
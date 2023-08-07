package com.kata.rpgcombat.characters;

import org.jetbrains.annotations.NotNull;

public class Melee extends Character {

    public Melee() {
        super("Melee");
        this.attackMaxRange = 2;
    }

    @Override
    protected boolean isWithinAttackRange(@NotNull Character target) {
        return super.isWithinAttackRange(target);
    }
}

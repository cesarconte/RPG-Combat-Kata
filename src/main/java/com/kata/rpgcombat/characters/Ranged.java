package com.kata.rpgcombat.characters;

import org.jetbrains.annotations.NotNull;

public class Ranged extends Character {

        public Ranged() {
                super("Ranged");
                this.attackMaxRange = 20;
        }
        @Override
        protected   boolean isWithinAttackRange(@NotNull Character target) {
                return super.isWithinAttackRange(target);
        }
}

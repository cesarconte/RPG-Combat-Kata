package com.kata.rpgcombat.actions;

import com.kata.rpgcombat.characters.Character;
import com.kata.rpgcombat.damageable.Damageable;
import org.jetbrains.annotations.NotNull;
public interface Actions {
    void dealDamage(@NotNull Damageable target, int damage);
    void heal(@NotNull Character target, int healAmount);
}

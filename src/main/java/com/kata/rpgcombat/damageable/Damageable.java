package com.kata.rpgcombat.damageable;

import com.kata.rpgcombat.characters.Character;
import org.jetbrains.annotations.NotNull;

public interface Damageable {
    void receiveDamage(Damageable source, int damage);
    void heal(@NotNull Character target, int healAmount);
    boolean isAlly(Character other);
}

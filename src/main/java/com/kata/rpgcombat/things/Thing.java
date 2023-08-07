package com.kata.rpgcombat.things;

import com.kata.rpgcombat.damageable.Damageable;

public interface Thing extends Damageable {
    int getHealth();

    boolean isDestroyed();
}


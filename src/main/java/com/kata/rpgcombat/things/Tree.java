package com.kata.rpgcombat.things;

import com.kata.rpgcombat.characters.Character;
import com.kata.rpgcombat.damageable.Damageable;
import com.kata.rpgcombat.factions.Faction;
import org.jetbrains.annotations.NotNull;

public class Tree implements Thing {
    private int health;
    private String faction;

    public Tree() {
        this.health = 2000;
        this.faction = Faction.FACTION_NEUTRAL;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void receiveDamage(Damageable source, int damage) {
        if (damage > 0) {
            health -= damage;
            if (health < 0) {
                health = 0;
            }
        }
    }

    @Override
    public void heal(@NotNull Character target, int healAmount) {
    }

    @Override
    public boolean isAlly(Character other) {
        return false;
    }

    @Override
    public boolean isDestroyed() {
        return health == 0;
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }
}

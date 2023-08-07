/*
package com.kata.rpgcombat.characters;

import com.kata.rpgcombat.actions.Actions;
import com.kata.rpgcombat.factions.Faction;
import org.jetbrains.annotations.NotNull;
import com.kata.rpgcombat.damageable.Damageable;
import com.kata.rpgcombat.things.Thing;


import java.util.ArrayList;
import java.util.List;

public class Character implements Actions, Damageable {
    private String name;
    private int health;
    private int level;
    private boolean alive;
    private int position;
    protected int attackMaxRange;
    private List<Faction> factions;

    public Character(String name) {
        this.name = name;
        this.health = 1000;
        this.level = 1;
        this.alive = true;
        this.position = 0;
        this.attackMaxRange = 0;
        this.factions = new ArrayList<>();
    }

    // Getters and Setters for health, level, and alive

    public String getName() {
        return name;
    }
    public int getHealth() {
        return health;
    }

    public int getLevel() {
        return level;
    }

    public int getPosition() {
        return position;
    }

    public int getAttackMaxRange() {
        return attackMaxRange;
    }

    public List<Faction> getFactions() {
        return factions;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
        if (!alive) {
            health = 0;
        }
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isAlive() {
        return alive;
    }

    @Override
    public void dealDamage(@NotNull Damageable target, int damage) {
        if (target instanceof Character characterTarget) {
            if (isAttackAllowed(characterTarget)) {
                int modifiedDamage = calculateModifiedDamage(characterTarget, damage);
                characterTarget.receiveDamage(this, modifiedDamage);
            }
        } else {
            target.receiveDamage(this, damage);
        }
    }

    boolean isAttackAllowed(@NotNull Character target) {
        return this.isAlive() && target.isAlive() && this != target && !isAlly(target) && isWithinAttackRange(target);
    }


    boolean isWithinAttackRange(@NotNull Character target) {
        return isWithinRangeByPosition(target) && isWithinRangeByAttackMax(target);
    }

    protected boolean isWithinRangeByPosition(@NotNull Character target) {
        int distance = Math.abs(this.position - target.position);
        return distance <= attackMaxRange;
    }

    private boolean isWithinRangeByAttackMax(@NotNull Character target) {
        int attackerMaxRange = getAttackMaxRange();
        int distance = Math.abs(this.position - target.position);
        return distance <= attackerMaxRange;
    }

    private int calculateModifiedDamage(@NotNull Character target, int damage) {
        int levelDifference = target.getLevel() - this.getLevel();
        if (levelDifference >= 5) {
            return damage / 2;
        } else if (levelDifference <= -5) {
            return damage + (damage / 2);
        }
        return damage;
    }

    public int calculateDistance(@NotNull Character target) {
        return Math.abs(this.position - target.position);
    }

    @Override
    public void receiveDamage(Damageable source, int damage) {
        if (this.isAlive() && damage > 0 && !isAlly((Character) source)) {
            this.health -= damage;
            if (this.health <= 0) {
                this.health = 0;
                this.alive = false;
            }
        }
    }

    @Override
    public void heal(@NotNull Character target, int healAmount) {
        if (canBeHealed(target, healAmount)) {
            target.setHealth(Math.min(target.getHealth() + healAmount, 1000));
        }
    }

    private boolean canBeHealed(Character target, int healAmount) {
        return (this == target && this.isAlive() && healAmount > 0) || (isAlly(target) && target.isAlive() && healAmount > 0);}

    public void joinFaction(Faction faction) {
        factions.add(faction);
    }

    public void leaveFaction(Faction faction) {
        factions.remove(faction);
    }

    public boolean isAlly(Character otherCharacter) {
        for (Faction faction : factions) {
            if (otherCharacter.getFactions().contains(faction)) {
                return true;
            }
        }
        return false;
    }
}*/

package com.kata.rpgcombat.characters;

import com.kata.rpgcombat.actions.Actions;
import com.kata.rpgcombat.factions.Faction;
import org.jetbrains.annotations.NotNull;
import com.kata.rpgcombat.damageable.Damageable;

import java.util.ArrayList;
import java.util.List;

public class Character implements Actions, Damageable {

    private String name;
    private int health;
    private int level;

    private boolean alive;
    private int position;
    protected int attackMaxRange;
    private List<Faction> factions;

    public Character(String name) {
        this.name = name;
        this.health = 1000;
        this.level = 1;
        this.alive = true;
        this.position = 0;
        this.attackMaxRange = 0;
        this.factions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getAttackMaxRange() {
        return attackMaxRange;
    }

    public void setAttackMaxRange(int attackMaxRange) {
        this.attackMaxRange = attackMaxRange;
    }

    public List<Faction> getFactions() {
        return factions;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
        if (!alive) {
            health = 0;
        }
    }

    @Override
    public void dealDamage(@NotNull Damageable target, int damage) {
        if (!this.isAlive()) {
            return;
        }

        if (this != target && target instanceof Character characterTarget && isAttackAllowed(characterTarget)) {
            int modifiedDamage = calculateModifiedDamage(characterTarget, damage);
            characterTarget.receiveDamage(this, modifiedDamage);
        } else if (target != this) {
            target.receiveDamage(this, damage);
        }
    }

    @Override
    public void receiveDamage(Damageable source, int damage) {
        if (this.isAlive() && damage > 0 && !isAlly((Character) source)) {
            this.health -= damage;
            if (this.health <= 0) {
                this.health = 0;
                this.alive = false;
            }
        }
    }

    @Override
    public void heal(@NotNull Character target, int healAmount) {
        if (canBeHealed((Character) target, healAmount)) {
            target.setHealth(Math.min(target.getHealth() + healAmount, 1000));
        }
    }

    public boolean isAlive() {
        return alive;
    }

    protected boolean isAttackAllowed(@NotNull Character target) {
        return this.isAlive() && target.isAlive() && this != target && !isAlly(target) && isWithinAttackRange(target);
    }

    protected boolean isWithinAttackRange(@NotNull Character target) {
        int distance = Math.abs(this.position - target.position);
        return distance <= attackMaxRange;
    }

    public int calculateDistance(@NotNull Character target) {
        return Math.abs(this.position - target.position);
    }

    private int calculateModifiedDamage(@NotNull Character target, int damage) {
        int levelDifference = target.getLevel() - this.getLevel();
        if (levelDifference >= 5) {
            return damage / 2;
        } else if (levelDifference <= -5) {
            return damage + (damage / 2);
        }
        return damage;
    }

    private boolean canBeHealed(Character target, int healAmount) {
        return (this == target && this.isAlive() && healAmount > 0) || (isAlly(target) && target.isAlive() && healAmount > 0);
    }

    public void joinFaction(Faction faction) {
        factions.add(faction);
    }

    public void leaveFaction(Faction faction) {
        factions.remove(faction);
    }

    public void addFaction(Faction faction) {
        factions.add(faction);
    }

    public void removeFaction(Faction faction) {
        factions.remove(faction);
    }

    public boolean isAlly(Character otherCharacter) {
        for (Faction faction : factions) {
            if (otherCharacter.getFactions().contains(faction)) {
                return true;
            }
        }
        return false;
    }
}

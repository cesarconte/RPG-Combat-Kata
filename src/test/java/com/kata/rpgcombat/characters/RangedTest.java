package com.kata.rpgcombat.characters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RangedTest {

    private Ranged ranged_attacker;
    private Character target;

    @BeforeEach
    void setUp() {
        ranged_attacker = new Ranged();
        target = new Character("target");
    }

    @Test
    void testRangedHasAttackMaxRangeOf20() {
        assertEquals(20, ranged_attacker.getAttackMaxRange());
    }

    @Test
    void testMeleeCanAttackInRange() {
        int attackerPosition = 2;
        int targetPosition = 18;

        System.out.println("Attacker position: " + attackerPosition);
        System.out.println("Target position: " + targetPosition);

        ranged_attacker.setPosition(attackerPosition);
        target.setPosition(targetPosition);

        int distance = ranged_attacker.calculateDistance(target);
        System.out.println("Distance between attacker and target: " + distance);

        boolean isInRange = distance <= ranged_attacker.getAttackMaxRange();

        System.out.println("Expected in range: " + true);
        System.out.println("Actual in range: " + isInRange);

        assertTrue(isInRange, "Test failed: Melee attacker cannot attack target within range.");
    }
}

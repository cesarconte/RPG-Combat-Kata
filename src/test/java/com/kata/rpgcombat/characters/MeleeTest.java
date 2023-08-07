package com.kata.rpgcombat.characters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MeleeTest {

    private Melee melee_attacker;
    private Character target;

    @BeforeEach
    void setUp() {
        melee_attacker = new Melee();
        target = new Character("target");
    }

    @Test
    void testMeleeHasAttackMaxRangeOf2() {
        assertEquals(2, melee_attacker.getAttackMaxRange());
    }

    @Test
    void testMeleeCanAttackInRange() {
        int attackerPosition = 2;
        int targetPosition = 1;

        System.out.println("Attacker position: " + attackerPosition);
        System.out.println("Target position: " + targetPosition);

        melee_attacker.setPosition(attackerPosition);
        target.setPosition(targetPosition);

        int distance = melee_attacker.calculateDistance(target);
        System.out.println("Distance between attacker and target: " + distance);

        boolean isInRange = distance <= melee_attacker.getAttackMaxRange();

        System.out.println("Expected in range: " + true);
        System.out.println("Actual in range: " + isInRange);

        assertTrue(isInRange, "Test failed: Melee attacker cannot attack target within range.");
    }
}

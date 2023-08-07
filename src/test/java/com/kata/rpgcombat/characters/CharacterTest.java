package com.kata.rpgcombat.characters;

import com.kata.rpgcombat.things.Thing;
import com.kata.rpgcombat.things.Tree;

import com.kata.rpgcombat.factions.Faction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterTest {

    private Character character;
    private Character character1;
    private Character character2;
    private Character attacker;
    private Character target;
    private Faction faction1;
    private Faction faction2;
    private Thing thing;

    @BeforeEach
    void setUp() {
        character = new Character("character");
        character1 = new Character("character1");
        character2 = new Character("character2");
        attacker = new Character("attacker");
        target = new Character("target");
        faction1 = new Faction("Faction 1");
        faction2 = new Faction("Faction 2");
        thing = new Tree();
    }

    @Test
    void newCharacterShouldHave1000Health() {
        assertEquals(1000, character1.getHealth());
    }

    @Test
    void newCharacterShouldHaveLevel1() {
        assertEquals(1, character1.getLevel());
    }

    @Test
    void newCharacterShouldBeAlive() {
        assertTrue(character1.isAlive());
    }

    @Test
    public void testAttackDealDamageToTarget() {
        int initialHealth = target.getHealth();
        int damage = 100;
        attacker.dealDamage(target, damage);
        assertEquals(initialHealth - damage, target.getHealth());
    }

    @Test
    public void testDeadAttackerCannotDealDamage() {
        int damage = 100;
        attacker.setAlive(false);
        attacker.dealDamage(target, damage);
        assertEquals(1000, target.getHealth());
    }

    @Test
    public void testAttackTargetIsDead() {
        target.setHealth(0);
        int initialHealth = target.getHealth();
        int damage = 100;
        attacker.dealDamage(target, damage);
        assertEquals(initialHealth, target.getHealth());
    }

    @Test
    void characterShouldTakeDamage() {
        character1.receiveDamage(character1, 500);
        assertEquals(500, character1.getHealth());
    }

    @Test
    void characterShouldDieWhenHealthReachesZero() {
        character1.receiveDamage(character1, 1000);
        assertFalse(character1.isAlive());
    }

    @Test
    void deadCharacterShouldNotBeHealed() {
        character1.receiveDamage(character1, 1000);
        character1.heal(character1, 500);
        assertFalse(character1.isAlive());
        assertEquals(0, character1.getHealth());
    }

    @Test
    void healingShouldNotExceed1000Health() {
        character1.receiveDamage(character1, 500);
        character1.heal(character1, 700);
        assertEquals(1000, character1.getHealth());
    }

    @Test
    void healingShouldNotWorkOnDeadCharacters() {
        character1.receiveDamage(character1, 1000);
        character1.heal(character1, 500);
        assertFalse(character1.isAlive());
        assertEquals(0, character1.getHealth());
    }

    @Test
    public void testCharacterCannotDealDamageToItself() {
        int damage = 100;
        character1.dealDamage(character1, damage);
        assertEquals(1000, character1.getHealth());
    }

    @Test
    public void testCharacterCanHealItself() {
        int initialHealth = character1.getHealth();
        int healAmount = 500;
        character1.heal(character1, healAmount);
        assertEquals(Math.min(initialHealth + healAmount, 1000), character1.getHealth());
    }

    @Test
    public void testCharacterCannotHealAnotherCharacter() {
        int initialHealthCharacter2 = character2.getHealth();
        int healAmount = 1000;
        character1.heal(character2, healAmount);
        assertEquals(initialHealthCharacter2, character2.getHealth());
    }

    @Test
    public void testCharacterCannotHealAnotherCharacterAfterDamageReceived() {
        int initialHealthCharacter2 = character2.getHealth();
        int damageAmount = 300;
        int healAmount = 200;
        character1.dealDamage(character2, damageAmount);
        character1.heal(character2, healAmount);
        assertEquals(initialHealthCharacter2 - damageAmount, character2.getHealth());
    }

    @Test
    public void testDealDamageAttackerHigherLevel() {
        attacker.setLevel(6);
        target.setLevel(1);
        int damage = 100;
        attacker.dealDamage(target, damage);
        assertEquals(850, target.getHealth(), "Damage should be increased by 50% if target is 5 or more levels below attacker");
    }

    @Test
    public void testDealDamageAttackerLowerLevel() {
        attacker.setLevel(5);
        target.setLevel(10);
        int damage = 100;
        attacker.dealDamage(target, damage);
        assertEquals(950, target.getHealth(), "Damage should be reduced by 50% if target is 5 or more levels above attacker");
    }

    @Test
    public void testDealDamageAttackerSameLevel() {
        attacker.setLevel(5);
        target.setLevel(5);
        int damage = 100;
        attacker.dealDamage(target, damage);
        assertEquals(900, target.getHealth(), "Damage should remain the same if attacker and target have the same level");
    }

    @Test
    public void testDealDamageAttackerCloseLevels() {
        attacker.setLevel(5);
        target.setLevel(9);
        int damage = 100;
        attacker.dealDamage(target, damage);
        assertEquals(900, target.getHealth(), "Damage should remain the same if attacker and target have close levels");
    }

    @Test
    void testNewlyCreatedCharacterDoesNotBelongToFaction() {
        List<Faction> characterFactions = character1.getFactions();
        boolean isCharacterBelongToFaction = characterFactions.isEmpty();

        System.out.println("Initial character factions: " + characterFactions);
        System.out.println("Expected result: true");
        System.out.println("Actual result: " + isCharacterBelongToFaction);

        assertTrue(characterFactions.isEmpty(), "Test failed: Newly created character should not belong to any faction.");
    }

    @Test
    void testJoinAndLeaveFaction() {
        character1.joinFaction(faction1);
        System.out.println(character1.getName() + " joined " + faction1);
        System.out.println("Factions after joining faction1: " + character1.getFactions());
        assertEquals(1, character1.getFactions().size(), "Test failed: Character should belong to one faction after joining.");

        character1.joinFaction(faction2);
        System.out.println(character1.getName() + " joined " + faction2);
        System.out.println("Factions after joining faction2: " + character1.getFactions());
        assertEquals(2, character1.getFactions().size(), "Test failed: Character should belong to two factions after joining.");

        character1.leaveFaction(faction1);
        System.out.println(character1.getName() + " left " + faction1);
        System.out.println("Factions after leaving faction1: " + character1.getFactions());
        assertEquals(1, character1.getFactions().size(), "Test failed: Character should belong to one faction after leaving.");

        character1.leaveFaction(faction2);
        System.out.println(character1.getName() + " left " + faction2);
        System.out.println("Factions after leaving faction2: " + character1.getFactions());
        assertEquals(0, character1.getFactions().size(), "Test failed: Character should belong to no factions after leaving all factions.");
    }


    @Test
    void testCharactersOfSameFactionAreAllies() {
        character1.joinFaction(faction1);
        character2.joinFaction(faction1);

        System.out.println("Character 1 joined " + faction1);
        System.out.println("Character 2 joined " + faction1);

        boolean areAllies = character1.isAlly(character2);
        System.out.println("Expected result: true");
        System.out.println("Actual result: " + areAllies);

        assertTrue(areAllies, "Test failed: Characters of the same faction should be allies.");
    }

    @Test
    void testAlliesCannotDealDamageToEachOther() {
        character1.joinFaction(faction1);
        character2.joinFaction(faction1);
        int initialHealth = character2.getHealth();
        int damage = 100;

        System.out.println("Initial health of target: " + initialHealth);

        character1.dealDamage(character2, damage);
        int actualHealth = character2.getHealth();

        System.out.println("Health after the attack: " + actualHealth);

        assertEquals(initialHealth, actualHealth, "Test failed: Allies should not deal damage to each other.");
    }

    @Test
    void testAlliesCannotDealDamageToEachOther_v2() {
        character1.joinFaction(faction1);
        character2.joinFaction(faction1);
        int initialHealth = character2.getHealth();
        int damage = 100;

        System.out.println("Initial health of target: " + initialHealth);

        if (character1.isAttackAllowed(character2)) {
            character1.dealDamage(character2, damage);
        }

        int actualHealth = character2.getHealth();

        System.out.println("Health after the attack: " + actualHealth);

        assertEquals(initialHealth, actualHealth, "Test failed: Allies should not deal damage to each other.");
    }

    @Test
    void testAlliesCanHealEachOther() {
        character1.joinFaction(faction1);
        character2.joinFaction(faction1);

        int initialHealth = 700;
        character1.setHealth(initialHealth);

        System.out.println("Initial health of target: " + initialHealth);

        character2.heal(character1, 400);
        int healedHealth = character1.getHealth();

        System.out.println("Health after receiving healing: " + healedHealth);

        assertEquals(1000, healedHealth, "Test failed: Allies should be able to heal each other.");
    }

    @Test
    void characterCouldOrShouldDamageThing() {
        character.dealDamage(thing, 500);

        assertEquals(1500, thing.getHealth());
    }
}
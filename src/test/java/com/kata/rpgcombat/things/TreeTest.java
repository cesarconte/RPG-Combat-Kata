package com.kata.rpgcombat.things;

        import com.kata.rpgcombat.characters.Character;
        import com.kata.rpgcombat.factions.Faction;
        import com.kata.rpgcombat.things.Thing;
        import com.kata.rpgcombat.things.Tree;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;

        import static org.junit.jupiter.api.Assertions.*;

public class TreeTest {

    private Character character;
    private Character character1;
    private Character character2;
    private Character attacker;
    private Character target;
    private Faction faction1;
    private Faction faction2;
    private Tree tree;

    @BeforeEach
    void setUp() {
        character = new Character("character");
        character1 = new Character("character1");
        character2 = new Character("character2");
        attacker = new Character("attacker");
        target = new Character("target");
        faction1 = new Faction("Faction 1");
        faction2 = new Faction("Faction 2");
        tree = new Tree();
    }

    @Test
    void testTreeShouldBeDestroyedWhenHealthReachesZero() {

        tree.receiveDamage(null, 2000);

        assertTrue(tree.isDestroyed());
    }

    @Test
    void testTreeIsNeutral() {
        String expectedFaction = Faction.FACTION_NEUTRAL;
        String actualFaction = tree.getFaction();

        assertEquals(expectedFaction, actualFaction, "Tree should be neutral.");
    }

    @Test
    void testTreeDoesNotBelongToAnyOtherFaction() {
        assertFalse(tree.getFaction().equals("SomeOtherFaction"), "Tree should not belong to any other faction.");
    }

    @Test
    void testTreeIsNotAnAllyOfAnyCharacter() {
        assertFalse(tree.isAlly(character), "Tree should not be an ally of any character.");
    }

    @Test
    void testTreeCannotBeHealed() {
        int initialHealth = tree.getHealth();
        tree.heal(character, 50);

        assertEquals(initialHealth, tree.getHealth(), "Tree health should remain the same after attempting to heal.");
    }

    @Test
    void testCharacterCanDamageTree() {
        int initialHealth = tree.getHealth();
        int damage = 200;

        character.dealDamage(tree, damage);

        int actualHealth = tree.getHealth();
        assertEquals(initialHealth - damage, actualHealth, "Test failed: Character should damage non-character things.");
    }
}


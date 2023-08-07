module com.kata.rpgcombatkata {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;


    opens com.kata.rpgcombat to javafx.fxml;
    exports com.kata.rpgcombat;
    exports com.kata.rpgcombat.characters;
    opens com.kata.rpgcombat.characters to javafx.fxml;
    exports com.kata.rpgcombat.things;
    opens com.kata.rpgcombat.things to javafx.fxml;
    exports com.kata.rpgcombat.actions;
    opens com.kata.rpgcombat.actions to javafx.fxml;
    exports com.kata.rpgcombat.factions;
    opens com.kata.rpgcombat.factions to javafx.fxml;
}
package com.kata.rpgcombat.factions;

import java.util.ArrayList;
import java.util.List;

public class Faction {
    public static final String FACTION_NEUTRAL = "Neutral";
    private String name;
    private List<Character> members;

    public Faction(String name) {
        this.name = name;
        members = new ArrayList<>();
    }

    /*public void addMember(Character character) {
        members.add(character);
    }

    public void removeMember(Character character) {
        members.remove(character);
    }*/

    public List<Character> getMembers() {
        return members;
    }

    @Override
    public String toString() {
        return name;
    }
}



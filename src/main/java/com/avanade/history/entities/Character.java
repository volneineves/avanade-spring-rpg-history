package com.avanade.history.entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class Character {
    private String name;
    private short health;
    private short strength;
    private short defense;
    private short agility;
    private short numDice;
    private String faces;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getHealth() {
        return health;
    }

    public void setHealth(short health) {
        this.health = health;
    }

    public short getStrength() {
        return strength;
    }

    public void setStrength(short strength) {
        this.strength = strength;
    }

    public short getDefense() {
        return defense;
    }

    public void setDefense(short defense) {
        this.defense = defense;
    }

    public short getAgility() {
        return agility;
    }

    public void setAgility(short agility) {
        this.agility = agility;
    }

    public short getNumDice() {
        return numDice;
    }

    public void setNumDice(short numDice) {
        this.numDice = numDice;
    }

    public String getFaces() {
        return faces;
    }

    public void setFaces(String faces) {
        this.faces = faces;
    }
}
package com.avanade.history.payloads.requests;

import java.io.Serializable;
import java.util.UUID;

public class HistoryTurnRequest implements Serializable {

    private UUID battleId;
    private Integer attack;
    private Integer defense;
    private Integer damage;
    private Integer numTurn;
    private CharacterRequest attacker;
    private CharacterRequest defender;

    public UUID getBattleId() {
        return battleId;
    }

    public Integer getAttack() {
        return attack;
    }

    public Integer getDefense() {
        return defense;
    }

    public Integer getDamage() {
        return damage;
    }

    public Integer getNumTurn() {
        return numTurn;
    }

    public CharacterRequest getAttacker() {
        return attacker;
    }

    public CharacterRequest getDefender() {
        return defender;
    }

}
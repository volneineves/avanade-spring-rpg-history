package com.avanade.history.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "history_turns", schema = "log")
public class HistoryTurn {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Integer attack;
    private Integer defense;
    private Integer damage;
    private Integer numTurn;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "attacker_name")),
            @AttributeOverride(name = "health", column = @Column(name = "attacker_health")),
            @AttributeOverride(name = "strength", column = @Column(name = "attacker_strength")),
            @AttributeOverride(name = "defense", column = @Column(name = "attacker_defense")),
            @AttributeOverride(name = "agility", column = @Column(name = "attacker_agility")),
            @AttributeOverride(name = "numDice", column = @Column(name = "attacker_numDice")),
            @AttributeOverride(name = "faces", column = @Column(name = "attacker_faces"))
    })
    private Character attacker;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "defender_name")),
            @AttributeOverride(name = "health", column = @Column(name = "defender_health")),
            @AttributeOverride(name = "strength", column = @Column(name = "defender_strength")),
            @AttributeOverride(name = "defense", column = @Column(name = "defender_defense")),
            @AttributeOverride(name = "agility", column = @Column(name = "defender_agility")),
            @AttributeOverride(name = "numDice", column = @Column(name = "defender_numDice")),
            @AttributeOverride(name = "faces", column = @Column(name = "defender_faces"))
    })
    private Character defender;

    @CreationTimestamp
    @Column(nullable = false)
    private Date createdAt;

    public UUID getId() {
        return id;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public Integer getNumTurn() {
        return numTurn;
    }

    public void setNumTurn(Integer numTurn) {
        this.numTurn = numTurn;
    }

    public Character getAttacker() {
        return attacker;
    }

    public void setAttacker(Character attacker) {
        this.attacker = attacker;
    }

    public Character getDefender() {
        return defender;
    }

    public void setDefender(Character defender) {
        this.defender = defender;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}

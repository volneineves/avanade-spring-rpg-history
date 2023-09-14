package com.avanade.history.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "history_battles")
public class HistoryBattle {

    @Id
    private UUID battleId;

    private String initiativeName;
    private String opponentName;
    private String winner;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<HistoryTurn> timeLine = new ArrayList<>();

    public UUID getBattleId() {
        return battleId;
    }

    public void setBattleId(UUID battleId) {
        this.battleId = battleId;
    }

    public String getInitiativeName() {
        return initiativeName;
    }

    public void setInitiativeName(String initiativeName) {
        this.initiativeName = initiativeName;
    }

    public String getOpponentName() {
        return opponentName;
    }

    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public List<HistoryTurn> getTimeLine() {
        timeLine.sort(Comparator.nullsLast(Comparator.comparing(HistoryTurn::getCreatedAt)));
        return timeLine;
    }

    public void addInTimeLine(HistoryTurn historyTurn) {
        this.timeLine.add(historyTurn);
    }
}

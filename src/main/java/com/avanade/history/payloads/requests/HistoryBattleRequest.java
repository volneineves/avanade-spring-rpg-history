package com.avanade.history.payloads.requests;

import java.io.Serializable;
import java.util.UUID;

public class HistoryBattleRequest implements Serializable {

    private UUID battleId;
    private String initiativeName;
    private String opponentName;
    private String winner;

    public UUID getBattleId() {
        return battleId;
    }

    public String getInitiativeName() {
        return initiativeName;
    }

    public String getOpponentName() {
        return opponentName;
    }

    public String getWinner() {
        return winner;
    }
}

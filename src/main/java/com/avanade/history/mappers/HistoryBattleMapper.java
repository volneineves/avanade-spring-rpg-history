package com.avanade.history.mappers;

import com.avanade.history.entities.HistoryBattle;
import com.avanade.history.payloads.requests.HistoryBattleRequest;
import com.avanade.history.payloads.responses.HistoryBattleResponse;
import com.avanade.history.payloads.responses.HistoryTurnResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HistoryBattleMapper {

    private final HistoryTurnMapper historyTurnMapper;

    public HistoryBattleMapper(HistoryTurnMapper historyTurnMapper) {
        this.historyTurnMapper = historyTurnMapper;
    }

    public HistoryBattleResponse toResponse(HistoryBattle entity) {
        List<HistoryTurnResponse> timeLineResponse = entity.getTimeLine()
                .stream()
                .map(historyTurnMapper::toResponse)
                .toList();

        return new HistoryBattleResponse(
                entity.getBattleId(),
                entity.getInitiativeName(),
                entity.getOpponentName(),
                entity.getWinner(),
                timeLineResponse
        );
    }

    public HistoryBattle toEntity(HistoryBattleRequest request) {
        HistoryBattle entity = new HistoryBattle();
        entity.setBattleId(request.getBattleId());
        entity.setInitiativeName(request.getInitiativeName());
        entity.setOpponentName(request.getOpponentName());
        entity.setWinner(request.getWinner());
        return entity;
    }

    public HistoryBattle updateEntity(HistoryBattle entity, HistoryBattleRequest request) {
        entity.setBattleId(request.getBattleId());
        entity.setInitiativeName(request.getInitiativeName());
        entity.setOpponentName(request.getOpponentName());
        entity.setWinner(request.getWinner());
        return entity;
    }
}
package com.avanade.history.mappers;

import com.avanade.history.entities.Character;
import com.avanade.history.entities.HistoryTurn;
import com.avanade.history.payloads.requests.HistoryTurnRequest;
import com.avanade.history.payloads.responses.CharacterResponse;
import com.avanade.history.payloads.responses.HistoryTurnResponse;
import org.springframework.stereotype.Component;

@Component
public class HistoryTurnMapper {

    private final CharacterMapper characterMapper;

    public HistoryTurnMapper(CharacterMapper characterMapper) {
        this.characterMapper = characterMapper;
    }

    public HistoryTurnResponse toResponse(HistoryTurn entity) {
        CharacterResponse attackerResponse = entity.getAttacker() != null ? characterMapper.toResponse(entity.getAttacker()) : null;
        CharacterResponse defenderResponse = entity.getDefender() != null ? characterMapper.toResponse(entity.getDefender()) : null;

        return new HistoryTurnResponse(
                entity.getId(),
                entity.getAttack(),
                entity.getDefense(),
                entity.getDamage(),
                entity.getNumTurn(),
                attackerResponse,
                defenderResponse,
                entity.getCreatedAt()
        );
    }

    public HistoryTurn toEntity(HistoryTurnRequest request) {

        Character attacker = request.getAttacker() != null ?  characterMapper.toEntity(request.getAttacker()) : null;
        Character defender = request.getDefender() != null ?  characterMapper.toEntity(request.getDefender()) : null;

        HistoryTurn entity = new HistoryTurn();
        entity.setAttack(request.getAttack());
        entity.setDefense(request.getDefense());
        entity.setDamage(request.getDamage());
        entity.setNumTurn(request.getNumTurn());
        entity.setAttacker(attacker);
        entity.setDefender(defender);
        return entity;
    }
}
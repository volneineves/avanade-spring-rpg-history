package com.avanade.history.mappers;

import com.avanade.history.entities.Character;
import com.avanade.history.payloads.requests.CharacterRequest;
import com.avanade.history.payloads.responses.CharacterResponse;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapper {

    public CharacterResponse toResponse(Character entity) {
        return new CharacterResponse(
                entity.getName(),
                entity.getHealth(),
                entity.getStrength(),
                entity.getDefense(),
                entity.getAgility(),
                entity.getNumDice(),
                entity.getFaces()
        );
    }

    public Character toEntity(CharacterRequest request) {
        Character entity = new Character();
        entity.setName(request.getName());
        entity.setHealth(request.getHealth());
        entity.setStrength(request.getStrength());
        entity.setDefense(request.getDefense());
        entity.setAgility(request.getAgility());
        entity.setNumDice(request.getNumDice());
        entity.setFaces(request.getFaces());
        return entity;
    }
}
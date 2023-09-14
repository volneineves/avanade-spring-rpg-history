package com.avanade.history.mappers;

import com.avanade.history.entities.Character;
import com.avanade.history.payloads.requests.CharacterRequest;
import com.avanade.history.payloads.responses.CharacterResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CharacterMapperTest {

    @InjectMocks
    private CharacterMapper mapper;

    @Test
    void shouldConvertToResponse() {
        Character character = new Character();
        character.setName("Lobisomen");
        character.setHealth((short) 100);
        character.setStrength((short) 10);
        character.setDefense((short) 5);
        character.setAgility((short) 2);
        character.setNumDice((short) 1);
        character.setFaces("D6");

        CharacterResponse expectedResponse = new CharacterResponse(
                "Lobisomen",
                (short) 100,
                (short) 10,
                (short) 5,
                (short) 2,
                (short) 1,
                "D6"
        );

        CharacterResponse actualResponse = mapper.toResponse(character);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void shouldConvertToEntity() {
        CharacterRequest request = mock(CharacterRequest.class);

        when(request.getName()).thenReturn("Guerreiro");
        when(request.getHealth()).thenReturn((short) 90);
        when(request.getStrength()).thenReturn((short) 8);
        when(request.getDefense()).thenReturn((short) 4);
        when(request.getAgility()).thenReturn((short) 3);
        when(request.getNumDice()).thenReturn((short) 1);
        when(request.getFaces()).thenReturn("D8");

        Character expectedCharacter = new Character();
        expectedCharacter.setName("Guerreiro");
        expectedCharacter.setHealth((short) 90);
        expectedCharacter.setStrength((short) 8);
        expectedCharacter.setDefense((short) 4);
        expectedCharacter.setAgility((short) 3);
        expectedCharacter.setNumDice((short) 1);
        expectedCharacter.setFaces("D8");

        Character actualCharacter = mapper.toEntity(request);

        assertEquals(expectedCharacter.getName(), actualCharacter.getName());
        assertEquals(expectedCharacter.getHealth(), actualCharacter.getHealth());
        assertEquals(expectedCharacter.getStrength(), actualCharacter.getStrength());
        assertEquals(expectedCharacter.getDefense(), actualCharacter.getDefense());
        assertEquals(expectedCharacter.getAgility(), actualCharacter.getAgility());
        assertEquals(expectedCharacter.getNumDice(), actualCharacter.getNumDice());
        assertEquals(expectedCharacter.getFaces(), actualCharacter.getFaces());
    }
}

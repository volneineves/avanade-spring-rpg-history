package com.avanade.history.mappers;

import com.avanade.history.entities.HistoryBattle;
import com.avanade.history.payloads.requests.HistoryBattleRequest;
import com.avanade.history.payloads.responses.HistoryBattleResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class HistoryBattleMapperTest {

    @Mock
    private HistoryTurnMapper historyTurnMapper;

    @InjectMocks
    private HistoryBattleMapper mapper;

    private HistoryBattle historyBattle;
    private HistoryBattleRequest historyBattleRequest;

    @BeforeEach
    void setUp() {
        historyBattle = new HistoryBattle();
        historyBattle.setBattleId(UUID.randomUUID());
        historyBattle.setInitiativeName("Guerreiro");
        historyBattle.setOpponentName("Lobisoment");
        historyBattle.setWinner("Guerreiro");

        historyBattleRequest = mock(HistoryBattleRequest.class);
    }

    @Test
    void shouldConvertToResponse() {
        HistoryBattleResponse actualResponse = mapper.toResponse(historyBattle);

        assertEquals(historyBattle.getBattleId(), actualResponse.battleId());
        assertEquals(historyBattle.getInitiativeName(), actualResponse.initiativeName());
        assertEquals(historyBattle.getOpponentName(), actualResponse.opponentName());
        assertEquals(historyBattle.getWinner(), actualResponse.winner());
    }

    @Test
    void shouldConvertToEntityAndUpdateEntity() {
        HistoryBattle expectedHistoryBattle = new HistoryBattle();
        expectedHistoryBattle.setBattleId(historyBattleRequest.getBattleId());
        expectedHistoryBattle.setInitiativeName(historyBattleRequest.getInitiativeName());
        expectedHistoryBattle.setOpponentName(historyBattleRequest.getOpponentName());
        expectedHistoryBattle.setWinner(historyBattleRequest.getWinner());

        HistoryBattle actualHistoryBattle = mapper.toEntity(historyBattleRequest);

        assertEquals(expectedHistoryBattle.getBattleId(), actualHistoryBattle.getBattleId());
        assertEquals(expectedHistoryBattle.getInitiativeName(), actualHistoryBattle.getInitiativeName());
        assertEquals(expectedHistoryBattle.getOpponentName(), actualHistoryBattle.getOpponentName());
        assertEquals(expectedHistoryBattle.getWinner(), actualHistoryBattle.getWinner());

        HistoryBattle updatedHistoryBattle = mapper.updateEntity(actualHistoryBattle, historyBattleRequest);

        assertEquals(expectedHistoryBattle.getBattleId(), updatedHistoryBattle.getBattleId());
        assertEquals(expectedHistoryBattle.getInitiativeName(), updatedHistoryBattle.getInitiativeName());
        assertEquals(expectedHistoryBattle.getOpponentName(), updatedHistoryBattle.getOpponentName());
        assertEquals(expectedHistoryBattle.getWinner(), updatedHistoryBattle.getWinner());
    }
}

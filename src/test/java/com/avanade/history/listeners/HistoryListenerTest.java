package com.avanade.history.listeners;

import com.avanade.history.amqp.HistoryListener;
import com.avanade.history.payloads.requests.HistoryBattleRequest;
import com.avanade.history.payloads.requests.HistoryTurnRequest;
import com.avanade.history.services.HistoryBattleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.Message;

import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HistoryListenerTest {

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private HistoryBattleService historyBattleService;

    @InjectMocks
    private HistoryListener listener;

    private HistoryBattleRequest historyBattleRequest;
    private HistoryTurnRequest historyTurnRequest;
    private String historyBattleRequestJson;
    private String historyTurnRequestJson;
    private Message message;


    @BeforeEach
    public void setup() {
        message = mock(Message.class);
        historyBattleRequestJson = "{\"battleId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\", \"initiativeName\": \"Player1\", \"opponentName\": \"Player2\", \"winner\": \"Player1\"}";
        historyTurnRequestJson = "{\"battleId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\", \"attack\": 50, \"defense\": 40, \"damage\": 10, \"numTurn\": 1, \"attacker\": {\"name\": \"Guerreiro\", \"health\": 100, \"strength\": 50, \"defense\": 40, \"agility\": 20, \"numDice\": 2, \"faces\": \"D6\"}, \"defender\": {\"name\": \"Lobisomen\", \"health\": 80, \"strength\": 30, \"defense\": 20, \"agility\": 30, \"numDice\": 1, \"faces\": \"D8\"}}";
        historyBattleRequest = new Gson().fromJson(historyBattleRequestJson, HistoryBattleRequest.class);
        historyTurnRequest = new Gson().fromJson(historyTurnRequestJson, HistoryTurnRequest.class);
    }

    @Test
    void shouldProcessHistoryBattle() throws Exception {
        when(message.getBody()).thenReturn(historyBattleRequestJson.getBytes(StandardCharsets.UTF_8));
        when(objectMapper.readValue(historyBattleRequestJson, HistoryBattleRequest.class)).thenReturn(this.historyBattleRequest);

        listener.processHistoryBattle(message);

        verify(objectMapper).readValue(historyBattleRequestJson, HistoryBattleRequest.class);
        verify(historyBattleService).processHistoryBattle(this.historyBattleRequest);
    }

    @Test
    void shouldProcessHistoryTurn() throws Exception {
        when(message.getBody()).thenReturn(historyTurnRequestJson.getBytes(StandardCharsets.UTF_8));
        when(objectMapper.readValue(historyTurnRequestJson, HistoryTurnRequest.class)).thenReturn(historyTurnRequest);

        listener.processHistoryTurn(message);

        verify(objectMapper).readValue(historyTurnRequestJson, HistoryTurnRequest.class);
        verify(historyBattleService).processHistoryTurn(historyTurnRequest);
    }

    @Test
    void shouldProcessHistoryBattleNullMessageBody() throws Exception {
        when(message.getBody()).thenReturn(null);

        listener.processHistoryBattle(message);

        verify(objectMapper, never()).readValue(anyString(), eq(HistoryBattleRequest.class));
        verify(historyBattleService, never()).processHistoryBattle(any(HistoryBattleRequest.class));
    }

}

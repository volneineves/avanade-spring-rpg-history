package com.avanade.history.amqp;

import com.avanade.history.payloads.requests.HistoryBattleRequest;
import com.avanade.history.payloads.requests.HistoryTurnRequest;
import com.avanade.history.services.HistoryBattleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

import static com.avanade.history.constants.HistoryRoutingKeys.HISTORY_BATTLE_RK;
import static com.avanade.history.constants.HistoryRoutingKeys.HISTORY_TURN_RK;

@Component
public class HistoryListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(HistoryListener.class);
    private final ObjectMapper mapper;
    private final HistoryBattleService service;

    public HistoryListener(ObjectMapper mapper, HistoryBattleService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @RabbitListener(queues = HISTORY_BATTLE_RK)
    public void processHistoryBattle(Message message) {
        try {
            String json = new String(message.getBody(), StandardCharsets.UTF_8);
            HistoryBattleRequest historyBattleRequest = mapper.readValue(json, HistoryBattleRequest.class);
            service.processHistoryBattle(historyBattleRequest);
            LOGGER.info("Received message with content historyBattleRequest: {}", historyBattleRequest);
        } catch (JsonProcessingException e) {
            LOGGER.error("JSON processing error: {}", e.getMessage());
        } catch (Exception e) {
            LOGGER.error("An unexpected error occurred: {}", e.getMessage());
        }
    }

    @RabbitListener(queues = HISTORY_TURN_RK)
    public void processHistoryTurn(Message message) {
        try {
            String json = new String(message.getBody(), StandardCharsets.UTF_8);
            HistoryTurnRequest historyTurnRequest = mapper.readValue(json, HistoryTurnRequest.class);
            service.processHistoryTurn(historyTurnRequest);
            LOGGER.info("Received message with content historyTurnRequest: {}", historyTurnRequest);
        } catch (JsonProcessingException e) {
            LOGGER.error("JSON processing error: {}", e.getMessage());
        } catch (Exception e) {
            LOGGER.error("An unexpected error occurred: {}", e.getMessage());
        }
    }
}

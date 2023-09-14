package com.avanade.history.services;

import com.avanade.history.entities.HistoryBattle;
import com.avanade.history.entities.HistoryTurn;
import com.avanade.history.exceptions.ConstraintViolationException;
import com.avanade.history.exceptions.ResourceNotFoundException;
import com.avanade.history.exceptions.UnknownViolationException;
import com.avanade.history.mappers.HistoryBattleMapper;
import com.avanade.history.mappers.HistoryTurnMapper;
import com.avanade.history.payloads.requests.HistoryBattleRequest;
import com.avanade.history.payloads.requests.HistoryTurnRequest;
import com.avanade.history.payloads.responses.HistoryBattleResponse;
import com.avanade.history.repository.HistoryBattleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.avanade.history.constants.ErrorMessages.HISTORY_BATTLE_NOT_FOUND;

@Service
public class HistoryBattleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HistoryBattleService.class);
    private final HistoryBattleRepository repository;
    private final HistoryBattleMapper mapper;
    private final HistoryTurnMapper historyTurnMapper;

    public HistoryBattleService(HistoryBattleRepository repository, HistoryBattleMapper mapper, HistoryTurnMapper historyTurnMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.historyTurnMapper = historyTurnMapper;
    }

    public List<HistoryBattleResponse> getAll() {
        List<HistoryBattle> historyBattles = repository.findAll();
        return historyBattles.stream().map(mapper::toResponse).toList();
    }

    public HistoryBattleResponse getById(UUID id) {
        HistoryBattle historyBattle = findBattleOrThrowError(id);
        return mapper.toResponse(historyBattle);
    }

    public void processHistoryBattle(HistoryBattleRequest request) {
        HistoryBattle historyBattle = getValidHistoryBattle(request);
        saveOrThrowException(historyBattle);
    }

    public void processHistoryTurn(HistoryTurnRequest request) {
        HistoryBattle historyBattle = findBattleOrThrowError(request.getBattleId());
        HistoryTurn historyTurn = historyTurnMapper.toEntity(request);
        historyBattle.addInTimeLine(historyTurn);
        saveOrThrowException(historyBattle);
    }

    private HistoryBattle getValidHistoryBattle(HistoryBattleRequest request) {
        UUID battleId = request.getBattleId();
        Optional<HistoryBattle> optionalHistoryBattle = repository.findById(battleId);
        return optionalHistoryBattle.isPresent()
                ? mapper.updateEntity(optionalHistoryBattle.get(), request)
                : mapper.toEntity(request);
    }

    private HistoryBattle findBattleOrThrowError(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(HISTORY_BATTLE_NOT_FOUND + id));
    }

    private void saveOrThrowException(HistoryBattle historyBattle) {
        try {
            repository.save(historyBattle);
            LOGGER.info("Successfully saved historyBattle with ID: {}", historyBattle.getBattleId());
        } catch (DataIntegrityViolationException e) {
            throw new ConstraintViolationException(e.getMessage());
        } catch (Exception e) {
            throw new UnknownViolationException(e.getMessage());
        }
    }

}

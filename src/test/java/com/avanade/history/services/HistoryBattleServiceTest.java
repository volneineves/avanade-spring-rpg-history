package com.avanade.history.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.avanade.history.exceptions.ConstraintViolationException;
import com.avanade.history.exceptions.ResourceNotFoundException;
import com.avanade.history.exceptions.UnknownViolationException;
import com.avanade.history.mappers.HistoryBattleMapper;
import com.avanade.history.mappers.HistoryTurnMapper;
import com.avanade.history.payloads.requests.HistoryBattleRequest;
import com.avanade.history.payloads.requests.HistoryTurnRequest;
import com.avanade.history.payloads.responses.HistoryBattleResponse;
import com.avanade.history.repository.HistoryBattleRepository;
import com.avanade.history.entities.HistoryBattle;
import com.avanade.history.entities.HistoryTurn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class HistoryBattleServiceTest {

    @Mock
    private HistoryBattleMapper mapper;

    @Mock
    private HistoryBattleRepository repository;

    @Mock
    private HistoryTurnMapper historyTurnMapper;

    @InjectMocks
    private HistoryBattleService service;

    private UUID battleId;
    private HistoryBattleRequest historyBattleRequest;
    HistoryBattleResponse expectedResponse;
    private HistoryTurnRequest historyTurnRequest;
    private HistoryBattle historyBattle;
    private HistoryTurn historyTurn;

    @BeforeEach
    void setUp() {
        battleId = UUID.randomUUID();
        historyBattleRequest = mock(HistoryBattleRequest.class);
        historyTurnRequest = mock(HistoryTurnRequest.class);
        expectedResponse = mock(HistoryBattleResponse.class);
        historyBattle = mock(HistoryBattle.class);
        historyTurn = mock(HistoryTurn.class);
    }

    @Test
    @DisplayName("Should process a new history battle")
    void shouldProcessNewHistoryBattle() {
        when(mapper.toEntity(any(HistoryBattleRequest.class))).thenReturn(historyBattle);

        service.processHistoryBattle(historyBattleRequest);

        verify(repository).save(any(HistoryBattle.class));
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when battle not found")
    void shouldThrowResourceNotFoundException() {
        when(repository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.getById(battleId));
    }

    @Test
    @DisplayName("Should throw ConstraintViolationException on DataIntegrityViolationException")
    void shouldThrowConstraintViolationException() {
        when(mapper.toEntity(any(HistoryBattleRequest.class))).thenReturn(historyBattle);
        doThrow(DataIntegrityViolationException.class).when(repository).save(any(HistoryBattle.class));

        assertThrows(ConstraintViolationException.class, () -> service.processHistoryBattle(historyBattleRequest));
    }

    @Test
    @DisplayName("Should throw UnknownViolationException on generic Exception")
    void shouldThrowUnknownViolationException() {
        when(mapper.toEntity(any(HistoryBattleRequest.class))).thenReturn(historyBattle);
        doThrow(RuntimeException.class).when(repository).save(any(HistoryBattle.class));

        assertThrows(UnknownViolationException.class, () -> service.processHistoryBattle(historyBattleRequest));
    }

    @Test
    @DisplayName("Should process a new history turn")
    void shouldProcessNewHistoryTurn() {
        when(historyTurnMapper.toEntity(any(HistoryTurnRequest.class))).thenReturn(historyTurn);
        when(repository.findById(historyTurnRequest.getBattleId())).thenReturn(Optional.of(historyBattle));

        service.processHistoryTurn(historyTurnRequest);

        verify(historyBattle).addInTimeLine(any(HistoryTurn.class));
        verify(repository).save(any(HistoryBattle.class));
    }

    @Test
    @DisplayName("Should return list of all history battles")
    void shouldReturnAllHistoryBattles() {
        List<HistoryBattle> historyBattles = List.of(historyBattle);
        when(repository.findAll()).thenReturn(historyBattles);

        List<HistoryBattleResponse> responses = service.getAll();

        assertEquals(1, responses.size());
    }

    @Test
    @DisplayName("Should get HistoryBattle by ID and map it to response")
    void shouldGetHistoryBattleById() {
        UUID uuid = UUID.randomUUID();
        when(repository.findById(uuid)).thenReturn(Optional.of(historyBattle));
        when(mapper.toResponse(historyBattle)).thenReturn(expectedResponse);

        HistoryBattleResponse actualResponse = service.getById(uuid);

        assertEquals(expectedResponse, actualResponse);
        verify(mapper).toResponse(historyBattle);
    }

    @Test
    @DisplayName("Should update existing HistoryBattle")
    void shouldUpdateExistingHistoryBattle() {
        when(repository.findById(historyBattleRequest.getBattleId())).thenReturn(Optional.of(historyBattle));
        when(mapper.updateEntity(historyBattle, historyBattleRequest)).thenReturn(historyBattle);

        service.processHistoryBattle(historyBattleRequest);

        verify(mapper).updateEntity(historyBattle, historyBattleRequest);
        verify(repository).save(historyBattle);
    }

}

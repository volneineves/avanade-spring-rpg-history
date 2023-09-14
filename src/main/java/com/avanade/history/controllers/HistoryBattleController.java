package com.avanade.history.controllers;

import com.avanade.history.payloads.responses.HistoryBattleResponse;
import com.avanade.history.services.HistoryBattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/history-battles")
public class HistoryBattleController {

    private final HistoryBattleService service;

    @Autowired
    public HistoryBattleController(HistoryBattleService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<HistoryBattleResponse>> getAll() {
        List<HistoryBattleResponse> historyBattles = service.getAll();
        return ResponseEntity.ok(historyBattles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoryBattleResponse> getById(@PathVariable UUID id) {
        HistoryBattleResponse historyBattle = service.getById(id);
        return ResponseEntity.ok(historyBattle);
    }
}

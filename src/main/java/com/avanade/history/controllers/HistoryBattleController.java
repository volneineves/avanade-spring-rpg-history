package com.avanade.history.controllers;

import com.avanade.history.payloads.responses.HistoryBattleResponse;
import com.avanade.history.payloads.responses.StandardErrorResponse;
import com.avanade.history.services.HistoryBattleService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    public HistoryBattleController(HistoryBattleService service) {
        this.service = service;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all history battles",
                    content = @Content(schema = @Schema(implementation = HistoryBattleResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(schema = @Schema(implementation = StandardErrorResponse.class)))
    })
    @GetMapping
    public ResponseEntity<List<HistoryBattleResponse>> getAll() {
        List<HistoryBattleResponse> historyBattles = service.getAll();
        return ResponseEntity.ok(historyBattles);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the history battle by ID",
                    content = @Content(schema = @Schema(implementation = HistoryBattleResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(schema = @Schema(implementation = StandardErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<HistoryBattleResponse> getById(@PathVariable UUID id) {
        HistoryBattleResponse historyBattle = service.getById(id);
        return ResponseEntity.ok(historyBattle);
    }
}

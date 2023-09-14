package com.avanade.history.payloads.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.UUID;

public record HistoryBattleResponse(
        @JsonProperty("id_da_batalha")
        @Schema(description = "The unique identifier of the battle", example = "f47ac10b-58cc-4372-a567-0e02b2c3d479")
        UUID battleId,

        @JsonProperty("nome_da_iniciativa")
        @Schema(description = "The name of the character who has the initiative", example = "John")
        String initiativeName,

        @JsonProperty("nome_do_oponente")
        @Schema(description = "The name of the opponent character", example = "Monster")
        String opponentName,

        @JsonProperty("vencedor")
        @Schema(description = "The winner of the battle", example = "John")
        String winner,

        @JsonProperty("linha_do_tempo")
        @Schema(description = "The timeline of turns taken during the battle")
        List<HistoryTurnResponse> timeLine
) {}

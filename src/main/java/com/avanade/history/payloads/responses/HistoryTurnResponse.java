package com.avanade.history.payloads.responses;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public record HistoryTurnResponse(
        @JsonProperty("id")
        @Schema(description = "The unique identifier of the turn", example = "f47ac10b-58cc-4372-a567-0e02b2c3d479")
        UUID id,

        @JsonProperty("ataque")
        @Schema(description = "Attack value for the turn", example = "7")
        Integer attack,

        @JsonProperty("defesa")
        @Schema(description = "Defense value for the turn", example = "5")
        Integer defense,

        @JsonProperty("dano")
        @Schema(description = "Damage inflicted during the turn", example = "2")
        Integer damage,

        @JsonProperty("numero_do_turno")
        @Schema(description = "Turn number", example = "1")
        Integer numTurn,

        @JsonProperty("atacante")
        @Schema(description = "Character initiating the attack")
        CharacterResponse attacker,

        @JsonProperty("defensor")
        @Schema(description = "Character defending against the attack")
        CharacterResponse defender,

        @JsonProperty("criado_em")
        @Schema(description = "Timestamp of turn creation", example = "2023-09-14T18:34:29Z")
        Date createdAt
) {
}

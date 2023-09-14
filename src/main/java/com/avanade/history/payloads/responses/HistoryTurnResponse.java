package com.avanade.history.payloads.responses;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public record HistoryTurnResponse(
        @JsonProperty("id") UUID id,
        @JsonProperty("ataque") Integer attack,
        @JsonProperty("defesa") Integer defense,
        @JsonProperty("dano") Integer damage,
        @JsonProperty("numero_do_turno") Integer numTurn,
        @JsonProperty("atacante") CharacterResponse attacker,
        @JsonProperty("defensor") CharacterResponse defender,
        @JsonProperty("criado_em") Date createdAt
) {}

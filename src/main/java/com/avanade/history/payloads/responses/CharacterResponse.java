package com.avanade.history.payloads.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public record CharacterResponse(
        @JsonProperty("nome")
        @Schema(description = "Name of the character", example = "John")
        String name,

        @JsonProperty("saude")
        @Schema(description = "Health points of the character", example = "100")
        short health,

        @JsonProperty("forca")
        @Schema(description = "Strength of the character", example = "10")
        short strength,

        @JsonProperty("defesa")
        @Schema(description = "Defense of the character", example = "8")
        short defense,

        @JsonProperty("agilidade")
        @Schema(description = "Agility of the character", example = "7")
        short agility,

        @JsonProperty("numero_de_dados")
        @Schema(description = "Number of dice the character can roll", example = "2")
        short numDice,

        @JsonProperty("faces")
        @Schema(description = "Types of dice the character can use", example = "d6")
        String faces
) {}

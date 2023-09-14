package com.avanade.history.payloads.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CharacterResponse(
        @JsonProperty("nome") String name,
        @JsonProperty("saude") short health,
        @JsonProperty("forca") short strength,
        @JsonProperty("defesa") short defense,
        @JsonProperty("agilidade") short agility,
        @JsonProperty("numero_de_dados") short numDice,
        @JsonProperty("faces") String faces
) {}

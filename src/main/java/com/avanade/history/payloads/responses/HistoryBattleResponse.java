package com.avanade.history.payloads.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.UUID;

public record HistoryBattleResponse(
        @JsonProperty("id_da_batalha") UUID battleId,
        @JsonProperty("nome_da_iniciativa") String initiativeName,
        @JsonProperty("nome_do_oponente") String opponentName,
        @JsonProperty("vencedor") String winner,
        @JsonProperty("linha_do_tempo") List<HistoryTurnResponse> timeLine
) {}

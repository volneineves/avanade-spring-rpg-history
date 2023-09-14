package com.avanade.history.repository;

import com.avanade.history.entities.HistoryBattle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HistoryBattleRepository extends JpaRepository<HistoryBattle, UUID> {
}

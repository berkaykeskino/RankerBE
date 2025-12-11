package com.berkay.ranker.rankEvent.data.repository;

import com.berkay.ranker.rankEvent.data.entity.RankEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RankEventRepository extends JpaRepository<RankEvent, Long> {
}

package com.berkay.ranker.rankEvent.data.repository;

import com.berkay.ranker.rankEvent.data.entity.RankingType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RankingTypeRepository extends JpaRepository<RankingType, Long> {
}

package com.berkay.ranker.rankEvent.data.repository;

import com.berkay.ranker.rankEvent.data.entity.RankEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RankEventRepository extends JpaRepository<RankEvent, Long> {
    List<RankEvent> findByActor_Id(Long actorId);
    List<RankEvent> findBySubject_Id(Long subjectId);
    List<RankEvent> findByActor_IdAndSubject_Id(Long actorId, Long subjectId);
}

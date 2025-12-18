package com.berkay.ranker.rankEvent.service.implementation;

import com.berkay.ranker.rankEvent.data.dto.RankEventDTO;
import com.berkay.ranker.rankEvent.data.entity.RankEvent;
import com.berkay.ranker.rankEvent.data.mapper.RankEventMapper;
import com.berkay.ranker.rankEvent.data.repository.RankEventRepository;
import com.berkay.ranker.rankEvent.service.RankEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RankEventServiceImpl implements RankEventService {
    private final RankEventRepository repository;
    private final RankEventMapper mapper;

    @Override
    public RankEventDTO createRankEvent(RankEventDTO request){
        RankEvent entity = mapper.toRankEvent(request);
        RankEvent saved = repository.save(entity);
        return mapper.toRankEventDTO(saved);
    }

    @Override
    public List<RankEventDTO> getRankEventList(Long actorId, Long subjectId){
        if(actorId != null && subjectId == null){
            return getRankEventListByActorId(actorId);
        }else if (actorId == null && subjectId != null) {
            return getRankEventListBySubjectId(subjectId);
        } else if (actorId != null) {
            return getRankEventListByActorAndSubjectId(actorId, subjectId);
        }
        return Collections.emptyList();
    }

    private List<RankEventDTO> getRankEventListByActorId(Long actorId){
        return repository.findByActor_Id(actorId)
                .stream()
                .map(mapper::toRankEventDTO)
                .toList();
    }

    private List<RankEventDTO> getRankEventListBySubjectId(Long subjectId){
        return repository.findBySubject_Id(subjectId)
                .stream()
                .map(mapper::toRankEventDTO)
                .toList();
    }

    private List<RankEventDTO> getRankEventListByActorAndSubjectId(Long actorId, Long subjectId){
        return repository.findByActor_IdAndSubject_Id(actorId, subjectId)
                .stream()
                .map(mapper::toRankEventDTO)
                .toList();
    }
}

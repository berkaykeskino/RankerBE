package com.berkay.ranker.rankEvent.service.implementation;

import com.berkay.ranker.rankEvent.data.dto.RankEventDTO;
import com.berkay.ranker.rankEvent.data.entity.RankEvent;
import com.berkay.ranker.rankEvent.data.mapper.RankEventMapper;
import com.berkay.ranker.rankEvent.data.repository.RankEventRepository;
import com.berkay.ranker.rankEvent.service.RankEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}

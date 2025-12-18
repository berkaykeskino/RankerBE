package com.berkay.ranker.rankEvent.service.implementation;

import com.berkay.ranker.common.exceptionHandling.customExceptions.ResourceNotFoundException;
import com.berkay.ranker.rankEvent.data.dto.RankingTypeDTO;
import com.berkay.ranker.rankEvent.data.entity.RankingType;
import com.berkay.ranker.rankEvent.data.mapper.RankingTypeMapper;
import com.berkay.ranker.rankEvent.data.repository.RankingTypeRepository;
import com.berkay.ranker.rankEvent.service.RankingTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RankingTypeServiceImpl implements RankingTypeService {

    private final RankingTypeMapper mapper;
    private final RankingTypeRepository repository;

    @Override
    public RankingTypeDTO createExternalRank(RankingTypeDTO request){
        RankingType entity = mapper.toRankingType(request);
        RankingType saved = repository.save(entity);
        return mapper.toRankingTypeDTO(saved);
    }

    @Override
    public RankingTypeDTO getRankingType(Long rankingTypeId){
        RankingType rankingType = repository.findById(rankingTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("ranking-type.not.found", rankingTypeId));
        return mapper.toRankingTypeDTO(rankingType);
    }
}

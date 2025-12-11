package com.berkay.ranker.likeEvent.service.implementation;

import com.berkay.ranker.common.exceptionHandling.customExceptions.DuplicateResourceException;
import com.berkay.ranker.likeEvent.data.dto.LikeEventDTO;
import com.berkay.ranker.likeEvent.data.entity.LikeEvent;
import com.berkay.ranker.likeEvent.data.mapper.LikeEventMapper;
import com.berkay.ranker.likeEvent.data.repository.LikeEventRepository;
import com.berkay.ranker.likeEvent.service.LikeEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeEventServiceImpl implements LikeEventService {
    private final LikeEventRepository repository;
    private final LikeEventMapper mapper;

    @Override
    public LikeEventDTO createLike(LikeEventDTO likeEventDTO){
        LikeEvent likeEvent = mapper.toLikeEvent(likeEventDTO);
        if (repository.existsByUserAndPost(likeEvent.getUser(), likeEvent.getPost())){
            throw new DuplicateResourceException("like.already.liked");
        }
        LikeEvent savedLikeEvent = repository.save(likeEvent);
        return mapper.toLikeEventDTO(savedLikeEvent);
    }
}

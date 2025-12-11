package com.berkay.ranker.likeEvent.service;

import com.berkay.ranker.common.exceptionHandling.customExceptions.DuplicateResourceException;
import com.berkay.ranker.likeEvent.data.dto.LikeEventDTO;
import com.berkay.ranker.likeEvent.data.entity.LikeEvent;
import com.berkay.ranker.likeEvent.data.mapper.LikeEventMapper;
import com.berkay.ranker.likeEvent.data.repository.LikeEventRepository;
import com.berkay.ranker.likeEvent.service.implementation.LikeEventServiceImpl;
import com.berkay.ranker.util.LikeEventTestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LikeEventServiceImplTest {
    @InjectMocks
    private LikeEventServiceImpl service;
    @Mock
    private LikeEventRepository repository;
    @Mock
    private LikeEventMapper mapper;

    @Test
    void createLike_ThrowsDuplicateResourceException_WhenUserAlreadyLikedPost(){
        LikeEventDTO likeEventDTO = LikeEventTestUtil.getLikeEventDTO(1L, 1L);
        LikeEvent likeEvent = LikeEventTestUtil.getLikeEvent(1L, 1L);
        when(mapper.toLikeEvent(likeEventDTO)).thenReturn(likeEvent);
        when(repository.existsByUserAndPost(likeEvent.getUser(), likeEvent.getPost())).thenReturn(true);
        Exception exception = assertThrows(DuplicateResourceException.class, () -> service.createLike(likeEventDTO));
        assertEquals("like.already.liked", exception.getMessage());
    }

    @Test
    void createLike_ReturnsSavedLikeDTO(){
        LikeEventDTO likeEventDTO = LikeEventTestUtil.getLikeEventDTO(1L, 1L);
        LikeEvent likeEvent = LikeEventTestUtil.getLikeEvent(1L, 1L);
        when(mapper.toLikeEvent(likeEventDTO)).thenReturn(likeEvent);
        when(repository.existsByUserAndPost(likeEvent.getUser(), likeEvent.getPost())).thenReturn(false);
        when(repository.save(likeEvent)).thenReturn(likeEvent);
        when(mapper.toLikeEventDTO(likeEvent)).thenReturn(likeEventDTO);
        LikeEventDTO response = service.createLike(likeEventDTO);
        assertEquals(1L, response.getUserId());
    }
}

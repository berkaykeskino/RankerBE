package com.berkay.ranker.user.service.implementation;

import com.berkay.ranker.user.data.dto.UserDTO;
import com.berkay.ranker.user.data.entity.User;
import com.berkay.ranker.user.data.mapper.UserMapper;
import com.berkay.ranker.user.data.repository.UserRepository;
import com.berkay.ranker.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO userDTO){
        UserDTO savedUserDTO;
        if (userRepository.existsByUsername(userDTO.getUsername())){
            throw new RuntimeException("Username already exists: "+ userDTO.getUsername());
        }else{
            User savedUser = userRepository.save(userMapper.toUser(userDTO));
            savedUserDTO = userMapper.toUserDTO(savedUser);
        }
        return savedUserDTO;
    }
}

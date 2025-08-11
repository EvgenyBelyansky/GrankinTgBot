package org.grtgb.grankintgbot.service;

import lombok.RequiredArgsConstructor;
import org.grtgb.grankintgbot.dto.UserDto;
import org.grtgb.grankintgbot.entity.UserEntity;
import org.grtgb.grankintgbot.mappers.UserMapper;
import org.grtgb.grankintgbot.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void registration(UserDto userDto) {
        UserEntity user = UserMapper.fromDtoToUserEntity(userDto);
        userRepository.save(user);
    }
}

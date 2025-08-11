package org.grtgb.grankintgbot.mappers;

import org.grtgb.grankintgbot.dto.UserDto;
import org.grtgb.grankintgbot.entity.UserEntity;

public class UserMapper {

    public static UserEntity fromDtoToUserEntity(UserDto userDto) {
        return UserEntity.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .build();
    }
}

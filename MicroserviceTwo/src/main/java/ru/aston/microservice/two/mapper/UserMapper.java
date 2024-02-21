package ru.aston.microservice.two.mapper;

import org.springframework.stereotype.Component;
import ru.aston.microservice.two.dto.UserDto;
import ru.aston.microservice.two.entity.User;
import java.time.LocalDateTime;

@Component
public class UserMapper {

    public User userDtoToEntity(UserDto dto) {

        User user = new User();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setEmail(dto.getEmail());
        user.setAddingTime(LocalDateTime.now());

        return user;
    }
}

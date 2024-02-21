package ru.aston.microservice.two.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aston.microservice.two.dto.UserDto;
import ru.aston.microservice.two.entity.User;
import ru.aston.microservice.two.mapper.UserMapper;
import ru.aston.microservice.two.repository.UserRepository;
import java.util.Optional;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;


    @Autowired
    public UserService(UserRepository userRepository,  UserMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }


    public void processingMessage(UserDto dto) {
        User user = userRepository.save(mapper.userDtoToEntity(dto));
        log.info("Saved user  {}, {}", user.getName(), user.getSurname());
    }



    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }


}

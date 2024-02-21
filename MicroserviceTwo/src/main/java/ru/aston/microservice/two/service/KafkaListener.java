package ru.aston.microservice.two.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.aston.microservice.two.dto.UserDto;

@Slf4j
@Component
public class KafkaListener {
    private final UserService userService;

@Autowired
public KafkaListener(UserService userService) {
    this.userService = userService;
}

    @org.springframework.kafka.annotation.KafkaListener(topics= "${kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void performKafkaMessage(UserDto dto){
        log.info("Received user {}, {}", dto.getName(), dto.getSurname());
        userService.processingMessage(dto);



}

}

package ru.aston.microservice.one.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.aston.microservice.one.dto.UserDto;


@Slf4j
@Service
public class KafkaSender {
    private final KafkaTemplate<String, UserDto> kafkaTemplate;
    @Value("${kafka.topic}")
    private String topic;





@Autowired
    public KafkaSender(KafkaTemplate<String, UserDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendMessage(UserDto dto){
        log.info("User sent {}, {}", dto.getName(), dto.getSurname());
        kafkaTemplate.send(topic,dto);
    }


}

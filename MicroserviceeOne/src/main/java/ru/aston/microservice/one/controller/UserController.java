package ru.aston.microservice.one.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aston.microservice.one.dto.UserDto;
import ru.aston.microservice.one.kafka.KafkaSender;
import ru.aston.microservice.one.service.MessageService;


@RestController
@RequestMapping("/app")
@Slf4j
public class UserController {
    private final KafkaSender kafkaProducer;
    private final MessageService messageService;


    @Autowired
    public UserController(KafkaSender kafkaProducer, MessageService messageService) {
        this.kafkaProducer = kafkaProducer;
        this.messageService = messageService;
    }

    @PostMapping("/kafka")
    public ResponseEntity<String> publish(@RequestBody UserDto dto) {
        kafkaProducer.sendMessage(dto);
        return ResponseEntity.ok("Message sent to the topic");

    }

    @GetMapping ("grpc/{id}")
    public ResponseEntity<String> response(@PathVariable("id") Long id) {
       String message = messageService.getMessage(id);

        return ResponseEntity.ok(message);


    }
}

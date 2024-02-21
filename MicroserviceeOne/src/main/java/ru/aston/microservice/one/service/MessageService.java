package ru.aston.microservice.one.service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aston.microservice.one.MessageRequest;
import ru.aston.microservice.one.MessageResponse;
import ru.aston.microservice.one.MessageServiceGrpc;
import ru.aston.microservice.one.entity.Info;


import java.time.LocalDateTime;


@Service
public class MessageService {
    private final InfoService infoService;
@Autowired
    public MessageService(InfoService infoService) {
        this.infoService = infoService;
    }


    public String getMessage(Long id) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565).usePlaintext().build();
        MessageServiceGrpc.MessageServiceBlockingStub stub = MessageServiceGrpc.newBlockingStub(channel);
        MessageResponse messageResponse = stub.getMessage(MessageRequest.newBuilder().setId(id).build());
        channel.shutdown();
        saveMessage(messageResponse.getMessage());
        System.out.println(messageResponse.getMessage());
        return messageResponse.getMessage();
    }


    public void saveMessage(String msg) {
       Info info= new Info();
       info.setMessage(msg);
       info.setAddingMessageTime(LocalDateTime.now());
       infoService.save(info);

    }


}
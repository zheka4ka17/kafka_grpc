package ru.aston.microservice.two.gRPC;

import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import ru.aston.microservice.two.entity.User;
import ru.aston.microservice.two.service.UserService;
import java.util.Optional;

@GRpcService
public class MessageService extends MessageServiceGrpc.MessageServiceImplBase {
    private final UserService userService;

    @Autowired
    public MessageService(UserService userService) {
        this.userService = userService;


    }

    @Override
    public void getMessage(MessageRequest request, StreamObserver<MessageResponse> responseObserver) {
        Optional<User> user = userService.findById(request.getId());
        MessageResponse response = null;

        if (user.isPresent()) {

            response = MessageResponse.newBuilder()
                    .setMessage(user.get().toString())
                    .build();
        } else {
            response = MessageResponse.newBuilder()
                    .setMessage("We didn't find any users with  id - " + request.getId()).build();
        }

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }
}

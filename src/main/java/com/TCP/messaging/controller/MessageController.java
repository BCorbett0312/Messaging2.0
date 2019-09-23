package com.TCP.messaging.controller;

import com.TCP.messaging.dto.MessageDataDTO;
import com.TCP.messaging.dto.MessageResponseDTO;
import com.TCP.messaging.model.Message;
import com.TCP.messaging.model.User;
import com.TCP.messaging.service.MessageService;
import com.TCP.messaging.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
public class MessageController extends TextWebSocketHandler {

    private ModelMapper modelMapper;
    private MessageService messageService;
    private UserService userService;

    @Autowired
    public MessageController(ModelMapper modelMapper, MessageService messageService, UserService userService) {
        this.modelMapper = modelMapper;
        this.messageService = messageService;
        this.userService = userService;
    }

    @PostMapping("/channel/{channelId}/message")
    public ResponseEntity<MessageResponseDTO> create(@RequestBody MessageDataDTO messageDataDTO, @PathVariable Long channelId, HttpServletRequest req){
        User user = userService.whoami(req);
        return new ResponseEntity<>(messageService.create(messageDataDTO, channelId, user), HttpStatus.CREATED);
    }

    @MessageMapping("/chat/message")
    @SendTo("/channel/{channelId}")
    public MessageResponseDTO createWithSock(@RequestBody MessageDataDTO messageDataDTO, @DestinationVariable Long channelId, HttpServletRequest req){
        User user = userService.whoami(req);
        return messageService.create(messageDataDTO, channelId, user);
    }

    @GetMapping("/channel/{channelId}")
    public ResponseEntity<Iterable<Message>> show(@PathVariable Long channelId){
        return new ResponseEntity<>(messageService.show(channelId), HttpStatus.OK);
    }


}

package com.TCP.messaging.service;

import com.TCP.messaging.dto.MessageDataDTO;
import com.TCP.messaging.dto.MessageResponseDTO;
import com.TCP.messaging.model.Message;
import com.TCP.messaging.model.User;
import com.TCP.messaging.repository.MessageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MessageService {

    private MessageRepository messageRepository;
    private ModelMapper modelMapper;

    @Autowired
    public MessageService(MessageRepository messageRepository, ModelMapper modelMapper) {
        this.messageRepository = messageRepository;
        this.modelMapper = modelMapper;
    }

    public Iterable<Message> show(Long channelId) {
        return messageRepository.findAllByChannelId(channelId);
    }

    public MessageResponseDTO create(MessageDataDTO messageDataDTO,Long channelId, User user){
        SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Message message = new Message();
        message.setChannelId(channelId);
        message.setContent(messageDataDTO.getContent());
        message.setUserId(user.getId());
        message.setTimestamp(simpleDateFormat.format(new Date()));
        messageRepository.save(message);
        return this.createNewReponseDTO(message, user);

    }

    public MessageResponseDTO createNewReponseDTO(Message message, User user){
        MessageResponseDTO messageResponseDTO = new MessageResponseDTO();
        messageResponseDTO.setFromUserName(user.getUsername());
        messageResponseDTO.setTimestamp(message.getTimestamp());
        messageResponseDTO.setContent(message.getContent());
        return messageResponseDTO;
    }

}



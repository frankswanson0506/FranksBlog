package com.myblog.myblog.service;

import com.myblog.myblog.model.Message;
import com.myblog.myblog.model.MessageRequest;
import com.myblog.myblog.repository.MessageRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    // 构造器注入Repository
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message create(MessageRequest request) {
        // 简单业务校验
        if(request.content == null || request.content.isBlank()){
            throw new RuntimeException("Message content cannot be empty");
        }
        // 请求DTO → 存储实体对象
        Message msg = new Message();
        msg.setContent(request.content);
        return messageRepository.save(msg);
    }

    public List<Message> list() {
        return messageRepository.findAll();
    }

    public Optional<Message> findById(Long id) {
        return messageRepository.findById(id);
    }
}
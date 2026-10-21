package com.myblog.myblog.repository;

import com.myblog.myblog.mapper.MessageMapper;
import com.myblog.myblog.model.Message;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class DbMessageRepository implements MessageRepository {

    private final MessageMapper messageMapper;

    public DbMessageRepository(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    @Override
    public Message save(Message message) {
        messageMapper.insert(message);
        return message;
    }

    @Override
    public List<Message> findAll() {
        return messageMapper.selectList(null);
    }

    @Override
    public Optional<Message> findById(Long id) {
        return Optional.ofNullable(messageMapper.selectById(id));
    }
}
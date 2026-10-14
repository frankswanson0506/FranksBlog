package com.myblog.myblog.repository;

import com.myblog.myblog.model.Message;
import java.util.List;
import java.util.Optional;

public interface MessageRepository {
    Message save(Message message);
    List<Message> findAll();
    Optional<Message> findById(Long id);
}
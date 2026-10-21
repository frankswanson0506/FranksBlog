package com.myblog.myblog.repository;

import com.myblog.myblog.model.Message;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Repository
public class InMemoryMessageRepository implements MessageRepository {

    // 原先Controller中的内存存储，迁移到此
    private final List<Message> store = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public Message save(Message message) {
        message.setId(nextId++);
        store.add(message);
        return message;
    }

    @Override
    public List<Message> findAll() {
        return store;
    }

    @Override
    public Optional<Message> findById(Long id) {
        return store.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst();
    }
}

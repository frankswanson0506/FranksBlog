package com.myblog.myblog.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myblog.myblog.mapper.MessageMapper;
import com.myblog.myblog.model.Message;
import com.myblog.myblog.model.MessageRequest;
import com.myblog.myblog.model.PageResult;
import com.myblog.myblog.repository.MessageRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    // 构造器注入：同时注入 Repository 和 Mapper
    public MessageService(MessageRepository messageRepository, MessageMapper messageMapper) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
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

    public PageResult<Message> search(String keyword, long page, long size){
        LambdaQueryWrapper<Message> qw = new LambdaQueryWrapper<>();
        // keyword不为空，执行模糊like查询
        if(keyword != null && !keyword.isBlank()){
            qw.like(Message::getContent, keyword);
        }
        IPage<Message> mpPage = messageMapper.selectPage(new Page<>(page,size), qw);
        return PageResult.of(mpPage);
    }
}
package com.myblog.myblog.controller;

import com.myblog.myblog.model.Message;
import com.myblog.myblog.model.MessageRequest;
import com.myblog.myblog.service.MessageService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class MessageController {

    // 构造器注入 MessageService（Spring自动提供实例）
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    // 提交留言 POST /messages
    @PostMapping("/messages")
    public Message create(@RequestBody MessageRequest request){
        return messageService.create(request);
    }

    // 获取全部留言 GET /messages
    @GetMapping("/messages")
    public List<Message> list(){
        return messageService.list();
    }

    // 根据id查询单条留言 GET /messages/{id}
    @GetMapping("/messages/{id}")
    public Message findById(@PathVariable Long id){
        Optional<Message> opt = messageService.findById(id);
        // 找不到抛出异常
        return opt.orElseThrow(() -> new RuntimeException("Message not found"));
    }
}
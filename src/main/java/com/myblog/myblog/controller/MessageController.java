package com.myblog.myblog.controller;

import com.myblog.myblog.model.Message;
import com.myblog.myblog.model.MessageRequest;
import com.myblog.myblog.service.MessageService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import com.myblog.myblog.model.PageResult;

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

    // 根据id查询单条留言 GET /messages/{id}
    @GetMapping("/messages/{id}")
    public Message findById(@PathVariable Long id){
        Optional<Message> opt = messageService.findById(id);
        // 找不到抛出异常
        return opt.orElseThrow(() -> new RuntimeException("Message not found"));
    }
    @GetMapping("/messages")
    public PageResult<Message> listSearch(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "1") long page,
            @RequestParam(defaultValue = "5") long size
    ){
        return messageService.search(keyword, page, size);
    }
}
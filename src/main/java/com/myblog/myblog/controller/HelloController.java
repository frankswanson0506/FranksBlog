package com.myblog.myblog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.myblog.myblog.model.BlogProfile;
import com.myblog.myblog.model.MeResponse;
import com.myblog.myblog.model.PersonalProfile;
import com.myblog.myblog.model.MessageRequest;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class HelloController {

    private final List<MessageRequest> messages = new ArrayList<>();

    @GetMapping("/hello")
    public String hello() {
        return "Hello, I'm Frank . I Exist.";
    }
    @GetMapping("/me")
    public MeResponse me() {
        MeResponse meResponse = new MeResponse();
        meResponse.name = "Frank's Blog";
        meResponse.owner = "Frank";
        meResponse.hobby = "travel";
        meResponse.favoriteCity = "San Francisco";
        return meResponse;
    }
    @GetMapping("/instagram")
    public String instagram() {
        return "My account is frankyanghm."+
                "My favorite influencer is aliciajanehk.";
    }
    @GetMapping("/oldprofile")
    public BlogProfile oldprofile() {
        BlogProfile blogProfile = new BlogProfile();
        blogProfile.name = "MyBlog";
        blogProfile.owner = "Frank";
        blogProfile.message = "Hi!";
        return blogProfile;
    }
    @GetMapping("/profile")
    public PersonalProfile profile(){
        PersonalProfile me = new PersonalProfile();
        me.blogName = "Frank's Blog";
        me.owner = "Frank";
        me.introduction = "Hi...";
        me.interests = "maps&architecture";
        me.vision = "100% perfect work&life balance";
        me.contact = "frankyanghm";
        return me;
    }
    @PostMapping("/messages")
    public MessageRequest create(@RequestBody MessageRequest request){
        messages.add(request);
        return request;
    }
    @GetMapping("/messages")
    public List<MessageRequest> list(){
        return messages;
    }

    @GetMapping("/messages/{index}")
    public MessageRequest getOne(@PathVariable int index) {
        return messages.get(index);
    }
}

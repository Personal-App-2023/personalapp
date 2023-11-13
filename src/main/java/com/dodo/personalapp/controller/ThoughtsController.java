package com.dodo.personalapp.controller;

import com.dodo.personalapp.dto.Feeling;
import com.dodo.personalapp.dto.Thought;
import com.dodo.personalapp.entity.UserThoughts;
import com.dodo.personalapp.service.ThougthsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personal")
@CrossOrigin
public class ThoughtsController {
    @Autowired
    ThougthsService service;

    @PostMapping("/thought")
    public UserThoughts save(@RequestBody UserThoughts thoughts){
        return service.save(thoughts);
    }

    @GetMapping("/emotions/{userId}")
    public List<Feeling> findEmotionsByUserId(@PathVariable int userId)
    {
        return service.findEmotionsByUserId(userId);
    }

    @GetMapping("/thoughts/{userId}/{emotion}")
    public List<Thought> findThoughtsByUserEmotion(@PathVariable String emotion, @PathVariable int userId)
    {
        return service.findThoughtsByUserEmotion(emotion,userId);
    }
}

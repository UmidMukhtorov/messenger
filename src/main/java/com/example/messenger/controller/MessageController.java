package com.example.messenger.controller;

import com.example.messenger.dto.MessageDTO;
import com.example.messenger.dto.ResponseData;
import com.example.messenger.service.impl.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/messages")
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/add")
    public ResponseData add(@RequestBody MessageDTO dto) {
        return messageService.addMessage(dto);
    }

    @PostMapping("/get")
    public ResponseData getMessages(@RequestBody MessageDTO dto) {
        return messageService.get(dto);
    }

}

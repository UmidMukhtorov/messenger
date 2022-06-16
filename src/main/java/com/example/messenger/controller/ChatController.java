package com.example.messenger.controller;


import com.example.messenger.dto.ChatDTO;
import com.example.messenger.dto.GetChatDTO;
import com.example.messenger.dto.ResponseData;
import com.example.messenger.service.impl.ChatService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/chats")
@Slf4j
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/add")
    public ResponseEntity<ResponseData> addChats(@RequestBody ChatDTO dto) {
        log.info("REST request to addChat dto : {}", dto);
        return ResponseEntity.ok(chatService.addChat(dto));
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseData> getChats(@RequestBody GetChatDTO dto) {
        log.info("REST request to addChat dto : {}", dto);
        return ResponseEntity.ok(chatService.getChatsByUser(dto));
    }
}

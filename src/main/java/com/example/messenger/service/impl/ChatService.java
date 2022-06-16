package com.example.messenger.service.impl;

import com.example.messenger.dto.ChatDTO;
import com.example.messenger.dto.GetChatDTO;
import com.example.messenger.dto.ResponseData;

public interface ChatService {
    ResponseData addChat(ChatDTO dto);

    ResponseData getChatsByUser(GetChatDTO user);
}

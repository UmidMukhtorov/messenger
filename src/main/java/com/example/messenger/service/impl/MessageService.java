package com.example.messenger.service.impl;

import com.example.messenger.dto.MessageDTO;
import com.example.messenger.dto.ResponseData;

public interface MessageService {
    ResponseData addMessage(MessageDTO dto);

    ResponseData get(MessageDTO dto);
}

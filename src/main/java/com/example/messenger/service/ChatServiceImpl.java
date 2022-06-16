package com.example.messenger.service;

import com.example.messenger.domain.Chat;
import com.example.messenger.dto.ChatDTO;
import com.example.messenger.dto.GetChatDTO;
import com.example.messenger.dto.ResponseData;
import com.example.messenger.repository.ChatRepository;
import com.example.messenger.service.impl.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService {

    private final ChatRepository repository;

    public ChatServiceImpl(ChatRepository repository) {
        this.repository = repository;
    }


    private Optional<Chat> findChatName(String name) {
        return repository.findByName(name);
    }

    @Override
    public ResponseData addChat(ChatDTO dto) {
        ResponseData result = new ResponseData<>();
        try {
            if (dto.getName() == null) {
                result.setMessage("chat name is null");
                result.setSuccess(false);
                result.setErrorCode("-2");
            } else if (findChatName(dto.getName()).isEmpty()) {
                Chat save = repository.save(dto.mapToEntity());
                result.setSuccess(true);
                result.setData(save.getId());
                result.setErrorCode("0");
            } else {
                Chat chat = findChatName(dto.getName()).get();
                chat.setUsers(dto.mapToEntity().getUsers());
                repository.save(chat);
                result.setMessage("success");
                result.setErrorCode("0");
                result.setData(chat);
                result.setSuccess(true);
            }
        } catch (Exception e) {
            log.error("error cause exception : {}", e.getMessage());
            result.setSuccess(false);
            result.setMessage("Error when saving to DataBase");
            result.setErrorCode(String.valueOf(HttpStatus.CONFLICT.value()));
        }
        return result;
    }

    @Override
    public ResponseData getChatsByUser(GetChatDTO dto) {
        List<Chat> chats = repository.findByUserId(dto.getUser());
        ResponseData<List<Chat>> responseData = new ResponseData<>();
        responseData.setData(chats);
        responseData.setSuccess(true);
        responseData.setMessage("success");
        responseData.setErrorCode("0");
        return responseData;
    }


}

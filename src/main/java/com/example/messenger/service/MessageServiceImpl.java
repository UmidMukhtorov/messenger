package com.example.messenger.service;

import com.example.messenger.domain.MessageType;
import com.example.messenger.domain.Messages;
import com.example.messenger.dto.MessageDTO;
import com.example.messenger.dto.ResponseData;
import com.example.messenger.repository.MessageRepository;
import com.example.messenger.service.impl.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public String uploadFile(String content, String ext, String fileName) throws IOException {
        byte[] imageByte = Base64.decodeBase64(content);
        Calendar date = Calendar.getInstance();
        String directory = "c:/" + date.get(Calendar.YEAR) + "/"
                + date.get(Calendar.MONTH) + "/"
                + date.get(Calendar.DATE) + "/";

        File file = new File(directory + fileName + "." + ext);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        try {
            fileOutputStream.write(imageByte);
        } finally {
            fileOutputStream.close();
        }
        return directory;
    }

    @Override
    public ResponseData addMessage(MessageDTO dto) {
        ResponseData result = new ResponseData();
        String fileName = UUID.randomUUID().toString();
        try {
            if (dto.getType().equals(MessageType.IMAGE)) {
                uploadFile(dto.getContent(), dto.getExt(), fileName);
            }
            Messages save = messageRepository.save(dto.mapToEntity(fileName));
            result.setData(save.getId());
            result.setSuccess(true);
            return result;
        } catch (Exception e) {
            result.setMessage(e.getMessage());
            result.setSuccess(false);
            result.setErrorCode(String.valueOf(HttpStatus.CONFLICT.value()));
            return result;
        }
    }

    @Override
    public ResponseData get(MessageDTO dto) {
        ResponseData result = new ResponseData();
        try {
            List<Messages> messages = messageRepository.findByChat(dto.getChat());
            result.setSuccess(true);
            result.setData(messages);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ERROR : {}", e.getMessage());
            result.setSuccess(true);
            result.setMessage("Could not get messages e : " + e.getMessage());
            return result;
        }
    }
}

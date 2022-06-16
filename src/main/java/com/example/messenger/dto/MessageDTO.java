package com.example.messenger.dto;


import com.example.messenger.domain.Chat;
import com.example.messenger.domain.MessageType;
import com.example.messenger.domain.Messages;
import com.example.messenger.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {

    private Long chat;
    private Long author;
    private MessageType type;
    private String content;
    private String ext;
    private LocalDateTime createAt;

    public Messages mapToEntity(String name) {
        Messages messages = new Messages();
        messages.setAuthor(new User(author));
        if (this.type.equals(MessageType.IMAGE)) {
            messages.setContent(name + "." + this.ext);
            messages.setContentType(this.type);
            messages.setChat(new Chat(chat));
            messages.setCreatedAt(LocalDateTime.now());
            return messages;
        }
        messages.setContent(this.content);
        messages.setContentType(this.type);
        messages.setChat(new Chat(chat));
        messages.setCreatedAt(LocalDateTime.now());
        return messages;
    }
}

package com.example.messenger.dto;

import com.example.messenger.domain.Chat;
import com.example.messenger.domain.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatDTO {
    private Long id;
    private LocalDateTime createAt;
    private String name;
    private List<Long> users;


    public Chat mapToEntity() {
        Chat chat = new Chat();
        List<User> list = new ArrayList<>();
        for (Long id : this.users) {
            list.add(new User(id));
        }
        chat.setUsers(list);
        chat.setId(this.id);
        chat.setName(this.name);
        chat.setCreatedAt(LocalDateTime.now());
        return chat;
    }
}

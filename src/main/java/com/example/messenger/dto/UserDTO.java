package com.example.messenger.dto;

import com.example.messenger.domain.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private Long id;

    @NotBlank
    private String username;

    private LocalDateTime createdAt;

    public User mapToEntity() {
        User user = new User();
        user.setUserName(this.username);
        user.setId(this.id);
        user.setCreatedAt(LocalDateTime.now());
        return user;
    }
}

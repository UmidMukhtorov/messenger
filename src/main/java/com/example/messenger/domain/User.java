package com.example.messenger.domain;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "user_name")
    private String userName;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public User(Long id) {
        this.id = id;
    }

    public User() {
    }
}

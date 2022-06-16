package com.example.messenger.domain;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<User> users;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Chat(Long id) {
        this.id = id;
    }

    public Chat() {
    }
}

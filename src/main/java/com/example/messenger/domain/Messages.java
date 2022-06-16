package com.example.messenger.domain;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "messages")
@Data
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Chat chat;

    @ManyToOne
    private User author;

    @Enumerated(EnumType.STRING)
    @Column(name = "content_type", length = 100000)
    private MessageType contentType;

    @Column(length = 100000)
    private String content;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private String ext;


}

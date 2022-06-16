package com.example.messenger.repository;

import com.example.messenger.domain.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Messages, Long> {
    @Query(value = "select m.* from messages m left join chat_users c on m.chat_id=c.chat_id where m.chat_id=:chatId ", nativeQuery = true)
    List<Messages> findByChat(@Param("chatId") Long chatId);

}

package com.example.messenger.repository;

import com.example.messenger.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    Optional<Chat> findByName(String name);

    @Query(value = "select c.* from chat c left join chat_users b on b.chat_id = c.id where b.users_id=:user", nativeQuery = true)
    List<Chat> findByUserId(@Param("user") Long user);

    List<Chat> findAll();
}

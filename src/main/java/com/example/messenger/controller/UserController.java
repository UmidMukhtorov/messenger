package com.example.messenger.controller;


import com.example.messenger.dto.ResponseData;
import com.example.messenger.dto.UserDTO;
import com.example.messenger.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseData> addUser(@RequestBody UserDTO dto) {
        log.info("REST request to addUser dto : {}", dto);
        return ResponseEntity.ok(userService.addUser(dto));
    }


}

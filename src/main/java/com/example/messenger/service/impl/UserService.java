package com.example.messenger.service.impl;

import com.example.messenger.dto.ResponseData;
import com.example.messenger.dto.UserDTO;

public interface UserService {
    ResponseData addUser(UserDTO userDTO);

}

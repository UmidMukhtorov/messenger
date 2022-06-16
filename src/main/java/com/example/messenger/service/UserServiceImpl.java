package com.example.messenger.service;

import com.example.messenger.domain.User;
import com.example.messenger.dto.ResponseData;
import com.example.messenger.dto.UserDTO;
import com.example.messenger.repository.UserRepository;
import com.example.messenger.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseData addUser(UserDTO userDTO) {
        ResponseData result = new ResponseData();
        try {
            User save = userRepository.save(userDTO.mapToEntity());
            result.setSuccess(true);
            result.setData(save.getId());
            return result;
        } catch (Throwable e) {
            log.error("error cause exception : {}", e.getMessage());
            result.setSuccess(false);
            result.setMessage("Error when saving to DataBase");
            result.setErrorCode(String.valueOf(HttpStatus.CONFLICT.value()));
            return result;
        }
    }
}

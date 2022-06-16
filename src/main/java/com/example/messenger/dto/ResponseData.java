package com.example.messenger.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseData<T> {
    private String message;
    private boolean success;
    private T data;
    private String errorCode;
}

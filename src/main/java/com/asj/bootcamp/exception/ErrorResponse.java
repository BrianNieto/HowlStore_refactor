package com.asj.bootcamp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    private String message;
    private int code;

    public ErrorResponse(Exception e, int code){
        this.message = e.getMessage();
        this.code = code;
    }
}
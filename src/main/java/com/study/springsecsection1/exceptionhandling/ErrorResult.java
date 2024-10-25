package com.study.springsecsection1.exceptionhandling;

import lombok.Getter;

@Getter
public class ErrorResult {
    private int status;
    private String error;
    private String message;
    private String path;

    public ErrorResult(int status, String error, String message, String path) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public static ErrorResult of(int status, String error, String message, String path) {
        return new ErrorResult(status, error, message, path);
    }
}

package com.tr.resume.exception;

public class BuzException extends RuntimeException{
    private String msg;
    public BuzException(String msg) {
        super(msg);
    }
}

package com.football.exception;

import java.nio.file.attribute.BasicFileAttributes;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String ex){
        super(ex);
    }
    public BadRequestException(){}

}

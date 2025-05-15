package com.tipaneque.bePrepared.exception;

public class EntityNotFoundException extends BadRequestException{
    public EntityNotFoundException(String message){
        super(message);
    }
}

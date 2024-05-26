package io.waveguide.social_media.exception;

public class UserAlreadyExistException extends RuntimeException{

    String message;

    public UserAlreadyExistException(String message){this.message = message;}
}

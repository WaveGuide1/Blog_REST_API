package io.waveguide.social_media.exception;

public class AuthenticationFailedException extends RuntimeException{

    String message;
    public AuthenticationFailedException(String message){this.message = message;}
}

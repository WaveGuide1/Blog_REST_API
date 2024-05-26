package io.waveguide.social_media.exception;

public class GeneralAppException extends RuntimeException{

    String message;

    public GeneralAppException(String message){this.message = message;}
}

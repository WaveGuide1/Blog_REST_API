package io.waveguide.social_media.exception;

import io.waveguide.social_media.utils.GeneralResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GeneralResponseEntity<String>> validationException(MethodArgumentNotValidException notValidException){

        GeneralResponseEntity<String> responseEntity = new GeneralResponseEntity<>();
        String message = notValidException.getBindingResult().getFieldError()
                .getDefaultMessage();
        responseEntity.setMessage(message);
        return new ResponseEntity<>(responseEntity, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<GeneralResponseEntity<String>> recordNotFoundException(RecordNotFoundException exception){

        GeneralResponseEntity<String> responseEntity = new GeneralResponseEntity<>();
        responseEntity.setMessage(exception.message);
        return new ResponseEntity<>(responseEntity, HttpStatus.NOT_FOUND);
    }
}
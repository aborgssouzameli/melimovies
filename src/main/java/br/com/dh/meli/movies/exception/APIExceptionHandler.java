package br.com.dh.meli.movies.exception;

import br.com.dh.meli.movies.dto.APIExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(APIException.class)
    protected ResponseEntity<APIExceptionDTO> handlerApiException(APIException exception)
    {
        return new ResponseEntity<>(exception.getDTO(), exception.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<APIExceptionDTO> handlerMethodException(MethodArgumentNotValidException exception)
    {
        APIException ex = new APIException(exception.getFieldError().getDefaultMessage());
        ex.setStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(ex.getDTO(), ex.getStatus());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<APIExceptionDTO> IllegalArgumentException(IllegalArgumentException exception)
    {
        APIException ex = new APIException(exception.getMessage());
        ex.setStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(ex.getDTO(), ex.getStatus());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<APIExceptionDTO> handlerHttpMessageNotReadable(HttpMessageNotReadableException exception)
    {
        APIException ex = new APIException(exception.getLocalizedMessage());
        ex.setStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(ex.getDTO(), ex.getStatus());
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<APIExceptionDTO> handlerNotFoundException(NotFoundException exception)
    {
        APIException ex = new APIException(exception.getLocalizedMessage());
        ex.setStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(ex.getDTO(), ex.getStatus());
    }
}

package br.com.dh.meli.movies.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends APIException {

    /**
     * NotFoundException
     * @param message to understand exception
     */
    public NotFoundException(String message) {
        super(message);
        this.setStatus(HttpStatus.NOT_FOUND);
    }
}

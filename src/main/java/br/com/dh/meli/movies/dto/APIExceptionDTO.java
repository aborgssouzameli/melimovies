package br.com.dh.meli.movies.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class APIExceptionDTO {
    private String title;
    private int status;
    private String message;
    private LocalDateTime timestamp;
}

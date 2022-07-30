package br.com.dh.meli.movies.exception;

import br.com.dh.meli.movies.dto.APIExceptionDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


@Setter @Getter
public class APIException extends RuntimeException {
    private String title;
    private HttpStatus status;
    private String messageData;
    private LocalDateTime timestamp;

    public APIException(String message) {
        super(message);
        this.setStatus(HttpStatus.MULTI_STATUS);
        this.setMessageData(message);
        this.setTimestamp(LocalDateTime.now());
    }

    public void setStatus(HttpStatus statusData) {
        this.status = statusData;
        this.setTitle(statusData.getReasonPhrase());
    }

    public APIExceptionDTO getDTO() {
        return APIExceptionDTO.builder()
                .title(this.getTitle())
                .status(this.getStatus().value())
                .message(this.getMessageData())
                .timestamp(this.getTimestamp())
                .build();
    }
}

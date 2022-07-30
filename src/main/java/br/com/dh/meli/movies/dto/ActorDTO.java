package br.com.dh.meli.movies.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ActorDTO {

    public long id;
    public String first_name;
    public String last_name;
}

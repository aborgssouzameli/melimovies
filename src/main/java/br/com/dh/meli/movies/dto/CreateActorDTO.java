package br.com.dh.meli.movies.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class CreateActorDTO {
    @NotNull
    @NotEmpty(message = "Nome da pessoa é obrigatório")
    @Size(max = 60, message = "Nome não pode ser superior a 60 caracteres")
    private String first_name;

    @NotNull
    @NotEmpty(message = "Sobrenome da pessoa é obrigatório")
    @Size(max = 60, message = "Sobrenome não pode ser superior a 60 caracteres")
    private String last_name;
}

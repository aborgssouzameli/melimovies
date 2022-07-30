package br.com.dh.meli.movies.util;

import br.com.dh.meli.movies.dto.ActorDTO;
import br.com.dh.meli.movies.dto.CreateActorDTO;
import br.com.dh.meli.movies.mapper.IActorMapper;
import br.com.dh.meli.movies.model.ActorModel;

import java.util.Optional;

final public class TestUtilsActor {

    private static CreateActorDTO generateActorDTO() {
        CreateActorDTO builder = CreateActorDTO.builder().build();
        return builder;
    }

    public static CreateActorDTO createActorDTOWithContent() {
        CreateActorDTO actor = generateActorDTO();
        actor.setFirst_name("Leandro");
        actor.setLast_name("Hassun");
        return actor;
    }

    public static CreateActorDTO createActorDTOWithoutParams() {
        CreateActorDTO actor = generateActorDTO();
        actor.setFirst_name("");
        actor.setLast_name("");
        return actor;
    }

    public static ActorModel getActorModel() {
        ActorModel actor = new ActorModel();
        actor.setId(1);
        actor.setFirst_name(createActorDTOWithContent().getFirst_name());
        actor.setLast_name(createActorDTOWithContent().getLast_name());
        return actor;
    }

    public static ActorDTO getActorByID_whitIDParam () {
        ActorModel actor = getActorModel();
        ActorDTO dto = IActorMapper.MAPPER.actorModelToActorDTO(actor);
        return dto;
    }
}

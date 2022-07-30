package br.com.dh.meli.movies.mapper;

import br.com.dh.meli.movies.dto.ActorDTO;
import br.com.dh.meli.movies.dto.CreateActorDTO;
import br.com.dh.meli.movies.model.ActorModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IActorMapper {

    IActorMapper MAPPER = Mappers.getMapper(IActorMapper.class);

    ActorDTO actorModelToActorDTO(ActorModel actor);

    ActorModel createActorToActorModel(CreateActorDTO actor);
}

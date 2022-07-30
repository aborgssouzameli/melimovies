package br.com.dh.meli.movies.service;

import br.com.dh.meli.movies.dto.ActorDTO;
import br.com.dh.meli.movies.dto.CreateActorDTO;
import br.com.dh.meli.movies.exception.NotFoundException;
import br.com.dh.meli.movies.mapper.IActorMapper;
import br.com.dh.meli.movies.model.ActorModel;
import br.com.dh.meli.movies.repository.IActorModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.NotActiveException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.Optional;

@Service
public class ActorService implements IActorService {

    @Autowired
    private IActorModelRepo repo;

    private final String ACTOR_NOT_FOUND_BY_ID_MESSAGE = "Ator/Atriz não encontrado";

    @Override
    public ActorDTO getActorByID(long actorID) {
        Optional<ActorModel> actor = repo.findById(actorID);
        if (actor.isPresent()){
            ActorDTO dto = IActorMapper.MAPPER.actorModelToActorDTO(actor.get());
            return dto;
        }
        throw new NotFoundException(ACTOR_NOT_FOUND_BY_ID_MESSAGE);
    }

    public ActorDTO addNewActor (CreateActorDTO actor) {
        ActorModel result = repo.save(IActorMapper.MAPPER.createActorToActorModel(actor));
        ActorDTO newActor = IActorMapper.MAPPER.actorModelToActorDTO(result);
        return newActor;
    }
}

package br.com.dh.meli.movies.service;

import br.com.dh.meli.movies.dto.ActorDTO;
import br.com.dh.meli.movies.dto.CreateActorDTO;
import br.com.dh.meli.movies.repository.IActorModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface IActorService {

    ActorDTO getActorByID(long actorID);

    ActorDTO addNewActor (CreateActorDTO actor);

}

package br.com.dh.meli.movies.repository;

import br.com.dh.meli.movies.model.ActorModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IActorModelRepo extends CrudRepository<ActorModel, Long> {
}

package br.com.dh.meli.movies.repository;

import br.com.dh.meli.movies.model.ActorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IActorModelRepo extends JpaRepository<ActorModel, Long> {
}

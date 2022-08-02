package br.com.dh.meli.movies.repository;

import br.com.dh.meli.movies.model.ActorModel;
import br.com.dh.meli.movies.util.TestUtilsActor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class IActorModelRepoTest {

    @Autowired
    private IActorModelRepo repo;

    @Test
    public void save_returnSaveActor_whenValidActor() {
        ActorModel actor = TestUtilsActor.getActorModel();
        ActorModel savedActor = repo.save(actor);
        assertThat(savedActor.getFirst_name()).isEqualTo(actor.getFirst_name());
        assertThat(savedActor.getLast_name()).isEqualTo(actor.getLast_name());
    }
}

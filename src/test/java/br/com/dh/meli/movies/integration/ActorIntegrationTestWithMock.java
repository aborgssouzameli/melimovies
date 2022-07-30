package br.com.dh.meli.movies.integration;

import br.com.dh.meli.movies.controller.ActorController;
import br.com.dh.meli.movies.dto.ActorDTO;
import br.com.dh.meli.movies.mapper.IActorMapper;
import br.com.dh.meli.movies.repository.IActorModelRepo;
import br.com.dh.meli.movies.service.IActorService;
import br.com.dh.meli.movies.util.TestUtilsActor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class ActorIntegrationTestWithMock {

    @LocalServerPort private int port;
    @Autowired
    TestRestTemplate testRestTemplate;


    private String BASE_URL = "";

    @InjectMocks
    ActorController controller;

    @Mock
    IActorService service;

    @Autowired
    IActorModelRepo repo;

    @BeforeEach
    void setup () {
        BASE_URL += String.format("%s%d/%s", "http://localhost:", port, "/api/v1/actor");
        repo.save(IActorMapper.MAPPER.createActorToActorModel(TestUtilsActor.createActorDTOWithContent()));
    }

    @AfterEach
    void destruct() {
        repo.deleteAll();
    }

    @Test
    public void getActorByID_returnPreConditionSuccessfuly_whenActorExist() throws NullPointerException {
        int actorID = 1;
        final String API_URL = String.format("%s/%d", BASE_URL, actorID);
        when(service.getActorByID(ArgumentMatchers.anyLong())).thenReturn(TestUtilsActor.getActorByID_whitIDParam());
        HttpEntity<ActorDTO> httpEntity =  controller.getActorByID(1L);
        ResponseEntity<ActorDTO> result = testRestTemplate.exchange(API_URL, HttpMethod.GET, httpEntity, ActorDTO.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().getFirst_name()).contains(httpEntity.getBody().getFirst_name());
    }
}

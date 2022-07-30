package br.com.dh.meli.movies.controller;

import br.com.dh.meli.movies.dto.ActorDTO;
import br.com.dh.meli.movies.dto.CreateActorDTO;
import br.com.dh.meli.movies.service.IActorService;
import br.com.dh.meli.movies.util.TestUtilsActor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ActorControllerTest {

    @InjectMocks
    ActorController controller;

    @Mock
    private IActorService service;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getActorByID_whenSuccess_thenWithParams() {
        when(service.getActorByID(ArgumentMatchers.anyLong()))
                .thenReturn(TestUtilsActor.getActorByID_whitIDParam());
        ResponseEntity<ActorDTO> response = controller.getActorByID(1L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    void addNewActor_whenSuccess_thenWithParams() {
        when(service.addNewActor(ArgumentMatchers.any(CreateActorDTO.class)))
                .thenReturn(TestUtilsActor.getActorByID_whitIDParam());
        CreateActorDTO newActor = TestUtilsActor.createActorDTOWithContent();
        ResponseEntity<ActorDTO> response = controller.addNewActor(newActor);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
    }
}

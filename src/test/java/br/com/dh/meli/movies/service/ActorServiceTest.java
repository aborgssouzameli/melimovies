package br.com.dh.meli.movies.service;

import br.com.dh.meli.movies.dto.ActorDTO;
import br.com.dh.meli.movies.dto.CreateActorDTO;
import br.com.dh.meli.movies.exception.NotFoundException;
import br.com.dh.meli.movies.mapper.IActorMapper;
import br.com.dh.meli.movies.model.ActorModel;
import br.com.dh.meli.movies.repository.IActorModelRepo;
import br.com.dh.meli.movies.util.TestUtilsActor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ActorServiceTest {

    @InjectMocks
    ActorService service;

    @Mock
    IActorModelRepo actorModelRepo;

    @Test
    public void getActorByID_whenHaveActorByID_thenReturnSucceedsResult() {
        when(actorModelRepo.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(TestUtilsActor.getActorModel()));
        ActorDTO getActor = service.getActorByID(1L);
        assertEquals(getActor.getId(), 1L);
        assertThat(getActor.getId()).isPositive();
        assertThat(getActor.getFirst_name()).isNotNull();
        assertThat(getActor.getLast_name()).isNotNull();
        assertEquals(getActor.getFirst_name(), "Leandro");
        assertEquals(getActor.getLast_name(), "Hassun");

    }
    @Test
    public void getActorByID_whenExceptionThrownNotFoundException_thenAssertionSucceeds() {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            ActorDTO getActor = service.getActorByID(1L);
        });
        String expectedMessage = "Ator/Atriz não encontrado";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        assertThat(exception.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void addNewActor_whenCreatedActor_thenReturnSucceedsResult() {
        when(actorModelRepo.save(any(ActorModel.class))).thenReturn(TestUtilsActor.getActorModel());

        CreateActorDTO newActor = TestUtilsActor.createActorDTOWithContent();
        ActorDTO result = service.addNewActor(newActor);

        assertEquals(result.getId(), 1L);
        assertThat(result.getId()).isPositive();
        assertThat(result.getFirst_name()).isNotNull();
        assertThat(result.getLast_name()).isNotNull();
        assertEquals(result.getFirst_name(), "Leandro");
        assertEquals(result.getLast_name(), "Hassun");

    }
    @Test
    public void addNewActor_whenFaliedCreatedActor_thenNotSucceeds() {
        when(actorModelRepo.save(any(ActorModel.class))).thenReturn(IActorMapper.MAPPER.createActorToActorModel(TestUtilsActor.createActorDTOWithoutParams()));
        ActorDTO result = service.addNewActor(TestUtilsActor.createActorDTOWithoutParams());
        assertFalse(result.getId() > 0);
        assertTrue(result.getFirst_name().isEmpty());
        assertTrue(result.getLast_name().isEmpty());
    }
}

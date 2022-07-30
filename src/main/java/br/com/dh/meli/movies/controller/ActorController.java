package br.com.dh.meli.movies.controller;

import br.com.dh.meli.movies.dto.ActorDTO;
import br.com.dh.meli.movies.dto.CreateActorDTO;
import br.com.dh.meli.movies.mapper.IActorMapper;
import br.com.dh.meli.movies.service.IActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/actor")
public class ActorController {

    @Autowired
    private IActorService service;

    @GetMapping("/{id}")
    public ResponseEntity<ActorDTO> getActorByID(@PathVariable Long id) {
        ActorDTO actor = service.getActorByID(id);
        return ResponseEntity.status(HttpStatus.OK).body(actor);
    }

    @PostMapping()
    public ResponseEntity<ActorDTO> addNewActor(@RequestBody @Validated CreateActorDTO actor) {
        ActorDTO actorDTO = service.addNewActor(actor);
        return ResponseEntity.status(HttpStatus.CREATED).body(actorDTO);
    }
}

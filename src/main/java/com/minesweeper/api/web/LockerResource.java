package com.minesweeper.api.web;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.minesweeper.api.domain.Game;
import com.minesweeper.api.domain.Locker;
import com.minesweeper.api.domain.LockerRequest;
import com.minesweeper.api.repository.LockerRepository;
import com.minesweeper.api.service.GameService;

/**
 * REST controller for managing {@link com.minesweeper.api.domain.Locker}.
 */
@RestController
@RequestMapping("/api")
public class LockerResource {

    private final Logger log = LoggerFactory.getLogger(LockerResource.class);

    private static final String ENTITY_NAME = "locker";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;
    
    @Autowired
    private GameService gameService;

    private final LockerRepository lockerRepository;

    public LockerResource(LockerRepository lockerRepository) {
        this.lockerRepository = lockerRepository;
    }

    /**
     * {@code POST  /lockers} : Create a new locker.
     *
     * @param locker the locker to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new locker, or with status {@code 400 (Bad Request)} if the locker has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/lockers")
    public ResponseEntity<Locker> createLocker(@RequestBody Locker locker) throws URISyntaxException {
        log.debug("REST request to save Locker : {}", locker);
        if (locker.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A new locker cannot already have an ID");
        }
        Locker result = lockerRepository.save(locker);
        return ResponseEntity.created(new URI("/api/lockers/" + result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /lockers} : Updates an existing locker.
     *
     * @param locker the locker to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated locker,
     * or with status {@code 400 (Bad Request)} if the locker is not valid,
     * or with status {@code 500 (Internal Server Error)} if the locker couldn't be updated.
     * @throws Exception 
     */
    @PutMapping("/lockers")
    public ResponseEntity<Game> updateLocker(@RequestBody @Valid LockerRequest locker) throws Exception {
        log.debug("REST request to update Locker : {}", locker);
        if (locker.getIdGame() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid id");
        }
        Game result = gameService.play(locker, null);
        return ResponseEntity.ok()
            .body(result);
    }

    /**
     * {@code GET  /lockers} : get all the lockers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of lockers in body.
     */
    @GetMapping("/lockers")
    public List<Locker> getAllLockers() {
        log.debug("REST request to get all Lockers");
        return lockerRepository.findAll();
    }

    /**
     * {@code GET  /lockers/:id} : get the "id" locker.
     *
     * @param id the id of the locker to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the locker, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/lockers/{id}")
    public ResponseEntity<Locker> getLocker(@PathVariable String id) {
        log.debug("REST request to get Locker : {}", id);
        Optional<Locker> locker = lockerRepository.findById(id);
        return locker.map(response -> ResponseEntity.ok().body(response))
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * {@code DELETE  /lockers/:id} : delete the "id" locker.
     *
     * @param id the id of the locker to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/lockers/{id}")
    public ResponseEntity<Void> deleteLocker(@PathVariable String id) {
        log.debug("REST request to delete Locker : {}", id);
        lockerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

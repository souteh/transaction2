package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Statutticketenum;
import com.mycompany.myapp.service.StatutticketenumService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.StatutticketenumCriteria;
import com.mycompany.myapp.service.StatutticketenumQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Statutticketenum}.
 */
@RestController
@RequestMapping("/api")
public class StatutticketenumResource {

    private final Logger log = LoggerFactory.getLogger(StatutticketenumResource.class);

    private static final String ENTITY_NAME = "statutticketenum";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StatutticketenumService statutticketenumService;

    private final StatutticketenumQueryService statutticketenumQueryService;

    public StatutticketenumResource(StatutticketenumService statutticketenumService, StatutticketenumQueryService statutticketenumQueryService) {
        this.statutticketenumService = statutticketenumService;
        this.statutticketenumQueryService = statutticketenumQueryService;
    }

    /**
     * {@code POST  /statutticketenums} : Create a new statutticketenum.
     *
     * @param statutticketenum the statutticketenum to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new statutticketenum, or with status {@code 400 (Bad Request)} if the statutticketenum has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/statutticketenums")
    public ResponseEntity<Statutticketenum> createStatutticketenum(@Valid @RequestBody Statutticketenum statutticketenum) throws URISyntaxException {
        log.debug("REST request to save Statutticketenum : {}", statutticketenum);
        if (statutticketenum.getId() != null) {
            throw new BadRequestAlertException("A new statutticketenum cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Statutticketenum result = statutticketenumService.save(statutticketenum);
        return ResponseEntity.created(new URI("/api/statutticketenums/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /statutticketenums} : Updates an existing statutticketenum.
     *
     * @param statutticketenum the statutticketenum to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated statutticketenum,
     * or with status {@code 400 (Bad Request)} if the statutticketenum is not valid,
     * or with status {@code 500 (Internal Server Error)} if the statutticketenum couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/statutticketenums")
    public ResponseEntity<Statutticketenum> updateStatutticketenum(@Valid @RequestBody Statutticketenum statutticketenum) throws URISyntaxException {
        log.debug("REST request to update Statutticketenum : {}", statutticketenum);
        if (statutticketenum.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Statutticketenum result = statutticketenumService.save(statutticketenum);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, statutticketenum.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /statutticketenums} : get all the statutticketenums.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of statutticketenums in body.
     */
    @GetMapping("/statutticketenums")
    public ResponseEntity<List<Statutticketenum>> getAllStatutticketenums(StatutticketenumCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Statutticketenums by criteria: {}", criteria);
        Page<Statutticketenum> page = statutticketenumQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /statutticketenums/count} : count all the statutticketenums.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/statutticketenums/count")
    public ResponseEntity<Long> countStatutticketenums(StatutticketenumCriteria criteria) {
        log.debug("REST request to count Statutticketenums by criteria: {}", criteria);
        return ResponseEntity.ok().body(statutticketenumQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /statutticketenums/:id} : get the "id" statutticketenum.
     *
     * @param id the id of the statutticketenum to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the statutticketenum, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/statutticketenums/{id}")
    public ResponseEntity<Statutticketenum> getStatutticketenum(@PathVariable Long id) {
        log.debug("REST request to get Statutticketenum : {}", id);
        Optional<Statutticketenum> statutticketenum = statutticketenumService.findOne(id);
        return ResponseUtil.wrapOrNotFound(statutticketenum);
    }

    /**
     * {@code DELETE  /statutticketenums/:id} : delete the "id" statutticketenum.
     *
     * @param id the id of the statutticketenum to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/statutticketenums/{id}")
    public ResponseEntity<Void> deleteStatutticketenum(@PathVariable Long id) {
        log.debug("REST request to delete Statutticketenum : {}", id);
        statutticketenumService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

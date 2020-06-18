package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Typeannulationenum;
import com.mycompany.myapp.service.TypeannulationenumService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.TypeannulationenumCriteria;
import com.mycompany.myapp.service.TypeannulationenumQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.Typeannulationenum}.
 */
@RestController
@RequestMapping("/api")
public class TypeannulationenumResource {

    private final Logger log = LoggerFactory.getLogger(TypeannulationenumResource.class);

    private static final String ENTITY_NAME = "typeannulationenum";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypeannulationenumService typeannulationenumService;

    private final TypeannulationenumQueryService typeannulationenumQueryService;

    public TypeannulationenumResource(TypeannulationenumService typeannulationenumService, TypeannulationenumQueryService typeannulationenumQueryService) {
        this.typeannulationenumService = typeannulationenumService;
        this.typeannulationenumQueryService = typeannulationenumQueryService;
    }

    /**
     * {@code POST  /typeannulationenums} : Create a new typeannulationenum.
     *
     * @param typeannulationenum the typeannulationenum to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typeannulationenum, or with status {@code 400 (Bad Request)} if the typeannulationenum has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/typeannulationenums")
    public ResponseEntity<Typeannulationenum> createTypeannulationenum(@Valid @RequestBody Typeannulationenum typeannulationenum) throws URISyntaxException {
        log.debug("REST request to save Typeannulationenum : {}", typeannulationenum);
        if (typeannulationenum.getId() != null) {
            throw new BadRequestAlertException("A new typeannulationenum cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Typeannulationenum result = typeannulationenumService.save(typeannulationenum);
        return ResponseEntity.created(new URI("/api/typeannulationenums/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /typeannulationenums} : Updates an existing typeannulationenum.
     *
     * @param typeannulationenum the typeannulationenum to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeannulationenum,
     * or with status {@code 400 (Bad Request)} if the typeannulationenum is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typeannulationenum couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/typeannulationenums")
    public ResponseEntity<Typeannulationenum> updateTypeannulationenum(@Valid @RequestBody Typeannulationenum typeannulationenum) throws URISyntaxException {
        log.debug("REST request to update Typeannulationenum : {}", typeannulationenum);
        if (typeannulationenum.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Typeannulationenum result = typeannulationenumService.save(typeannulationenum);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typeannulationenum.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /typeannulationenums} : get all the typeannulationenums.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typeannulationenums in body.
     */
    @GetMapping("/typeannulationenums")
    public ResponseEntity<List<Typeannulationenum>> getAllTypeannulationenums(TypeannulationenumCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Typeannulationenums by criteria: {}", criteria);
        Page<Typeannulationenum> page = typeannulationenumQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /typeannulationenums/count} : count all the typeannulationenums.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/typeannulationenums/count")
    public ResponseEntity<Long> countTypeannulationenums(TypeannulationenumCriteria criteria) {
        log.debug("REST request to count Typeannulationenums by criteria: {}", criteria);
        return ResponseEntity.ok().body(typeannulationenumQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /typeannulationenums/:id} : get the "id" typeannulationenum.
     *
     * @param id the id of the typeannulationenum to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typeannulationenum, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/typeannulationenums/{id}")
    public ResponseEntity<Typeannulationenum> getTypeannulationenum(@PathVariable Long id) {
        log.debug("REST request to get Typeannulationenum : {}", id);
        Optional<Typeannulationenum> typeannulationenum = typeannulationenumService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeannulationenum);
    }

    /**
     * {@code DELETE  /typeannulationenums/:id} : delete the "id" typeannulationenum.
     *
     * @param id the id of the typeannulationenum to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/typeannulationenums/{id}")
    public ResponseEntity<Void> deleteTypeannulationenum(@PathVariable Long id) {
        log.debug("REST request to delete Typeannulationenum : {}", id);
        typeannulationenumService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

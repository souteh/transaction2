package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Typeoperationenum;
import com.mycompany.myapp.service.TypeoperationenumService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.TypeoperationenumCriteria;
import com.mycompany.myapp.service.TypeoperationenumQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.Typeoperationenum}.
 */
@RestController
@RequestMapping("/api")
public class TypeoperationenumResource {

    private final Logger log = LoggerFactory.getLogger(TypeoperationenumResource.class);

    private static final String ENTITY_NAME = "typeoperationenum";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypeoperationenumService typeoperationenumService;

    private final TypeoperationenumQueryService typeoperationenumQueryService;

    public TypeoperationenumResource(TypeoperationenumService typeoperationenumService, TypeoperationenumQueryService typeoperationenumQueryService) {
        this.typeoperationenumService = typeoperationenumService;
        this.typeoperationenumQueryService = typeoperationenumQueryService;
    }

    /**
     * {@code POST  /typeoperationenums} : Create a new typeoperationenum.
     *
     * @param typeoperationenum the typeoperationenum to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typeoperationenum, or with status {@code 400 (Bad Request)} if the typeoperationenum has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/typeoperationenums")
    public ResponseEntity<Typeoperationenum> createTypeoperationenum(@Valid @RequestBody Typeoperationenum typeoperationenum) throws URISyntaxException {
        log.debug("REST request to save Typeoperationenum : {}", typeoperationenum);
        if (typeoperationenum.getId() != null) {
            throw new BadRequestAlertException("A new typeoperationenum cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Typeoperationenum result = typeoperationenumService.save(typeoperationenum);
        return ResponseEntity.created(new URI("/api/typeoperationenums/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /typeoperationenums} : Updates an existing typeoperationenum.
     *
     * @param typeoperationenum the typeoperationenum to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeoperationenum,
     * or with status {@code 400 (Bad Request)} if the typeoperationenum is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typeoperationenum couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/typeoperationenums")
    public ResponseEntity<Typeoperationenum> updateTypeoperationenum(@Valid @RequestBody Typeoperationenum typeoperationenum) throws URISyntaxException {
        log.debug("REST request to update Typeoperationenum : {}", typeoperationenum);
        if (typeoperationenum.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Typeoperationenum result = typeoperationenumService.save(typeoperationenum);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typeoperationenum.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /typeoperationenums} : get all the typeoperationenums.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typeoperationenums in body.
     */
    @GetMapping("/typeoperationenums")
    public ResponseEntity<List<Typeoperationenum>> getAllTypeoperationenums(TypeoperationenumCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Typeoperationenums by criteria: {}", criteria);
        Page<Typeoperationenum> page = typeoperationenumQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /typeoperationenums/count} : count all the typeoperationenums.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/typeoperationenums/count")
    public ResponseEntity<Long> countTypeoperationenums(TypeoperationenumCriteria criteria) {
        log.debug("REST request to count Typeoperationenums by criteria: {}", criteria);
        return ResponseEntity.ok().body(typeoperationenumQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /typeoperationenums/:id} : get the "id" typeoperationenum.
     *
     * @param id the id of the typeoperationenum to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typeoperationenum, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/typeoperationenums/{id}")
    public ResponseEntity<Typeoperationenum> getTypeoperationenum(@PathVariable Long id) {
        log.debug("REST request to get Typeoperationenum : {}", id);
        Optional<Typeoperationenum> typeoperationenum = typeoperationenumService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeoperationenum);
    }

    /**
     * {@code DELETE  /typeoperationenums/:id} : delete the "id" typeoperationenum.
     *
     * @param id the id of the typeoperationenum to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/typeoperationenums/{id}")
    public ResponseEntity<Void> deleteTypeoperationenum(@PathVariable Long id) {
        log.debug("REST request to delete Typeoperationenum : {}", id);
        typeoperationenumService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

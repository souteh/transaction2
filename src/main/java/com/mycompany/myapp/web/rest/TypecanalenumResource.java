package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Typecanalenum;
import com.mycompany.myapp.service.TypecanalenumService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.TypecanalenumCriteria;
import com.mycompany.myapp.service.TypecanalenumQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.Typecanalenum}.
 */
@RestController
@RequestMapping("/api")
public class TypecanalenumResource {

    private final Logger log = LoggerFactory.getLogger(TypecanalenumResource.class);

    private static final String ENTITY_NAME = "typecanalenum";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypecanalenumService typecanalenumService;

    private final TypecanalenumQueryService typecanalenumQueryService;

    public TypecanalenumResource(TypecanalenumService typecanalenumService, TypecanalenumQueryService typecanalenumQueryService) {
        this.typecanalenumService = typecanalenumService;
        this.typecanalenumQueryService = typecanalenumQueryService;
    }

    /**
     * {@code POST  /typecanalenums} : Create a new typecanalenum.
     *
     * @param typecanalenum the typecanalenum to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typecanalenum, or with status {@code 400 (Bad Request)} if the typecanalenum has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/typecanalenums")
    public ResponseEntity<Typecanalenum> createTypecanalenum(@Valid @RequestBody Typecanalenum typecanalenum) throws URISyntaxException {
        log.debug("REST request to save Typecanalenum : {}", typecanalenum);
        if (typecanalenum.getId() != null) {
            throw new BadRequestAlertException("A new typecanalenum cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Typecanalenum result = typecanalenumService.save(typecanalenum);
        return ResponseEntity.created(new URI("/api/typecanalenums/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /typecanalenums} : Updates an existing typecanalenum.
     *
     * @param typecanalenum the typecanalenum to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typecanalenum,
     * or with status {@code 400 (Bad Request)} if the typecanalenum is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typecanalenum couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/typecanalenums")
    public ResponseEntity<Typecanalenum> updateTypecanalenum(@Valid @RequestBody Typecanalenum typecanalenum) throws URISyntaxException {
        log.debug("REST request to update Typecanalenum : {}", typecanalenum);
        if (typecanalenum.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Typecanalenum result = typecanalenumService.save(typecanalenum);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typecanalenum.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /typecanalenums} : get all the typecanalenums.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typecanalenums in body.
     */
    @GetMapping("/typecanalenums")
    public ResponseEntity<List<Typecanalenum>> getAllTypecanalenums(TypecanalenumCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Typecanalenums by criteria: {}", criteria);
        Page<Typecanalenum> page = typecanalenumQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /typecanalenums/count} : count all the typecanalenums.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/typecanalenums/count")
    public ResponseEntity<Long> countTypecanalenums(TypecanalenumCriteria criteria) {
        log.debug("REST request to count Typecanalenums by criteria: {}", criteria);
        return ResponseEntity.ok().body(typecanalenumQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /typecanalenums/:id} : get the "id" typecanalenum.
     *
     * @param id the id of the typecanalenum to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typecanalenum, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/typecanalenums/{id}")
    public ResponseEntity<Typecanalenum> getTypecanalenum(@PathVariable Long id) {
        log.debug("REST request to get Typecanalenum : {}", id);
        Optional<Typecanalenum> typecanalenum = typecanalenumService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typecanalenum);
    }

    /**
     * {@code DELETE  /typecanalenums/:id} : delete the "id" typecanalenum.
     *
     * @param id the id of the typecanalenum to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/typecanalenums/{id}")
    public ResponseEntity<Void> deleteTypecanalenum(@PathVariable Long id) {
        log.debug("REST request to delete Typecanalenum : {}", id);
        typecanalenumService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

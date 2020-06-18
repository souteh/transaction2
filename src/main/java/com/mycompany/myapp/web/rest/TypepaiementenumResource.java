package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Typepaiementenum;
import com.mycompany.myapp.service.TypepaiementenumService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.TypepaiementenumCriteria;
import com.mycompany.myapp.service.TypepaiementenumQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.Typepaiementenum}.
 */
@RestController
@RequestMapping("/api")
public class TypepaiementenumResource {

    private final Logger log = LoggerFactory.getLogger(TypepaiementenumResource.class);

    private static final String ENTITY_NAME = "typepaiementenum";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypepaiementenumService typepaiementenumService;

    private final TypepaiementenumQueryService typepaiementenumQueryService;

    public TypepaiementenumResource(TypepaiementenumService typepaiementenumService, TypepaiementenumQueryService typepaiementenumQueryService) {
        this.typepaiementenumService = typepaiementenumService;
        this.typepaiementenumQueryService = typepaiementenumQueryService;
    }

    /**
     * {@code POST  /typepaiementenums} : Create a new typepaiementenum.
     *
     * @param typepaiementenum the typepaiementenum to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typepaiementenum, or with status {@code 400 (Bad Request)} if the typepaiementenum has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/typepaiementenums")
    public ResponseEntity<Typepaiementenum> createTypepaiementenum(@Valid @RequestBody Typepaiementenum typepaiementenum) throws URISyntaxException {
        log.debug("REST request to save Typepaiementenum : {}", typepaiementenum);
        if (typepaiementenum.getId() != null) {
            throw new BadRequestAlertException("A new typepaiementenum cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Typepaiementenum result = typepaiementenumService.save(typepaiementenum);
        return ResponseEntity.created(new URI("/api/typepaiementenums/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /typepaiementenums} : Updates an existing typepaiementenum.
     *
     * @param typepaiementenum the typepaiementenum to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typepaiementenum,
     * or with status {@code 400 (Bad Request)} if the typepaiementenum is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typepaiementenum couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/typepaiementenums")
    public ResponseEntity<Typepaiementenum> updateTypepaiementenum(@Valid @RequestBody Typepaiementenum typepaiementenum) throws URISyntaxException {
        log.debug("REST request to update Typepaiementenum : {}", typepaiementenum);
        if (typepaiementenum.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Typepaiementenum result = typepaiementenumService.save(typepaiementenum);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typepaiementenum.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /typepaiementenums} : get all the typepaiementenums.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typepaiementenums in body.
     */
    @GetMapping("/typepaiementenums")
    public ResponseEntity<List<Typepaiementenum>> getAllTypepaiementenums(TypepaiementenumCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Typepaiementenums by criteria: {}", criteria);
        Page<Typepaiementenum> page = typepaiementenumQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /typepaiementenums/count} : count all the typepaiementenums.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/typepaiementenums/count")
    public ResponseEntity<Long> countTypepaiementenums(TypepaiementenumCriteria criteria) {
        log.debug("REST request to count Typepaiementenums by criteria: {}", criteria);
        return ResponseEntity.ok().body(typepaiementenumQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /typepaiementenums/:id} : get the "id" typepaiementenum.
     *
     * @param id the id of the typepaiementenum to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typepaiementenum, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/typepaiementenums/{id}")
    public ResponseEntity<Typepaiementenum> getTypepaiementenum(@PathVariable Long id) {
        log.debug("REST request to get Typepaiementenum : {}", id);
        Optional<Typepaiementenum> typepaiementenum = typepaiementenumService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typepaiementenum);
    }

    /**
     * {@code DELETE  /typepaiementenums/:id} : delete the "id" typepaiementenum.
     *
     * @param id the id of the typepaiementenum to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/typepaiementenums/{id}")
    public ResponseEntity<Void> deleteTypepaiementenum(@PathVariable Long id) {
        log.debug("REST request to delete Typepaiementenum : {}", id);
        typepaiementenumService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

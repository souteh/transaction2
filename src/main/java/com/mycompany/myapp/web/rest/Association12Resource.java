package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Association12;
import com.mycompany.myapp.service.Association12Service;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.Association12Criteria;
import com.mycompany.myapp.service.Association12QueryService;

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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Association12}.
 */
@RestController
@RequestMapping("/api")
public class Association12Resource {

    private final Logger log = LoggerFactory.getLogger(Association12Resource.class);

    private static final String ENTITY_NAME = "association12";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final Association12Service association12Service;

    private final Association12QueryService association12QueryService;

    public Association12Resource(Association12Service association12Service, Association12QueryService association12QueryService) {
        this.association12Service = association12Service;
        this.association12QueryService = association12QueryService;
    }

    /**
     * {@code POST  /association-12-s} : Create a new association12.
     *
     * @param association12 the association12 to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new association12, or with status {@code 400 (Bad Request)} if the association12 has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/association-12-s")
    public ResponseEntity<Association12> createAssociation12(@RequestBody Association12 association12) throws URISyntaxException {
        log.debug("REST request to save Association12 : {}", association12);
        if (association12.getId() != null) {
            throw new BadRequestAlertException("A new association12 cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Association12 result = association12Service.save(association12);
        return ResponseEntity.created(new URI("/api/association-12-s/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /association-12-s} : Updates an existing association12.
     *
     * @param association12 the association12 to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated association12,
     * or with status {@code 400 (Bad Request)} if the association12 is not valid,
     * or with status {@code 500 (Internal Server Error)} if the association12 couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/association-12-s")
    public ResponseEntity<Association12> updateAssociation12(@RequestBody Association12 association12) throws URISyntaxException {
        log.debug("REST request to update Association12 : {}", association12);
        if (association12.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Association12 result = association12Service.save(association12);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, association12.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /association-12-s} : get all the association12s.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of association12s in body.
     */
    @GetMapping("/association-12-s")
    public ResponseEntity<List<Association12>> getAllAssociation12s(Association12Criteria criteria, Pageable pageable) {
        log.debug("REST request to get Association12s by criteria: {}", criteria);
        Page<Association12> page = association12QueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /association-12-s/count} : count all the association12s.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/association-12-s/count")
    public ResponseEntity<Long> countAssociation12s(Association12Criteria criteria) {
        log.debug("REST request to count Association12s by criteria: {}", criteria);
        return ResponseEntity.ok().body(association12QueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /association-12-s/:id} : get the "id" association12.
     *
     * @param id the id of the association12 to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the association12, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/association-12-s/{id}")
    public ResponseEntity<Association12> getAssociation12(@PathVariable Long id) {
        log.debug("REST request to get Association12 : {}", id);
        Optional<Association12> association12 = association12Service.findOne(id);
        return ResponseUtil.wrapOrNotFound(association12);
    }

    /**
     * {@code DELETE  /association-12-s/:id} : delete the "id" association12.
     *
     * @param id the id of the association12 to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/association-12-s/{id}")
    public ResponseEntity<Void> deleteAssociation12(@PathVariable Long id) {
        log.debug("REST request to delete Association12 : {}", id);
        association12Service.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

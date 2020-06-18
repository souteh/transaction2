package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Pdventity;
import com.mycompany.myapp.service.PdventityService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.PdventityCriteria;
import com.mycompany.myapp.service.PdventityQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.Pdventity}.
 */
@RestController
@RequestMapping("/api")
public class PdventityResource {

    private final Logger log = LoggerFactory.getLogger(PdventityResource.class);

    private static final String ENTITY_NAME = "pdventity";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PdventityService pdventityService;

    private final PdventityQueryService pdventityQueryService;

    public PdventityResource(PdventityService pdventityService, PdventityQueryService pdventityQueryService) {
        this.pdventityService = pdventityService;
        this.pdventityQueryService = pdventityQueryService;
    }

    /**
     * {@code POST  /pdventities} : Create a new pdventity.
     *
     * @param pdventity the pdventity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pdventity, or with status {@code 400 (Bad Request)} if the pdventity has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pdventities")
    public ResponseEntity<Pdventity> createPdventity(@Valid @RequestBody Pdventity pdventity) throws URISyntaxException {
        log.debug("REST request to save Pdventity : {}", pdventity);
        if (pdventity.getId() != null) {
            throw new BadRequestAlertException("A new pdventity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Pdventity result = pdventityService.save(pdventity);
        return ResponseEntity.created(new URI("/api/pdventities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pdventities} : Updates an existing pdventity.
     *
     * @param pdventity the pdventity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pdventity,
     * or with status {@code 400 (Bad Request)} if the pdventity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pdventity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pdventities")
    public ResponseEntity<Pdventity> updatePdventity(@Valid @RequestBody Pdventity pdventity) throws URISyntaxException {
        log.debug("REST request to update Pdventity : {}", pdventity);
        if (pdventity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Pdventity result = pdventityService.save(pdventity);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pdventity.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pdventities} : get all the pdventities.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pdventities in body.
     */
    @GetMapping("/pdventities")
    public ResponseEntity<List<Pdventity>> getAllPdventities(PdventityCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Pdventities by criteria: {}", criteria);
        Page<Pdventity> page = pdventityQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /pdventities/count} : count all the pdventities.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/pdventities/count")
    public ResponseEntity<Long> countPdventities(PdventityCriteria criteria) {
        log.debug("REST request to count Pdventities by criteria: {}", criteria);
        return ResponseEntity.ok().body(pdventityQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /pdventities/:id} : get the "id" pdventity.
     *
     * @param id the id of the pdventity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pdventity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pdventities/{id}")
    public ResponseEntity<Pdventity> getPdventity(@PathVariable Long id) {
        log.debug("REST request to get Pdventity : {}", id);
        Optional<Pdventity> pdventity = pdventityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pdventity);
    }

    /**
     * {@code DELETE  /pdventities/:id} : delete the "id" pdventity.
     *
     * @param id the id of the pdventity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pdventities/{id}")
    public ResponseEntity<Void> deletePdventity(@PathVariable Long id) {
        log.debug("REST request to delete Pdventity : {}", id);
        pdventityService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Clientglentity;
import com.mycompany.myapp.service.ClientglentityService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.ClientglentityCriteria;
import com.mycompany.myapp.service.ClientglentityQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.Clientglentity}.
 */
@RestController
@RequestMapping("/api")
public class ClientglentityResource {

    private final Logger log = LoggerFactory.getLogger(ClientglentityResource.class);

    private static final String ENTITY_NAME = "clientglentity";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClientglentityService clientglentityService;

    private final ClientglentityQueryService clientglentityQueryService;

    public ClientglentityResource(ClientglentityService clientglentityService, ClientglentityQueryService clientglentityQueryService) {
        this.clientglentityService = clientglentityService;
        this.clientglentityQueryService = clientglentityQueryService;
    }

    /**
     * {@code POST  /clientglentities} : Create a new clientglentity.
     *
     * @param clientglentity the clientglentity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new clientglentity, or with status {@code 400 (Bad Request)} if the clientglentity has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/clientglentities")
    public ResponseEntity<Clientglentity> createClientglentity(@Valid @RequestBody Clientglentity clientglentity) throws URISyntaxException {
        log.debug("REST request to save Clientglentity : {}", clientglentity);
        if (clientglentity.getId() != null) {
            throw new BadRequestAlertException("A new clientglentity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Clientglentity result = clientglentityService.save(clientglentity);
        return ResponseEntity.created(new URI("/api/clientglentities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /clientglentities} : Updates an existing clientglentity.
     *
     * @param clientglentity the clientglentity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated clientglentity,
     * or with status {@code 400 (Bad Request)} if the clientglentity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the clientglentity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/clientglentities")
    public ResponseEntity<Clientglentity> updateClientglentity(@Valid @RequestBody Clientglentity clientglentity) throws URISyntaxException {
        log.debug("REST request to update Clientglentity : {}", clientglentity);
        if (clientglentity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Clientglentity result = clientglentityService.save(clientglentity);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, clientglentity.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /clientglentities} : get all the clientglentities.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of clientglentities in body.
     */
    @GetMapping("/clientglentities")
    public ResponseEntity<List<Clientglentity>> getAllClientglentities(ClientglentityCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Clientglentities by criteria: {}", criteria);
        Page<Clientglentity> page = clientglentityQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /clientglentities/count} : count all the clientglentities.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/clientglentities/count")
    public ResponseEntity<Long> countClientglentities(ClientglentityCriteria criteria) {
        log.debug("REST request to count Clientglentities by criteria: {}", criteria);
        return ResponseEntity.ok().body(clientglentityQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /clientglentities/:id} : get the "id" clientglentity.
     *
     * @param id the id of the clientglentity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the clientglentity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/clientglentities/{id}")
    public ResponseEntity<Clientglentity> getClientglentity(@PathVariable Long id) {
        log.debug("REST request to get Clientglentity : {}", id);
        Optional<Clientglentity> clientglentity = clientglentityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(clientglentity);
    }

    /**
     * {@code DELETE  /clientglentities/:id} : delete the "id" clientglentity.
     *
     * @param id the id of the clientglentity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/clientglentities/{id}")
    public ResponseEntity<Void> deleteClientglentity(@PathVariable Long id) {
        log.debug("REST request to delete Clientglentity : {}", id);
        clientglentityService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

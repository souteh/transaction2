package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Modeoperationenum;
import com.mycompany.myapp.service.ModeoperationenumService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.ModeoperationenumCriteria;
import com.mycompany.myapp.service.ModeoperationenumQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.Modeoperationenum}.
 */
@RestController
@RequestMapping("/api")
public class ModeoperationenumResource {

    private final Logger log = LoggerFactory.getLogger(ModeoperationenumResource.class);

    private static final String ENTITY_NAME = "modeoperationenum";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ModeoperationenumService modeoperationenumService;

    private final ModeoperationenumQueryService modeoperationenumQueryService;

    public ModeoperationenumResource(ModeoperationenumService modeoperationenumService, ModeoperationenumQueryService modeoperationenumQueryService) {
        this.modeoperationenumService = modeoperationenumService;
        this.modeoperationenumQueryService = modeoperationenumQueryService;
    }

    /**
     * {@code POST  /modeoperationenums} : Create a new modeoperationenum.
     *
     * @param modeoperationenum the modeoperationenum to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new modeoperationenum, or with status {@code 400 (Bad Request)} if the modeoperationenum has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/modeoperationenums")
    public ResponseEntity<Modeoperationenum> createModeoperationenum(@Valid @RequestBody Modeoperationenum modeoperationenum) throws URISyntaxException {
        log.debug("REST request to save Modeoperationenum : {}", modeoperationenum);
        if (modeoperationenum.getId() != null) {
            throw new BadRequestAlertException("A new modeoperationenum cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Modeoperationenum result = modeoperationenumService.save(modeoperationenum);
        return ResponseEntity.created(new URI("/api/modeoperationenums/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /modeoperationenums} : Updates an existing modeoperationenum.
     *
     * @param modeoperationenum the modeoperationenum to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated modeoperationenum,
     * or with status {@code 400 (Bad Request)} if the modeoperationenum is not valid,
     * or with status {@code 500 (Internal Server Error)} if the modeoperationenum couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/modeoperationenums")
    public ResponseEntity<Modeoperationenum> updateModeoperationenum(@Valid @RequestBody Modeoperationenum modeoperationenum) throws URISyntaxException {
        log.debug("REST request to update Modeoperationenum : {}", modeoperationenum);
        if (modeoperationenum.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Modeoperationenum result = modeoperationenumService.save(modeoperationenum);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, modeoperationenum.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /modeoperationenums} : get all the modeoperationenums.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of modeoperationenums in body.
     */
    @GetMapping("/modeoperationenums")
    public ResponseEntity<List<Modeoperationenum>> getAllModeoperationenums(ModeoperationenumCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Modeoperationenums by criteria: {}", criteria);
        Page<Modeoperationenum> page = modeoperationenumQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /modeoperationenums/count} : count all the modeoperationenums.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/modeoperationenums/count")
    public ResponseEntity<Long> countModeoperationenums(ModeoperationenumCriteria criteria) {
        log.debug("REST request to count Modeoperationenums by criteria: {}", criteria);
        return ResponseEntity.ok().body(modeoperationenumQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /modeoperationenums/:id} : get the "id" modeoperationenum.
     *
     * @param id the id of the modeoperationenum to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the modeoperationenum, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/modeoperationenums/{id}")
    public ResponseEntity<Modeoperationenum> getModeoperationenum(@PathVariable Long id) {
        log.debug("REST request to get Modeoperationenum : {}", id);
        Optional<Modeoperationenum> modeoperationenum = modeoperationenumService.findOne(id);
        return ResponseUtil.wrapOrNotFound(modeoperationenum);
    }

    /**
     * {@code DELETE  /modeoperationenums/:id} : delete the "id" modeoperationenum.
     *
     * @param id the id of the modeoperationenum to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/modeoperationenums/{id}")
    public ResponseEntity<Void> deleteModeoperationenum(@PathVariable Long id) {
        log.debug("REST request to delete Modeoperationenum : {}", id);
        modeoperationenumService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Modeoperationenum;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Modeoperationenum}.
 */
public interface ModeoperationenumService {

    /**
     * Save a modeoperationenum.
     *
     * @param modeoperationenum the entity to save.
     * @return the persisted entity.
     */
    Modeoperationenum save(Modeoperationenum modeoperationenum);

    /**
     * Get all the modeoperationenums.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Modeoperationenum> findAll(Pageable pageable);


    /**
     * Get the "id" modeoperationenum.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Modeoperationenum> findOne(Long id);

    /**
     * Delete the "id" modeoperationenum.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Typeannulationenum;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Typeannulationenum}.
 */
public interface TypeannulationenumService {

    /**
     * Save a typeannulationenum.
     *
     * @param typeannulationenum the entity to save.
     * @return the persisted entity.
     */
    Typeannulationenum save(Typeannulationenum typeannulationenum);

    /**
     * Get all the typeannulationenums.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Typeannulationenum> findAll(Pageable pageable);


    /**
     * Get the "id" typeannulationenum.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Typeannulationenum> findOne(Long id);

    /**
     * Delete the "id" typeannulationenum.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

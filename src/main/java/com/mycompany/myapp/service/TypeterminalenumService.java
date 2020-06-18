package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Typeterminalenum;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Typeterminalenum}.
 */
public interface TypeterminalenumService {

    /**
     * Save a typeterminalenum.
     *
     * @param typeterminalenum the entity to save.
     * @return the persisted entity.
     */
    Typeterminalenum save(Typeterminalenum typeterminalenum);

    /**
     * Get all the typeterminalenums.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Typeterminalenum> findAll(Pageable pageable);


    /**
     * Get the "id" typeterminalenum.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Typeterminalenum> findOne(Long id);

    /**
     * Delete the "id" typeterminalenum.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

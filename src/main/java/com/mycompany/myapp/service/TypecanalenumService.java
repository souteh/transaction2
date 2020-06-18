package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Typecanalenum;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Typecanalenum}.
 */
public interface TypecanalenumService {

    /**
     * Save a typecanalenum.
     *
     * @param typecanalenum the entity to save.
     * @return the persisted entity.
     */
    Typecanalenum save(Typecanalenum typecanalenum);

    /**
     * Get all the typecanalenums.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Typecanalenum> findAll(Pageable pageable);


    /**
     * Get the "id" typecanalenum.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Typecanalenum> findOne(Long id);

    /**
     * Delete the "id" typecanalenum.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

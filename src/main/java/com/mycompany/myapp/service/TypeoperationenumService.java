package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Typeoperationenum;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Typeoperationenum}.
 */
public interface TypeoperationenumService {

    /**
     * Save a typeoperationenum.
     *
     * @param typeoperationenum the entity to save.
     * @return the persisted entity.
     */
    Typeoperationenum save(Typeoperationenum typeoperationenum);

    /**
     * Get all the typeoperationenums.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Typeoperationenum> findAll(Pageable pageable);


    /**
     * Get the "id" typeoperationenum.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Typeoperationenum> findOne(Long id);

    /**
     * Delete the "id" typeoperationenum.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

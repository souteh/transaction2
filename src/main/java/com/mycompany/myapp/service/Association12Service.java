package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Association12;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Association12}.
 */
public interface Association12Service {

    /**
     * Save a association12.
     *
     * @param association12 the entity to save.
     * @return the persisted entity.
     */
    Association12 save(Association12 association12);

    /**
     * Get all the association12s.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Association12> findAll(Pageable pageable);


    /**
     * Get the "id" association12.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Association12> findOne(Long id);

    /**
     * Delete the "id" association12.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

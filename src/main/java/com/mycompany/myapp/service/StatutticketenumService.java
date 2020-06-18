package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Statutticketenum;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Statutticketenum}.
 */
public interface StatutticketenumService {

    /**
     * Save a statutticketenum.
     *
     * @param statutticketenum the entity to save.
     * @return the persisted entity.
     */
    Statutticketenum save(Statutticketenum statutticketenum);

    /**
     * Get all the statutticketenums.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Statutticketenum> findAll(Pageable pageable);


    /**
     * Get the "id" statutticketenum.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Statutticketenum> findOne(Long id);

    /**
     * Delete the "id" statutticketenum.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

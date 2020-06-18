package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Pdventity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Pdventity}.
 */
public interface PdventityService {

    /**
     * Save a pdventity.
     *
     * @param pdventity the entity to save.
     * @return the persisted entity.
     */
    Pdventity save(Pdventity pdventity);

    /**
     * Get all the pdventities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Pdventity> findAll(Pageable pageable);


    /**
     * Get the "id" pdventity.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Pdventity> findOne(Long id);

    /**
     * Delete the "id" pdventity.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

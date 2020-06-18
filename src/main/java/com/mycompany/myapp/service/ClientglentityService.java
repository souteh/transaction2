package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Clientglentity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Clientglentity}.
 */
public interface ClientglentityService {

    /**
     * Save a clientglentity.
     *
     * @param clientglentity the entity to save.
     * @return the persisted entity.
     */
    Clientglentity save(Clientglentity clientglentity);

    /**
     * Get all the clientglentities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Clientglentity> findAll(Pageable pageable);


    /**
     * Get the "id" clientglentity.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Clientglentity> findOne(Long id);

    /**
     * Delete the "id" clientglentity.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Formulationentity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Formulationentity}.
 */
public interface FormulationentityService {

    /**
     * Save a formulationentity.
     *
     * @param formulationentity the entity to save.
     * @return the persisted entity.
     */
    Formulationentity save(Formulationentity formulationentity);

    /**
     * Get all the formulationentities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Formulationentity> findAll(Pageable pageable);


    /**
     * Get the "id" formulationentity.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Formulationentity> findOne(Long id);

    /**
     * Delete the "id" formulationentity.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

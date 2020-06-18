package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Typepaiementenum;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Typepaiementenum}.
 */
public interface TypepaiementenumService {

    /**
     * Save a typepaiementenum.
     *
     * @param typepaiementenum the entity to save.
     * @return the persisted entity.
     */
    Typepaiementenum save(Typepaiementenum typepaiementenum);

    /**
     * Get all the typepaiementenums.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Typepaiementenum> findAll(Pageable pageable);


    /**
     * Get the "id" typepaiementenum.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Typepaiementenum> findOne(Long id);

    /**
     * Delete the "id" typepaiementenum.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

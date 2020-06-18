package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Catonlinecanalenum;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Catonlinecanalenum}.
 */
public interface CatonlinecanalenumService {

    /**
     * Save a catonlinecanalenum.
     *
     * @param catonlinecanalenum the entity to save.
     * @return the persisted entity.
     */
    Catonlinecanalenum save(Catonlinecanalenum catonlinecanalenum);

    /**
     * Get all the catonlinecanalenums.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Catonlinecanalenum> findAll(Pageable pageable);


    /**
     * Get the "id" catonlinecanalenum.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Catonlinecanalenum> findOne(Long id);

    /**
     * Delete the "id" catonlinecanalenum.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

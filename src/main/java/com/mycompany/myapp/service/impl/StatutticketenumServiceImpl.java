package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.StatutticketenumService;
import com.mycompany.myapp.domain.Statutticketenum;
import com.mycompany.myapp.repository.StatutticketenumRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Statutticketenum}.
 */
@Service
@Transactional
public class StatutticketenumServiceImpl implements StatutticketenumService {

    private final Logger log = LoggerFactory.getLogger(StatutticketenumServiceImpl.class);

    private final StatutticketenumRepository statutticketenumRepository;

    public StatutticketenumServiceImpl(StatutticketenumRepository statutticketenumRepository) {
        this.statutticketenumRepository = statutticketenumRepository;
    }

    /**
     * Save a statutticketenum.
     *
     * @param statutticketenum the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Statutticketenum save(Statutticketenum statutticketenum) {
        log.debug("Request to save Statutticketenum : {}", statutticketenum);
        return statutticketenumRepository.save(statutticketenum);
    }

    /**
     * Get all the statutticketenums.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Statutticketenum> findAll(Pageable pageable) {
        log.debug("Request to get all Statutticketenums");
        return statutticketenumRepository.findAll(pageable);
    }


    /**
     * Get one statutticketenum by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Statutticketenum> findOne(Long id) {
        log.debug("Request to get Statutticketenum : {}", id);
        return statutticketenumRepository.findById(id);
    }

    /**
     * Delete the statutticketenum by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Statutticketenum : {}", id);
        statutticketenumRepository.deleteById(id);
    }
}

package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.TypeterminalenumService;
import com.mycompany.myapp.domain.Typeterminalenum;
import com.mycompany.myapp.repository.TypeterminalenumRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Typeterminalenum}.
 */
@Service
@Transactional
public class TypeterminalenumServiceImpl implements TypeterminalenumService {

    private final Logger log = LoggerFactory.getLogger(TypeterminalenumServiceImpl.class);

    private final TypeterminalenumRepository typeterminalenumRepository;

    public TypeterminalenumServiceImpl(TypeterminalenumRepository typeterminalenumRepository) {
        this.typeterminalenumRepository = typeterminalenumRepository;
    }

    /**
     * Save a typeterminalenum.
     *
     * @param typeterminalenum the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Typeterminalenum save(Typeterminalenum typeterminalenum) {
        log.debug("Request to save Typeterminalenum : {}", typeterminalenum);
        return typeterminalenumRepository.save(typeterminalenum);
    }

    /**
     * Get all the typeterminalenums.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Typeterminalenum> findAll(Pageable pageable) {
        log.debug("Request to get all Typeterminalenums");
        return typeterminalenumRepository.findAll(pageable);
    }


    /**
     * Get one typeterminalenum by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Typeterminalenum> findOne(Long id) {
        log.debug("Request to get Typeterminalenum : {}", id);
        return typeterminalenumRepository.findById(id);
    }

    /**
     * Delete the typeterminalenum by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Typeterminalenum : {}", id);
        typeterminalenumRepository.deleteById(id);
    }
}

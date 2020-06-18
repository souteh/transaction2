package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.TypeannulationenumService;
import com.mycompany.myapp.domain.Typeannulationenum;
import com.mycompany.myapp.repository.TypeannulationenumRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Typeannulationenum}.
 */
@Service
@Transactional
public class TypeannulationenumServiceImpl implements TypeannulationenumService {

    private final Logger log = LoggerFactory.getLogger(TypeannulationenumServiceImpl.class);

    private final TypeannulationenumRepository typeannulationenumRepository;

    public TypeannulationenumServiceImpl(TypeannulationenumRepository typeannulationenumRepository) {
        this.typeannulationenumRepository = typeannulationenumRepository;
    }

    /**
     * Save a typeannulationenum.
     *
     * @param typeannulationenum the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Typeannulationenum save(Typeannulationenum typeannulationenum) {
        log.debug("Request to save Typeannulationenum : {}", typeannulationenum);
        return typeannulationenumRepository.save(typeannulationenum);
    }

    /**
     * Get all the typeannulationenums.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Typeannulationenum> findAll(Pageable pageable) {
        log.debug("Request to get all Typeannulationenums");
        return typeannulationenumRepository.findAll(pageable);
    }


    /**
     * Get one typeannulationenum by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Typeannulationenum> findOne(Long id) {
        log.debug("Request to get Typeannulationenum : {}", id);
        return typeannulationenumRepository.findById(id);
    }

    /**
     * Delete the typeannulationenum by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Typeannulationenum : {}", id);
        typeannulationenumRepository.deleteById(id);
    }
}

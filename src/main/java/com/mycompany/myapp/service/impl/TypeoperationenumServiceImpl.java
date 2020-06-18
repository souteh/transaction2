package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.TypeoperationenumService;
import com.mycompany.myapp.domain.Typeoperationenum;
import com.mycompany.myapp.repository.TypeoperationenumRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Typeoperationenum}.
 */
@Service
@Transactional
public class TypeoperationenumServiceImpl implements TypeoperationenumService {

    private final Logger log = LoggerFactory.getLogger(TypeoperationenumServiceImpl.class);

    private final TypeoperationenumRepository typeoperationenumRepository;

    public TypeoperationenumServiceImpl(TypeoperationenumRepository typeoperationenumRepository) {
        this.typeoperationenumRepository = typeoperationenumRepository;
    }

    /**
     * Save a typeoperationenum.
     *
     * @param typeoperationenum the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Typeoperationenum save(Typeoperationenum typeoperationenum) {
        log.debug("Request to save Typeoperationenum : {}", typeoperationenum);
        return typeoperationenumRepository.save(typeoperationenum);
    }

    /**
     * Get all the typeoperationenums.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Typeoperationenum> findAll(Pageable pageable) {
        log.debug("Request to get all Typeoperationenums");
        return typeoperationenumRepository.findAll(pageable);
    }


    /**
     * Get one typeoperationenum by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Typeoperationenum> findOne(Long id) {
        log.debug("Request to get Typeoperationenum : {}", id);
        return typeoperationenumRepository.findById(id);
    }

    /**
     * Delete the typeoperationenum by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Typeoperationenum : {}", id);
        typeoperationenumRepository.deleteById(id);
    }
}

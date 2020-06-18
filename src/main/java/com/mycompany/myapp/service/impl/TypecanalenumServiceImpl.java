package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.TypecanalenumService;
import com.mycompany.myapp.domain.Typecanalenum;
import com.mycompany.myapp.repository.TypecanalenumRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Typecanalenum}.
 */
@Service
@Transactional
public class TypecanalenumServiceImpl implements TypecanalenumService {

    private final Logger log = LoggerFactory.getLogger(TypecanalenumServiceImpl.class);

    private final TypecanalenumRepository typecanalenumRepository;

    public TypecanalenumServiceImpl(TypecanalenumRepository typecanalenumRepository) {
        this.typecanalenumRepository = typecanalenumRepository;
    }

    /**
     * Save a typecanalenum.
     *
     * @param typecanalenum the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Typecanalenum save(Typecanalenum typecanalenum) {
        log.debug("Request to save Typecanalenum : {}", typecanalenum);
        return typecanalenumRepository.save(typecanalenum);
    }

    /**
     * Get all the typecanalenums.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Typecanalenum> findAll(Pageable pageable) {
        log.debug("Request to get all Typecanalenums");
        return typecanalenumRepository.findAll(pageable);
    }


    /**
     * Get one typecanalenum by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Typecanalenum> findOne(Long id) {
        log.debug("Request to get Typecanalenum : {}", id);
        return typecanalenumRepository.findById(id);
    }

    /**
     * Delete the typecanalenum by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Typecanalenum : {}", id);
        typecanalenumRepository.deleteById(id);
    }
}

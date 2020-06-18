package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.CatonlinecanalenumService;
import com.mycompany.myapp.domain.Catonlinecanalenum;
import com.mycompany.myapp.repository.CatonlinecanalenumRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Catonlinecanalenum}.
 */
@Service
@Transactional
public class CatonlinecanalenumServiceImpl implements CatonlinecanalenumService {

    private final Logger log = LoggerFactory.getLogger(CatonlinecanalenumServiceImpl.class);

    private final CatonlinecanalenumRepository catonlinecanalenumRepository;

    public CatonlinecanalenumServiceImpl(CatonlinecanalenumRepository catonlinecanalenumRepository) {
        this.catonlinecanalenumRepository = catonlinecanalenumRepository;
    }

    /**
     * Save a catonlinecanalenum.
     *
     * @param catonlinecanalenum the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Catonlinecanalenum save(Catonlinecanalenum catonlinecanalenum) {
        log.debug("Request to save Catonlinecanalenum : {}", catonlinecanalenum);
        return catonlinecanalenumRepository.save(catonlinecanalenum);
    }

    /**
     * Get all the catonlinecanalenums.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Catonlinecanalenum> findAll(Pageable pageable) {
        log.debug("Request to get all Catonlinecanalenums");
        return catonlinecanalenumRepository.findAll(pageable);
    }


    /**
     * Get one catonlinecanalenum by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Catonlinecanalenum> findOne(Long id) {
        log.debug("Request to get Catonlinecanalenum : {}", id);
        return catonlinecanalenumRepository.findById(id);
    }

    /**
     * Delete the catonlinecanalenum by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Catonlinecanalenum : {}", id);
        catonlinecanalenumRepository.deleteById(id);
    }
}

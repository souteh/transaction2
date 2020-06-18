package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.PdventityService;
import com.mycompany.myapp.domain.Pdventity;
import com.mycompany.myapp.repository.PdventityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Pdventity}.
 */
@Service
@Transactional
public class PdventityServiceImpl implements PdventityService {

    private final Logger log = LoggerFactory.getLogger(PdventityServiceImpl.class);

    private final PdventityRepository pdventityRepository;

    public PdventityServiceImpl(PdventityRepository pdventityRepository) {
        this.pdventityRepository = pdventityRepository;
    }

    /**
     * Save a pdventity.
     *
     * @param pdventity the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Pdventity save(Pdventity pdventity) {
        log.debug("Request to save Pdventity : {}", pdventity);
        return pdventityRepository.save(pdventity);
    }

    /**
     * Get all the pdventities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Pdventity> findAll(Pageable pageable) {
        log.debug("Request to get all Pdventities");
        return pdventityRepository.findAll(pageable);
    }


    /**
     * Get one pdventity by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Pdventity> findOne(Long id) {
        log.debug("Request to get Pdventity : {}", id);
        return pdventityRepository.findById(id);
    }

    /**
     * Delete the pdventity by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Pdventity : {}", id);
        pdventityRepository.deleteById(id);
    }
}

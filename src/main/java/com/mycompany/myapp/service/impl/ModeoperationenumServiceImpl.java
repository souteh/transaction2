package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.ModeoperationenumService;
import com.mycompany.myapp.domain.Modeoperationenum;
import com.mycompany.myapp.repository.ModeoperationenumRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Modeoperationenum}.
 */
@Service
@Transactional
public class ModeoperationenumServiceImpl implements ModeoperationenumService {

    private final Logger log = LoggerFactory.getLogger(ModeoperationenumServiceImpl.class);

    private final ModeoperationenumRepository modeoperationenumRepository;

    public ModeoperationenumServiceImpl(ModeoperationenumRepository modeoperationenumRepository) {
        this.modeoperationenumRepository = modeoperationenumRepository;
    }

    /**
     * Save a modeoperationenum.
     *
     * @param modeoperationenum the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Modeoperationenum save(Modeoperationenum modeoperationenum) {
        log.debug("Request to save Modeoperationenum : {}", modeoperationenum);
        return modeoperationenumRepository.save(modeoperationenum);
    }

    /**
     * Get all the modeoperationenums.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Modeoperationenum> findAll(Pageable pageable) {
        log.debug("Request to get all Modeoperationenums");
        return modeoperationenumRepository.findAll(pageable);
    }


    /**
     * Get one modeoperationenum by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Modeoperationenum> findOne(Long id) {
        log.debug("Request to get Modeoperationenum : {}", id);
        return modeoperationenumRepository.findById(id);
    }

    /**
     * Delete the modeoperationenum by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Modeoperationenum : {}", id);
        modeoperationenumRepository.deleteById(id);
    }
}

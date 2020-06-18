package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.ClientglentityService;
import com.mycompany.myapp.domain.Clientglentity;
import com.mycompany.myapp.repository.ClientglentityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Clientglentity}.
 */
@Service
@Transactional
public class ClientglentityServiceImpl implements ClientglentityService {

    private final Logger log = LoggerFactory.getLogger(ClientglentityServiceImpl.class);

    private final ClientglentityRepository clientglentityRepository;

    public ClientglentityServiceImpl(ClientglentityRepository clientglentityRepository) {
        this.clientglentityRepository = clientglentityRepository;
    }

    /**
     * Save a clientglentity.
     *
     * @param clientglentity the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Clientglentity save(Clientglentity clientglentity) {
        log.debug("Request to save Clientglentity : {}", clientglentity);
        return clientglentityRepository.save(clientglentity);
    }

    /**
     * Get all the clientglentities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Clientglentity> findAll(Pageable pageable) {
        log.debug("Request to get all Clientglentities");
        return clientglentityRepository.findAll(pageable);
    }


    /**
     * Get one clientglentity by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Clientglentity> findOne(Long id) {
        log.debug("Request to get Clientglentity : {}", id);
        return clientglentityRepository.findById(id);
    }

    /**
     * Delete the clientglentity by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Clientglentity : {}", id);
        clientglentityRepository.deleteById(id);
    }
}

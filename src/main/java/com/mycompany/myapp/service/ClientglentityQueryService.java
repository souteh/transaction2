package com.mycompany.myapp.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.mycompany.myapp.domain.Clientglentity;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.ClientglentityRepository;
import com.mycompany.myapp.service.dto.ClientglentityCriteria;

/**
 * Service for executing complex queries for {@link Clientglentity} entities in the database.
 * The main input is a {@link ClientglentityCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Clientglentity} or a {@link Page} of {@link Clientglentity} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ClientglentityQueryService extends QueryService<Clientglentity> {

    private final Logger log = LoggerFactory.getLogger(ClientglentityQueryService.class);

    private final ClientglentityRepository clientglentityRepository;

    public ClientglentityQueryService(ClientglentityRepository clientglentityRepository) {
        this.clientglentityRepository = clientglentityRepository;
    }

    /**
     * Return a {@link List} of {@link Clientglentity} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Clientglentity> findByCriteria(ClientglentityCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Clientglentity> specification = createSpecification(criteria);
        return clientglentityRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Clientglentity} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Clientglentity> findByCriteria(ClientglentityCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Clientglentity> specification = createSpecification(criteria);
        return clientglentityRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ClientglentityCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Clientglentity> specification = createSpecification(criteria);
        return clientglentityRepository.count(specification);
    }

    /**
     * Function to convert {@link ClientglentityCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Clientglentity> createSpecification(ClientglentityCriteria criteria) {
        Specification<Clientglentity> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Clientglentity_.id));
            }
            if (criteria.getNom() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNom(), Clientglentity_.nom));
            }
            if (criteria.getPrenom() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPrenom(), Clientglentity_.prenom));
            }
            if (criteria.getCin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCin(), Clientglentity_.cin));
            }
            if (criteria.getTelephone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelephone(), Clientglentity_.telephone));
            }
            if (criteria.getCommentaire() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCommentaire(), Clientglentity_.commentaire));
            }
        }
        return specification;
    }
}

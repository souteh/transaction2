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

import com.mycompany.myapp.domain.Association12;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.Association12Repository;
import com.mycompany.myapp.service.dto.Association12Criteria;

/**
 * Service for executing complex queries for {@link Association12} entities in the database.
 * The main input is a {@link Association12Criteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Association12} or a {@link Page} of {@link Association12} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class Association12QueryService extends QueryService<Association12> {

    private final Logger log = LoggerFactory.getLogger(Association12QueryService.class);

    private final Association12Repository association12Repository;

    public Association12QueryService(Association12Repository association12Repository) {
        this.association12Repository = association12Repository;
    }

    /**
     * Return a {@link List} of {@link Association12} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Association12> findByCriteria(Association12Criteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Association12> specification = createSpecification(criteria);
        return association12Repository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Association12} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Association12> findByCriteria(Association12Criteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Association12> specification = createSpecification(criteria);
        return association12Repository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(Association12Criteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Association12> specification = createSpecification(criteria);
        return association12Repository.count(specification);
    }

    /**
     * Function to convert {@link Association12Criteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Association12> createSpecification(Association12Criteria criteria) {
        Specification<Association12> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Association12_.id));
            }
            if (criteria.getIdentifiantconc() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdentifiantconc(), Association12_.identifiantconc));
            }
            if (criteria.getIdId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdId(),
                    root -> root.join(Association12_.id, JoinType.LEFT).get(Clientglentity_.id)));
            }
        }
        return specification;
    }
}

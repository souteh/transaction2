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

import com.mycompany.myapp.domain.Typeterminalenum;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.TypeterminalenumRepository;
import com.mycompany.myapp.service.dto.TypeterminalenumCriteria;

/**
 * Service for executing complex queries for {@link Typeterminalenum} entities in the database.
 * The main input is a {@link TypeterminalenumCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Typeterminalenum} or a {@link Page} of {@link Typeterminalenum} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TypeterminalenumQueryService extends QueryService<Typeterminalenum> {

    private final Logger log = LoggerFactory.getLogger(TypeterminalenumQueryService.class);

    private final TypeterminalenumRepository typeterminalenumRepository;

    public TypeterminalenumQueryService(TypeterminalenumRepository typeterminalenumRepository) {
        this.typeterminalenumRepository = typeterminalenumRepository;
    }

    /**
     * Return a {@link List} of {@link Typeterminalenum} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Typeterminalenum> findByCriteria(TypeterminalenumCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Typeterminalenum> specification = createSpecification(criteria);
        return typeterminalenumRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Typeterminalenum} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Typeterminalenum> findByCriteria(TypeterminalenumCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Typeterminalenum> specification = createSpecification(criteria);
        return typeterminalenumRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TypeterminalenumCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Typeterminalenum> specification = createSpecification(criteria);
        return typeterminalenumRepository.count(specification);
    }

    /**
     * Function to convert {@link TypeterminalenumCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Typeterminalenum> createSpecification(TypeterminalenumCriteria criteria) {
        Specification<Typeterminalenum> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Typeterminalenum_.id));
            }
            if (criteria.gett2020() != null) {
                specification = specification.and(buildStringSpecification(criteria.gett2020(), Typeterminalenum_.t2020));
            }
            if (criteria.gett2030() != null) {
                specification = specification.and(buildStringSpecification(criteria.gett2030(), Typeterminalenum_.t2030));
            }
            if (criteria.getb2062() != null) {
                specification = specification.and(buildStringSpecification(criteria.getb2062(), Typeterminalenum_.b2062));
            }
            if (criteria.getPda() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPda(), Typeterminalenum_.pda));
            }
        }
        return specification;
    }
}

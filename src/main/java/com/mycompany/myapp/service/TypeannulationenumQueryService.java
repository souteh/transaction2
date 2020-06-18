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

import com.mycompany.myapp.domain.Typeannulationenum;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.TypeannulationenumRepository;
import com.mycompany.myapp.service.dto.TypeannulationenumCriteria;

/**
 * Service for executing complex queries for {@link Typeannulationenum} entities in the database.
 * The main input is a {@link TypeannulationenumCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Typeannulationenum} or a {@link Page} of {@link Typeannulationenum} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TypeannulationenumQueryService extends QueryService<Typeannulationenum> {

    private final Logger log = LoggerFactory.getLogger(TypeannulationenumQueryService.class);

    private final TypeannulationenumRepository typeannulationenumRepository;

    public TypeannulationenumQueryService(TypeannulationenumRepository typeannulationenumRepository) {
        this.typeannulationenumRepository = typeannulationenumRepository;
    }

    /**
     * Return a {@link List} of {@link Typeannulationenum} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Typeannulationenum> findByCriteria(TypeannulationenumCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Typeannulationenum> specification = createSpecification(criteria);
        return typeannulationenumRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Typeannulationenum} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Typeannulationenum> findByCriteria(TypeannulationenumCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Typeannulationenum> specification = createSpecification(criteria);
        return typeannulationenumRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TypeannulationenumCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Typeannulationenum> specification = createSpecification(criteria);
        return typeannulationenumRepository.count(specification);
    }

    /**
     * Function to convert {@link TypeannulationenumCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Typeannulationenum> createSpecification(TypeannulationenumCriteria criteria) {
        Specification<Typeannulationenum> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Typeannulationenum_.id));
            }
            if (criteria.getClient() != null) {
                specification = specification.and(buildStringSpecification(criteria.getClient(), Typeannulationenum_.client));
            }
            if (criteria.getMachine() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMachine(), Typeannulationenum_.machine));
            }
            if (criteria.getExceptionnelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getExceptionnelle(), Typeannulationenum_.exceptionnelle));
            }
            if (criteria.getSysteme() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSysteme(), Typeannulationenum_.systeme));
            }
        }
        return specification;
    }
}

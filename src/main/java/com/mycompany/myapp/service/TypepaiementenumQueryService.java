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

import com.mycompany.myapp.domain.Typepaiementenum;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.TypepaiementenumRepository;
import com.mycompany.myapp.service.dto.TypepaiementenumCriteria;

/**
 * Service for executing complex queries for {@link Typepaiementenum} entities in the database.
 * The main input is a {@link TypepaiementenumCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Typepaiementenum} or a {@link Page} of {@link Typepaiementenum} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TypepaiementenumQueryService extends QueryService<Typepaiementenum> {

    private final Logger log = LoggerFactory.getLogger(TypepaiementenumQueryService.class);

    private final TypepaiementenumRepository typepaiementenumRepository;

    public TypepaiementenumQueryService(TypepaiementenumRepository typepaiementenumRepository) {
        this.typepaiementenumRepository = typepaiementenumRepository;
    }

    /**
     * Return a {@link List} of {@link Typepaiementenum} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Typepaiementenum> findByCriteria(TypepaiementenumCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Typepaiementenum> specification = createSpecification(criteria);
        return typepaiementenumRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Typepaiementenum} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Typepaiementenum> findByCriteria(TypepaiementenumCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Typepaiementenum> specification = createSpecification(criteria);
        return typepaiementenumRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TypepaiementenumCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Typepaiementenum> specification = createSpecification(criteria);
        return typepaiementenumRepository.count(specification);
    }

    /**
     * Function to convert {@link TypepaiementenumCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Typepaiementenum> createSpecification(TypepaiementenumCriteria criteria) {
        Specification<Typepaiementenum> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Typepaiementenum_.id));
            }
            if (criteria.getTotal() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTotal(), Typepaiementenum_.total));
            }
            if (criteria.getAvance() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAvance(), Typepaiementenum_.avance));
            }
        }
        return specification;
    }
}

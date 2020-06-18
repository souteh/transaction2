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

import com.mycompany.myapp.domain.Modeoperationenum;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.ModeoperationenumRepository;
import com.mycompany.myapp.service.dto.ModeoperationenumCriteria;

/**
 * Service for executing complex queries for {@link Modeoperationenum} entities in the database.
 * The main input is a {@link ModeoperationenumCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Modeoperationenum} or a {@link Page} of {@link Modeoperationenum} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ModeoperationenumQueryService extends QueryService<Modeoperationenum> {

    private final Logger log = LoggerFactory.getLogger(ModeoperationenumQueryService.class);

    private final ModeoperationenumRepository modeoperationenumRepository;

    public ModeoperationenumQueryService(ModeoperationenumRepository modeoperationenumRepository) {
        this.modeoperationenumRepository = modeoperationenumRepository;
    }

    /**
     * Return a {@link List} of {@link Modeoperationenum} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Modeoperationenum> findByCriteria(ModeoperationenumCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Modeoperationenum> specification = createSpecification(criteria);
        return modeoperationenumRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Modeoperationenum} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Modeoperationenum> findByCriteria(ModeoperationenumCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Modeoperationenum> specification = createSpecification(criteria);
        return modeoperationenumRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ModeoperationenumCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Modeoperationenum> specification = createSpecification(criteria);
        return modeoperationenumRepository.count(specification);
    }

    /**
     * Function to convert {@link ModeoperationenumCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Modeoperationenum> createSpecification(ModeoperationenumCriteria criteria) {
        Specification<Modeoperationenum> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Modeoperationenum_.id));
            }
            if (criteria.getAutomatique() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAutomatique(), Modeoperationenum_.automatique));
            }
            if (criteria.getManuel() != null) {
                specification = specification.and(buildStringSpecification(criteria.getManuel(), Modeoperationenum_.manuel));
            }
        }
        return specification;
    }
}

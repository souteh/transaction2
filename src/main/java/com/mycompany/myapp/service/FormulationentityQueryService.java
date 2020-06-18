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

import com.mycompany.myapp.domain.Formulationentity;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.FormulationentityRepository;
import com.mycompany.myapp.service.dto.FormulationentityCriteria;

/**
 * Service for executing complex queries for {@link Formulationentity} entities in the database.
 * The main input is a {@link FormulationentityCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Formulationentity} or a {@link Page} of {@link Formulationentity} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FormulationentityQueryService extends QueryService<Formulationentity> {

    private final Logger log = LoggerFactory.getLogger(FormulationentityQueryService.class);

    private final FormulationentityRepository formulationentityRepository;

    public FormulationentityQueryService(FormulationentityRepository formulationentityRepository) {
        this.formulationentityRepository = formulationentityRepository;
    }

    /**
     * Return a {@link List} of {@link Formulationentity} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Formulationentity> findByCriteria(FormulationentityCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Formulationentity> specification = createSpecification(criteria);
        return formulationentityRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Formulationentity} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Formulationentity> findByCriteria(FormulationentityCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Formulationentity> specification = createSpecification(criteria);
        return formulationentityRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FormulationentityCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Formulationentity> specification = createSpecification(criteria);
        return formulationentityRepository.count(specification);
    }

    /**
     * Function to convert {@link FormulationentityCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Formulationentity> createSpecification(FormulationentityCriteria criteria) {
        Specification<Formulationentity> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Formulationentity_.id));
            }
            if (criteria.getIdentifiantconc() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdentifiantconc(), Formulationentity_.identifiantconc));
            }
            if (criteria.getIdformulation() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdformulation(), Formulationentity_.idformulation));
            }
            if (criteria.getCodeproduit() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCodeproduit(), Formulationentity_.codeproduit));
            }
            if (criteria.getFormcomplete() != null) {
                specification = specification.and(buildSpecification(criteria.getFormcomplete(), Formulationentity_.formcomplete));
            }
            if (criteria.getDesignation() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDesignation(), Formulationentity_.designation));
            }
            if (criteria.getMisecomb() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMisecomb(), Formulationentity_.misecomb));
            }
            if (criteria.getMisetotale() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMisetotale(), Formulationentity_.misetotale));
            }
            if (criteria.getNumform() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumform(), Formulationentity_.numform));
            }
        }
        return specification;
    }
}

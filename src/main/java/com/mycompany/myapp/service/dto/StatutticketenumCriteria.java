package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.Statutticketenum} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.StatutticketenumResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /statutticketenums?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class StatutticketenumCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter valide;

    private StringFilter annule;

    private StringFilter paye;

    private StringFilter rembourse;

    public StatutticketenumCriteria() {
    }

    public StatutticketenumCriteria(StatutticketenumCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.valide = other.valide == null ? null : other.valide.copy();
        this.annule = other.annule == null ? null : other.annule.copy();
        this.paye = other.paye == null ? null : other.paye.copy();
        this.rembourse = other.rembourse == null ? null : other.rembourse.copy();
    }

    @Override
    public StatutticketenumCriteria copy() {
        return new StatutticketenumCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getValide() {
        return valide;
    }

    public void setValide(StringFilter valide) {
        this.valide = valide;
    }

    public StringFilter getAnnule() {
        return annule;
    }

    public void setAnnule(StringFilter annule) {
        this.annule = annule;
    }

    public StringFilter getPaye() {
        return paye;
    }

    public void setPaye(StringFilter paye) {
        this.paye = paye;
    }

    public StringFilter getRembourse() {
        return rembourse;
    }

    public void setRembourse(StringFilter rembourse) {
        this.rembourse = rembourse;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final StatutticketenumCriteria that = (StatutticketenumCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(valide, that.valide) &&
            Objects.equals(annule, that.annule) &&
            Objects.equals(paye, that.paye) &&
            Objects.equals(rembourse, that.rembourse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        valide,
        annule,
        paye,
        rembourse
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StatutticketenumCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (valide != null ? "valide=" + valide + ", " : "") +
                (annule != null ? "annule=" + annule + ", " : "") +
                (paye != null ? "paye=" + paye + ", " : "") +
                (rembourse != null ? "rembourse=" + rembourse + ", " : "") +
            "}";
    }

}

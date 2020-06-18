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
 * Criteria class for the {@link com.mycompany.myapp.domain.Typeoperationenum} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.TypeoperationenumResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /typeoperationenums?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class TypeoperationenumCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter pari;

    private StringFilter annulation;

    private StringFilter paiement;

    public TypeoperationenumCriteria() {
    }

    public TypeoperationenumCriteria(TypeoperationenumCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.pari = other.pari == null ? null : other.pari.copy();
        this.annulation = other.annulation == null ? null : other.annulation.copy();
        this.paiement = other.paiement == null ? null : other.paiement.copy();
    }

    @Override
    public TypeoperationenumCriteria copy() {
        return new TypeoperationenumCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getPari() {
        return pari;
    }

    public void setPari(StringFilter pari) {
        this.pari = pari;
    }

    public StringFilter getAnnulation() {
        return annulation;
    }

    public void setAnnulation(StringFilter annulation) {
        this.annulation = annulation;
    }

    public StringFilter getPaiement() {
        return paiement;
    }

    public void setPaiement(StringFilter paiement) {
        this.paiement = paiement;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final TypeoperationenumCriteria that = (TypeoperationenumCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(pari, that.pari) &&
            Objects.equals(annulation, that.annulation) &&
            Objects.equals(paiement, that.paiement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        pari,
        annulation,
        paiement
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypeoperationenumCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (pari != null ? "pari=" + pari + ", " : "") +
                (annulation != null ? "annulation=" + annulation + ", " : "") +
                (paiement != null ? "paiement=" + paiement + ", " : "") +
            "}";
    }

}

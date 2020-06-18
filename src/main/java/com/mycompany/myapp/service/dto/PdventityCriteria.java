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
 * Criteria class for the {@link com.mycompany.myapp.domain.Pdventity} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.PdventityResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /pdventities?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PdventityCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter idpdv;

    private StringFilter referencepdv;

    public PdventityCriteria() {
    }

    public PdventityCriteria(PdventityCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.idpdv = other.idpdv == null ? null : other.idpdv.copy();
        this.referencepdv = other.referencepdv == null ? null : other.referencepdv.copy();
    }

    @Override
    public PdventityCriteria copy() {
        return new PdventityCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getIdpdv() {
        return idpdv;
    }

    public void setIdpdv(LongFilter idpdv) {
        this.idpdv = idpdv;
    }

    public StringFilter getReferencepdv() {
        return referencepdv;
    }

    public void setReferencepdv(StringFilter referencepdv) {
        this.referencepdv = referencepdv;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PdventityCriteria that = (PdventityCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(idpdv, that.idpdv) &&
            Objects.equals(referencepdv, that.referencepdv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        idpdv,
        referencepdv
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PdventityCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (idpdv != null ? "idpdv=" + idpdv + ", " : "") +
                (referencepdv != null ? "referencepdv=" + referencepdv + ", " : "") +
            "}";
    }

}

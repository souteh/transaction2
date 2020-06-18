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
 * Criteria class for the {@link com.mycompany.myapp.domain.Association12} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.Association12Resource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /association-12-s?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class Association12Criteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter identifiantconc;

    private LongFilter idId;

    public Association12Criteria() {
    }

    public Association12Criteria(Association12Criteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.identifiantconc = other.identifiantconc == null ? null : other.identifiantconc.copy();
        this.idId = other.idId == null ? null : other.idId.copy();
    }

    @Override
    public Association12Criteria copy() {
        return new Association12Criteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getIdentifiantconc() {
        return identifiantconc;
    }

    public void setIdentifiantconc(LongFilter identifiantconc) {
        this.identifiantconc = identifiantconc;
    }

    public LongFilter getIdId() {
        return idId;
    }

    public void setIdId(LongFilter idId) {
        this.idId = idId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Association12Criteria that = (Association12Criteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(identifiantconc, that.identifiantconc) &&
            Objects.equals(idId, that.idId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        identifiantconc,
        idId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Association12Criteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (identifiantconc != null ? "identifiantconc=" + identifiantconc + ", " : "") +
                (idId != null ? "idId=" + idId + ", " : "") +
            "}";
    }

}

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
 * Criteria class for the {@link com.mycompany.myapp.domain.Catonlinecanalenum} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.CatonlinecanalenumResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /catonlinecanalenums?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CatonlinecanalenumCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter pel;

    private StringFilter ptel;

    public CatonlinecanalenumCriteria() {
    }

    public CatonlinecanalenumCriteria(CatonlinecanalenumCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.pel = other.pel == null ? null : other.pel.copy();
        this.ptel = other.ptel == null ? null : other.ptel.copy();
    }

    @Override
    public CatonlinecanalenumCriteria copy() {
        return new CatonlinecanalenumCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getPel() {
        return pel;
    }

    public void setPel(StringFilter pel) {
        this.pel = pel;
    }

    public StringFilter getPtel() {
        return ptel;
    }

    public void setPtel(StringFilter ptel) {
        this.ptel = ptel;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CatonlinecanalenumCriteria that = (CatonlinecanalenumCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(pel, that.pel) &&
            Objects.equals(ptel, that.ptel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        pel,
        ptel
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CatonlinecanalenumCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (pel != null ? "pel=" + pel + ", " : "") +
                (ptel != null ? "ptel=" + ptel + ", " : "") +
            "}";
    }

}

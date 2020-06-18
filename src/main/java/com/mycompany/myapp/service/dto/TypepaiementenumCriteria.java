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
 * Criteria class for the {@link com.mycompany.myapp.domain.Typepaiementenum} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.TypepaiementenumResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /typepaiementenums?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class TypepaiementenumCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter total;

    private StringFilter avance;

    public TypepaiementenumCriteria() {
    }

    public TypepaiementenumCriteria(TypepaiementenumCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.total = other.total == null ? null : other.total.copy();
        this.avance = other.avance == null ? null : other.avance.copy();
    }

    @Override
    public TypepaiementenumCriteria copy() {
        return new TypepaiementenumCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getTotal() {
        return total;
    }

    public void setTotal(StringFilter total) {
        this.total = total;
    }

    public StringFilter getAvance() {
        return avance;
    }

    public void setAvance(StringFilter avance) {
        this.avance = avance;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final TypepaiementenumCriteria that = (TypepaiementenumCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(total, that.total) &&
            Objects.equals(avance, that.avance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        total,
        avance
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypepaiementenumCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (total != null ? "total=" + total + ", " : "") +
                (avance != null ? "avance=" + avance + ", " : "") +
            "}";
    }

}

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
 * Criteria class for the {@link com.mycompany.myapp.domain.Typeterminalenum} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.TypeterminalenumResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /typeterminalenums?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class TypeterminalenumCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter t2020;

    private StringFilter t2030;

    private StringFilter b2062;

    private StringFilter pda;

    public TypeterminalenumCriteria() {
    }

    public TypeterminalenumCriteria(TypeterminalenumCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.t2020 = other.t2020 == null ? null : other.t2020.copy();
        this.t2030 = other.t2030 == null ? null : other.t2030.copy();
        this.b2062 = other.b2062 == null ? null : other.b2062.copy();
        this.pda = other.pda == null ? null : other.pda.copy();
    }

    @Override
    public TypeterminalenumCriteria copy() {
        return new TypeterminalenumCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter gett2020() {
        return t2020;
    }

    public void sett2020(StringFilter t2020) {
        this.t2020 = t2020;
    }

    public StringFilter gett2030() {
        return t2030;
    }

    public void sett2030(StringFilter t2030) {
        this.t2030 = t2030;
    }

    public StringFilter getb2062() {
        return b2062;
    }

    public void setb2062(StringFilter b2062) {
        this.b2062 = b2062;
    }

    public StringFilter getPda() {
        return pda;
    }

    public void setPda(StringFilter pda) {
        this.pda = pda;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final TypeterminalenumCriteria that = (TypeterminalenumCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(t2020, that.t2020) &&
            Objects.equals(t2030, that.t2030) &&
            Objects.equals(b2062, that.b2062) &&
            Objects.equals(pda, that.pda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        t2020,
        t2030,
        b2062,
        pda
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypeterminalenumCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (t2020 != null ? "t2020=" + t2020 + ", " : "") +
                (t2030 != null ? "t2030=" + t2030 + ", " : "") +
                (b2062 != null ? "b2062=" + b2062 + ", " : "") +
                (pda != null ? "pda=" + pda + ", " : "") +
            "}";
    }

}

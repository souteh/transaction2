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
 * Criteria class for the {@link com.mycompany.myapp.domain.Modeoperationenum} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.ModeoperationenumResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /modeoperationenums?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ModeoperationenumCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter automatique;

    private StringFilter manuel;

    public ModeoperationenumCriteria() {
    }

    public ModeoperationenumCriteria(ModeoperationenumCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.automatique = other.automatique == null ? null : other.automatique.copy();
        this.manuel = other.manuel == null ? null : other.manuel.copy();
    }

    @Override
    public ModeoperationenumCriteria copy() {
        return new ModeoperationenumCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getAutomatique() {
        return automatique;
    }

    public void setAutomatique(StringFilter automatique) {
        this.automatique = automatique;
    }

    public StringFilter getManuel() {
        return manuel;
    }

    public void setManuel(StringFilter manuel) {
        this.manuel = manuel;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ModeoperationenumCriteria that = (ModeoperationenumCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(automatique, that.automatique) &&
            Objects.equals(manuel, that.manuel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        automatique,
        manuel
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ModeoperationenumCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (automatique != null ? "automatique=" + automatique + ", " : "") +
                (manuel != null ? "manuel=" + manuel + ", " : "") +
            "}";
    }

}

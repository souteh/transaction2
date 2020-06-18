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
 * Criteria class for the {@link com.mycompany.myapp.domain.Typecanalenum} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.TypecanalenumResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /typecanalenums?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class TypecanalenumCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter terminal;

    private StringFilter online;

    public TypecanalenumCriteria() {
    }

    public TypecanalenumCriteria(TypecanalenumCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.terminal = other.terminal == null ? null : other.terminal.copy();
        this.online = other.online == null ? null : other.online.copy();
    }

    @Override
    public TypecanalenumCriteria copy() {
        return new TypecanalenumCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getTerminal() {
        return terminal;
    }

    public void setTerminal(StringFilter terminal) {
        this.terminal = terminal;
    }

    public StringFilter getOnline() {
        return online;
    }

    public void setOnline(StringFilter online) {
        this.online = online;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final TypecanalenumCriteria that = (TypecanalenumCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(terminal, that.terminal) &&
            Objects.equals(online, that.online);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        terminal,
        online
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypecanalenumCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (terminal != null ? "terminal=" + terminal + ", " : "") +
                (online != null ? "online=" + online + ", " : "") +
            "}";
    }

}

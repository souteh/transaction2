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
 * Criteria class for the {@link com.mycompany.myapp.domain.Typeannulationenum} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.TypeannulationenumResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /typeannulationenums?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class TypeannulationenumCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter client;

    private StringFilter machine;

    private StringFilter exceptionnelle;

    private StringFilter systeme;

    public TypeannulationenumCriteria() {
    }

    public TypeannulationenumCriteria(TypeannulationenumCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.client = other.client == null ? null : other.client.copy();
        this.machine = other.machine == null ? null : other.machine.copy();
        this.exceptionnelle = other.exceptionnelle == null ? null : other.exceptionnelle.copy();
        this.systeme = other.systeme == null ? null : other.systeme.copy();
    }

    @Override
    public TypeannulationenumCriteria copy() {
        return new TypeannulationenumCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getClient() {
        return client;
    }

    public void setClient(StringFilter client) {
        this.client = client;
    }

    public StringFilter getMachine() {
        return machine;
    }

    public void setMachine(StringFilter machine) {
        this.machine = machine;
    }

    public StringFilter getExceptionnelle() {
        return exceptionnelle;
    }

    public void setExceptionnelle(StringFilter exceptionnelle) {
        this.exceptionnelle = exceptionnelle;
    }

    public StringFilter getSysteme() {
        return systeme;
    }

    public void setSysteme(StringFilter systeme) {
        this.systeme = systeme;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final TypeannulationenumCriteria that = (TypeannulationenumCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(client, that.client) &&
            Objects.equals(machine, that.machine) &&
            Objects.equals(exceptionnelle, that.exceptionnelle) &&
            Objects.equals(systeme, that.systeme);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        client,
        machine,
        exceptionnelle,
        systeme
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypeannulationenumCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (client != null ? "client=" + client + ", " : "") +
                (machine != null ? "machine=" + machine + ", " : "") +
                (exceptionnelle != null ? "exceptionnelle=" + exceptionnelle + ", " : "") +
                (systeme != null ? "systeme=" + systeme + ", " : "") +
            "}";
    }

}

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
 * Criteria class for the {@link com.mycompany.myapp.domain.Formulationentity} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.FormulationentityResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /formulationentities?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class FormulationentityCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter identifiantconc;

    private LongFilter idformulation;

    private StringFilter codeproduit;

    private BooleanFilter formcomplete;

    private StringFilter designation;

    private StringFilter misecomb;

    private IntegerFilter misetotale;

    private IntegerFilter numform;

    public FormulationentityCriteria() {
    }

    public FormulationentityCriteria(FormulationentityCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.identifiantconc = other.identifiantconc == null ? null : other.identifiantconc.copy();
        this.idformulation = other.idformulation == null ? null : other.idformulation.copy();
        this.codeproduit = other.codeproduit == null ? null : other.codeproduit.copy();
        this.formcomplete = other.formcomplete == null ? null : other.formcomplete.copy();
        this.designation = other.designation == null ? null : other.designation.copy();
        this.misecomb = other.misecomb == null ? null : other.misecomb.copy();
        this.misetotale = other.misetotale == null ? null : other.misetotale.copy();
        this.numform = other.numform == null ? null : other.numform.copy();
    }

    @Override
    public FormulationentityCriteria copy() {
        return new FormulationentityCriteria(this);
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

    public LongFilter getIdformulation() {
        return idformulation;
    }

    public void setIdformulation(LongFilter idformulation) {
        this.idformulation = idformulation;
    }

    public StringFilter getCodeproduit() {
        return codeproduit;
    }

    public void setCodeproduit(StringFilter codeproduit) {
        this.codeproduit = codeproduit;
    }

    public BooleanFilter getFormcomplete() {
        return formcomplete;
    }

    public void setFormcomplete(BooleanFilter formcomplete) {
        this.formcomplete = formcomplete;
    }

    public StringFilter getDesignation() {
        return designation;
    }

    public void setDesignation(StringFilter designation) {
        this.designation = designation;
    }

    public StringFilter getMisecomb() {
        return misecomb;
    }

    public void setMisecomb(StringFilter misecomb) {
        this.misecomb = misecomb;
    }

    public IntegerFilter getMisetotale() {
        return misetotale;
    }

    public void setMisetotale(IntegerFilter misetotale) {
        this.misetotale = misetotale;
    }

    public IntegerFilter getNumform() {
        return numform;
    }

    public void setNumform(IntegerFilter numform) {
        this.numform = numform;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final FormulationentityCriteria that = (FormulationentityCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(identifiantconc, that.identifiantconc) &&
            Objects.equals(idformulation, that.idformulation) &&
            Objects.equals(codeproduit, that.codeproduit) &&
            Objects.equals(formcomplete, that.formcomplete) &&
            Objects.equals(designation, that.designation) &&
            Objects.equals(misecomb, that.misecomb) &&
            Objects.equals(misetotale, that.misetotale) &&
            Objects.equals(numform, that.numform);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        identifiantconc,
        idformulation,
        codeproduit,
        formcomplete,
        designation,
        misecomb,
        misetotale,
        numform
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FormulationentityCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (identifiantconc != null ? "identifiantconc=" + identifiantconc + ", " : "") +
                (idformulation != null ? "idformulation=" + idformulation + ", " : "") +
                (codeproduit != null ? "codeproduit=" + codeproduit + ", " : "") +
                (formcomplete != null ? "formcomplete=" + formcomplete + ", " : "") +
                (designation != null ? "designation=" + designation + ", " : "") +
                (misecomb != null ? "misecomb=" + misecomb + ", " : "") +
                (misetotale != null ? "misetotale=" + misetotale + ", " : "") +
                (numform != null ? "numform=" + numform + ", " : "") +
            "}";
    }

}

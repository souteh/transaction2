package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Formulationentity.
 */
@Entity
@Table(name = "formulationentity")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Formulationentity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "identifiantconc", nullable = false)
    private Long identifiantconc;

    @Column(name = "idformulation")
    private Long idformulation;

    @Size(max = 254)
    @Column(name = "codeproduit", length = 254)
    private String codeproduit;

    @Column(name = "formcomplete")
    private Boolean formcomplete;

    @Size(max = 254)
    @Column(name = "designation", length = 254)
    private String designation;

    @Size(max = 254)
    @Column(name = "misecomb", length = 254)
    private String misecomb;

    @Column(name = "misetotale")
    private Integer misetotale;

    @Column(name = "numform")
    private Integer numform;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdentifiantconc() {
        return identifiantconc;
    }

    public Formulationentity identifiantconc(Long identifiantconc) {
        this.identifiantconc = identifiantconc;
        return this;
    }

    public void setIdentifiantconc(Long identifiantconc) {
        this.identifiantconc = identifiantconc;
    }

    public Long getIdformulation() {
        return idformulation;
    }

    public Formulationentity idformulation(Long idformulation) {
        this.idformulation = idformulation;
        return this;
    }

    public void setIdformulation(Long idformulation) {
        this.idformulation = idformulation;
    }

    public String getCodeproduit() {
        return codeproduit;
    }

    public Formulationentity codeproduit(String codeproduit) {
        this.codeproduit = codeproduit;
        return this;
    }

    public void setCodeproduit(String codeproduit) {
        this.codeproduit = codeproduit;
    }

    public Boolean isFormcomplete() {
        return formcomplete;
    }

    public Formulationentity formcomplete(Boolean formcomplete) {
        this.formcomplete = formcomplete;
        return this;
    }

    public void setFormcomplete(Boolean formcomplete) {
        this.formcomplete = formcomplete;
    }

    public String getDesignation() {
        return designation;
    }

    public Formulationentity designation(String designation) {
        this.designation = designation;
        return this;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getMisecomb() {
        return misecomb;
    }

    public Formulationentity misecomb(String misecomb) {
        this.misecomb = misecomb;
        return this;
    }

    public void setMisecomb(String misecomb) {
        this.misecomb = misecomb;
    }

    public Integer getMisetotale() {
        return misetotale;
    }

    public Formulationentity misetotale(Integer misetotale) {
        this.misetotale = misetotale;
        return this;
    }

    public void setMisetotale(Integer misetotale) {
        this.misetotale = misetotale;
    }

    public Integer getNumform() {
        return numform;
    }

    public Formulationentity numform(Integer numform) {
        this.numform = numform;
        return this;
    }

    public void setNumform(Integer numform) {
        this.numform = numform;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Formulationentity)) {
            return false;
        }
        return id != null && id.equals(((Formulationentity) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Formulationentity{" +
            "id=" + getId() +
            ", identifiantconc=" + getIdentifiantconc() +
            ", idformulation=" + getIdformulation() +
            ", codeproduit='" + getCodeproduit() + "'" +
            ", formcomplete='" + isFormcomplete() + "'" +
            ", designation='" + getDesignation() + "'" +
            ", misecomb='" + getMisecomb() + "'" +
            ", misetotale=" + getMisetotale() +
            ", numform=" + getNumform() +
            "}";
    }
}

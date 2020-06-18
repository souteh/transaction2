package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Statutticketenum.
 */
@Entity
@Table(name = "statutticketenum")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Statutticketenum implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 254)
    @Column(name = "valide", length = 254)
    private String valide;

    @Size(max = 254)
    @Column(name = "annule", length = 254)
    private String annule;

    @Size(max = 254)
    @Column(name = "paye", length = 254)
    private String paye;

    @Size(max = 254)
    @Column(name = "rembourse", length = 254)
    private String rembourse;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValide() {
        return valide;
    }

    public Statutticketenum valide(String valide) {
        this.valide = valide;
        return this;
    }

    public void setValide(String valide) {
        this.valide = valide;
    }

    public String getAnnule() {
        return annule;
    }

    public Statutticketenum annule(String annule) {
        this.annule = annule;
        return this;
    }

    public void setAnnule(String annule) {
        this.annule = annule;
    }

    public String getPaye() {
        return paye;
    }

    public Statutticketenum paye(String paye) {
        this.paye = paye;
        return this;
    }

    public void setPaye(String paye) {
        this.paye = paye;
    }

    public String getRembourse() {
        return rembourse;
    }

    public Statutticketenum rembourse(String rembourse) {
        this.rembourse = rembourse;
        return this;
    }

    public void setRembourse(String rembourse) {
        this.rembourse = rembourse;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Statutticketenum)) {
            return false;
        }
        return id != null && id.equals(((Statutticketenum) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Statutticketenum{" +
            "id=" + getId() +
            ", valide='" + getValide() + "'" +
            ", annule='" + getAnnule() + "'" +
            ", paye='" + getPaye() + "'" +
            ", rembourse='" + getRembourse() + "'" +
            "}";
    }
}

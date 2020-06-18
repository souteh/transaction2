package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Pdventity.
 */
@Entity
@Table(name = "pdventity")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Pdventity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "idpdv", nullable = false)
    private Long idpdv;

    @Size(max = 254)
    @Column(name = "referencepdv", length = 254)
    private String referencepdv;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdpdv() {
        return idpdv;
    }

    public Pdventity idpdv(Long idpdv) {
        this.idpdv = idpdv;
        return this;
    }

    public void setIdpdv(Long idpdv) {
        this.idpdv = idpdv;
    }

    public String getReferencepdv() {
        return referencepdv;
    }

    public Pdventity referencepdv(String referencepdv) {
        this.referencepdv = referencepdv;
        return this;
    }

    public void setReferencepdv(String referencepdv) {
        this.referencepdv = referencepdv;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pdventity)) {
            return false;
        }
        return id != null && id.equals(((Pdventity) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Pdventity{" +
            "id=" + getId() +
            ", idpdv=" + getIdpdv() +
            ", referencepdv='" + getReferencepdv() + "'" +
            "}";
    }
}

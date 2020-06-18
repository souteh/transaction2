package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Typeoperationenum.
 */
@Entity
@Table(name = "typeoperationenum")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Typeoperationenum implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 254)
    @Column(name = "pari", length = 254)
    private String pari;

    @Size(max = 254)
    @Column(name = "annulation", length = 254)
    private String annulation;

    @Size(max = 254)
    @Column(name = "paiement", length = 254)
    private String paiement;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPari() {
        return pari;
    }

    public Typeoperationenum pari(String pari) {
        this.pari = pari;
        return this;
    }

    public void setPari(String pari) {
        this.pari = pari;
    }

    public String getAnnulation() {
        return annulation;
    }

    public Typeoperationenum annulation(String annulation) {
        this.annulation = annulation;
        return this;
    }

    public void setAnnulation(String annulation) {
        this.annulation = annulation;
    }

    public String getPaiement() {
        return paiement;
    }

    public Typeoperationenum paiement(String paiement) {
        this.paiement = paiement;
        return this;
    }

    public void setPaiement(String paiement) {
        this.paiement = paiement;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Typeoperationenum)) {
            return false;
        }
        return id != null && id.equals(((Typeoperationenum) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Typeoperationenum{" +
            "id=" + getId() +
            ", pari='" + getPari() + "'" +
            ", annulation='" + getAnnulation() + "'" +
            ", paiement='" + getPaiement() + "'" +
            "}";
    }
}

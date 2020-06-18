package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Modeoperationenum.
 */
@Entity
@Table(name = "modeoperationenum")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Modeoperationenum implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 254)
    @Column(name = "automatique", length = 254)
    private String automatique;

    @Size(max = 254)
    @Column(name = "manuel", length = 254)
    private String manuel;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAutomatique() {
        return automatique;
    }

    public Modeoperationenum automatique(String automatique) {
        this.automatique = automatique;
        return this;
    }

    public void setAutomatique(String automatique) {
        this.automatique = automatique;
    }

    public String getManuel() {
        return manuel;
    }

    public Modeoperationenum manuel(String manuel) {
        this.manuel = manuel;
        return this;
    }

    public void setManuel(String manuel) {
        this.manuel = manuel;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Modeoperationenum)) {
            return false;
        }
        return id != null && id.equals(((Modeoperationenum) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Modeoperationenum{" +
            "id=" + getId() +
            ", automatique='" + getAutomatique() + "'" +
            ", manuel='" + getManuel() + "'" +
            "}";
    }
}

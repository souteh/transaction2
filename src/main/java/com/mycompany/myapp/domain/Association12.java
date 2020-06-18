package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Association12.
 */
@Entity
@Table(name = "association_12")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Association12 implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identifiantconc")
    private Long identifiantconc;

    @ManyToOne
    @JsonIgnoreProperties(value = "association12s", allowSetters = true)
    private Clientglentity id;

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

    public Association12 identifiantconc(Long identifiantconc) {
        this.identifiantconc = identifiantconc;
        return this;
    }

    public void setIdentifiantconc(Long identifiantconc) {
        this.identifiantconc = identifiantconc;
    }

    public Clientglentity getId() {
        return id;
    }

    public Association12 id(Clientglentity clientglentity) {
        this.id = clientglentity;
        return this;
    }

    public void setId(Clientglentity clientglentity) {
        this.id = clientglentity;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Association12)) {
            return false;
        }
        return id != null && id.equals(((Association12) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Association12{" +
            "id=" + getId() +
            ", identifiantconc=" + getIdentifiantconc() +
            "}";
    }
}

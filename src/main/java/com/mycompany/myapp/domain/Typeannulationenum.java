package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Typeannulationenum.
 */
@Entity
@Table(name = "typeannulationenum")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Typeannulationenum implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 254)
    @Column(name = "client", length = 254)
    private String client;

    @Size(max = 254)
    @Column(name = "machine", length = 254)
    private String machine;

    @Size(max = 254)
    @Column(name = "exceptionnelle", length = 254)
    private String exceptionnelle;

    @Size(max = 254)
    @Column(name = "systeme", length = 254)
    private String systeme;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public Typeannulationenum client(String client) {
        this.client = client;
        return this;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getMachine() {
        return machine;
    }

    public Typeannulationenum machine(String machine) {
        this.machine = machine;
        return this;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }

    public String getExceptionnelle() {
        return exceptionnelle;
    }

    public Typeannulationenum exceptionnelle(String exceptionnelle) {
        this.exceptionnelle = exceptionnelle;
        return this;
    }

    public void setExceptionnelle(String exceptionnelle) {
        this.exceptionnelle = exceptionnelle;
    }

    public String getSysteme() {
        return systeme;
    }

    public Typeannulationenum systeme(String systeme) {
        this.systeme = systeme;
        return this;
    }

    public void setSysteme(String systeme) {
        this.systeme = systeme;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Typeannulationenum)) {
            return false;
        }
        return id != null && id.equals(((Typeannulationenum) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Typeannulationenum{" +
            "id=" + getId() +
            ", client='" + getClient() + "'" +
            ", machine='" + getMachine() + "'" +
            ", exceptionnelle='" + getExceptionnelle() + "'" +
            ", systeme='" + getSysteme() + "'" +
            "}";
    }
}

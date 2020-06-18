package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Catonlinecanalenum.
 */
@Entity
@Table(name = "catonlinecanalenum")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Catonlinecanalenum implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 254)
    @Column(name = "pel", length = 254)
    private String pel;

    @Size(max = 254)
    @Column(name = "ptel", length = 254)
    private String ptel;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPel() {
        return pel;
    }

    public Catonlinecanalenum pel(String pel) {
        this.pel = pel;
        return this;
    }

    public void setPel(String pel) {
        this.pel = pel;
    }

    public String getPtel() {
        return ptel;
    }

    public Catonlinecanalenum ptel(String ptel) {
        this.ptel = ptel;
        return this;
    }

    public void setPtel(String ptel) {
        this.ptel = ptel;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Catonlinecanalenum)) {
            return false;
        }
        return id != null && id.equals(((Catonlinecanalenum) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Catonlinecanalenum{" +
            "id=" + getId() +
            ", pel='" + getPel() + "'" +
            ", ptel='" + getPtel() + "'" +
            "}";
    }
}

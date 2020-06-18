package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Typepaiementenum.
 */
@Entity
@Table(name = "typepaiementenum")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Typepaiementenum implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 254)
    @Column(name = "total", length = 254)
    private String total;

    @Size(max = 254)
    @Column(name = "avance", length = 254)
    private String avance;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTotal() {
        return total;
    }

    public Typepaiementenum total(String total) {
        this.total = total;
        return this;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getAvance() {
        return avance;
    }

    public Typepaiementenum avance(String avance) {
        this.avance = avance;
        return this;
    }

    public void setAvance(String avance) {
        this.avance = avance;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Typepaiementenum)) {
            return false;
        }
        return id != null && id.equals(((Typepaiementenum) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Typepaiementenum{" +
            "id=" + getId() +
            ", total='" + getTotal() + "'" +
            ", avance='" + getAvance() + "'" +
            "}";
    }
}

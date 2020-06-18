package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Typeterminalenum.
 */
@Entity
@Table(name = "typeterminalenum")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Typeterminalenum implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 254)
    @Column(name = "t_2020", length = 254)
    private String t2020;

    @Size(max = 254)
    @Column(name = "t_2030", length = 254)
    private String t2030;

    @Size(max = 254)
    @Column(name = "b_2062", length = 254)
    private String b2062;

    @Size(max = 254)
    @Column(name = "pda", length = 254)
    private String pda;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String gett2020() {
        return t2020;
    }

    public Typeterminalenum t2020(String t2020) {
        this.t2020 = t2020;
        return this;
    }

    public void sett2020(String t2020) {
        this.t2020 = t2020;
    }

    public String gett2030() {
        return t2030;
    }

    public Typeterminalenum t2030(String t2030) {
        this.t2030 = t2030;
        return this;
    }

    public void sett2030(String t2030) {
        this.t2030 = t2030;
    }

    public String getb2062() {
        return b2062;
    }

    public Typeterminalenum b2062(String b2062) {
        this.b2062 = b2062;
        return this;
    }

    public void setb2062(String b2062) {
        this.b2062 = b2062;
    }

    public String getPda() {
        return pda;
    }

    public Typeterminalenum pda(String pda) {
        this.pda = pda;
        return this;
    }

    public void setPda(String pda) {
        this.pda = pda;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Typeterminalenum)) {
            return false;
        }
        return id != null && id.equals(((Typeterminalenum) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Typeterminalenum{" +
            "id=" + getId() +
            ", t2020='" + gett2020() + "'" +
            ", t2030='" + gett2030() + "'" +
            ", b2062='" + getb2062() + "'" +
            ", pda='" + getPda() + "'" +
            "}";
    }
}

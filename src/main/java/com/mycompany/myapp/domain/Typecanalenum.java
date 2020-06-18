package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Typecanalenum.
 */
@Entity
@Table(name = "typecanalenum")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Typecanalenum implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 254)
    @Column(name = "terminal", length = 254)
    private String terminal;

    @Size(max = 254)
    @Column(name = "online", length = 254)
    private String online;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTerminal() {
        return terminal;
    }

    public Typecanalenum terminal(String terminal) {
        this.terminal = terminal;
        return this;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getOnline() {
        return online;
    }

    public Typecanalenum online(String online) {
        this.online = online;
        return this;
    }

    public void setOnline(String online) {
        this.online = online;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Typecanalenum)) {
            return false;
        }
        return id != null && id.equals(((Typecanalenum) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Typecanalenum{" +
            "id=" + getId() +
            ", terminal='" + getTerminal() + "'" +
            ", online='" + getOnline() + "'" +
            "}";
    }
}

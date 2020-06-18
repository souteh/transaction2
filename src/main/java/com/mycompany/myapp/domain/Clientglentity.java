package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Clientglentity.
 */
@Entity
@Table(name = "clientglentity")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Clientglentity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 254)
    @Column(name = "nom", length = 254)
    private String nom;

    @Size(max = 254)
    @Column(name = "prenom", length = 254)
    private String prenom;

    @Size(max = 254)
    @Column(name = "cin", length = 254)
    private String cin;

    @Size(max = 254)
    @Column(name = "telephone", length = 254)
    private String telephone;

    @Size(max = 254)
    @Column(name = "commentaire", length = 254)
    private String commentaire;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Clientglentity nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Clientglentity prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCin() {
        return cin;
    }

    public Clientglentity cin(String cin) {
        this.cin = cin;
        return this;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getTelephone() {
        return telephone;
    }

    public Clientglentity telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public Clientglentity commentaire(String commentaire) {
        this.commentaire = commentaire;
        return this;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Clientglentity)) {
            return false;
        }
        return id != null && id.equals(((Clientglentity) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Clientglentity{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", cin='" + getCin() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", commentaire='" + getCommentaire() + "'" +
            "}";
    }
}

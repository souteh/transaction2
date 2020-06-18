package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.Clientglentity} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.ClientglentityResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /clientglentities?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ClientglentityCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter nom;

    private StringFilter prenom;

    private StringFilter cin;

    private StringFilter telephone;

    private StringFilter commentaire;

    public ClientglentityCriteria() {
    }

    public ClientglentityCriteria(ClientglentityCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.nom = other.nom == null ? null : other.nom.copy();
        this.prenom = other.prenom == null ? null : other.prenom.copy();
        this.cin = other.cin == null ? null : other.cin.copy();
        this.telephone = other.telephone == null ? null : other.telephone.copy();
        this.commentaire = other.commentaire == null ? null : other.commentaire.copy();
    }

    @Override
    public ClientglentityCriteria copy() {
        return new ClientglentityCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getNom() {
        return nom;
    }

    public void setNom(StringFilter nom) {
        this.nom = nom;
    }

    public StringFilter getPrenom() {
        return prenom;
    }

    public void setPrenom(StringFilter prenom) {
        this.prenom = prenom;
    }

    public StringFilter getCin() {
        return cin;
    }

    public void setCin(StringFilter cin) {
        this.cin = cin;
    }

    public StringFilter getTelephone() {
        return telephone;
    }

    public void setTelephone(StringFilter telephone) {
        this.telephone = telephone;
    }

    public StringFilter getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(StringFilter commentaire) {
        this.commentaire = commentaire;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ClientglentityCriteria that = (ClientglentityCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(nom, that.nom) &&
            Objects.equals(prenom, that.prenom) &&
            Objects.equals(cin, that.cin) &&
            Objects.equals(telephone, that.telephone) &&
            Objects.equals(commentaire, that.commentaire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        nom,
        prenom,
        cin,
        telephone,
        commentaire
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClientglentityCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (nom != null ? "nom=" + nom + ", " : "") +
                (prenom != null ? "prenom=" + prenom + ", " : "") +
                (cin != null ? "cin=" + cin + ", " : "") +
                (telephone != null ? "telephone=" + telephone + ", " : "") +
                (commentaire != null ? "commentaire=" + commentaire + ", " : "") +
            "}";
    }

}

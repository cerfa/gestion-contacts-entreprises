package fenncim.genba.genesis.gce.entity;


import fenncim.genba.genesis.gce.model.ContactType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CONTACT", schema = "GCE")
public class Contact {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer contactId;
     @Column(unique = true)
     private String nom;
    @Column(unique = true)
     private String  prenom;
    @NotNull
    @Enumerated(EnumType.STRING)
    private ContactType contactType;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "adresseId", referencedColumnName = "adresseId")
    private Adresse adresse;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private  List<Entreprise> entreprises;
    private Timestamp creationDate;
    private Timestamp updateDate;
    private Timestamp deleteDate;


    public Contact() {
        //For the sake of making sonar happy.
    }

    public Contact(Integer contactId, String nom, String prenom, Adresse adresse, Timestamp creationDate, Timestamp updateDate) {
        this.contactId = contactId;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.entreprises = new ArrayList<>();
    }

    public Contact(String nom, String prenom, ContactType contactType, Adresse adresse, List<Entreprise> entreprises) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.contactType = contactType;
        this.entreprises = entreprises;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public Timestamp getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Timestamp deleteDate) {
        this.deleteDate = deleteDate;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    public List<Entreprise> getEntreprises() {
        return entreprises;
    }

    public void setEntreprises(List<Entreprise> entreprises) {
        this.entreprises = entreprises;
    }
}

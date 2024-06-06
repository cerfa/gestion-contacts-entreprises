package fenncim.genba.genesis.gce.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ENTREPRISE", schema = "GCE")
public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer enterpriseId;
    @Column(unique = true)
    private String tva;
    @Column(unique = true)
    private String  denomination;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "adresseId", referencedColumnName = "adresseId")
    private Adresse adresse;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Contact> contacts;
    private Timestamp creationDate;
    private Timestamp updateDate;
    private Timestamp deleteDate;


    public Entreprise() {
        this.contacts = new ArrayList<>();
    }

    public Entreprise(String tva, String denomination, Adresse adresse) {
        this.tva = tva;
        this.denomination = denomination;
        this.adresse = adresse;
        this.contacts = new ArrayList<>();
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getTva() {
        return tva;
    }

    public void setTva(String tva) {
        this.tva = tva;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
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
    public List<Contact> getContacts() {
        return contacts;
    }
    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
}

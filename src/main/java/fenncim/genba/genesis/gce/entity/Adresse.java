package fenncim.genba.genesis.gce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;
@Entity
@Table(name = "ADRESSE", schema = "GCE")
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adresseId;
    private String rue ;
    private int numeroDeRue;
    private int boite;
    private String ville;
    private int cap;
    private Timestamp creationDate;
    private Timestamp updateDate;

    public Adresse() {
    }

    public Adresse(String rue, int numeroDeRue, int boite, String ville, int cap, Timestamp creationDate, Timestamp updateDate) {
        this.rue = rue;
        this.numeroDeRue = numeroDeRue;
        this.boite = boite;
        this.ville = ville;
        this.cap = cap;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }

    public Adresse(String rue, int numeroDeRue, int boite, String ville, int cap) {
        this.rue = rue;
        this.numeroDeRue = numeroDeRue;
        this.boite = boite;
        this.ville = ville;
        this.cap = cap;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public int getNumeroDeRue() {
        return numeroDeRue;
    }

    public void setNumeroDeRue(int numeroDeRue) {
        this.numeroDeRue = numeroDeRue;
    }

    public int getBoite() {
        return boite;
    }

    public void setBoite(int boite) {
        this.boite = boite;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
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

    public Integer getAdresseId() {
        return adresseId;
    }

    public void setAdresseId(Integer adresseId) {
        this.adresseId = adresseId;
    }
}

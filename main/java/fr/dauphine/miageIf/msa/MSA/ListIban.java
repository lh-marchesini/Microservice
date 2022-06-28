package fr.dauphine.miageIf.msa.MSA;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


// Classe persistente representant  un "taux de change"
@Entity
public class ListIban {

    @Id()
    private String iban;

    @Column(name="type_compte")
    private String type;

    private Double interet;

    @Column(name="frais_compte")
    private String frais;

    private Double solde;

    public ListIban() {

    }

    public ListIban(String iban, String type_compte, Double interet, String frais, Double solde) {
        super();
        this.iban = iban;
        this.type = type_compte;
        this.interet = interet;
        this.frais = frais;
        this.solde = solde;
    }

    public String getIban() {
        return iban;
    }
    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public Double getInteret() {
        return interet;
    }
    public void setInteret(Double interet) {
        this.interet = interet;
    }

    public String getFrais() { return frais; }
    public void setFrais(String frais) {
        this.frais = frais;
    }

    public Double getSolde() {
        return solde;
    }
    public void setSolde(Double solde) {
        this.solde = solde;
    }

}
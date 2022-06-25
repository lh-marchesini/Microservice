package fr.dauphine.miageIf.msa.MSA;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;


// Classe persistente representant  un "taux de change"
@Entity
public class Operation {




    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;

    @Column(name="iban")
    private String iban;

    @Column(name="iban_dest")
    private String dest;

    @Column(name="type")
    private String type;

    private Double montant;

    @Column(name="Dates")
    private String date;

    public Operation() {

    }

    public Operation(String iban, String iban_dest, String type, Double montant, Date date) {
        super();
        this.id = id;
        this.iban = iban;
        this.dest = iban_dest;
        this.type = type;
        this.montant = montant;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        this.date = formatter.format(date);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getIban() { return iban; }
    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getDest() { return dest; }
    public void setDest(String dest) { this.dest = dest; }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public Double getMontant() { return montant; }
    public void setMontant(Double montant) { this.montant = montant; }

}

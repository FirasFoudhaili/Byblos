package com.byblos.ticketapi.model;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @Column(name= "objet")
    private String objet;


    @Column(name= "DateDec")
    private Date DateDec;

    @Nullable
    @Column(name= "perimetre")
    private String perimetre ;


    @Column(name= "module")
    private String module;


    @Column(name= "facturation")
    private String facturation;


    @Column(name= "profil")
    private String profil;


    @Column(name= "tdr")
    private String tdr;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public Date getDateDec() {
        return DateDec;
    }

    public void setDateDec(Date dateDec) {
        DateDec = dateDec;
    }

    public String getPerimetre() {
        return perimetre;
    }

    public void setPerimetre(String perimetre) {
        this.perimetre = perimetre;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getFacturation() {
        return facturation;
    }

    public void setFacturation(String facturation) {
        this.facturation = facturation;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    public String getTdr() {
        return tdr;
    }

    public void setTdr(String tdr) {
        this.tdr = tdr;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", objet='" + objet + '\'' +
                ", DateDec=" + DateDec +
                ", perimetre='" + perimetre + '\'' +
                ", module='" + module + '\'' +
                ", facturation='" + facturation + '\'' +
                ", profil='" + profil + '\'' +
                ", tdr='" + tdr + '\'' +
                '}';
    }

    public Ticket(String objet, Date dateDec, String perimetre, String module, String facturation, String profil, String tdr) {
        this.objet = objet;
        DateDec = dateDec;
        this.perimetre = perimetre;
        this.module = module;
        this.facturation = facturation;
        this.profil = profil;
        this.tdr = tdr;
    }

    public Ticket() {
    }
}

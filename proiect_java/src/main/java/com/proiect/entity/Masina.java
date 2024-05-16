package com.proiect.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
@Entity
@Table(name="masini")
public class Masina {
    @Id
    @Column(name = "nr_Inmatriculare", unique = true)
    private String nrInmatriculare;

    @Column(name = "id_utilizator")
    private int idUtilizator;

    private String marca;
    private String model;
    private String culoare;
    private int an_fabricatie;
    private int capacitate;
    private String combustibil;
    private int putere;
    private int cuplu;
    private int volum_portbagaj;
    private double pret;

    public Masina(String nrInmatriculare, int idUtilizator, String marca, String model, String culoare, int an_fabricatie, int capacitate, String combustibil, int putere, int cuplu, int volum_portbagaj, double pret) {
        this.nrInmatriculare = nrInmatriculare;
        this.idUtilizator = idUtilizator;
        this.marca = marca;
        this.model = model;
        this.culoare = culoare;
        this.an_fabricatie = an_fabricatie;
        this.capacitate = capacitate;
        this.combustibil = combustibil;
        this.putere = putere;
        this.cuplu = cuplu;
        this.volum_portbagaj = volum_portbagaj;
        this.pret = pret;
    }

    public void setNrInmatriculare(String nrInmatriculare) {
        this.nrInmatriculare = nrInmatriculare;
    }

    public void setIdUtilizator(int idUtilizator) {
        this.idUtilizator = idUtilizator;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }

    public void setAn_fabricatie(int an_fabricatie) {
        this.an_fabricatie = an_fabricatie;
    }

    public void setCapacitate(int capacitate) {
        this.capacitate = capacitate;
    }

    public void setCombustibil(String combustibil) {
        this.combustibil = combustibil;
    }

    public void setPutere(int putere) {
        this.putere = putere;
    }

    public void setCuplu(int cuplu) {
        this.cuplu = cuplu;
    }

    public void setVolum_portbagaj(int volum_portbagaj) {
        this.volum_portbagaj = volum_portbagaj;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }
}

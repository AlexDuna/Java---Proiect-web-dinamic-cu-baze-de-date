package com.example.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="masini")
public class Masina {
    @Id
    @Column(name="nr_inmatriculare", unique=true)
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

}
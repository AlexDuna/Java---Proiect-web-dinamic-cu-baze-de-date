package com.example.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MasinaFiltres {
    private String nrInmatriculare;
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

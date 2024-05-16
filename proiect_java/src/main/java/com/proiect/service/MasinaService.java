package com.proiect.service;

import com.proiect.entity.Masina;
import com.proiect.repository.MasinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@Service
public class MasinaService {

    @Autowired
    private final MasinaRepository masinaRepository;

    public MasinaService(MasinaRepository masinaRepository) {
        this.masinaRepository = masinaRepository;
    }

    //Metoda pentru obtinerea tuturor masinilor
    public List<Masina> getAllMasini(){
        return masinaRepository.findAll();
    }

    //Metoda obtinere masina dupa numarul de inmatriculare
    public Masina getMasinaByNrInmatriculare(String nrInmatriculare){
        return masinaRepository.findById(nrInmatriculare).orElseThrow(()->new MasinaNotFoundException("Masina cu numarul de inmatriculare: "+nrInmatriculare+" nu a fost gasita!"));
    }

    //Metoda pentru adaugarea unei masini
    public Masina addMasina(Masina masina){
        return masinaRepository.save(masina);
    }

    //Metoda pentru stergerea unei masini
    public Masina updateMasina(String nrInmatriculare, Masina masinaDetalii){
        Masina existingMasina = masinaRepository.findById(nrInmatriculare).orElseThrow(()->new MasinaNotFoundException("Masina cu numarul de inmatriculare: "+nrInmatriculare+" nu a fost gasita!"));
        existingMasina.setMarca(masinaDetalii.getMarca());
        existingMasina.setModel(masinaDetalii.getModel());
        existingMasina.setCuloare(masinaDetalii.getCuloare());
        existingMasina.setAn_fabricatie(masinaDetalii.getAn_fabricatie());
        existingMasina.setCapacitate(masinaDetalii.getCapacitate());
        existingMasina.setCombustibil(masinaDetalii.getCombustibil());
        existingMasina.setPutere(masinaDetalii.getPutere());
        existingMasina.setCuplu(masinaDetalii.getCuplu());
        existingMasina.setVolum_portbagaj(masinaDetalii.getVolum_portbagaj());
        existingMasina.setPret(masinaDetalii.getPret());

        return masinaRepository.save(existingMasina);
    }

    public void deleteMasina(String nrInmatriculare){
        masinaRepository.deleteById(nrInmatriculare);
    }
}

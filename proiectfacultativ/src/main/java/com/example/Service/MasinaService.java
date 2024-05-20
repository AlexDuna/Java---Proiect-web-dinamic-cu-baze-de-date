package com.example.Service;

import com.example.Entity.Masina;
import com.example.Repository.MasinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasinaService {
    @Autowired
    private final MasinaRepository masinaRepository;

    public MasinaService(MasinaRepository masinaRepository) {
        this.masinaRepository = masinaRepository;
    }

    public List<Masina> findAllMasini(){
        return masinaRepository.findAll();
    }

    public Masina addMasina(Masina masina){
        return masinaRepository.save(masina);
    }

    public Masina updateMasina(String nrInmatriculare, Masina masinaDetalii){
        Masina existingMasina = masinaRepository.findById(nrInmatriculare).orElseThrow(()-> new MasinaNotFoundException("Masina cu numarul de inmatriculare "+nrInmatriculare+" nu a fost gasita!"));

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

    public Masina findMasinaByNrInmatriculare(String nrInmatriculare){
        return masinaRepository.findById(nrInmatriculare).orElseThrow(()->new MasinaNotFoundException("Masina cu nr de inmatriculare "+nrInmatriculare+" nu a fost gasita!"));
    }
    public void deleteMasina(String nrInmatriculare){
        masinaRepository.deleteById(nrInmatriculare);
    }

    public List<Masina> findMasiniByMarca(String marca) {
        return masinaRepository.findMasiniByMarca(marca);
    }

    public List<Masina> findMasiniByPutereMaiMareDecat(int putere) {
        return masinaRepository.findMasiniByPutereMaiMareDecat(putere);
    }

    public List<Masina> findMasiniByAnFabricatie(int anFabricatie) {
        return masinaRepository.findMasiniByAnFabricatie(anFabricatie);
    }







    public List<Masina> findMasiniByMarcaAndPutereAndAnFabricatie(String marca, int putere, int anFabricatie) {
        return masinaRepository.findMasiniByMarcaAndPutereAndAnFabricatie(marca, putere, anFabricatie);
    }

    public List<Masina> findMasiniByMarcaAndPutere(String marca, int putere) {
        return masinaRepository.findMasiniByMarcaAndPutere(marca, putere);
    }

    public List<Masina> findMasiniByMarcaAndAnFabricatie(String marca, int anFabricatie) {
        return masinaRepository.findMasiniByMarcaAndAnFabricatie(marca, anFabricatie);
    }

    public List<Masina> findMasiniByPutereAndAnFabricatie(int putere, int anFabricatie) {
        return masinaRepository.findMasiniByPutereAndAnFabricatie(putere, anFabricatie);
    }


}
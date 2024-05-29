package com.example.Service;

import com.example.Entity.Masina;
import com.example.Entity.MasinaFiltres;
import com.example.Repository.MasinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;


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
    
    public List<Masina> findCarsByFilters(MasinaFiltres filters)
    {
        Specification<Masina> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filters.getNrInmatriculare() != null && !filters.getNrInmatriculare().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("nrInmatriculare"), filters.getNrInmatriculare()));
            }
            if (filters.getMarca() != null && !filters.getMarca().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("marca"), filters.getMarca()));
            }
            if (filters.getModel() != null && !filters.getModel().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("model"), filters.getModel()));
            }
            if (filters.getCuloare() != null && !filters.getCuloare().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("culoare"), filters.getCuloare()));
            }
            if (filters.getAn_fabricatie() != 0) {
                predicates.add(criteriaBuilder.equal(root.get("an_fabricatie"), filters.getAn_fabricatie()));
            }
            if (filters.getCapacitate() != 0) {
                predicates.add(criteriaBuilder.equal(root.get("capacitate"), filters.getCapacitate()));
            }
            if (filters.getCombustibil() != null && !filters.getCombustibil().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("combustibil"), filters.getCombustibil()));
            }
            if (filters.getPutere() != 0) {
                predicates.add(criteriaBuilder.equal(root.get("putere"), filters.getPutere()));
            }
            if (filters.getCuplu() != 0) {
                predicates.add(criteriaBuilder.equal(root.get("cuplu"), filters.getCuplu()));
            }
            if (filters.getVolum_portbagaj() != 0) {
                predicates.add(criteriaBuilder.equal(root.get("volum_portbagaj"), filters.getVolum_portbagaj()));
            }
            if (filters.getPret() != 0.0) {
                predicates.add(criteriaBuilder.equal(root.get("pret"), filters.getPret()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        return masinaRepository.findAll(spec);
    }

}
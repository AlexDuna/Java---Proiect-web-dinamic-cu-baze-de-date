package com.proiect.controller;
import java.util.List;

import com.proiect.entity.Masina;
import com.proiect.service.MasinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/masini")
public class MasinaController {

    @Autowired
    private MasinaService masinaService;

    @GetMapping
    public List<Masina>getAllMasini(){
        return masinaService.getAllMasini();
    }

    @GetMapping("/{nrInmatriculare}")
    public ResponseEntity<Masina>getMasinaByNrInmatriculare(@PathVariable String nrInmatriculare){
        Masina masina = masinaService.getMasinaByNrInmatriculare(nrInmatriculare);
        return ResponseEntity.ok(masina);
    }

    @PostMapping
    public ResponseEntity<Masina>addMasina(@RequestBody Masina masina){
        Masina addedMasina = masinaService.addMasina(masina);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedMasina);
    }

    @PutMapping("{nrInmatriculare}")
    public ResponseEntity<Masina>updateMasina(@PathVariable String nrInmatriculare, @RequestBody Masina masinaDetalii){
        Masina updatedMasina = masinaService.updateMasina(nrInmatriculare,masinaDetalii);
        return ResponseEntity.ok(updatedMasina);
    }

    @DeleteMapping("{nrInmatriculare}")
    public ResponseEntity<Void>deleteMasina(@PathVariable String nrInmatriculare){
        masinaService.deleteMasina(nrInmatriculare);
        return ResponseEntity.noContent().build();
    }

}

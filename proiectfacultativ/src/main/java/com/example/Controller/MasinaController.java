package com.example.Controller;

import com.example.Entity.Masina;
import com.example.Service.MasinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/masini")
public class MasinaController {
    @Autowired
    private MasinaService masinaService;

    @GetMapping("/view")
    public String getMasini(Model model){
        List<Masina>masini = masinaService.findAllMasini();
        model.addAttribute("masini",masini);
        return "view";
    }

    @PostMapping("/add")
    public String addMasini(@ModelAttribute Masina masina){
        masinaService.addMasina(masina);
        return "redirect:/masini/view";
    }

    @PostMapping("/update")
    public String updateMasina(@ModelAttribute Masina masina) {
        masinaService.updateMasina(masina.getNrInmatriculare(), masina);
        return "redirect:/masini/view";
    }


    @PostMapping("/delete")
    public String deleteMasina(@RequestParam String nrInmatriculare){
        masinaService.deleteMasina(nrInmatriculare);
        return "redirect:/masini/view";
    }

    @GetMapping("/masini-by-marca")
    public String getMasiniByNume(@RequestParam("nume") String marca, Model model) {
        List<Masina> masini = masinaService.findMasiniByMarca(marca);
        model.addAttribute("masini", masini);
        return "view";
    }

    @GetMapping("/masini-by-putere")
    public String getMasiniByPutereMaiMareDecat(@RequestParam("putere") int putere, Model model) {
        List<Masina> masini = masinaService.findMasiniByPutereMaiMareDecat(putere);
        model.addAttribute("masini", masini);
        return "view";
    }

    @GetMapping("/masini-by-an-fabricatie")
    public String getMasiniByAnFabricatie(@RequestParam("anFabricatie") int anFabricatie, Model model) {
        List<Masina> masini = masinaService.findMasiniByAnFabricatie(anFabricatie);
        model.addAttribute("masini", masini);
        return "view";
    }

}

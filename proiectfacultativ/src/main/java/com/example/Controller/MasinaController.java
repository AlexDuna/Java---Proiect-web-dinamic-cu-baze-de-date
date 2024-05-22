package com.example.Controller;

import com.example.Entity.CustomUserDetails;
import com.example.Entity.Masina;
import com.example.Service.MasinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            String fullName = userDetails.getFullName();
            model.addAttribute("fullName", fullName);
        }
        List<Masina>masini = masinaService.findAllMasini();
        model.addAttribute("masini",masini);
        return "view";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ROLE_EDITOR')")
    public String addMasini(@ModelAttribute Masina masina){
        masinaService.addMasina(masina);
        return "redirect:/masini/view";
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_EDITOR')")
    public String updateMasina(@ModelAttribute Masina masina) {
        masinaService.updateMasina(masina.getNrInmatriculare(), masina);
        return "redirect:/masini/view";
    }


    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('ROLE_EDITOR')")
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

    @GetMapping("/filtreaza")
    public String filtreazaMasini(
            @RequestParam(value = "nume", required = false) String marca,
            @RequestParam(value = "putere",required = false) int putere,
            @RequestParam(value = "anFabricatie",required = false) int anFabricatie,
            Model model) {
        List<Masina> masini;

        if (marca != null && putere != 0 && anFabricatie != 0) {
            masini = masinaService.findMasiniByMarcaAndPutereAndAnFabricatie(marca, putere, anFabricatie);
        } else if (marca != null && putere != 0) {
            masini = masinaService.findMasiniByMarcaAndPutere(marca, putere);
        } else if (marca != null && anFabricatie != 0) {
            masini = masinaService.findMasiniByMarcaAndAnFabricatie(marca, anFabricatie);
        } else if (putere != 0 && anFabricatie != 0) {
            masini = masinaService.findMasiniByPutereAndAnFabricatie(putere, anFabricatie);
        } else if (marca != null) {
            masini = masinaService.findMasiniByMarca(marca);
        } else if (putere != 0) {
            masini = masinaService.findMasiniByPutereMaiMareDecat(putere);
        } else if (anFabricatie != 0) {
            masini = masinaService.findMasiniByAnFabricatie(anFabricatie);
        } else {
            masini = masinaService.findAllMasini();
        }

        model.addAttribute("masini", masini);
        return "view";
    }


}
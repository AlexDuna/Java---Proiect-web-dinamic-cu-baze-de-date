package com.example.Controller;

import com.example.Entity.CustomUserDetails;
import com.example.Entity.Masina;
import com.example.Entity.MasinaFiltres;
import com.example.Service.MasinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    @GetMapping("/user")
    public String userPage(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        if (currentUser != null) {
            model.addAttribute("fullName", currentUser.getUsername());
        }
        List<Masina>masini = masinaService.findAllMasini();
        model.addAttribute("masini",masini);
        return "user";
    }
    @GetMapping("/view")
    public String getMasini(Model model,@AuthenticationPrincipal UserDetails currentUser){
        if (currentUser != null) {
            model.addAttribute("fullName", currentUser.getUsername());
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

   @GetMapping("/filtered")
   public String getFilteredCars(@ModelAttribute MasinaFiltres filters, Model model)
   {
       List<Masina> masini = masinaService.findCarsByFilters(filters);
       model.addAttribute("masini", masini);
       return "view";
   }

    @GetMapping("/userfiltered")
    public String getUserFilteredCars(@ModelAttribute MasinaFiltres filters, Model model)
    {
        List<Masina> masini = masinaService.findCarsByFilters(filters);
        model.addAttribute("masini", masini);
        return "user";
    }

}
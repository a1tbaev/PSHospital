package com.example.pshospital.controller;

import com.example.pshospital.models.Hospital;
import com.example.pshospital.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hospitals")
public class HospitalController {
    private final HospitalService hospitalService;

    @Autowired
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping
    public String getAllHospitals(Model model) {
        model.addAttribute("hospitals", hospitalService.hospitals());
        return "hospital/mainPage";
    }

    @GetMapping("/new")
    public String newHospital(Model model) {
        model.addAttribute("newHospital", new Hospital());
        return "hospital/newHospital";
    }

    @PostMapping("/save")
    public String saveHospital(@ModelAttribute("newHospital") Hospital hospital) {
        hospitalService.save(hospital);
        return "redirect:/hospitals";
    }

    @DeleteMapping({"{hospitalId}/delete"})
    public String deleteHospital(@PathVariable("hospitalId") Long id) {
        hospitalService.deleteHospitalByID(id);
        return "redirect:/hospitals";
    }

    @GetMapping("{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("updateHospital", hospitalService.getHospitalById(id));
        return "hospital/edit";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @ModelAttribute("updateHospital") Hospital hospital) {
        hospitalService.updateHospitalById(id, hospital);
        return "redirect:/hospitals";
    }
}

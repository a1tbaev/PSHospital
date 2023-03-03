package com.example.pshospital.controller;

import com.example.pshospital.models.Doctor;
import com.example.pshospital.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @GetMapping("{departmentId}")
    public String getAllDoctors(Model model, @PathVariable("departmentId") Long id){
        model.addAttribute("doctors", doctorService.getAllDoctors(id));
        return "doctor/doctorsMainPage";
    }

    @GetMapping("/new/{departmentId}")
    public String newDoctor(Model model, @PathVariable("departmentId") Long id){
        model.addAttribute("newDoctor", new Doctor());
        model.addAttribute("departmentId", id);
        return "doctor/newDoctor";
    }

    @PostMapping("/save/{departmentId}")
    public String saveDoctor(@PathVariable("departmentId") Long id, @ModelAttribute("newDoctor") Doctor doctor){
        doctorService.saveDoctor(doctor, id);
        return "redirect:/doctors/"+ id;
    }
    @DeleteMapping({"/{departmentId}/deleteDoctor/{doctorId}"})
    public String deleteHospital(@PathVariable("doctorId")Long id, @PathVariable("departmentId") Long departmentId){
        doctorService.deleteDoctor(id);
        return "redirect:/doctors/"+departmentId;
    }

    @GetMapping("/{departmentId}/edit/{id}")
    public String edit(@PathVariable("id") Long id, @PathVariable("departmentId")Long Id,Model model){
        model.addAttribute("newDoctor", doctorService.getDoctorById(id));
        model.addAttribute("departmentId",Id);
        return "doctor/editDoctor";
    }

    @PatchMapping("/{departmentId}/update/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("newDoctor")Doctor doctor, @PathVariable("departmentId") Long Id){
        doctorService.updateDoctor(doctor, id);
        return "redirect:/doctors/"+Id;
    }

}

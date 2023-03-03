package com.example.pshospital.controller;

import com.example.pshospital.models.Patient;
import com.example.pshospital.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @GetMapping("{appointmentId}")
    public String getAllPatients(@PathVariable Long appointmentId, Model model){
        model.addAttribute("patients", patientService.getAllPatients(appointmentId));
        return "patientMainPage";
    }
    @GetMapping("/new/{appointmentId}")
    public String newPatient(Model model, @PathVariable Long appointmentId) {
        model.addAttribute("newPatient", new Patient());
        model.addAttribute("appointmentId", appointmentId);
        return "newPatient";
    }

    @PostMapping("/save/{appointmentId}")
    public String savePatient(@ModelAttribute("newPatient") Patient patient, @PathVariable Long appointmentId) {
        patientService.savePatient(appointmentId, patient);
        return "redirect:/patients/"+appointmentId;
    }

    @DeleteMapping("{appointmentId}/delete/{patientId}")
    public String deletePatient(@PathVariable Long patientId, @PathVariable Long appointmentId){
        patientService.deletePatient(patientId, appointmentId);
        return "redirect:/patients/"+appointmentId;
    }
}

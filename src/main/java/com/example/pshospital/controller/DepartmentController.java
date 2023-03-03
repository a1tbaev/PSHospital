package com.example.pshospital.controller;

import com.example.pshospital.models.Department;
import com.example.pshospital.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping("{hospitalId}")
    public String getAllDepartments(Model model, @PathVariable("hospitalId") Long id){
        model.addAttribute("departments", departmentService.getAll(id));
        return "department/departmentMainPage";
    }

    @GetMapping("/new/{hospitalId}")
    public String newDepartment(Model model, @PathVariable("hospitalId") Long id){
        model.addAttribute("newDepartment", new Department());
        model.addAttribute("hospitalId", id);
        return "department/newDepartment";
    }

    @PostMapping("/save/{hospitalId}")
    public String saveHospital(@PathVariable("hospitalId") Long id, @ModelAttribute("newDepartment") Department department){
        departmentService.save(department, id);
        return "redirect:/departments/"+ id;
    }

    @DeleteMapping({"/{hospitalId}/deleteDepartment/{departmentId}"})
    public String deleteHospital(@PathVariable("departmentId")Long id, @PathVariable("hospitalId") Long hospitalId){
        departmentService.deleteById(id);
        return "redirect:/departments/"+hospitalId;
    }

    @GetMapping("/{hospitalId}/edit/{id}")
    public String edit(@PathVariable("id") Long id, @PathVariable("hospitalId")Long hospitalId,Model model){
        model.addAttribute("newDepartment", departmentService.getById(id));
        model.addAttribute("hospitalId",hospitalId);
        return "department/editDepartment";
    }

    @PatchMapping("/{hospitalId}/update/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("newDepartment") Department department, @PathVariable("hospitalId") Long hospitalId){
        departmentService.update(id, department);
        return "redirect:/departments/"+hospitalId;
    }

}

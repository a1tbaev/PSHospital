package com.example.pshospital.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "departments")
public class Department {
    @Id
    @SequenceGenerator(name = "department_generator", sequenceName = "department_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_generator")
    private Long id;
    private String name;
    @ManyToMany(cascade = {REFRESH, DETACH, MERGE, PERSIST}, mappedBy = "departments")
    private List<Doctor> doctors;
    public void addDoctor(Doctor doctor){
        if (doctors == null){
            doctors = new ArrayList<>();
        }
        doctors.add(doctor);
    }

    @ManyToOne()
    private Hospital hospital;
}

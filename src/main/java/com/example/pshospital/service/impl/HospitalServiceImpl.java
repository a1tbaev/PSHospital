package com.example.pshospital.service.impl;
import com.example.pshospital.models.Hospital;
import com.example.pshospital.repository.HospitalRepository;
import com.example.pshospital.service.HospitalService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HospitalServiceImpl implements HospitalService {
    private final HospitalRepository hospitalRepository;

    @Autowired
    public HospitalServiceImpl(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public void save(Hospital hospital) {
        hospitalRepository.save(hospital);
    }

    @Override
    public List<Hospital> hospitals() {
        return hospitalRepository.findAll();
    }

    @Override
    public Hospital getHospitalById(Long id) {
        return hospitalRepository.findById(id).get();
    }

    @Override
    public void deleteHospitalByID(Long id) {
        hospitalRepository.deleteById(id);
    }

    @Override
    public void updateHospitalById(Long id, Hospital hospital) {
        Hospital hospital1 = hospitalRepository.findById(id).get();
        hospital1.setName(hospital.getName());
        hospital1.setAddress(hospital.getAddress());
        hospital1.setImage(hospital.getImage());
    }
}

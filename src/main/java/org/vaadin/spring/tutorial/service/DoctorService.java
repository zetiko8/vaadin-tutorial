package org.vaadin.spring.tutorial.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.spring.tutorial.db.doctor.Doctor;
import org.vaadin.spring.tutorial.db.doctor.DoctorRepository;

@Service
public class DoctorService {
		
	@Autowired
	private DoctorRepository doctorRepository;
	
	public List<Doctor> getAll () {
		return doctorRepository.findAll();
	}
	
	public Doctor save (Doctor doctor) {
		return doctorRepository.save(doctor);
	}

	public Doctor getById (Long id) {
		return doctorRepository.findOne(id);
	}
}

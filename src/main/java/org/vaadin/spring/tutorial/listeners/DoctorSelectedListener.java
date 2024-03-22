package org.vaadin.spring.tutorial.listeners;

import org.vaadin.spring.tutorial.db.doctor.Doctor;

public interface DoctorSelectedListener {
	public void onDoctorSelected(Doctor doctor);
}

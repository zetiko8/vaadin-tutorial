package org.vaadin.spring.tutorial.components;

import org.vaadin.spring.tutorial.db.doctor.Doctor;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class DoctorDetailsComponent extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	public DoctorDetailsComponent() {
    	setSizeFull();
	}
	
	public void update(Doctor doctor) {
    	Label nameLabel = new Label(doctor.getName());
    	nameLabel.setIcon(FontAwesome.USER);
    	Label phoneNumberLabel = new Label(doctor.getPhoneNumber());
    	phoneNumberLabel.setIcon(FontAwesome.PHONE);
    	this.removeAllComponents();
    	this.addComponent(nameLabel);
    	this.addComponent(phoneNumberLabel);
	}
}

package org.vaadin.spring.tutorial.components;

import org.vaadin.spring.tutorial.db.doctor.Doctor;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class DoctorDetailsComponent extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	private TutorialTextField nameTextField
	= new TutorialTextField("Name", FontAwesome.USER);
	private TutorialTextField phoneTextField
	= new TutorialTextField("Phone number", FontAwesome.USER);

	public DoctorDetailsComponent() {
    	setSizeFull();
    	nameTextField.setReadonly(true);
    	phoneTextField.setReadonly(true);
	}
	
	public void update(Doctor doctor) {
		removeAllComponents();
    	nameTextField.setValue(doctor.getName());
    	phoneTextField.setValue(doctor.getPhoneNumber());
    	addComponent(nameTextField.getComponent());
    	addComponent(phoneTextField.getComponent());
	}
}

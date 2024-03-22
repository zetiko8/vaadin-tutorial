package org.vaadin.spring.tutorial.views;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.tutorial.components.TutorialTextField;
import org.vaadin.spring.tutorial.db.doctor.Doctor;
import org.vaadin.spring.tutorial.service.DoctorService;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = CreateDoctorView.VIEW_NAME)
public class CreateDoctorView extends VerticalLayout implements View {
	
	private static final long serialVersionUID = 1L;

	public static final String VIEW_NAME = "create";
	
	@Autowired
	private DoctorService doctorService;
	
	private TutorialTextField nameTextField
		= new TutorialTextField("Name", FontAwesome.USER);
	private TutorialTextField phoneTextField
	= new TutorialTextField("Phone number", FontAwesome.USER);
	Button submitButton = new Button("Submit");

    @PostConstruct
    void init() {
        addComponent(new Label("Create a new doctor"));
        FormLayout form = new FormLayout();        
        form.addComponent(nameTextField.getComponent());
        form.addComponent(phoneTextField.getComponent());
        Button submitButton = new Button("Submit");
        submitButton.addClickListener(
				event -> { onSubmit(); });
        form.addComponent(submitButton);
        
        addComponent(form);
    }

    @Override
    public void enter(ViewChangeEvent event) {

    }
    
    private void onSubmit () {
    	boolean nameTextFieldValid 
    		= isValidRequiredValue(
    			nameTextField.getValue());
    	boolean phoneNumberTextFieldValid 
    	= isValidRequiredValue(
    			phoneTextField.getValue());
    	if (!nameTextFieldValid) {
    		nameTextField
    			.displayError("* Field is required");
    	}
    	if (!phoneNumberTextFieldValid) {
    		phoneTextField
    			.displayError("* Field is required");
    	}
    	
    	if (!(nameTextFieldValid && phoneNumberTextFieldValid)) {
    		return;
    	}
    	
    	Doctor doctor = Doctor.builder()
    			.name(
    					nameTextField.getValue().trim())
    			.phoneNumber(
    					phoneTextField.getValue().trim())
    			.build();
    	doctorService.save(doctor);
    	getUI()
    		.getNavigator()
    		.navigateTo(DoctorsListView.VIEW_NAME);
    }
    
    private boolean isValidRequiredValue (String value) {
    	if (value == null) return false;
    	if (value.equals("")) return false;
    	return true;
    }
}
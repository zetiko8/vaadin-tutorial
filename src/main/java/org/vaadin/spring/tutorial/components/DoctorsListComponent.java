package org.vaadin.spring.tutorial.components;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.spring.tutorial.db.doctor.Doctor;
import org.vaadin.spring.tutorial.listeners.DoctorSelectedListener;

import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class DoctorsListComponent extends Panel {

	private static final long serialVersionUID = 1L;

	VerticalLayout layout = new VerticalLayout ();
	List<DoctorSelectedListener> listeners 
		= new ArrayList<DoctorSelectedListener>();
	
	public DoctorsListComponent() {
    	setSizeFull();
    	setContent(layout);
	}
	
	public void update(List<Doctor> doctors) {
    	layout.removeAllComponents();
        for (Doctor doctor : doctors)
    		displayDoctorInList(doctor);
        
        if (doctors.size() == 0)
        	layout.addComponent(
        			new Label("No doctors jet."));
	}
	
    private void displayDoctorInList (Doctor doctor) {
    	GridLayout grid = new GridLayout(4, 1);
		Button button = new Button(doctor.getName());
		button.addStyleName(ValoTheme.BUTTON_SMALL);
		button
			.addClickListener(
			event -> {
				for (
					DoctorSelectedListener listener 
					: listeners) {
					listener.onDoctorSelected(doctor);
				}
			});
		button.setWidth("150px");
		grid.addComponent(button);
		layout.addComponent(grid);
    }

	public void addOnDocotorSelectedListener(
			DoctorSelectedListener listener) {
		listeners.add(listener);
	}
}

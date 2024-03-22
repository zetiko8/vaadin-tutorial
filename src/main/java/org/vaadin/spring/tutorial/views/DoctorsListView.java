package org.vaadin.spring.tutorial.views;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.tutorial.db.doctor.Doctor;
import org.vaadin.spring.tutorial.service.DoctorService;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@UIScope
@SpringView(name = DoctorsListView.VIEW_NAME)
public class DoctorsListView extends VerticalLayout implements View {
	
	private static final long serialVersionUID = 1L;

	public static final String VIEW_NAME = "";
	
	@Autowired
	private DoctorService doctorService;
	
	VerticalLayout doctorsLayout = new VerticalLayout ();
	VerticalLayout selectedDoctorLayout = new VerticalLayout ();
	
    @PostConstruct
    void init() {
    	GridLayout mainGrid = new GridLayout(2, 1);
    	mainGrid.setWidth("100%");
    	mainGrid.setHeight("100%");
    	addComponent(mainGrid);
    	
    	Panel doctorsPanel = new Panel();
    	doctorsPanel.setSizeFull();
    	doctorsPanel.setContent(doctorsLayout);
    	mainGrid.addComponent(doctorsPanel);

    	Panel selectedDoctorPanel = new Panel();
    	selectedDoctorPanel.setSizeFull();
    	VerticalLayout selectedDoctorPanelLayout = new VerticalLayout();
    	mainGrid.addComponent(selectedDoctorPanel);
    	selectedDoctorPanel.setContent(selectedDoctorPanelLayout);
		Button newDoctorButton = new Button("New Doctor");
		newDoctorButton.addStyleName(ValoTheme.BUTTON_SMALL);
		newDoctorButton.addClickListener(
                event -> getUI().getNavigator()
                .navigateTo(CreateDoctorView.VIEW_NAME));
		selectedDoctorPanelLayout.addComponent(newDoctorButton);
		selectedDoctorPanelLayout.addComponent(selectedDoctorLayout);

    }

    @Override
    public void enter(ViewChangeEvent event) {
    	doctorsLayout.removeAllComponents();
    	List<Doctor> doctors = doctorService.getAll();
        for (Doctor doctor : doctors)
    		displayDoctorInList(doctor);
        
        if (doctors.size() == 0)
        	doctorsLayout.addComponent(new Label("No doctors jet."));
    }
    
    private void displayDoctorInList (Doctor doctor) {
    	GridLayout grid = new GridLayout(4, 1);
		Button button = new Button(doctor.getName());
		button.addStyleName(ValoTheme.BUTTON_SMALL);
		button.addClickListener(
				event -> { displaySelectedDoctor(doctor); });
		button.setWidth("150px");
		grid.addComponent(button);
		doctorsLayout.addComponent(grid);
    }
    
    private void displaySelectedDoctor (Doctor doctor) {
    	Label nameLabel = new Label(doctor.getName());
    	nameLabel.setIcon(FontAwesome.USER);
    	Label phoneNumberLabel = new Label(doctor.getPhoneNumber());
    	phoneNumberLabel.setIcon(FontAwesome.PHONE);
    	selectedDoctorLayout.removeAllComponents();
    	selectedDoctorLayout.addComponent(nameLabel);
    	selectedDoctorLayout.addComponent(phoneNumberLabel);
    }
}
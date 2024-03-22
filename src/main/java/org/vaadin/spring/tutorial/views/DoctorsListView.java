package org.vaadin.spring.tutorial.views;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.tutorial.components.DoctorDetailsComponent;
import org.vaadin.spring.tutorial.components.DoctorsListComponent;
import org.vaadin.spring.tutorial.db.doctor.Doctor;
import org.vaadin.spring.tutorial.listeners.DoctorSelectedListener;
import org.vaadin.spring.tutorial.service.DoctorService;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@UIScope
@SpringView(name = DoctorsListView.VIEW_NAME)
public class DoctorsListView 
	extends VerticalLayout 
	implements View, DoctorSelectedListener {
	
	private static final long serialVersionUID = 1L;

	public static final String VIEW_NAME = "";
	
	@Autowired
	private DoctorService doctorService;
	
	VerticalLayout doctorsLayout = new VerticalLayout ();
	DoctorsListComponent doctorsList
		= new DoctorsListComponent();
	DoctorDetailsComponent doctorDetails
		= new DoctorDetailsComponent();
	
    @PostConstruct
    void init() {
    	GridLayout mainGrid = new GridLayout(2, 1);
    	mainGrid.setSizeFull();
    	addComponent(mainGrid);
    	
    	// left side
    	doctorsList.addOnDocotorSelectedListener(this);
    	mainGrid.addComponent(doctorsList);
    	
    	// right side
    	Panel selectedDoctorPanel = new Panel();
    	selectedDoctorPanel.setSizeFull();
    	VerticalLayout selectedDoctorPanelLayout 
    		= new VerticalLayout();
    	mainGrid.addComponent(selectedDoctorPanel);
    	selectedDoctorPanel.setContent(selectedDoctorPanelLayout);
		Button newDoctorButton = new Button("New Doctor");
		newDoctorButton.addStyleName(ValoTheme.BUTTON_SMALL);
		newDoctorButton.addClickListener(
                event -> getUI().getNavigator()
                .navigateTo(CreateDoctorView.VIEW_NAME));
		selectedDoctorPanelLayout.addComponent(newDoctorButton);
		selectedDoctorPanelLayout.addComponent(doctorDetails);

    }

    @Override
    public void enter(ViewChangeEvent event) {        
        doctorsList.update(doctorService.getAll());
    }

	@Override
	public void onDoctorSelected(Doctor doctor) {
		doctorDetails.update(doctor);
	}
}
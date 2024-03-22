package org.vaadin.spring.tutorial.components;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

public class TutorialTextField {
	
	private GridLayout textFieldGrid = new GridLayout(1, 2);
	private TextField textField;
	private Label errorLabel = new Label();
	
	public TutorialTextField(
		String label,
		FontAwesome icon
	) {
        textField = new TextField(label);
        textField.setIcon(icon);
        textFieldGrid.addComponent(textField);
        textFieldGrid.addComponent(errorLabel);
	}
	
	public Component getComponent () {
		return textFieldGrid;
	}
	
	public void displayError (String message) {
		errorLabel.setCaption(message);
	}
	
	public String getValue () {
		return textField.getValue();
	}
	
	public void setReadonly (boolean readonly) {
		this.textField.setReadOnly(readonly);
	}

	public void setValue(String value) {
		this.textField.setValue(value);
	}
}

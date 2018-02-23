package com.vaadin.testbenchexample;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasClickListeners.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.testbenchexample.logic.CalculatorLogic;

/**
 * The Keypad object provides a calculator display and keypad, and makes up the
 * main part of the UI of our application.
 */
public class Keypad extends VerticalLayout {

    private TextField display;
    private CalculatorLogic logic;

    ComponentEventListener<ClickEvent<Button>> buttonClickHandler = event -> {
        // Get a reference to the button that received the click
        Button button = event.getSource();

        // Get the requested operation from the button text
        char requestedOperation = button.getText().charAt(0);

        // Calculate the new value
        double newValue = logic.performOperation(requestedOperation);
        // Update the result label with the new value
        display.setValue("" + newValue);
    };

    public Keypad(Log log) {
        setId("keypad");
        logic = new CalculatorLogic(log::log, log::clear);

        setSpacing(true);

        display = new TextField();
        display.setReadOnly(true);
        display.setWidth("100%");
        display.setId("display");
        display.setValue("0.0");
        setFlexGrow(1, display);

        add(display);
        add(createButtonRow("7", "8", "9", "/"));
        add(createButtonRow("4", "5", "6", "*"));
        add(createButtonRow("1", "2", "3", "-"));
        add(createButtonRow("0", "=", "C", "+"));

    }

    private Component createButtonRow(String... texts) {
        HorizontalLayout hl = new HorizontalLayout();
        hl.setSpacing(true);
        for (String caption : texts) {
            // Create a button and add it to the layout
            Button button = new Button(caption);
            button.setId("button_" + caption);
            button.setWidth("40px");
            hl.add(button);

            // Re-use the same click handler for all buttons
            button.addClickListener(buttonClickHandler);
        }
        return hl;
    }

}

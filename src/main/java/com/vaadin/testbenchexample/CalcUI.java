package com.vaadin.testbenchexample;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("valo")
@Title("Calculator example")
@SuppressWarnings("serial")
public class CalcUI extends UI {

    @Override
    protected void init(VaadinRequest request) {

        // Define a panel that will hold our app
        Panel applicationPanel = new Panel();
        applicationPanel.setCaption("Calculator");
        applicationPanel.setStyleName("calculator-app");
        applicationPanel.setSizeUndefined();

        // Use Vaadin's style handling feature to add a margin around our app
        getPage().getStyles().add(".calculator-app { margin: 25px; }");

        // Instantiate a keypad, which contains our main logic
        Keypad keypad = new Keypad();

        // The Log object displays a list of performed operations, enhancing
        // usability
        Log log = new Log();

        // Connect our Log to the keypad
        keypad.addLogger(log);

        // Use a horizontal layout to place the keypad and log side-by-side
        HorizontalLayout hlayout = new HorizontalLayout();
        hlayout.setSpacing(true);
        hlayout.setMargin(true);
        hlayout.addComponent(keypad);
        hlayout.addComponent(log);
        hlayout.setSizeUndefined();

        // Add the horizontal layout to our panel to define the application.
        applicationPanel.setContent(hlayout);

        // Add the app panel to a container in order to center it.
        VerticalLayout container = new VerticalLayout();
        container.setSizeFull();
        container.addComponent(applicationPanel);
        container.setComponentAlignment(applicationPanel,
                Alignment.MIDDLE_CENTER);
        // Set the container containing the app panel to be our UI content
        // to display our app.
        setContent(container);
    }

}

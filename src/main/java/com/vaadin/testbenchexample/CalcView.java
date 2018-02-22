package com.vaadin.testbenchexample;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Theme(Lumo.class)
@Route("")
@PageTitle("Calculator example")
public class CalcView extends HorizontalLayout {

    public CalcView() {
        setSpacing(true);
        setHeight("300px");
        Log log = new Log();
        log.setId("log");
        Keypad keypad = new Keypad(log);
        add(keypad, log);
    }

}

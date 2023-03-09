package com.vaadin.testbenchexample;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("")
@PageTitle("Calculator example")
public class CalcView extends HorizontalLayout {

    public CalcView() {
        setId("calc-view");
        setSpacing(true);
        setHeight("300px");
        Log log = new Log();
        log.setId("log");
        Keypad keypad = new Keypad(log);
        add(keypad, log);
    }

}

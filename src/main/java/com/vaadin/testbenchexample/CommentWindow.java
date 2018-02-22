package com.vaadin.testbenchexample;

import java.util.function.Consumer;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class CommentWindow extends Dialog {

    private TextField commentField;
    private Button okButton;

    public CommentWindow(Consumer<String> onOk) {
        commentField = new TextField();
        commentField.setWidth("100%");
        commentField.setId("comment");

        okButton = new Button("OK");
        okButton.setId("ok");
        okButton.setWidth("100%");
        okButton.getElement().getThemeList().add("primary");
        okButton.addClickListener(e -> {
            onOk.accept(commentField.getValue());
            close();
        });

        final Button cancelButton = new Button("Cancel");
        cancelButton.setId("cancel");
        cancelButton.setWidth("100%");
        cancelButton.addClickListener(e -> {
            close();
        });

        // Create a horizontal layout to hold the OK and Cancel buttons
        // side by side
        HorizontalLayout buttonContainer = new HorizontalLayout();
        buttonContainer.setSpacing(true);
        buttonContainer.add(okButton);
        buttonContainer.add(cancelButton);
        buttonContainer.setWidth("100%");

        // Create a vertical layout to hold the input field above the OK and
        // Cancel buttons
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.add(commentField);
        layout.add(buttonContainer);

        // Make the vertical layout the content of our window
        add(layout);
    }

    @Override
    public void open() {
        super.open();
        commentField.clear();
    }

}

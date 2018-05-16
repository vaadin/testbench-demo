package com.vaadin.testbenchexample;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

/**
 * Instances of this class are used to store activity information from
 * {@link Keypad} and displayed in a {@link Log} in the application.
 */
public class Log extends VerticalLayout {

    private VerticalLayout messages;
    private Button addCommentButton;
    private CommentWindow commentWindow;

    public Log() {
        setWidth("400px");
        setHeight("100%");

        messages = new VerticalLayout();
        messages.setId("messages");
        messages.setPadding(false);
        messages.setSpacing(false);
        messages.setSizeFull();
        // messages.getElement().getStyle().set("max-height", "250px");
        messages.getElement().getStyle().set("overflow", "auto");
        commentWindow = new CommentWindow(text -> {
            log("[ " + text + " ]");
        });
        setFlexGrow(1, messages);
        addCommentButton = new Button("Add Comment");
        addCommentButton.setId("add-comment");
        addCommentButton.setWidth("100%");

        addCommentButton.addClickListener(e -> {
            commentWindow.open();
        });

        add(messages, addCommentButton);

        setSpacing(true);
        setPadding(false);
    }

    public void log(String row) {
        messages.add(new Paragraph(row));
        getUI().get().getPage().executeJavaScript("$0.scrollTop=129341",
                messages);
    }

    public void clear() {
        messages.removeAll();
    }

}

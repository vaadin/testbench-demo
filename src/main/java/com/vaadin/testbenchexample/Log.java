package com.vaadin.testbenchexample;

import java.util.ArrayList;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Instances of this class are used to store activity information from
 * {@link Keypad} and displayed in a {@link Grid} in the application. This
 * class implements {@link CalculatorLogger}.
 */
@SuppressWarnings("serial")
public class Log extends VerticalLayout implements CalculatorLogger {

    private final ArrayList<String> gridItems = new ArrayList<>();
    ListDataProvider<String> dataProvider = new ListDataProvider<>(gridItems);
    /**
     * Nested class CommentWindow is used to get user comments to be displayed
     * among the actual activity log.
     */
    private class CommentWindow extends Window {

        private TextField commentField;

        public CommentWindow() {
            super("Add comment");
            setResizable(false);

            // Create a new textfield, where the user can enter their comment
            commentField = new TextField();
            commentField.setSizeFull();

            // Create a button that writes a new line to the Log
            final Button okButton = new Button("OK");

            // Add a tooltip to button
            okButton.setDescription("Clicking this button will add a comment row to log.");
            okButton.setWidth("100%");
            okButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
            okButton.addClickListener(e ->  {
                    log("[ " + commentField.getValue() + " ]");
                    close();
            });

            // Create a button that closes the window, discarding the contents
            // of the comment field
            final Button cancelButton = new Button("Cancel");
            cancelButton.setWidth("100%");
            cancelButton.addClickListener(new ClickListener() {
                @Override
                public void buttonClick(ClickEvent event) {
                    close();
                }
            });

            // Simulate a press of the OK button when Enter is pressed in the
            // comment field
            commentField.addShortcutListener(new ShortcutListener("OK",
                    KeyCode.ENTER, null) {
                @Override
                public void handleAction(Object sender, Object target) {
                    okButton.click();
                }

            });

            // Simulate a press of the Cancel button when ESC is pressed in the
            // comment field
            commentField.addShortcutListener(new ShortcutListener("Cancel",
                    KeyCode.ESCAPE, null) {
                @Override
                public void handleAction(Object sender, Object target) {
                    cancelButton.click();
                }

            });

            // Create a horizontal layout to hold the OK and Cancel buttons
            // side by side
            HorizontalLayout buttonContainer = new HorizontalLayout();
            buttonContainer.setSpacing(true);
            buttonContainer.addComponent(okButton);
            buttonContainer.addComponent(cancelButton);
            buttonContainer.setWidth("100%");

            // Create a vertical layout to hold the input field above the OK and
            // Cancel buttons
            VerticalLayout layout = new VerticalLayout();
            layout.setMargin(true);
            layout.setSpacing(true);
            layout.addComponent(commentField);
            layout.addComponent(buttonContainer);

            // Make the vertical layout the content of our window
            setContent(layout);

            // Mark the window as not draggable for a better user experience
            setDraggable(false);
        }

        /**
         * By overriding the close function of the Window, we can make sure that
         * the comment field is always reset whenever and however the window is
         * closed
         */
        @Override
        public void close() {
            commentField.setValue("");
            super.close();
        }

        /**
         * When the window is focused, we want the comment field to have input
         * focus
         */
        @Override
        public void focus() {
            commentField.focus();
        }

    }

    private Grid<String> grid;
    private Button addCommentButton;
    private CommentWindow commentWindow;
    private int line = 0;

    public Log() {
        grid = new Grid<>();
        grid.addColumn(String::toString).setCaption("Name");
        grid.setSizeFull();

        setWidth("400px");
        setHeight("100%");
        grid.removeHeaderRow(grid.getHeaderRow(0));
        grid.setDataProvider(dataProvider);
        addComponent(grid);

        commentWindow = new CommentWindow();

        addCommentButton = new Button("Add Comment");
        addCommentButton.setWidth("100%");

        addCommentButton.addClickListener(e -> {
            UI.getCurrent().addWindow(commentWindow);
            commentWindow.setModal(true);
            commentWindow.focus();
        });

        addComponent(addCommentButton);

        setExpandRatio(grid, 1);
        setSpacing(true);
    }

    /**
     * This function implements the log(String) method mandated by the
     * CalculatorLogger interface.
     */
    @Override
    public void log(String row) {

        gridItems.add(row);
        dataProvider.refreshAll();
        grid.scrollTo(line);
        line++;
    }

    /**
     * Clears all items from the log table
     */
    @Override
    public void clear() {
        gridItems.clear();
        dataProvider.refreshAll();
        line = 0;
    }

}

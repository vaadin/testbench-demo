package com.vaadin.testbenchexample.pageobjectexample.pageobjects;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.dialog.testbench.DialogElement;
import com.vaadin.flow.component.orderedlayout.testbench.VerticalLayoutElement;
import com.vaadin.flow.component.textfield.testbench.TextFieldElement;

public class CommentWindowElement extends DialogElement {

    /**
     * Enters a comment in the comment text field. Does not submit the comment.
     *
     * @param comment
     *            The text to enter in the comment field
     */
    public void enterComment(String comment) {
        getCommentInput().setValue(comment);
    }

    private TextFieldElement getCommentInput() {
        return $(VerticalLayoutElement.class).first().$(TextFieldElement.class).id("comment");
    }

    /**
     * Clicks the 'OK' button to submit the comment entered in the text field.
     */
    public void ok() {
        $(ButtonElement.class).id("ok").click();
    }

    /**
     * Clicks the 'Cancel' button to abort submitting the comment entered in the
     * text field.
     */
    public void cancel() {
        $(VerticalLayoutElement.class).first().$(ButtonElement.class).id("cancel").click();
    }
}

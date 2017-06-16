package org.vaadin.simplemde.demo;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import org.vaadin.simplemde.SimpleMarkdownEditor;
import org.vaadin.viritin.MSize;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

@SpringUI
@Theme("valo")
public class DemoUI extends UI {

    @Override
    protected void init(final VaadinRequest vaadinRequest) {
        SimpleMarkdownEditor editor = new SimpleMarkdownEditor();
        editor.setValue("# Hello World \nwe can write markdown!");
        setContent(new MVerticalLayout()
                .add(editor, 1)
                .add(new MHorizontalLayout()
                        .add(new MButton("clear", e -> editor.setValue("")))
                        .add(new MButton("getValue", e -> Notification.show(editor.getValue()))))
                .withSize(MSize.FULL_SIZE));
    }
}

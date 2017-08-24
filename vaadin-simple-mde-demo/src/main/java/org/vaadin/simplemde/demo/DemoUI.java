package org.vaadin.simplemde.demo;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import org.vaadin.simplemde.SimpleMarkdownEditor;
import org.vaadin.simplemde.SimpleMarkdownToolbarIcon;
import org.vaadin.viritin.MSize;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.Arrays;

@SpringUI
@Theme("valo")
public class DemoUI extends UI {

    @Override
    protected void init(final VaadinRequest vaadinRequest) {

        SimpleMarkdownEditor editor = new SimpleMarkdownEditor();
        editor.setHideIcons(Arrays.asList(SimpleMarkdownToolbarIcon.GUIDE, SimpleMarkdownToolbarIcon.FULLSCREEN, SimpleMarkdownToolbarIcon.SIDE_BY_SIDE));
        editor.setValue("# Hello World \nwe can write markdown!");
        editor.setShowStatus(false);
        editor.addValueChangeListener(e -> Notification.show(e.getValue(), Notification.Type.TRAY_NOTIFICATION));
        setContent(new MVerticalLayout()
                .add(editor, 1)
                .add(new MHorizontalLayout()
                        .add(new MButton("clear", e -> editor.setValue("")))
                        .add(new MButton("getValue", e -> Notification.show(editor.getValue()))))
                .withSize(MSize.FULL_SIZE));
    }
}

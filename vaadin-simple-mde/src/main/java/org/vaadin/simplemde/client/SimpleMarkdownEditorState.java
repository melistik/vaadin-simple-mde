package org.vaadin.simplemde.client;

import com.vaadin.shared.ui.JavaScriptComponentState;
import org.vaadin.simplemde.SimpleMarkdownToolbarIcon;

import java.util.ArrayList;
import java.util.List;

/**
 * Transfer states to JavaScript connector
 *
 * @author Marten Prieß (http://www.rocketbase.io)
 * @version 1.0
 */
public class SimpleMarkdownEditorState extends JavaScriptComponentState {

    public int changeTimeOut = 250;

    public boolean toolbar = true;

    public boolean showStatus = true;

    public boolean spellChecker = false;

    public boolean lineWrapping = true;

    public Boolean preview = null;

    public String markdownText = "";

    public List<SimpleMarkdownToolbarIcon> hideIcons = new ArrayList<>();

}

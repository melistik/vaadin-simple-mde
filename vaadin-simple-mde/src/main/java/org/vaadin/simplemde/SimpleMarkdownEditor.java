package org.vaadin.simplemde;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.ui.AbstractJavaScriptComponent;
import org.vaadin.simplemde.client.SimpleMarkdownEditorServerRpc;
import org.vaadin.simplemde.client.SimpleMarkdownEditorState;

import java.util.List;

/**
 * Vaadin wrapper for the JavaScript Plugin SimpleMDE (https://github.com/sparksuite/simplemde-markdown-editor)<br>
 *
 * @author Marten Prieß (http://www.rocketbase.io)
 * @version 1.0
 */
@StyleSheet("vaadin://simplemde/simplemde.min.css")
@JavaScript({"vaadin://simplemde/simplemde.min.js", "SimpleMarkdownEditor.js"})
public class SimpleMarkdownEditor extends AbstractJavaScriptComponent {


    public SimpleMarkdownEditor() {
        /**
         * register the ServerRpc to the server-implementation in order to listen to it's calls
         */
        registerRpc(new SimpleMarkdownEditorServerRpc() {

            @Override
            public void textChanged(String text) {
                getState().markdownText = text;
            }
        });
        setPrimaryStyleName("simplemde-editor");
    }

    public String getValue() {
        return getState().markdownText;
    }

    public void setValue(String text) {
        if (isAttached()) {
            callFunction("setMarkdownText", text);
            getState().markdownText = text;
        } else {
            getState().markdownText = text;
        }
    }

    public void setHideIcons(List<SimpleMarkdownToolbarIcon> icons) {
        getState().hideIcons = icons;
    }

    /**
     * hide the status bar
     *
     * @param showStatus default true
     */
    public void setShowStatus(boolean showStatus) {
        getState().showStatus = showStatus;
    }

    /**
     * delay of text value changes
     *
     * @param changeTimeOut default 250
     */
    public void setChangeTimeOut(int changeTimeOut) {
        getState().changeTimeOut = changeTimeOut;
    }

    @Override
    protected SimpleMarkdownEditorState getState() {
        return (SimpleMarkdownEditorState) super.getState();
    }


}

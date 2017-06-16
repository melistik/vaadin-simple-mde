package org.vaadin.simplemde;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.ui.AbstractJavaScriptComponent;
import org.vaadin.simplemde.client.SimpleMarkdownEditorServerRpc;
import org.vaadin.simplemde.client.SimpleMarkdownEditorState;

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

    @Override
    protected SimpleMarkdownEditorState getState() {
        return (SimpleMarkdownEditorState) super.getState();
    }


}

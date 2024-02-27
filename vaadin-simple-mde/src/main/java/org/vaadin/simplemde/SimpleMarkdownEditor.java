package org.vaadin.simplemde;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.Component;
import com.vaadin.util.ReflectTools;
import org.vaadin.simplemde.client.SimpleMarkdownEditorServerRpc;
import org.vaadin.simplemde.client.SimpleMarkdownEditorState;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Vaadin wrapper for the JavaScript Plugin SimpleMDE (https://github.com/sparksuite/simplemde-markdown-editor)<br>
 *
 * @author Marten Prieß (http://www.rocketbase.io)
 * @version 1.0
 */
@StyleSheet("vaadin://simplemde/simplemde-1.0.3.min.css")
@JavaScript({"vaadin://simplemde/simplemde.min.js", "SimpleMarkdownEditor.js"})
public class SimpleMarkdownEditor extends AbstractJavaScriptComponent {


    public SimpleMarkdownEditor() {
        /**
         * register the ServerRpc to the server-implementation in order to listen to it's calls
         */
        registerRpc((SimpleMarkdownEditorServerRpc) text -> {
            getState().markdownText = text;
            SimpleMarkdownEditor.this.fireEvent(new ValueChangedEvent(SimpleMarkdownEditor.this));
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
     * enable spell checker
     *
     * @param spellChecker default false
     */
    public void setSpellChecker(boolean spellChecker) {
        getState().spellChecker = spellChecker;
    }

    /**
     * disable line wrapping
     *
     * @param lineWrapping default true
     */
    public void setLineWrapping(boolean lineWrapping) {
        getState().lineWrapping = lineWrapping;
    }

    /**
     * used to hide toolbar at all<br>
     * in order to disable some use hideIcons
     */
    public void hideToolbar() {
        getState().toolbar = false;
    }

    public boolean isPreview() {
        return getState().preview != null ? getState().preview : false;
    }

    /**
     * toggle previewMode
     *
     * @param preview default undefined/null
     */
    public void setPreview(boolean preview) {
        getState().preview = preview;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        getState().preview = true;
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

    //FIXME: Modify this method if necessary and compile to newer version of Vaadin, as mentioned in issue #1
    public void addValueChangeListener(ValueChangeListener listener) {
        this.addListener(ValueChangedEvent.class, listener, ValueChangeListener.ELEMENT_VALUE_CHANGED_METHOD);
    }

    public interface ValueChangeListener extends Serializable {

        Method ELEMENT_VALUE_CHANGED_METHOD = ReflectTools
                .findMethod(SimpleMarkdownEditor.ValueChangeListener.class, "valueChanged",
                        ValueChangedEvent.class);

        void valueChanged(ValueChangedEvent event);
    }

    public static class ValueChangedEvent extends Component.Event {


        public ValueChangedEvent(SimpleMarkdownEditor source) {
            super(source);
        }

        @Override
        public SimpleMarkdownEditor getSource() {
            return (SimpleMarkdownEditor) super.getSource();
        }

        public String getValue() {
            return getSource().getValue();
        }
    }

}

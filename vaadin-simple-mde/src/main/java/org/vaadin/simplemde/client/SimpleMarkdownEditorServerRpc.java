package org.vaadin.simplemde.client;

import com.vaadin.shared.communication.ServerRpc;

/**
 * is been called from JavaScript connector
 *
 * @author Marten Prieß (http://www.rocketbase.io)
 * @version 1.0
 */
public interface SimpleMarkdownEditorServerRpc extends ServerRpc {

    void textChanged(String text);

}

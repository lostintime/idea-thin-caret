package com.lostintimedev.java.idea;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.event.EditorFactoryEvent;
import com.intellij.openapi.editor.event.EditorFactoryListener;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ThinCaretPlugin implements ApplicationComponent, Configurable {
    private static final int UNKNOWN_CURSOR_WIDTH = -1;
    private static final int DEFAULT_CURSOR_WIDTH = 2;
    private static final int THIN_CURSOR_WIDTH = 1;

    private ThinCaretConfiguration theConfiguration;
    private ThinCaretConfigurationPanel userInterface;
    private int originalCursorWidth;

    @Nls
    @Override
    public String getDisplayName() {
        return "ThinCaret";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return "preferences.lookFeel";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        if (userInterface == null) {
            userInterface = new ThinCaretConfigurationPanel();
            reset();
        }

        return userInterface;
    }

    @Override
    public boolean isModified() {
        boolean flag = false;
        if (userInterface != null && theConfiguration != null) {
            flag = userInterface.isEnabled() != theConfiguration.enabled;
        }
        return flag;
    }

    @Override
    public void apply() throws ConfigurationException {
        if (userInterface != null && theConfiguration != null) {
            theConfiguration.enabled = userInterface.isEnabled();
            applyNewCaretWidth();
        }
    }

    private void applyNewCaretWidth() {
        if (theConfiguration != null) {

            final int currentWidth = theConfiguration.enabled
                    ? THIN_CURSOR_WIDTH
                    : (originalCursorWidth != UNKNOWN_CURSOR_WIDTH ? originalCursorWidth : DEFAULT_CURSOR_WIDTH);
            for (Editor e : EditorFactory.getInstance().getAllEditors()) {
                e.getSettings().setLineCursorWidth(currentWidth);
            }
        }
    }

    @Override
    public void reset() {
        if (userInterface != null && theConfiguration != null) {
            userInterface.setEnabled(theConfiguration.enabled);
        }
    }

    @Override
    public void disposeUIResources() {
        userInterface = null;
    }

    @Override
    public void initComponent() {
        originalCursorWidth = UNKNOWN_CURSOR_WIDTH;

        EditorFactoryListener editorFactoryListener = new EditorFactoryListener() {
            @Override
            public void editorCreated(@NotNull EditorFactoryEvent editorFactoryEvent) {
                // save default cursor width
                if (originalCursorWidth == UNKNOWN_CURSOR_WIDTH) {
                    originalCursorWidth = editorFactoryEvent.getEditor().getSettings().getLineCursorWidth();
                }

                if (theConfiguration.enabled) {
                    editorFactoryEvent.getEditor().getSettings().setLineCursorWidth(THIN_CURSOR_WIDTH);
                }
            }

            @Override
            public void editorReleased(@NotNull EditorFactoryEvent editorFactoryEvent) {

            }
        };

        Application application = ApplicationManager.getApplication();
        theConfiguration = application.getComponent(ThinCaretConfiguration.class);

        EditorFactory.getInstance().addEditorFactoryListener(editorFactoryListener,
                new Disposable() {
                    @Override
                    public void dispose() {
                        // pass
                    }
                });
    }

    @Override
    public void disposeComponent() {
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "ThinCaretPlugin";
    }
}

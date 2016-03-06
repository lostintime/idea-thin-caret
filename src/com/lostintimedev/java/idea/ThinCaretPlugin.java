package com.lostintimedev.java.idea;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.event.EditorFactoryEvent;
import com.intellij.openapi.editor.event.EditorFactoryListener;
import org.jetbrains.annotations.NotNull;


public class ThinCaretPlugin implements ApplicationComponent {
    private static final int UNKNOWN_CURSOR_WIDTH = -1;
    private static final int DEFAULT_CURSOR_WIDTH = 2;
    private static final int THIN_CURSOR_WIDTH = 1;

    private ThinCaretConfiguration theConfiguration;
    private int originalCursorWidth;

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

    boolean isThinCaretEnabled() {
        return theConfiguration != null && theConfiguration.enabled;
    }

    void setThinCaretEnabled(boolean enabled) {
        if (theConfiguration != null) {
            if (theConfiguration.enabled != enabled) {
                theConfiguration.enabled = enabled;

                final int currentWidth = theConfiguration.enabled
                        ? ThinCaretPlugin.THIN_CURSOR_WIDTH
                        : (originalCursorWidth != ThinCaretPlugin.UNKNOWN_CURSOR_WIDTH ? originalCursorWidth : ThinCaretPlugin.DEFAULT_CURSOR_WIDTH);

                for (Editor e : EditorFactory.getInstance().getAllEditors()) {
                    e.getSettings().setLineCursorWidth(currentWidth);
                }
            }
        }
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

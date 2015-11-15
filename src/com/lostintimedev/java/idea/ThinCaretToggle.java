package com.lostintimedev.java.idea;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;

public class ThinCaretToggle extends AnAction {
    public void actionPerformed(AnActionEvent e) {
        Application application = ApplicationManager.getApplication();

        ThinCaretPlugin plugin = application.getComponent(ThinCaretPlugin.class);

        if (plugin != null) {
            plugin.toggleCaretWidth();
        }
    }
}

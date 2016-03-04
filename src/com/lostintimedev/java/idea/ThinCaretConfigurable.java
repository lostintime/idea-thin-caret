package com.lostintimedev.java.idea;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Configuration panel
 */
public class ThinCaretConfigurable implements Configurable {

    private ThinCaretConfigurationPanel userInterface;
    private ThinCaretPlugin thinCaretPlugin;

    public ThinCaretConfigurable() {
        thinCaretPlugin = ApplicationManager.getApplication().getComponent(ThinCaretPlugin.class);
    }

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
        }

        return userInterface;
    }

    @Override
    public boolean isModified() {
        return userInterface != null && thinCaretPlugin != null
                && userInterface.isEnabled() != thinCaretPlugin.isThinCaretEnabled();

    }

    @Override
    public void apply() throws ConfigurationException {
        if (userInterface != null && thinCaretPlugin != null) {
            thinCaretPlugin.setThinCaretEnabled(userInterface.isEnabled());
        }
    }

    @Override
    public void reset() {
        if (userInterface != null && thinCaretPlugin != null) {
            userInterface.setEnabled(thinCaretPlugin.isThinCaretEnabled());
        }
    }

    @Override
    public void disposeUIResources() {
        userInterface = null;
    }
}

package com.lostintimedev.java.idea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

/**
 * ThinCaretConfigurationPanel
 */
public class ThinCaretConfigurationPanel extends JComponent {

    private JCheckBox cbxEnabled;
    private JPanel myPane;

    public ThinCaretConfigurationPanel() {
        this.setLayout(new GridBagLayout());
        cbxEnabled.setBackground(myPane.getBackground());

        cbxEnabled.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                setEnabled(ItemEvent.SELECTED == event.getStateChange());
            }
        });

        this.add(myPane);
    }

    public boolean isEnabled() {
        return cbxEnabled.isSelected();
    }

    public void setEnabled(boolean b) {
        cbxEnabled.setSelected(b);
    }
}

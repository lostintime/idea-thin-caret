package com.lostintimedev.java.idea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

/**
 * ThinCaretConfigurationPanel
 */
public class ThinCaretConfigurationPanel extends JComponent {

    public ThinCaretConfigurationPanel() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gb = new GridBagConstraints();
        gb.insets = new Insets(5, 5, 5, 0);
        gb.weightx = 0.0D;
        gb.weighty = 0.0D;
        gb.gridx = 0;
        gb.gridy = 0;
        gb.fill = 2;

        cbxEnabled = new Checkbox("Make my caret thin!", false);
        cbxEnabled.setBackground(this.getBackground());

        this.add(cbxEnabled, gb);
        cbxEnabled.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                setEnabled(ItemEvent.SELECTED == event.getStateChange());
            }
        });

        gb.gridx = 0;
        gb.gridy++;

        this.add(new JPanel(), gb);
    }

    public boolean isEnabled() {
        return cbxEnabled.getState();
    }

    public void setEnabled(boolean b) {
        cbxEnabled.setState(b);
    }

    private Checkbox cbxEnabled;
}

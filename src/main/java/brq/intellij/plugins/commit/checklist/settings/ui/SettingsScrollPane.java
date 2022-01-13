package brq.intellij.plugins.commit.checklist.settings.ui;

import com.intellij.ui.components.JBScrollPane;

import javax.swing.*;

public class SettingsScrollPane extends JBScrollPane {

    public SettingsScrollPane(JPanel innerPanel) {
        super(innerPanel, VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_NEVER);
        setBorder(null);
    }
}

package brq.intellij.plugins.commit.checklist.checkin;

import com.intellij.openapi.vcs.ui.RefreshableOnComponent;

import javax.swing.*;

import static brq.intellij.plugins.commit.checklist.common.CommitChecklistBundle.message;

public class CommitChecklistCheckbox implements RefreshableOnComponent {
    final JCheckBox commitCheckbox = new JCheckBox(message("plugin.name.commit.handler"));
    public static boolean SELECTED = true;

    public CommitChecklistCheckbox() {
        SELECTED = true;
    }

    @Override
    public JComponent getComponent() {
        return commitCheckbox;
    }

    @Override
    public void saveState() {
        SELECTED = commitCheckbox.isSelected();
    }

    @Override
    public void restoreState() {
        commitCheckbox.setSelected(SELECTED);
    }
}

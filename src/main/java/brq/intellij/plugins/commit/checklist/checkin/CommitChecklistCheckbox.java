package brq.intellij.plugins.commit.checklist.checkin;

import com.intellij.openapi.vcs.ui.RefreshableOnComponent;

import javax.swing.*;

public class CommitChecklistCheckbox implements RefreshableOnComponent {
    final JCheckBox commitCheckbox = new JCheckBox("Commit checklist");
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

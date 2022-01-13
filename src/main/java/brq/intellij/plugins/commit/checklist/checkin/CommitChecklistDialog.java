package brq.intellij.plugins.commit.checklist.checkin;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.components.JBCheckBox;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CommitChecklistDialog extends DialogWrapper {
    private List<JBCheckBox> checkboxChecklist = new ArrayList<>();
    private List<String> checklist;

    public CommitChecklistDialog(List<String> checklist) {
        super(true);
        this.checklist = checklist;
        setTitle("Commit Checklist");
        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout((new BoxLayout(dialogPanel, BoxLayout.PAGE_AXIS)));

        myOKAction.putValue(Action.NAME, "Commit");
        myOKAction.setEnabled(false);

        checklist.forEach(e -> {
            JBCheckBox checkBox = new JBCheckBox(e);
            checkBox.addActionListener(new ChecklistCheckboxListener());
            checkboxChecklist.add(checkBox);
        });
        checkboxChecklist.forEach(dialogPanel::add);
        return dialogPanel;
    }

    private class ChecklistCheckboxListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean allSelected = checkboxChecklist.stream().allMatch(AbstractButton::isSelected);
            myOKAction.setEnabled(allSelected);
        }
    }
}

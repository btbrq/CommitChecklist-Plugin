package brq.intellij.plugins.commit.checklist.checkin;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.components.JBCheckBox;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CommitChecklistDialog extends DialogWrapper {
    private final Project project;
    private List<JBCheckBox> checklist = new ArrayList<>();

    public CommitChecklistDialog(@Nullable Project project) {
        super(true);
        this.project = project;
        setTitle("Commit Checklist");
        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout((new BoxLayout(dialogPanel, BoxLayout.PAGE_AXIS)));

        myOKAction.putValue(Action.NAME, "Commit");
        myOKAction.setEnabled(false);

        List<String> list = List.of("test1", "test2");
        list.forEach(e -> {
            JBCheckBox checkBox = new JBCheckBox(e);
            checkBox.addActionListener(new ChecklistCheckboxListener());
            checklist.add(checkBox);
        });
        checklist.forEach(dialogPanel::add);
        return dialogPanel;
    }

    private class ChecklistCheckboxListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean allSelected = checklist.stream().allMatch(AbstractButton::isSelected);
            myOKAction.setEnabled(allSelected);
        }
    }
}

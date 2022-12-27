package brq.intellij.plugins.commit.checklist.checkin;

import brq.intellij.plugins.commit.checklist.settings.ProjectSettings;
import brq.intellij.plugins.commit.checklist.settings.ui.SettingsScrollPane;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static brq.intellij.plugins.commit.checklist.common.Constants.*;

public class CommitChecklistDialog extends DialogWrapper {
    private final List<JBCheckBox> checkboxChecklist = new ArrayList<>();
    private final ProjectSettings settings;
    private final List<String> checklist;
    private JPanel dialogPanel;

    public CommitChecklistDialog(ProjectSettings settings, List<String> checklist) {
        //todo add global checklist
        super(true);
        this.settings = settings;
        this.checklist = checklist;
        setTitle("Commit Checklist");
        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        dialogPanel = new JPanel();
        dialogPanel.setLayout((new BoxLayout(dialogPanel, BoxLayout.PAGE_AXIS)));
        setDialogDimensions(dialogPanel);

        myOKAction.putValue(Action.NAME, "Commit");
        myOKAction.setEnabled(false);

        JPanel innerPanel = new JPanel();
        innerPanel.setLayout((new BoxLayout(innerPanel, BoxLayout.PAGE_AXIS)));

        checklist.forEach(e -> {
            JBCheckBox checkBox = new JBCheckBox("<html>" + e + "</html>");
            checkBox.addActionListener(new ChecklistCheckboxListener());
            checkBox.setBorder(JBUI.Borders.empty(6, 0));

            innerPanel.add(checkBox);
            checkboxChecklist.add(checkBox);
        });

        dialogPanel.add(new SettingsScrollPane(innerPanel));
        return dialogPanel;
    }

    @Override
    public @Nullable JComponent getPreferredFocusedComponent() {
        return dialogPanel;
    }

    public int getWidth() {
        return dialogPanel.getWidth();
    }

    public int getHeight() {
        return dialogPanel.getHeight();
    }

    private void setDialogDimensions(Component dialog) {
        dialog.setMinimumSize(new Dimension(DIALOG_MIN_WIDTH, DIALOG_MIN_HEIGHT));
        dialog.setMaximumSize(new Dimension(DIALOG_MAX_WIDTH, DIALOG_MAX_HEIGHT));
        dialog.setPreferredSize(settings.getPreferredDimension());
    }

    private class ChecklistCheckboxListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean allSelected = checkboxChecklist.stream().allMatch(AbstractButton::isSelected);
            myOKAction.setEnabled(allSelected);
        }
    }

}

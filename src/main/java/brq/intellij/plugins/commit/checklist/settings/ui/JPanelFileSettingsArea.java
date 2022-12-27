package brq.intellij.plugins.commit.checklist.settings.ui;

import brq.intellij.plugins.commit.checklist.settings.ProjectSettings;
import com.intellij.icons.AllIcons;
import com.intellij.json.JsonFileType;
import com.intellij.openapi.fileChooser.FileChooserFactory;
import com.intellij.openapi.fileChooser.FileTextField;
import com.intellij.openapi.ui.TextBrowseFolderListener;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.components.JBCheckBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.intellij.openapi.fileChooser.FileChooserDescriptorFactory.createSingleFileDescriptor;

public class JPanelFileSettingsArea extends JPanel {
    private final JBCheckBox useFromFileCheckbox;
    private final TextFieldWithBrowseButton fileTextField;
    private final SettingsTabbedPane tablePanel;

    public JPanelFileSettingsArea(ProjectSettings settings, SettingsTabbedPane tablePanel) {
        this.tablePanel = tablePanel;
        setLayout(new BorderLayout());
        setMaximumSize(new Dimension(getMaximumSize().width, AllIcons.General.GearPlain.getIconHeight() + 10));

        JPanel fileChooserPanel = new JPanel();
        fileChooserPanel.setLayout(new BorderLayout());
        fileChooserPanel.setMaximumSize(new Dimension(getMaximumSize().width, AllIcons.General.GearPlain.getIconHeight() + 10));

        FileTextField fileChooser = FileChooserFactory.getInstance().createFileTextField(createSingleFileDescriptor(JsonFileType.INSTANCE), null);
        fileTextField = new TextFieldWithBrowseButton(fileChooser.getField());
        fileTextField.addBrowseFolderListener(new TextBrowseFolderListener(createSingleFileDescriptor(JsonFileType.INSTANCE)));
        fileTextField.setPreferredSize(new Dimension(300, AllIcons.General.GearPlain.getIconHeight() + 10));

        useFromFileCheckbox = new JBCheckBox("Use checklist from file");
        useFromFileCheckbox.addActionListener(new TextFieldEnabledCheckboxListener());
        JPanel fileTextPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fileTextPanel.add(useFromFileCheckbox);
        fileTextPanel.add(fileTextField);

        fileChooserPanel.add(fileTextPanel, BorderLayout.WEST);

        JLabel importExportButton = ImportExportButton.create(tablePanel);
        fileChooserPanel.add(importExportButton, BorderLayout.EAST);

        setUseSettingsFromFile(settings.isUseSettingsFromFile());
        fileTextField.setText(settings.getSettingsFilePath());

        add(fileChooserPanel, BorderLayout.NORTH);
    }

    public boolean isUseSettingsFromFile() {
        return useFromFileCheckbox.isSelected();
    }

    public String getSettingsFilePath() {
        return fileTextField.getText();
    }

    public void setUseSettingsFromFile(boolean value) {
        useFromFileCheckbox.setSelected(value);
        fileTextField.setEnabled(useFromFileCheckbox.isSelected());
        tablePanel.setProjectTableEnabled(!useFromFileCheckbox.isSelected());
    }

    public void setSettingsFilePath(String value) {
        fileTextField.setText(value);
    }

    public static JPanelFileSettingsArea create(ProjectSettings settings, SettingsTabbedPane tablePanel) {
        return new JPanelFileSettingsArea(settings, tablePanel);
    }

    private class TextFieldEnabledCheckboxListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            fileTextField.setEnabled(useFromFileCheckbox.isSelected());
            tablePanel.setProjectTableEnabled(!useFromFileCheckbox.isSelected());
        }
    }

}

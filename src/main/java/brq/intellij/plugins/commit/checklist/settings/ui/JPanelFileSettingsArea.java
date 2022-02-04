package brq.intellij.plugins.commit.checklist.settings.ui;

import brq.intellij.plugins.commit.checklist.settings.Settings;
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
    private final JBCheckBox checkBox;
    private final TextFieldWithBrowseButton fileTextField;
    private final SettingsTable table;

    public JPanelFileSettingsArea(SettingsTable table) {
        this.table = table;
        setLayout(new BorderLayout());
        setMaximumSize(new Dimension(getMaximumSize().width, AllIcons.General.GearPlain.getIconHeight() + 10));

        FileTextField fileChooser = FileChooserFactory.getInstance().createFileTextField(createSingleFileDescriptor(JsonFileType.INSTANCE), null);
        fileTextField = new TextFieldWithBrowseButton(fileChooser.getField());
        fileTextField.addBrowseFolderListener(new TextBrowseFolderListener(createSingleFileDescriptor(JsonFileType.INSTANCE)));
        fileTextField.setPreferredSize(new Dimension(300, AllIcons.General.GearPlain.getIconHeight() + 10));

        checkBox = new JBCheckBox("Use checklist from file");
        checkBox.addActionListener(new TextFieldEnabledCheckboxListener());
        JPanel fileTextPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fileTextPanel.add(checkBox);
        fileTextPanel.add(fileTextField);

        add(fileTextPanel, BorderLayout.WEST);

        JLabel importExportButton = ImportExportButton.create(table);
        add(importExportButton, BorderLayout.EAST);

        Settings settings = Settings.getInstance();
        setUseSettingsFromFile(settings.isUseSettingsFromFile());
        fileTextField.setText(settings.getSettingsFilePath());
    }

    public boolean isUseSettingsFromFile() {
        return checkBox.isSelected();
    }

    public String getSettingsFilePath() {
        return fileTextField.getText();
    }

    public void setUseSettingsFromFile(boolean value) {
        checkBox.setSelected(value);
        fileTextField.setEnabled(checkBox.isSelected());
        table.enabled(!checkBox.isSelected());
    }

    public void setSettingsFilePath(String value) {
        fileTextField.setText(value);
    }

    public static JPanelFileSettingsArea create(SettingsTable table) {
        return new JPanelFileSettingsArea(table);
    }

    private class TextFieldEnabledCheckboxListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            fileTextField.setEnabled(checkBox.isSelected());
            table.enabled(!checkBox.isSelected());
        }
    }

}

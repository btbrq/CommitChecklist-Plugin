package brq.intellij.plugins.commit.checklist.settings.ui;

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

    public JPanelFileSettingsArea(SettingsTable table) {
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
        table.enabled(false);
        //TODO set checkbox selected & field enabled based on settings

        add(fileTextPanel, BorderLayout.WEST);

        JLabel importExportButton = ImportExportButton.create(table);
        add(importExportButton, BorderLayout.EAST);
    }


    public static JPanelFileSettingsArea create(SettingsTable table) {
        return new JPanelFileSettingsArea(table);
    }

    private class TextFieldEnabledCheckboxListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
//            checkBox.setSelected(!checkBox.isSelected());
            fileTextField.setEnabled(checkBox.isSelected());
        }
    }

}

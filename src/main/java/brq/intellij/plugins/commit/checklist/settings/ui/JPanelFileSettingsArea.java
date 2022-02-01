package brq.intellij.plugins.commit.checklist.settings.ui;

import com.intellij.icons.AllIcons;
import com.intellij.json.JsonFileType;
import com.intellij.openapi.fileChooser.FileChooserFactory;
import com.intellij.openapi.fileChooser.FileTextField;
import com.intellij.openapi.ui.TextBrowseFolderListener;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;

import javax.swing.*;
import java.awt.*;

import static com.intellij.openapi.fileChooser.FileChooserDescriptorFactory.createSingleFileDescriptor;

public class JPanelFileSettingsArea extends JPanel {
    private TextFieldWithBrowseButton fileTextField;
    private JLabel importExportButton;

    public JPanelFileSettingsArea() {
        setLayout(new BorderLayout());
        setMaximumSize(new Dimension(getMaximumSize().width, AllIcons.General.GearPlain.getIconHeight() * 2));

        FileTextField fileChooser = FileChooserFactory.getInstance().createFileTextField(createSingleFileDescriptor(JsonFileType.INSTANCE), null);
        fileTextField = new TextFieldWithBrowseButton(fileChooser.getField());
        fileTextField.addBrowseFolderListener(new TextBrowseFolderListener(createSingleFileDescriptor(JsonFileType.INSTANCE)));

        fileTextField.setPreferredSize(new Dimension(400, AllIcons.General.GearPlain.getIconHeight() * 2));
        add(fileTextField, BorderLayout.WEST);

        importExportButton = ImportExportButton.create();
        add(importExportButton, BorderLayout.EAST);
    }


    public static JPanelFileSettingsArea create() {
        JPanelFileSettingsArea panel = new JPanelFileSettingsArea();
        return panel;
    }

}

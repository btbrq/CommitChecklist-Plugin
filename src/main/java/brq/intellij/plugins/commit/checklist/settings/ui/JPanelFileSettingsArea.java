package brq.intellij.plugins.commit.checklist.settings.ui;

import com.intellij.icons.AllIcons;
import com.intellij.json.JsonFileType;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserFactory;
import com.intellij.openapi.fileChooser.FileTextField;
import com.intellij.openapi.ui.TextBrowseFolderListener;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.vfs.VirtualFile;

import javax.swing.*;
import java.awt.*;

import static com.intellij.openapi.fileChooser.FileChooserDescriptorFactory.createSingleFileDescriptor;

public class JPanelFileSettingsArea extends JPanel {
    private VirtualFile virtualFile;
    private TextFieldWithBrowseButton fileTextField;

    public JPanelFileSettingsArea() {
        setLayout(new BorderLayout());
        setMaximumSize(new Dimension(getMaximumSize().width, AllIcons.General.GearPlain.getIconHeight() * 2));

        FileTextField fileTextField2 = FileChooserFactory.getInstance().createFileTextField(createSingleFileDescriptor(JsonFileType.INSTANCE), null);
        TextFieldWithBrowseButton fileTextField = new TextFieldWithBrowseButton(fileTextField2.getField());
        fileTextField.addBrowseFolderListener(new TextBrowseFolderListener(createSingleFileDescriptor(JsonFileType.INSTANCE)));

        fileTextField.setPreferredSize(new Dimension(400, AllIcons.General.GearPlain.getIconHeight() * 2));
        add(fileTextField, BorderLayout.WEST);

        JLabel comp = new JLabel(AllIcons.General.GearPlain);
        comp.addMouseListener(new UseFileFromDiskMouseListener(this));
        add(comp, BorderLayout.EAST);
    }

    public void launchFileOpener() {
        //import file
        virtualFile = FileChooser.chooseFile(createSingleFileDescriptor(JsonFileType.INSTANCE), null, null);
        if (virtualFile != null) {
            fileTextField.setText(virtualFile.getName());
        }
    }

    public static JPanelFileSettingsArea create() {
        JPanelFileSettingsArea panel = new JPanelFileSettingsArea();
        return panel;
    }

}

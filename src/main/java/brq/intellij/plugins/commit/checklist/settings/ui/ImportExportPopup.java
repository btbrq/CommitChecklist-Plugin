package brq.intellij.plugins.commit.checklist.settings.ui;

import com.intellij.json.JsonFileType;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserFactory;
import com.intellij.openapi.fileChooser.FileSaverDescriptor;
import com.intellij.openapi.fileChooser.FileSaverDialog;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileWrapper;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static com.intellij.openapi.fileChooser.FileChooserDescriptorFactory.createSingleFileDescriptor;

public class ImportExportPopup {

    public static void createPopup(JLabel importExportButton) {
        List<Item> items = new ArrayList<>();
        Item importItem = new Item("Import checklist from file", Item.ItemType.IMPORT);
        items.add(importItem);

        Item exportItem = new Item("Export checklist to file", Item.ItemType.EXPORT);
        items.add(exportItem);

        JBPopupFactory.getInstance().createPopupChooserBuilder(items)
                .setItemChosenCallback(item -> {
                    if (item.type == Item.ItemType.IMPORT) {
                        importFile();
                    } else {
                        exportFile();
                    }
                })
                .createPopup()
                .showUnderneathOf(importExportButton);
    }

    private static void exportFile() {
        FileSaverDescriptor fileSaverDescriptor = new FileSaverDescriptor("Export Checklist", "File name:", "json");
        FileSaverDialog saveFileDialog = FileChooserFactory.getInstance().createSaveFileDialog(fileSaverDescriptor, (Project) null);
        VirtualFileWrapper fileWrapper = saveFileDialog.save("commit-checklist.json");
        try {
            if (fileWrapper != null) {
                FileUtil.writeToFile(fileWrapper.getFile(), "test");
            }
        } catch (Exception e) {

        }
    }

    private static void importFile() {
        VirtualFile virtualFile = FileChooser.chooseFile(createSingleFileDescriptor(JsonFileType.INSTANCE), null, null);
        if (virtualFile != null) {

        }
    }

    private static class Item {
        private String label;
        private ItemType type;

        public Item(String label, ItemType type) {
            this.label = label;
            this.type = type;
        }

        @Override
        public String toString() {
            return label;
        }

        private enum ItemType { IMPORT, EXPORT }
    }

}

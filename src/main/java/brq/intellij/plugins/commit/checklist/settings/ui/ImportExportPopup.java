package brq.intellij.plugins.commit.checklist.settings.ui;

import brq.intellij.plugins.commit.checklist.settings.ImportSettingsResponse;
import brq.intellij.plugins.commit.checklist.settings.MessageItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellij.json.JsonFileType;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserFactory;
import com.intellij.openapi.fileChooser.FileSaverDescriptor;
import com.intellij.openapi.fileChooser.FileSaverDialog;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileWrapper;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static brq.intellij.plugins.commit.checklist.settings.SettingsImporter.importChecklist;
import static com.intellij.openapi.fileChooser.FileChooserDescriptorFactory.createSingleFileDescriptor;

public class ImportExportPopup {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void createPopup(JLabel importExportButton, SettingsTable table) {
        List<Item> items = new ArrayList<>();
        Item importItem = new Item("Import checklist from file", Item.ItemType.IMPORT);
        items.add(importItem);

        Item exportItem = new Item("Export checklist to file", Item.ItemType.EXPORT);
        items.add(exportItem);

        JBPopupFactory.getInstance().createPopupChooserBuilder(items)
                .setItemChosenCallback(item -> {
                    if (item.type == Item.ItemType.IMPORT) {
                        importFile(table);
                    } else {
                        exportFile(table);
                    }
                })
                .createPopup()
                .showUnderneathOf(importExportButton);
    }

    private static void exportFile(SettingsTable table) {
        FileSaverDescriptor fileSaverDescriptor = new FileSaverDescriptor("Export Checklist", "File name:", "json");
        FileSaverDialog saveFileDialog = FileChooserFactory.getInstance().createSaveFileDialog(fileSaverDescriptor, (Project) null);
        VirtualFileWrapper fileWrapper = saveFileDialog.save("commit-checklist.json");
        try {
            if (fileWrapper != null) {
                List<MessageItem> checklistItems = table.getChecklistItems();
                FileUtil.writeToFile(fileWrapper.getFile(), mapper.writeValueAsString(checklistItems));
            }
        } catch (IOException e) {
            DialogWrapper dialog = new ImportErrorDialog("Error occurred while exporting checklist to file.");
            dialog.show();
        }
    }

    private static void importFile(SettingsTable table) {
        VirtualFile virtualFile = FileChooser.chooseFile(createSingleFileDescriptor(JsonFileType.INSTANCE), null, null);
        if (virtualFile != null) {
            ImportSettingsResponse response = importChecklist(virtualFile.getPath());
            if (!response.hasErrors()) {
                table.reset(response.getChecklist());
            }
        }
    }

    private static class Item {
        private final String label;
        private final ItemType type;

        public Item(String label, ItemType type) {
            this.label = label;
            this.type = type;
        }

        @Override
        public String toString() {
            return label;
        }

        private enum ItemType {IMPORT, EXPORT}
    }

}

package brq.intellij.plugins.commit.checklist.settings.ui;

import brq.intellij.plugins.commit.checklist.settings.Settings;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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
                List<MessageItem> checklistItems = Settings.getInstance().getChecklistItems();
                FileUtil.writeToFile(fileWrapper.getFile(), mapper.writeValueAsString(checklistItems));
            }
        } catch (Exception e) {
// TODO
        }
    }

    private static void importFile(SettingsTable table) {
        VirtualFile virtualFile = FileChooser.chooseFile(createSingleFileDescriptor(JsonFileType.INSTANCE), null, null);
        if (virtualFile != null) {
            try {
                String content = Files.readString(Path.of(virtualFile.getPath()));
                List<MessageItem> items = mapper.readValue(content, mapper.getTypeFactory().constructCollectionType(List.class, MessageItem.class));
                table.reset(items);
            } catch (IOException e) {
// TODO
            }
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

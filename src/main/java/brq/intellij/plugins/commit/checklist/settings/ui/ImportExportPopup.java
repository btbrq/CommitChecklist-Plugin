package brq.intellij.plugins.commit.checklist.settings.ui;

import com.intellij.json.JsonFileType;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.vfs.VirtualFile;

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
                        System.out.println("import");
                        VirtualFile virtualFile = FileChooser.chooseFile(createSingleFileDescriptor(JsonFileType.INSTANCE), null, null);
                        if (virtualFile != null) {

                        }
                    } else {
                        System.out.println("export");
                    }
                })
                .createPopup()
                .showUnderneathOf(importExportButton);
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

package brq.intellij.plugins.commit.checklist.settings.ui;

import com.intellij.util.ui.CollectionItemEditor;
import com.intellij.util.ui.ColumnInfo;
import com.intellij.util.ui.table.TableModelEditor;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static brq.intellij.plugins.commit.checklist.settings.ui.EditableColumn.Column.FILE_MASK;
import static brq.intellij.plugins.commit.checklist.settings.ui.EditableColumn.Column.VALUE;

public class SettingsTable extends TableModelEditor<MessageItem> {

    public SettingsTable(ColumnInfo @NotNull [] columns, @NotNull CollectionItemEditor<MessageItem> itemEditor, @NotNull @Nls(capitalization = Nls.Capitalization.Sentence) String emptyText) {
        super(columns, itemEditor, emptyText);
    }

    public static SettingsTable createTable(List<MessageItem> checklist) {
        final ColumnInfo[] columns = getColumns();
        SettingsTableEditor editor = new SettingsTableEditor();
        SettingsTable table = new SettingsTable(columns, editor, "");
        checklist.forEach(i -> table.getModel().addRow(new MessageItem(i.getValue(), i.getFileMask())));
        return table;
    }

    public static ColumnInfo[] getColumns() {
        return new ColumnInfo[] {
                new EditableColumn("Item", VALUE),
                new EditableColumn("File mask", FILE_MASK)
        };
    }

}

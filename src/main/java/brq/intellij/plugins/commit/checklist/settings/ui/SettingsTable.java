package brq.intellij.plugins.commit.checklist.settings.ui;

import com.intellij.util.ui.CollectionItemEditor;
import com.intellij.util.ui.ColumnInfo;
import com.intellij.util.ui.table.TableModelEditor;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SettingsTable extends TableModelEditor<MessageItem> {

    public SettingsTable(ColumnInfo @NotNull [] columns, @NotNull CollectionItemEditor<MessageItem> itemEditor, @NotNull @Nls(capitalization = Nls.Capitalization.Sentence) String emptyText) {
        super(columns, itemEditor, emptyText);
    }

    public static SettingsTable createTable(List<String> checklist) {
        final ColumnInfo[] columns = getColumns();
        SettingsTableEditor editor = new SettingsTableEditor();
        SettingsTable table = new SettingsTable(columns, editor, "");
        checklist.forEach(i -> table.getModel().addRow(new MessageItem(i)));
        return table;
    }

    public static ColumnInfo[] getColumns() {
        return new ColumnInfo[]{ new EditableColumn("Item") };
    }

}

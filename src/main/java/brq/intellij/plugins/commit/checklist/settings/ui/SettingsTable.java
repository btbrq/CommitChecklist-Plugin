package brq.intellij.plugins.commit.checklist.settings.ui;

import brq.intellij.plugins.commit.checklist.settings.MessageItem;
import com.intellij.util.ui.CollectionItemEditor;
import com.intellij.util.ui.ColumnInfo;
import com.intellij.util.ui.table.TableModelEditor;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.IntStream;

import static brq.intellij.plugins.commit.checklist.settings.ui.EditableColumn.Column.FILE_MASK;
import static brq.intellij.plugins.commit.checklist.settings.ui.EditableColumn.Column.VALUE;
import static java.util.stream.Collectors.toList;

public class SettingsTable extends TableModelEditor<MessageItem> {
    private final String title;
    private final Type type;

    public SettingsTable(String title, Type type, ColumnInfo @NotNull [] columns, @NotNull CollectionItemEditor<MessageItem> itemEditor, @NotNull @Nls(capitalization = Nls.Capitalization.Sentence) String emptyText) {
        super(columns, itemEditor, emptyText);
        this.title = title;
        this.type = type;
    }

    public List<MessageItem> getChecklistItems() {
        int rowCount = getModel().getRowCount();
        return IntStream.range(0, rowCount)
                .mapToObj(i -> this.getModel().getRowValue(i))
                .collect(toList());
    }

    public String getTitle() {
        return title;
    }

    public Type getType() {
        return type;
    }

    public static SettingsTable createTable(String title, Type type, List<MessageItem> checklist) {
        final ColumnInfo[] columns = getColumns();
        SettingsTableEditor editor = new SettingsTableEditor();
        SettingsTable table = new SettingsTable(title, type, columns, editor, "");
        checklist.forEach(i -> table.getModel().addRow(new MessageItem(i.getValue(), i.getFileMask())));
        return table;
    }

    public static ColumnInfo[] getColumns() {
        return new ColumnInfo[] {
                new EditableColumn("Item", VALUE),
                new EditableColumn("File mask", FILE_MASK)
        };
    }

    public enum Type {
        PROJECT, GLOBAL
    }

}

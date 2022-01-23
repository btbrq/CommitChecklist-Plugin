package brq.intellij.plugins.commit.checklist.settings.ui;

import com.intellij.openapi.util.NlsContexts;
import com.intellij.util.ui.table.TableModelEditor;
import org.jetbrains.annotations.Nullable;

public class EditableColumn extends TableModelEditor.EditableColumnInfo<MessageItem, String> {
    private final Column column;

    public EditableColumn(String name, Column column) {
        super(name);
        this.column = column;
    }

    @Nullable
    @Override
    public String valueOf(MessageItem item) {
        switch (column) {
            case VALUE:
                return item.getValue();
            case FILE_MASK:
                return item.getFileMask();
            default:
                return "";
        }
    }

    @Override
    public void setValue(MessageItem item, String value) {
        switch (column) {
            case VALUE:
                item.setValue(value);
                break;
            case FILE_MASK:
                item.setFileMask((value != null && !value.isBlank()) ? value : "*");
        }
    }

    @Override
    public @NlsContexts.Tooltip @Nullable String getTooltipText() {
        if (column == Column.FILE_MASK) {
            return "IDEA-like search file mask. Item will be displayed if committed files are matching the file mask.";
        }
        return super.getTooltipText();
    }

    enum Column { VALUE, FILE_MASK }
}

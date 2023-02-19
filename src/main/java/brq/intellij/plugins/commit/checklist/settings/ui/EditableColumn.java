package brq.intellij.plugins.commit.checklist.settings.ui;

import brq.intellij.plugins.commit.checklist.settings.MessageItem;
import com.intellij.util.ui.table.TableModelEditor;
import org.jetbrains.annotations.Nullable;

import static brq.intellij.plugins.commit.checklist.common.CommitChecklistBundle.message;

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
    public @Nullable String getTooltipText() {
        if (column == Column.FILE_MASK) {
            return message("settings.file.mask.tooltip.description");
        }
        return super.getTooltipText();
    }

    enum Column { VALUE, FILE_MASK }
}

package brq.intellij.plugins.commit.checklist.settings.ui;

import com.intellij.util.ui.table.TableModelEditor;
import org.jetbrains.annotations.Nullable;

public class EditableColumn extends TableModelEditor.EditableColumnInfo<MessageItem, String> {
    public EditableColumn(String name) {
        super(name);
    }

    @Nullable
    @Override
    public String valueOf(MessageItem item) {
        return item.getValue();
    }

    @Override
    public void setValue(MessageItem item, String value) {
        item.setValue(value);
    }
}

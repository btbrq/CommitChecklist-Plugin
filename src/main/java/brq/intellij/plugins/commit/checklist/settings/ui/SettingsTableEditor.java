package brq.intellij.plugins.commit.checklist.settings.ui;

import com.intellij.util.Function;
import com.intellij.util.ui.table.TableModelEditor;
import org.jetbrains.annotations.NotNull;

public class SettingsTableEditor implements TableModelEditor.DialogItemEditor<MessageItem> {

    @Override
    public void edit(@NotNull MessageItem item, @NotNull Function<? super MessageItem, ? extends MessageItem> mutator, boolean isAdd) {

    }

    @Override
    public void applyEdited(@NotNull MessageItem oldItem, @NotNull MessageItem newItem) {

    }

    @Override
    public @NotNull Class<? extends MessageItem> getItemClass() {
        return MessageItem.class;
    }

    @Override
    public MessageItem clone(@NotNull MessageItem item, boolean forInPlaceEditing) {
        return new MessageItem(item.getValue());
    }
    
    
}

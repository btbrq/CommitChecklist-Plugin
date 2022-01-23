package brq.intellij.plugins.commit.checklist.settings.ui;

import com.intellij.util.ui.CollectionItemEditor;
import org.jetbrains.annotations.NotNull;

public class SettingsTableEditor implements CollectionItemEditor<MessageItem> {
    @Override
    public @NotNull Class<? extends MessageItem> getItemClass() {
        return MessageItem.class;
    }

    @Override
    public MessageItem clone(@NotNull MessageItem item, boolean forInPlaceEditing) {
        return new MessageItem(item.getValue(), item.getFileMask());
    }
}

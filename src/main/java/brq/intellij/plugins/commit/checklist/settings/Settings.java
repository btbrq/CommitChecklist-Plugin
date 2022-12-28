package brq.intellij.plugins.commit.checklist.settings;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@State(name = "brq.intellij.plugins.commit.checklist.settings.Settings", storages = @Storage("commit-checklist.xml"))
public class Settings implements PersistentStateComponent<Settings> {
    private List<MessageItem> checklistItems = new ArrayList<>();

    public static Settings getInstance() {
        return ApplicationManager.getApplication().getService(Settings.class);
    }

    @Override
    public Settings getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull Settings state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    public List<MessageItem> getChecklistItems() {
        return checklistItems;
    }

    public void setChecklistItems(List<MessageItem> checklistItems) {
        this.checklistItems = checklistItems;
    }

}

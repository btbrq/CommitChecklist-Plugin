package brq.intellij.plugins.commit.checklist.settings;

import brq.intellij.plugins.commit.checklist.settings.ui.MessageItem;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static brq.intellij.plugins.commit.checklist.common.Constants.*;
import static java.util.stream.Collectors.toList;

@State(name = "brq.intellij.plugins.commit.checklist.settings.Settings", storages = @Storage("commit-checklist.xml"))
public class Settings implements PersistentStateComponent<Settings> {

    private List<String> checklist = new ArrayList<>();
    private List<MessageItem> checklistItems = new ArrayList<>();
    private int preferredWidth = DIALOG_DEFAULT_WIDTH;
    private int preferredHeight = DIALOG_DEFAULT_HEIGHT;

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

    public List<String> getChecklist() {
        return checklist;
    }

    public List<MessageItem> getChecklistItems() {
        migrateFromPreviousChecklist();
        return checklistItems;
    }

    private void migrateFromPreviousChecklist() {
        if (!checklist.isEmpty()) {
            List<MessageItem> objectStream = checklist.stream().map(MessageItem::new).collect(toList());
            checklistItems.addAll(objectStream);
            checklist.clear();
        }
    }

    public void setChecklistItems(List<MessageItem> checklistItems) {
        this.checklistItems = checklistItems;
    }

    public void setChecklist(List<String> checklist) {
        this.checklist = checklist;
    }

    public void setDimensions(int width, int height) {
        if (width < DIALOG_MAX_WIDTH && height < DIALOG_MAX_HEIGHT) {
            this.preferredWidth = width;
            this.preferredHeight = height;
        }
    }

    public Dimension getPreferredDimension() {
        return new Dimension(preferredWidth, preferredHeight);
    }
}

package brq.intellij.plugins.commit.checklist.settings;

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

    private List<MessageItem> checklistItems = new ArrayList<>();
    private int preferredWidth = DIALOG_DEFAULT_WIDTH;
    private int preferredHeight = DIALOG_DEFAULT_HEIGHT;
    private boolean useSettingsFromFile = false;
    private String settingsFilePath = "";
    private boolean applyGlobal = false;

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

    public void setDimensions(int width, int height) {
        if (width < DIALOG_MAX_WIDTH && height < DIALOG_MAX_HEIGHT) {
            this.preferredWidth = width;
            this.preferredHeight = height;
        }
    }

    public Dimension getPreferredDimension() {
        return new Dimension(preferredWidth, preferredHeight);
    }

    public boolean isUseSettingsFromFile() {
        return useSettingsFromFile;
    }

    public void setUseSettingsFromFile(boolean useSettingsFromFile) {
        this.useSettingsFromFile = useSettingsFromFile;
    }

    public String getSettingsFilePath() {
        return settingsFilePath;
    }

    public void setSettingsFilePath(String settingsFilePath) {
        this.settingsFilePath = settingsFilePath;
    }

    public boolean isApplyGlobal() {
        return applyGlobal;
    }

    public void setApplyGlobal(boolean applyGlobal) {
        this.applyGlobal = applyGlobal;
    }
}

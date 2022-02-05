package brq.intellij.plugins.commit.checklist.settings;

import brq.intellij.plugins.commit.checklist.settings.ui.MessageItem;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static brq.intellij.plugins.commit.checklist.common.Constants.*;

@State(name = "brq.intellij.plugins.commit.checklist.settings.ProjectSettings", storages = @Storage("commit-checklist-project.xml"))
public class ProjectSettings implements PersistentStateComponent<ProjectSettings> {
    private List<MessageItem> checklistItems = new ArrayList<>();
    private int preferredWidth = DIALOG_DEFAULT_WIDTH;
    private int preferredHeight = DIALOG_DEFAULT_HEIGHT;
    private boolean useSettingsFromFile = false;
    private String settingsFilePath = "";

    public static ProjectSettings getInstance(Project project) {
        return project.getService(ProjectSettings.class);
    }

    @Override
    public ProjectSettings getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull ProjectSettings state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    public List<MessageItem> getChecklistItems() {
        migrateFromAppSettings();
        return checklistItems;
    }

    private void migrateFromAppSettings() {
        List<MessageItem> appItems = AppSettings.getInstance().getChecklistItems();
        System.out.println("migrate app settings");

        if (!appItems.isEmpty()) {
            System.out.println("NOT EMPTY, ADDING");
            checklistItems.addAll(appItems);
            appItems.clear();
        }
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
}

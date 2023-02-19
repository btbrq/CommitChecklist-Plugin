package brq.intellij.plugins.commit.checklist.settings;

import brq.intellij.plugins.commit.checklist.settings.ui.JPanelSettings;
import com.intellij.openapi.project.Project;

import javax.swing.*;

import static brq.intellij.plugins.commit.checklist.common.CommitChecklistBundle.message;

public class Configurable implements com.intellij.openapi.options.Configurable {
    private final ProjectSettings projectSettings;
    private final Settings appSettings;
    private JPanelSettings settingsPanel;

    public Configurable(Project project) {
        this.projectSettings = ProjectSettings.getInstance(project);
        this.appSettings = Settings.getInstance();
    }

    @Override
    public JComponent getPreferredFocusedComponent() {
        return settingsPanel;
    }

    @Override
    public JComponent createComponent() {
        settingsPanel = JPanelSettings.createAppSettingsPanel(projectSettings, appSettings);
        return settingsPanel;
    }

    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public void apply() {
        projectSettings.setChecklistItems(settingsPanel.getProjectChecklistItems());
        projectSettings.setUseSettingsFromFile(settingsPanel.isUseSettingsFromFile());
        projectSettings.setSettingsFilePath(settingsPanel.getSettingsFilePath());
        appSettings.setChecklistItems(settingsPanel.getGlobalChecklistItems());
    }

    @Override
    public void reset() {
        settingsPanel.reset(
                projectSettings.getChecklistItems(),
                appSettings.getChecklistItems(),
                projectSettings.isUseSettingsFromFile(),
                projectSettings.getSettingsFilePath()
        );
    }

    @Override
    public void disposeUIResources() {
        settingsPanel = null;
    }

    @Override
    public String getDisplayName() {
        return message("plugin.name");
    }

}

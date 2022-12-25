package brq.intellij.plugins.commit.checklist.settings;

import brq.intellij.plugins.commit.checklist.settings.ui.JPanelSettings;
import com.intellij.openapi.project.Project;

import javax.swing.*;

public class Configurable implements com.intellij.openapi.options.Configurable {
    private final Project project;
    private final ProjectSettings projectSettings;
    private final Settings appSettings;
    private JPanelSettings settingsPanel;

    public Configurable(Project project) {
        this.project = project;
        this.projectSettings = ProjectSettings.getInstance(project);
        this.appSettings = Settings.getInstance();
    }

    @Override
    public JComponent getPreferredFocusedComponent() {
        return settingsPanel;
    }

    @Override
    public JComponent createComponent() {
        settingsPanel = JPanelSettings.createAppSettingsPanel(projectSettings);
        return settingsPanel;
    }

    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public void apply() {
        projectSettings.setChecklistItems(settingsPanel.getChecklistItems());
        projectSettings.setUseSettingsFromFile(settingsPanel.isUseSettingsFromFile());
        projectSettings.setApplyGlobal(settingsPanel.isApplyGlobal());
        projectSettings.setSettingsFilePath(settingsPanel.getSettingsFilePath());
        //todo apply global
    }

    @Override
    public void reset() {
        settingsPanel.reset(
                projectSettings.getChecklistItems(),
                projectSettings.isUseSettingsFromFile(),
                projectSettings.getSettingsFilePath(),
                projectSettings.isApplyGlobal()
        );
        //todo reset global
    }

    @Override
    public void disposeUIResources() {
        settingsPanel = null;
    }

    @Override
    public String getDisplayName() {
        return "Commit Checklist";
    }

}

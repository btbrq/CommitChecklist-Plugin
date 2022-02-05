package brq.intellij.plugins.commit.checklist.settings;

import brq.intellij.plugins.commit.checklist.settings.ui.JPanelSettings;
import com.intellij.openapi.project.Project;

import javax.swing.*;

public class Configurable implements com.intellij.openapi.options.Configurable {
    private final Project project;
    private final ProjectSettings settings;
    private JPanelSettings settingsPanel;

    public Configurable(Project project) {
        this.project = project;
        this.settings = ProjectSettings.getInstance(project);
    }

    @Override
    public JComponent getPreferredFocusedComponent() {
        return settingsPanel;
    }

    @Override
    public JComponent createComponent() {
        settingsPanel = JPanelSettings.createAppSettingsPanel(settings);
        return settingsPanel;
    }

    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public void apply() {
        settings.setChecklistItems(settingsPanel.getChecklistItems());
        settings.setUseSettingsFromFile(settingsPanel.isUseSettingsFromFile());
        settings.setSettingsFilePath(settingsPanel.getSettingsFilePath());
    }

    @Override
    public void reset() {
        settingsPanel.reset(
                settings.getChecklistItems(),
                settings.isUseSettingsFromFile(),
                settings.getSettingsFilePath()
        );
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

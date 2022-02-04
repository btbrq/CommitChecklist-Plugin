package brq.intellij.plugins.commit.checklist.settings;

import brq.intellij.plugins.commit.checklist.settings.ui.JPanelSettings;

import javax.swing.*;

public class Configurable implements com.intellij.openapi.options.Configurable {

    private JPanelSettings settingsPanel;

    @Override
    public JComponent getPreferredFocusedComponent() {
        return settingsPanel;
    }

    @Override
    public JComponent createComponent() {
        settingsPanel = JPanelSettings.createAppSettingsPanel(Settings.getInstance().getChecklistItems());
        return settingsPanel;
    }

    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public void apply() {
        Settings settings = Settings.getInstance();
        settings.setChecklistItems(settingsPanel.getItems());
        settings.setUseSettingsFromFile(settingsPanel.isUseSettingsFromFile());
        settings.setSettingsFilePath(settingsPanel.getSettingsFilePath());
    }

    @Override
    public void reset() {
        Settings settings = Settings.getInstance();
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

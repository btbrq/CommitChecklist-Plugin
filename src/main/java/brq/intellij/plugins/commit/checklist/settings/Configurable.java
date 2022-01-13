package brq.intellij.plugins.commit.checklist.settings;

import brq.intellij.plugins.commit.checklist.settings.ui.JPanelSettings;

import javax.swing.*;
import java.util.List;

public class Configurable implements com.intellij.openapi.options.Configurable {

    private JPanelSettings settingsPanel;

    @Override
    public JComponent getPreferredFocusedComponent() {
        return settingsPanel;
    }

    @Override
    public JComponent createComponent() {
        settingsPanel = JPanelSettings.createAppSettingsPanel(Settings.getInstance().getChecklist());
        return settingsPanel;
    }

    @Override
    public boolean isModified() {
        return isModified(Settings.getInstance());
    }

    private boolean isModified(Settings settings) {
        return true;
    }

    @Override
    public void apply() {
        Settings settings = Settings.getInstance();
        settings.setChecklist(settingsPanel.getItems());
    }

    @Override
    public void reset() {
        List<String> checklist = Settings.getInstance().getChecklist();
//        settingsPanel.update
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

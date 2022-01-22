package brq.intellij.plugins.commit.checklist.settings;

import brq.intellij.plugins.commit.checklist.settings.ui.JPanelSettings;
import brq.intellij.plugins.commit.checklist.settings.ui.MessageItem;

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
    }

    @Override
    public void reset() {
        List<MessageItem> checklist = Settings.getInstance().getChecklistItems();
        settingsPanel.reset(checklist);
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

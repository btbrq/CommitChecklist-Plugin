package brq.intellij.plugins.commit.checklist.settings.ui;

import brq.intellij.plugins.commit.checklist.settings.MessageItem;
import brq.intellij.plugins.commit.checklist.settings.ProjectSettings;

import javax.swing.*;
import java.util.List;

public class JPanelSettings extends JPanel {
    private final SettingsTable table;
    private final JPanelFileSettingsArea settingsFileArea;

    private JPanelSettings(ProjectSettings settings) {
        table = SettingsTable.createTable(settings.getChecklistItems());
        settingsFileArea = JPanelFileSettingsArea.create(settings, table);
        add(settingsFileArea);
        add(table.createComponent());
    }

    public List<MessageItem> getChecklistItems() {
        return table.getChecklistItems();
    }

    public boolean isUseSettingsFromFile() {
        return settingsFileArea.isUseSettingsFromFile();
    }

    public boolean isApplyGlobal() {
        return settingsFileArea.isApplyGlobal();
    }

    public String getSettingsFilePath() {
        return settingsFileArea.getSettingsFilePath();
    }

    public void reset(List<MessageItem> checklist, boolean useSettingsFromFile, String settingsFilePath, boolean applyGlobal) {
        table.reset(checklist);
        settingsFileArea.setUseSettingsFromFile(useSettingsFromFile);
        settingsFileArea.setSettingsFilePath(settingsFilePath);
        settingsFileArea.setApplyGlobal(applyGlobal);
    }

    public static JPanelSettings createAppSettingsPanel(ProjectSettings settings) {
        JPanelSettings settingsPanel = new JPanelSettings(settings);
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.PAGE_AXIS));

        return settingsPanel;
    }

}

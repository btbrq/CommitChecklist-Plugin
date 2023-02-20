package brq.intellij.plugins.commit.checklist.settings.ui;

import brq.intellij.plugins.commit.checklist.settings.MessageItem;
import brq.intellij.plugins.commit.checklist.settings.ProjectSettings;
import brq.intellij.plugins.commit.checklist.settings.Settings;

import javax.swing.*;
import java.util.List;

import static brq.intellij.plugins.commit.checklist.settings.ui.SettingsTable.Type.*;

public class JPanelSettings extends JPanel {
    private final SettingsTable projectTable;
    private final SettingsTable globalTable;
    private final JPanelFileSettingsArea settingsFileArea;

    private JPanelSettings(ProjectSettings projectSettings, Settings appSettings) {
        projectTable = SettingsTable.createTable(PROJECT, projectSettings.getChecklistItems());
        globalTable = SettingsTable.createTable(GLOBAL, appSettings.getChecklistItems());
        SettingsTabbedPane tablePanel = SettingsTabbedPane.create(projectTable, globalTable);

        settingsFileArea = JPanelFileSettingsArea.create(projectSettings, tablePanel);
        add(settingsFileArea);
        add(tablePanel);
    }

    public List<MessageItem> getProjectChecklistItems() {
        return projectTable.getChecklistItems();
    }

    public List<MessageItem> getGlobalChecklistItems() {
        return globalTable.getChecklistItems();
    }

    public boolean isUseSettingsFromFile() {
        return settingsFileArea.isUseSettingsFromFile();
    }

    public String getSettingsFilePath() {
        return settingsFileArea.getSettingsFilePath();
    }

    public void reset(List<MessageItem> projectChecklist, List<MessageItem> appChecklist, boolean useSettingsFromFile, String settingsFilePath) {
        globalTable.reset(appChecklist);
        projectTable.reset(projectChecklist);
        settingsFileArea.setUseSettingsFromFile(useSettingsFromFile);
        settingsFileArea.setSettingsFilePath(settingsFilePath);
    }

    public static JPanelSettings createAppSettingsPanel(ProjectSettings projectSettings, Settings appSettings) {
        JPanelSettings settingsPanel = new JPanelSettings(projectSettings, appSettings);
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.PAGE_AXIS));

        return settingsPanel;
    }

}

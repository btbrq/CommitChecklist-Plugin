package brq.intellij.plugins.commit.checklist.settings.ui;

import com.intellij.ui.components.JBTabbedPane;

import java.util.Arrays;

import static brq.intellij.plugins.commit.checklist.settings.ui.SettingsTable.Type.PROJECT;

public class SettingsTabbedPane extends JBTabbedPane {
    private final SettingsTable[] tables;

    private SettingsTabbedPane(SettingsTable... tables) {
        this.tables = tables;
    }

    public SettingsTable getSelectedTable() {
        return tables[getSelectedIndex()];
    }

    public void setProjectTableEnabled(boolean value) {
        var projectTable = Arrays.stream(tables).filter(t -> t.getType() == PROJECT).findFirst();
        projectTable.ifPresent(e -> e.enabled(value));
    }

    public static SettingsTabbedPane create(SettingsTable... tables) {
        var panel = new SettingsTabbedPane(tables);
        for (SettingsTable table : tables) {
            panel.addTab(table.getTitle(), table.createComponent());
        }
        return panel;
    }
}

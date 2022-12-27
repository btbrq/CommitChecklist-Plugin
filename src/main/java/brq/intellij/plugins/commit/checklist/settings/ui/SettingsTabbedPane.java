package brq.intellij.plugins.commit.checklist.settings.ui;

import com.intellij.ui.components.JBTabbedPane;

import java.util.Arrays;

public class SettingsTabbedPane extends JBTabbedPane {
    private final SettingsTable[] tables;

    private SettingsTabbedPane(SettingsTable... tables) {
        this.tables = tables;
    }

    public SettingsTable getSelectedTable() {
        return tables[getSelectedIndex()];
    }

    public void setProjectTableEnabled(boolean value) {
        var projectTable = Arrays.stream(tables).filter(SettingsTable::isProjectTable).findFirst();
        projectTable.ifPresent(e -> e.enabled(value));
    }

    public static SettingsTabbedPane create(SettingsTable... tables) {
        var panel = new SettingsTabbedPane(tables);
        for (int i = 0; i < tables.length; i++) {
            SettingsTable table = tables[i];
            panel.addTab(table.getTitle(), table.createComponent());
            panel.setToolTipTextAt(i, table.getTooltip());
        }

        return panel;
    }
}

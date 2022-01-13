package brq.intellij.plugins.commit.checklist.settings.ui;

import javax.swing.*;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class JPanelSettings extends JPanel {
    private SettingsTable table;

    private JPanelSettings(List<String> checklist) {
        table = SettingsTable.createTable(checklist);
        add(table.createComponent());
    }

    public List<String> getItems() {
        int rowCount = table.getModel().getRowCount();
        return IntStream.range(0, rowCount).mapToObj(i -> table.getModel().getRowValue(i).getValue()).collect(toList());
    }

    public static JPanelSettings createAppSettingsPanel(List<String> checklist) {
        JPanelSettings settingsPanel = new JPanelSettings(checklist);
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.PAGE_AXIS));

        return settingsPanel;
    }

}

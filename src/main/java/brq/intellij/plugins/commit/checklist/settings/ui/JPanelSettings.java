package brq.intellij.plugins.commit.checklist.settings.ui;

import javax.swing.*;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class JPanelSettings extends JPanel {
    private final SettingsTable table;

    private JPanelSettings(List<MessageItem> checklist) {
        table = SettingsTable.createTable(checklist);
        add(JPanelFileSettingsArea.create(table));
        add(table.createComponent());
    }

    public List<MessageItem> getItems() {
        int rowCount = table.getModel().getRowCount();
        return IntStream.range(0, rowCount)
                .mapToObj(i -> table.getModel().getRowValue(i))
                .collect(toList());
    }

    public void reset(List<MessageItem> checklist) {
        table.reset(checklist);
    }

    public static JPanelSettings createAppSettingsPanel(List<MessageItem> checklist) {
        JPanelSettings settingsPanel = new JPanelSettings(checklist);
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.PAGE_AXIS));

        return settingsPanel;
    }

}

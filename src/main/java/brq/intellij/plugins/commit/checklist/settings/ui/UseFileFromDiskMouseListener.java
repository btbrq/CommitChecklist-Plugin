package brq.intellij.plugins.commit.checklist.settings.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UseFileFromDiskMouseListener implements MouseListener {
    private final JPanelFileSettingsArea jPanelFileSettingsArea;

    public UseFileFromDiskMouseListener(JPanelFileSettingsArea jPanelFileSettingsArea) {
        this.jPanelFileSettingsArea = jPanelFileSettingsArea;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        jPanelFileSettingsArea.launchFileOpener();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

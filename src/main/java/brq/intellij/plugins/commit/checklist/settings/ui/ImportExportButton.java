package brq.intellij.plugins.commit.checklist.settings.ui;

import com.intellij.icons.AllIcons;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ImportExportButton extends JLabel {
    public ImportExportButton(Icon icon) {
        super(icon);
    }

    public void displayImportExportPopup() {
        ImportExportPopup.createPopup(this);
    }

    public static ImportExportButton create() {
        ImportExportButton label = new ImportExportButton(AllIcons.General.GearPlain);
        label.addMouseListener(new ImportExportButtonMouseListener(label));
        return label;
    }

    private static class ImportExportButtonMouseListener implements MouseListener {
        private final ImportExportButton importExportButton;

        public ImportExportButtonMouseListener(ImportExportButton importExportButton) {
            this.importExportButton = importExportButton;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            importExportButton.displayImportExportPopup();
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
}

package brq.intellij.plugins.commit.checklist.settings.ui;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

import static brq.intellij.plugins.commit.checklist.common.Constants.*;

public class ImportErrorDialog extends DialogWrapper {
    private final String message;
    private JPanel dialogPanel;

    public ImportErrorDialog(String message) {
        super(true);
        this.message = message;
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        dialogPanel = new JPanel();
        dialogPanel.setLayout(new BorderLayout());
        setDimensions(dialogPanel);
        String text = "<html>" + message + "</html>";
        JLabel label = new JLabel(text);
        dialogPanel.add(label, BorderLayout.CENTER);
        return dialogPanel;
    }

    @Override
    protected Action @NotNull [] createActions() {
        return new Action[]{getOKAction()};
    }

    private void setDimensions(JPanel dialog) {
        dialog.setMinimumSize(new Dimension(DIALOG_MIN_WIDTH, ERROR_DIALOG_MIN_HEIGHT));
        dialog.setMaximumSize(new Dimension(desktopWidth(), Integer.MAX_VALUE));
        dialog.setPreferredSize(new Dimension(DIALOG_MIN_WIDTH, ERROR_DIALOG_MIN_HEIGHT));
    }

    public static int desktopWidth() {
        return (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    }

    @Override
    public @Nullable JComponent getPreferredFocusedComponent() {
        return dialogPanel;
    }

}

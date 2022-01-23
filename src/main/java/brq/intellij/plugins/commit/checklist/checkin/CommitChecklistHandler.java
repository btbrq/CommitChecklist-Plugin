package brq.intellij.plugins.commit.checklist.checkin;

import brq.intellij.plugins.commit.checklist.settings.Settings;
import brq.intellij.plugins.commit.checklist.settings.ui.MessageItem;
import com.intellij.find.impl.FindInProjectUtil;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.vcs.CheckinProjectPanel;
import com.intellij.openapi.vcs.checkin.CheckinHandler;
import com.intellij.openapi.vcs.ui.RefreshableOnComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class CommitChecklistHandler extends CheckinHandler {
    private final CheckinProjectPanel panel;

    public CommitChecklistHandler(CheckinProjectPanel panel) {
        this.panel = panel;
    }

    @Override
    public @Nullable RefreshableOnComponent getBeforeCheckinConfigurationPanel() {
        return new CommitChecklistCheckbox();
    }

    @Override
    public ReturnResult beforeCheckin() {
        if (!CommitChecklistCheckbox.SELECTED) return super.beforeCheckin();
        List<String> checklist = getChecklist(panel.getFiles());
        if (!checklist.isEmpty()) {
            CommitChecklistDialog dialog = new CommitChecklistDialog(checklist);
            boolean isCommitExit = dialog.showAndGet();
            Settings.getInstance().setDimensions(dialog.getWidth(), dialog.getHeight());
            return isCommitExit ? ReturnResult.COMMIT : ReturnResult.CANCEL;
        }
        return ReturnResult.COMMIT;
    }

    @NotNull
    private List<String> getChecklist(Collection<File> files) {
        return Settings.getInstance().getChecklistItems().stream()
                .filter(c -> c.getValue() != null && !c.getValue().isBlank())
                .filter(c -> isFileMatchingFileMask(files, c.getFileMask()))
                .map(MessageItem::getValue)
                .collect(toList());
    }

    private boolean isFileMatchingFileMask(Collection<File> files, String fileMask) {
        try {
            Condition<CharSequence> fileMaskCondition = FindInProjectUtil.createFileMaskCondition(fileMask);
            return files.stream().anyMatch(f -> fileMaskCondition.value(f.getName()));
        } catch (Exception e) {
            return false;
        }
    }

}

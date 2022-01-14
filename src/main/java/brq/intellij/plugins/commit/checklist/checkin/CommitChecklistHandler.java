package brq.intellij.plugins.commit.checklist.checkin;

import brq.intellij.plugins.commit.checklist.settings.Settings;
import com.intellij.openapi.vcs.CheckinProjectPanel;
import com.intellij.openapi.vcs.checkin.CheckinHandler;
import com.intellij.openapi.vcs.ui.RefreshableOnComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

        List<String> checklist = getChecklist();
        if (!checklist.isEmpty()) {
            CommitChecklistDialog dialog = new CommitChecklistDialog(checklist);
            boolean isCommitExit = dialog.showAndGet();
            Settings.getInstance().setDimensions(dialog.getWidth(), dialog.getHeight());
            return isCommitExit ? ReturnResult.COMMIT : ReturnResult.CANCEL;
        }
        return ReturnResult.COMMIT;
    }

    @NotNull
    private List<String> getChecklist() {
        return Settings.getInstance().getChecklist().stream().filter(c -> c != null && !c.isBlank()).collect(toList());
    }

}

package brq.intellij.plugins.commit.checklist.checkin;

import brq.intellij.plugins.commit.checklist.settings.Settings;
import com.intellij.openapi.vcs.CheckinProjectPanel;
import com.intellij.openapi.vcs.checkin.CheckinHandler;
import com.intellij.openapi.vcs.ui.RefreshableOnComponent;
import org.jetbrains.annotations.Nullable;

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

        CommitChecklistDialog dialog = new CommitChecklistDialog(Settings.getInstance().getChecklist());
        boolean isCommitExit = dialog.showAndGet();

        return isCommitExit ? ReturnResult.COMMIT : ReturnResult.CANCEL;
    }

}

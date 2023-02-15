package brq.intellij.plugins.commit.checklist.checkin;

import brq.intellij.plugins.commit.checklist.settings.ImportSettingsResponse;
import brq.intellij.plugins.commit.checklist.settings.ProjectSettings;
import brq.intellij.plugins.commit.checklist.settings.MessageItem;
import brq.intellij.plugins.commit.checklist.settings.Settings;
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

import static brq.intellij.plugins.commit.checklist.settings.SettingsImporter.importChecklist;
import static java.util.stream.Collectors.toList;

public class CommitChecklistHandler extends CheckinHandler {
    private final CheckinProjectPanel panel;
    private final ProjectSettings settings;

    public CommitChecklistHandler(CheckinProjectPanel panel) {
        this.panel = panel;
        this.settings = ProjectSettings.getInstance(panel.getProject());
    }

    @Override
    public @Nullable RefreshableOnComponent getBeforeCheckinConfigurationPanel() {
        return new CommitChecklistCheckbox();
    }

    @Override
    public ReturnResult beforeCheckin() {
        if (!CommitChecklistCheckbox.SELECTED) return super.beforeCheckin();

        List<String> checklist = getChecklist();
        if (checklist == null) return ReturnResult.CANCEL;

        if (!checklist.isEmpty()) {
            CommitChecklistDialog dialog = new CommitChecklistDialog(settings, checklist);
            boolean isCommitExit = dialog.showAndGet();
            settings.setDimensions(dialog.getWidth(), dialog.getHeight());
            return isCommitExit ? ReturnResult.COMMIT : ReturnResult.CANCEL;
        }
        return ReturnResult.COMMIT;
    }

    @Nullable
    private List<String> getChecklist() {
        List<String> checklist;

        if (settings.isUseSettingsFromFile()) {
            ImportSettingsResponse response = importChecklist(settings.getSettingsFilePath());
            if (response.hasErrors()) {
                return null;
            } else {
                checklist = getMatchedChecklist(response.getChecklist(), panel.getFiles());
            }

        } else {
            checklist = getUserDefinedChecklist(panel.getFiles());
        }

        checklist.addAll(getGlobalChecklist());
        return checklist;
    }

    private List<String> getGlobalChecklist() {
        return getMatchedChecklist(Settings.getInstance().getChecklistItems(), panel.getFiles());
    }

    @NotNull
    private List<String> getUserDefinedChecklist(Collection<File> files) {
        return getMatchedChecklist(settings.getChecklistItems(), files);
    }

    private List<String> getMatchedChecklist(List<MessageItem> checklistItems, Collection<File> files) {
        return checklistItems.stream()
                .filter(c -> c.getValue() != null && !c.getValue().isBlank())
                .filter(c -> isFileMatchingFileMask(files, c.getFileMask()))
                .map(MessageItem::getValue)
                .collect(toList());
    }

    private boolean isFileMatchingFileMask(Collection<File> files, String fileMask) {
        try {
            Condition<CharSequence> fileMaskCondition = FindInProjectUtil.createFileMaskCondition(fileMask);
            return files.stream().anyMatch(f -> fileMaskCondition.value(f.getPath()));
        } catch (Exception e) {
            return false;
        }
    }

}

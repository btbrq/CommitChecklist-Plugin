package brq.intellij.plugins.commit.checklist.settings;

import java.util.List;

public class ImportSettingsResponse {
    private List<MessageItem> checklist;
    private boolean hasErrors;

    public List<MessageItem> getChecklist() {
        return checklist;
    }

    public void setChecklist(List<MessageItem> checklist) {
        this.checklist = checklist;
    }

    public boolean hasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }
}

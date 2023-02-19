package brq.intellij.plugins.commit.checklist.common;

import com.intellij.DynamicBundle;

public class CommitChecklistBundle extends DynamicBundle {
    private static final String BUNDLE = "messages.CommitChecklistBundle";
    private static final CommitChecklistBundle INSTANCE = new CommitChecklistBundle();

    public static String message(String key) {
        return INSTANCE.containsKey(key) ? INSTANCE.getMessage(key) : "";
    }

    public CommitChecklistBundle() {
        super(BUNDLE);
    }
}

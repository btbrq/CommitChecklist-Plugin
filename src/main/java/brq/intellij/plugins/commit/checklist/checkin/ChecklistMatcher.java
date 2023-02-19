package brq.intellij.plugins.commit.checklist.checkin;

import brq.intellij.plugins.commit.checklist.settings.MessageItem;
import com.intellij.find.impl.FindInProjectUtil;
import com.intellij.openapi.util.Condition;

import java.io.File;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ChecklistMatcher {
    public static List<String> getMatchedChecklist(List<MessageItem> checklistItems, Collection<File> files) {
        return checklistItems.stream()
                .filter(c -> c.getValue() != null && !c.getValue().isBlank())
                .filter(c -> isFileMatchingFileMask(files, c.getFileMask()))
                .map(MessageItem::getValue)
                .collect(toList());
    }

    private static boolean isFileMatchingFileMask(Collection<File> files, String fileMask) {
        try {
            Condition<CharSequence> fileMaskCondition = FindInProjectUtil.createFileMaskCondition(fileMask);
            return files.stream().anyMatch(f -> fileMaskCondition.value(f.getPath()));
        } catch (Exception e) {
            return false;
        }
    }
}

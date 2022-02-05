package brq.intellij.plugins.commit.checklist.settings;

public class MessageItem {
    private String value;
    private String fileMask;

    public MessageItem() {
        value = "";
        fileMask = "*";
    }

    public MessageItem(String value) {
        this.value = value;
        fileMask = "*";
    }

    public MessageItem(String value, String fileMask) {
        this.value = value;
        if (fileMask == null || fileMask.isBlank()) {
            this.fileMask = "*";
        } else {
            this.fileMask = fileMask;
        }
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFileMask() {
        return fileMask;
    }

    public void setFileMask(String fileMask) {
        if (fileMask == null || fileMask.isBlank()) {
            this.fileMask = "*";
        } else {
            this.fileMask = fileMask;
        }
    }
}

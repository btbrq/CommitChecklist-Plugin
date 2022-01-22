package brq.intellij.plugins.commit.checklist.settings.ui;

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
        this.fileMask = fileMask;
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
        this.fileMask = fileMask;
    }
}

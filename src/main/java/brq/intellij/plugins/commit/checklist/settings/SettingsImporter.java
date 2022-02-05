package brq.intellij.plugins.commit.checklist.settings;

import brq.intellij.plugins.commit.checklist.settings.ui.ImportErrorDialog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellij.openapi.ui.DialogWrapper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class SettingsImporter {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static ImportSettingsResponse importChecklist(String path) {
        ImportSettingsResponse result = new ImportSettingsResponse();
        List<MessageItem> checklist = Collections.emptyList();
        try {
            if (path != null && !path.isBlank()) {
                String content = Files.readString(Path.of(path));
                checklist = mapper.readValue(content, mapper.getTypeFactory().constructCollectionType(List.class, MessageItem.class));
            }
        } catch (JsonProcessingException e) {
            result.setHasErrors(true);
            DialogWrapper dialog = new ImportErrorDialog("Error parsing file: " + path + ". Check file syntax.");
            dialog.show();
        } catch (FileNotFoundException e) {
            result.setHasErrors(true);
            DialogWrapper dialog = new ImportErrorDialog("File not found: " + path + ".");
            dialog.show();
        } catch (IOException e) {
            result.setHasErrors(true);
            DialogWrapper dialog = new ImportErrorDialog("Unknown exception occurred when loading checklist file: " + path + ".");
            dialog.show();
        }
        result.setChecklist(checklist);
        return result;
    }
}

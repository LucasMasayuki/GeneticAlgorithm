package Utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {
    private List<String> lines = new ArrayList<>();
    private String fileName = "";

    public void write(String text) {
        this.lines.add(text);
    }

    public void save() {
        String name = fileName + ".txt";
        Path file = Paths.get(name);

        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

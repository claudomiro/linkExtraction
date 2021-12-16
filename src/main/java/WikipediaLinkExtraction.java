import lombok.extern.java.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log
public class WikipediaLinkExtraction {

    public static void main(String[] args) throws IOException {
        Path inputPath = Paths.get(args[0]);
        log.info("Opening path '" + inputPath + "'");

        new WikipediaLinkExtraction(inputPath);
    }
    private static Pattern PATTERN_TITLE = Pattern.compile(".*<title>(.+)</title>.*");

    private String currentTitle = "";

    public WikipediaLinkExtraction(Path inputPath) throws IOException {
        run(inputPath);
    }

    private void run(Path inputPath) throws FileNotFoundException {
        FileInputStream inputFIS = new FileInputStream(inputPath.toFile());
        Scanner sc = new Scanner(inputFIS, "UTF-8");
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            Matcher titleMatcher = PATTERN_TITLE.matcher(line);
            if(titleMatcher.matches()) {
                currentTitle = titleMatcher.group(1);
                log.info("title line: '" + currentTitle + "'");
            }

        }
    }
}

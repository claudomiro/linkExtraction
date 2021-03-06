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
    private static Pattern PATTERN_LINK = Pattern.compile("<<([^>]+)>>");

    private String currentTitle = "";

    public WikipediaLinkExtraction(Path inputPath) throws IOException {
        run(inputPath);
    }

    private void run(Path inputPath) throws FileNotFoundException {
        FileInputStream inputFIS = new FileInputStream(inputPath.toFile());
        Scanner sc = new Scanner(inputFIS, "UTF-8");
        while (sc.hasNextLine()) {
            // substitute chars to simplify the algorithm
            String replacedLine = sc.nextLine().replace('[', '<').replace(']', '>');
            Matcher titleMatcher = PATTERN_TITLE.matcher(replacedLine);
            Matcher linkMatcher = PATTERN_LINK.matcher(replacedLine);
            if(titleMatcher.matches()) {
                currentTitle = titleMatcher.group(1);
            }
            while (linkMatcher.find()) {
                int begin = linkMatcher.start(1);
                int end = linkMatcher.end(1);
                String linkName = replacedLine.substring(begin, end);
                int position = linkName.indexOf('|');
                if(position > -1) {
                    linkName = linkName.substring(position+1);
                }
                System.out.println(currentTitle + '\t' + linkName);

            }
        }
    }
}

import lombok.extern.java.Log;

import java.io.File;

@Log
public class WikipediaLinkExtraction {
    public static void main(String[] args) {
        log.info("Opening file '" + args[0] + "'");
        File inputFile = new File(args[0]);
        System.out.println(inputFile.exists());
    }
}

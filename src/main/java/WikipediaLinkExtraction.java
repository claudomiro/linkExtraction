import lombok.extern.java.Log;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@Log
public class WikipediaLinkExtraction {
    public static void main(String[] args) {
        Path inputPath = Paths.get(args[0]);
        log.info("Opening path '" + inputPath + "'");
        log.info("CAN READ:" + inputPath.toFile().canRead());
    }
}

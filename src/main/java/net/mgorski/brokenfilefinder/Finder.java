package net.mgorski.brokenfilefinder;

import net.mgorski.brokenfilefinder.dto.FileDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Marcin Górski <mg@mgorski.net>
 */
public class Finder {
    private static final Logger L = LoggerFactory.getLogger(Finder.class);

    public static void main(String[] args) {


        String directoryRecovered = "/home/ski/pics-broken-single/";
        String directoryBackup = "/home/ski/pics-backup";
        String directoryResult = "/home/ski/pics-result";

        Integer offset = 24;

        // S1
        DirectoryScanner scanner = new DirectoryScanner();

        List<FileDto> recoveredFiles = scanner.scan(directoryRecovered);
        List<FileDto> backedupFiles = scanner.scan(directoryBackup);

        L.info("Found {} recovered and {} backed-up files.", recoveredFiles.size(), backedupFiles.size());

        // S2
        FileProcessor processor = new FileProcessor();
        for (FileDto backedupFile : backedupFiles) {
            processor.process(backedupFile, offset);
        }
        for (FileDto recoveredFile : recoveredFiles) {
            processor.process(recoveredFile, offset);
        }

        // S3 Find matches
        FileMatcher matcher = new FileMatcher();
        matcher.findMatches(recoveredFiles, backedupFiles);


    }
}

package net.mgorski.brokenfilefinder;

import net.mgorski.brokenfilefinder.dto.FileDto;
import net.mgorski.brokenfilefinder.dto.MatchDto;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Marcin Górski <mg@mgorski.net>
 */
public class Finder {
    private static final Logger L = LoggerFactory.getLogger(Finder.class);

    public static void main(String[] args) {


        String directoryRecovered = "/home/ski/pics-broken/"; // good structure, broken files
        String directoryBackup = "/home/ski/pics-backup";     // good files, bad structure
        String directoryResult = "/home/ski/pics-result";     // merge result

        Double matchAbove = 10.;
        boolean copyOver = true;

        Integer offset = 500;

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
        List<MatchDto> matches = matcher.findMatches(recoveredFiles, backedupFiles, matchAbove);

        // S4 Copy matches
        if (copyOver) {
            for (MatchDto match : matches) {

                String recoveredName = match.getRecovered().getFilename();
                String recoveredPath = match.getRecovered().getPath();

                int index = recoveredPath.indexOf(directoryRecovered);
                String relativePath = recoveredPath.substring(index + directoryRecovered.length(), recoveredPath.length());

                String newFileLocation = directoryResult + File.separator + relativePath;

                File result = new File(newFileLocation, recoveredName);
                File input = new File(match.getBackedUp().getPath(), match.getBackedUp().getFilename());

                try {
                    L.info("[{}] Copy {} ---> {} ", match.getMatch(), input.getAbsolutePath(), result.getAbsolutePath());
                    FileUtils.copyFile(input, result);
                } catch (IOException e) {
                    L.error("Error copying {} ---> {}, {}", input.getAbsolutePath(), result.getAbsolutePath(), e.getMessage());
                }
            }

        }

        L.info("Done. {} matches found out of {} backed up and {} recovered files",
                matches.size(), backedupFiles.size(), recoveredFiles.size());


    }
}

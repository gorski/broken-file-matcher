package net.mgorski.brokenfilefinder;

import net.mgorski.brokenfilefinder.dto.FileDto;
import net.mgorski.brokenfilefinder.dto.MatchDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Marcin Górski <mg@mgorski.net>
 */
public class FileMatcher {

    private final FileComparator fc = new FileComparator();

    private static final Logger L = LoggerFactory.getLogger(FileMatcher.class);

    public List<MatchDto> findMatches(List<FileDto> recoveredFiles, List<FileDto> backedupFiles, Double matchAbove) {

        List<MatchDto> matches = new LinkedList<>();

        int found = 0;
        int recovered = 0;
        for (FileDto recoveredFile : recoveredFiles) {

            List<MatchDto> matchesForRecovered = new LinkedList<>();
            String recoveredPath = recoveredFile.getPath() + File.separator + recoveredFile.getFilename();
            for (FileDto backedupFile : backedupFiles) {

//
                double match = calculateMatch(recoveredFile, backedupFile);
//                L.debug("{} \t{} <> {}", match, recoveredFile.getFullPath(), backedupFile.getFullPath());
                L.debug("\nA: {}\nB: {}\nM: {}", recoveredFile.getFullPath(), backedupFile.getFullPath(), match);
                if (match >= matchAbove) {
                    L.debug("YES");
                    matchesForRecovered.add(new MatchDto(backedupFile, recoveredFile, match));
                }
                recovered++;
            }

            if (matchesForRecovered.size() == 0) {
                L.info("** {}\t{}\t{}\t{}\t{}", "NO MATCH", "-", "0", recoveredPath, "-");
            } else if (matchesForRecovered.size() == 1) {
                MatchDto matchDto = matchesForRecovered.get(0);
                matches.add(matchDto);
                L.info("** {}\t{}\t{}\t{}\t{}", "SINGLE", matchDto.getMatch(), "1", recoveredPath, matchDto.getBackedUp().getFilename());
                found++;
            } else {
                MatchDto winningMatch = matchesForRecovered.get(0);
                for (int i = 1; i < matchesForRecovered.size(); i++) {
                    L.debug("** {}", matchesForRecovered.get(i).getMatch());
                    if (matchesForRecovered.get(i).getMatch() > winningMatch.getMatch()) {
                        winningMatch = matchesForRecovered.get(i);
                    }
                }
                matches.add(winningMatch);
                L.info("** {}\t{}\t{}\t{}\t{}", "MULTI", winningMatch.getMatch(), matchesForRecovered.size(), recoveredPath, winningMatch.getBackedUp().getFilename());
                found++;
            }
            recovered++;
        }

        L.info("** COMPARISON DONE ** {} / {} have match above {} %", found, recovered, matchAbove);
        return matches;
    }

    private double calculateMatch(FileDto recoveredFile, FileDto backedupFile) {
        return fc.compare(backedupFile, recoveredFile);
    }
}

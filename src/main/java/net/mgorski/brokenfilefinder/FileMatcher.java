package net.mgorski.brokenfilefinder;

import net.mgorski.brokenfilefinder.dto.FileDto;

import java.util.List;

/**
 * @author Marcin Górski <mg@mgorski.net>
 */
public class FileMatcher {

    public void findMatches(List<FileDto> recoveredFiles, List<FileDto> backedupFiles) {

        for (FileDto recoveredFile : recoveredFiles) {
            for (FileDto backedupFile : backedupFiles) {

                double match = getMatchBasedOnStartupMarker(recoveredFile, backedupFile);


            }
        }


    }

    private double getMatchBasedOnStartupMarker(FileDto recoveredFile, FileDto backedupFile) {

//        List<Byte> aParts = recoveredFile.getPartsFromStartingMarker();
//        List<Byte> bParts = backedupFile.getPartsFromStartingMarker();

//        for (Byte bPart : bParts) {
//
//        }


        return 0d;

    }
}

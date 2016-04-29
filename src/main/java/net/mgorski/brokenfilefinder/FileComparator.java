package net.mgorski.brokenfilefinder;

import net.mgorski.brokenfilefinder.dto.DescriptorDto;
import net.mgorski.brokenfilefinder.dto.FileDto;
import net.mgorski.brokenfilefinder.dto.ResultDto;

/**
 * @author Marcin Górski <mg@mgorski.net>
 */
public class FileComparator {
    public double compare(FileDto fine, FileDto broken1) {

        ResultDto match = new ResultDto(0., 0.);

        DescriptorDto d1 = fine.getDescriptor();
        DescriptorDto d2 = broken1.getDescriptor();

        compare(d1.getBytesFromStart(), d2.getBytesFromStart(), match);
        compare(d1.getBytesFromFFD8(), d2.getBytesFromFFD8(), match);
        compare(d1.getBytesFromEndOffset(), d2.getBytesFromEndOffset(), match);
        compare(d1.getBytesFromEnd(), d2.getBytesFromEnd(), match);
        compare(d1.getBytesFromEndOffset(), d2.getBytesFromEndOffset(), match);


        return  match.getTotal() == 0. ? 0. :match.getMatch() / match.getTotal() * 100;
    }

    private void compare(byte[] first, byte[] second, ResultDto match) {

        if (first == null || second == null){
            return;
        }


        for (int i = 0; i < first.length && i < second.length; i++) {
            if (first[i] == second[i]) {
                match.increaseSuccess();
            } else {
                match.increaseFail();
            }
        }


    }

}

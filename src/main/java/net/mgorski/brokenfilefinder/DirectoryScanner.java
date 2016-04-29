package net.mgorski.brokenfilefinder;

import net.mgorski.brokenfilefinder.dto.FileDto;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Marcin Górski <mg@mgorski.net>
 */
public class DirectoryScanner {

    private static final Logger L = LoggerFactory.getLogger(DirectoryScanner.class);

    public List<FileDto> scan(String dir) {

        Collection<File> files = FileUtils.listFiles(
                new File(dir),
                new RegexFileFilter("^(.*?)"),
                DirectoryFileFilter.DIRECTORY
        );

        List<FileDto> result = new LinkedList<>();

        for (File file : files) {
            L.debug("Found file: {}", file);
            FileDto dto = new FileDto();
            dto.setPath(file.getParent());
            dto.setFilename(file.getName());
            result.add(dto);
        }


        return result;

    }
}

package net.mgorski.brokenfilefinder;

import net.mgorski.brokenfilefinder.dto.FileDto;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Marcin Górski <mg@mgorski.net>
 */
public class TestUtils {
    final static FileProcessor fp = new FileProcessor();

    public static FileDto loadFile(String s, int i) {
        URL url = TestUtils.class.getResource(s);
        File testFile = new File(url.getFile());
        Path path = Paths.get(testFile.getPath());

        byte[] data = new byte[0];
        try {
            data = Files.readAllBytes(path);

            FileDto dto = new FileDto();

            fp.extract(dto, data, i);
            return dto;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

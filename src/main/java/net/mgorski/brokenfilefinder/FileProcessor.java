package net.mgorski.brokenfilefinder;

import net.mgorski.brokenfilefinder.dto.FileDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Marcin Górski <mg@mgorski.net>
 */
public class FileProcessor {

    private static final Logger L = LoggerFactory.getLogger(FileProcessor.class);

    public void process(FileDto file, Integer offset) {
        L.info("Processing file : {}", file);

        Path path = Paths.get(file.getPath());
        try {
            byte[] data = Files.readAllBytes(path);
            file.setSize(data.length);
            extract(file, data, offset);

        } catch (IOException e) {
            L.error("Error reading file {} : {}", file, e.getMessage());
        }


    }

    public void extract(FileDto file, byte[] data, Integer offset) {

        int len = data.length;


        // PROBE_SIZE from start marker
        byte ff = (byte) Integer.parseInt("FF", 16);
        byte da = (byte) Integer.parseInt("DA", 16);

        System.out.println();

        // lookup FFDA
        Integer startingMarker = null;
        for (int i = 1; i < len; i++) {
            if (data[i - 1] == ff && data[i] == da) {
                startingMarker = i;
                break;
            }
        }

        L.trace("Starting marker: {}", startingMarker);
        if (startingMarker != null) {

            int index = 0;
            for (int i = startingMarker; i <= len - 1 && index < Constants.PROBE_SIZE; i += offset) {
                file.getDescriptor().getBytesFromFFD8()[index++] = data[i];
            }
        } else {
            L.warn("No starting marker for {}", file);
        }

        // PROBE_SIZE first bytes
        for (int i = 0, index = 0; i < len && index < Constants.PROBE_SIZE; i += offset, index++) {
            file.getDescriptor().getBytesFromStart()[index] = data[i];
        }

        // PROBE_SIZE from offset
        for (int i = offset, index = 0; i < len && index < Constants.PROBE_SIZE; i += offset, index++) {
            file.getDescriptor().getBytesFromOffset()[index] = data[i];
        }

        // PROBE_SIZE last bytes
        for (int i = len - 1, index = 0; i > 0 && index < Constants.PROBE_SIZE; i -= offset, index++) {
            file.getDescriptor().getBytesFromEnd()[index] = data[i];
        }

        // PROBE_SIZE last bytes
        for (int i = len - 1, index = 0; i > 0 && index < Constants.PROBE_SIZE; i -= offset, index++) {
            file.getDescriptor().getBytesFromEndOffset()[index] = data[i];
        }

    }
}

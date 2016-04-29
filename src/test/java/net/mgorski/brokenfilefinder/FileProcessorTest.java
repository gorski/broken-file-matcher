package net.mgorski.brokenfilefinder;

import net.mgorski.brokenfilefinder.dto.DescriptorDto;
import net.mgorski.brokenfilefinder.dto.FileDto;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertThat;

/**
 * @author Marcin Górski <mg@mgorski.net>
 */
public class FileProcessorTest {

    private static final int OFFSET = 200;

    @Test
    public void testExtraction() throws IOException {

        FileDto fine = TestUtils.loadFile("/example1-ok.JPG", OFFSET);
        FileDto broken1 = TestUtils.loadFile("/example1-broken.jpg", OFFSET);
        FileDto broken2 = TestUtils.loadFile("/example1-broken1.JPG", OFFSET);

        DescriptorDto descriptor1 = broken1.getDescriptor();
        assertThat(descriptor1, CoreMatchers.notNullValue());
        assertThat(descriptor1.getBytesFromStart()[0], CoreMatchers.notNullValue());

//        System.out.println(descriptor1);
//        System.out.println(broken2.getDescriptor());
//        System.out.println(fine.getDescriptor());

        assertThat(broken1.getDescriptor().getBytesFromStart()[0], CoreMatchers.equalTo(broken2.getDescriptor().getBytesFromStart()[0]));
        assertThat(broken1.getDescriptor().getBytesFromEnd()[0], CoreMatchers.equalTo(broken2.getDescriptor().getBytesFromEnd()[0]));
        assertThat(fine.getDescriptor().getBytesFromEnd()[0], CoreMatchers.equalTo(broken2.getDescriptor().getBytesFromEnd()[0]));
    }
}

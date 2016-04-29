package net.mgorski.brokenfilefinder;

import net.mgorski.brokenfilefinder.dto.FileDto;
import org.junit.Test;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

/**
 * @author Marcin Górski <mg@mgorski.net>
 */
public class FileComparatorTest {

    public static final double THRESHOLD = 80.;
    private static final int OFFSET = 200;
    private final FileComparator comparator = new FileComparator();

    @Test
    public void testMatcherDetectsCorrectSimilarity() {

        FileDto fine = TestUtils.loadFile("/example1-ok.JPG", OFFSET);
        FileDto broken1 = TestUtils.loadFile("/example1-broken.jpg", OFFSET);
        FileDto broken2 = TestUtils.loadFile("/example1-broken1.JPG", OFFSET);

        assertThat("Below threshold", comparator.compare(fine, broken1), greaterThan(THRESHOLD));
        assertThat("Below threshold", comparator.compare(broken2, broken1), greaterThan(THRESHOLD));
        assertThat("Below threshold", comparator.compare(broken2, fine), greaterThan(THRESHOLD));

    }
}

package busroute.integration;

import busroute.service.FileReaderService;
import org.apache.commons.io.LineIterator;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertThat;

public class FileReaderServiceTest {

    private FileReaderService service;
    private static final String CORRECT_LOCATION = "./src/test/resources/data";

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldReadFile() throws Exception {
        service = new FileReaderService(CORRECT_LOCATION);
        LineIterator fileLines = service.getFileLines();

        int linesInTextFile = 0;
        try {
            while (fileLines.hasNext()) {
                linesInTextFile++;
                fileLines.nextLine();
            }
        } finally {
            LineIterator.closeQuietly(fileLines);
        }

        assertThat(linesInTextFile, Matchers.is(4));
    }
}
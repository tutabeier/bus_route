package busroute.integration;

import busroute.service.FileReaderService;
import busroute.service.RouteSearcherService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RouteSearcherServiceTest {

    private FileReaderService fileReaderService;
    private RouteSearcherService searcherService;
    private static final String FILE_LOCATION = "./src/test/resources/data";

    @Before
    public void setUp() {
        fileReaderService = new FileReaderService(FILE_LOCATION);
        searcherService = new RouteSearcherService(fileReaderService);
    }

    @Test
    public void shouldReturnTrueIfThereIsARouteBetweenZeroAndFour() throws Exception {
        boolean hasRouteBetween = searcherService.hasRouteBetween(0, 4);

        assertTrue(hasRouteBetween);
    }

    @Test
    public void shoulfReturnFalseSinceThereIsNoRouteBetweenZeroAndFive() throws Exception {
        boolean hasRouteBetween = searcherService.hasRouteBetween(0, 5);

        assertFalse(hasRouteBetween);
    }

    @Test
    public void shouldReturnFalseIfDeparturesDoesntExist() throws Exception {
        boolean hasRouteBetween = searcherService.hasRouteBetween(8, 5);

        assertFalse(hasRouteBetween);
    }

    @Test
    public void shouldReturnFalseIfArrivalDoesntExist() throws Exception {
        boolean hasRouteBetween = searcherService.hasRouteBetween(0, 8);

        assertFalse(hasRouteBetween);
    }
}
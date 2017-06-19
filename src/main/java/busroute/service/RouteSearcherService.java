package busroute.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.LineIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.OptionalInt;

import static java.util.Arrays.asList;
import static java.util.stream.IntStream.of;
import static java.util.stream.IntStream.range;
import static org.apache.commons.io.LineIterator.closeQuietly;

@Slf4j
@Service
public class RouteSearcherService {
    private FileReaderService fileReaderService;
    private int[] stationsList;
    private int depsId;
    private int arrsId;
    private int FIRST_STATION_ID_POSITION = 1;

    @Autowired
    public RouteSearcherService(FileReaderService fileReaderService) {
        this.fileReaderService = fileReaderService;
    }

    public boolean hasRouteBetween(int depsId, int arrsId) {
        this.depsId = depsId;
        this.arrsId = arrsId;
        LineIterator lineIterator = fileReaderService.getFileLines();

        boolean directBusRoute = checkIfRouteExist(lineIterator);

        return directBusRoute;
    }

    private boolean checkIfRouteExist(LineIterator lineIterator) {
        while (lineIterator.hasNext()) {
            String line = lineIterator.nextLine();
            this.stationsList = asList(line.split(" ")).stream()
                    .mapToInt(Integer::parseInt).toArray();

            OptionalInt hasDeparture = hasDepartureRoute();

            if (hasDeparture.isPresent()) {
                OptionalInt arrivePosition = hasArrivelRoute(hasDeparture.getAsInt());

                if (arrivePosition.isPresent()) {
                    return true;
                }
            }
        }

        closeQuietly(lineIterator);

        return false;
    }

    private OptionalInt hasDepartureRoute() {
        return hasElementInArray(FIRST_STATION_ID_POSITION, this.depsId);
    }

    private OptionalInt hasArrivelRoute(int departureStationIndex) {
        return hasElementInArray(departureStationIndex + 1, this.arrsId);
    }

    private OptionalInt hasElementInArray(int startingIndex, int element) {
        return range(startingIndex, this.stationsList.length)
                .filter(i -> element == this.stationsList[i])
                .findFirst();
    }
}

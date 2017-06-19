package busroute.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
@Service
public class FileReaderService {

    private String fileLocation;
    private FileInputStream inputStream;
    private LineIterator lineIterator;

    public FileReaderService(@Value("${fileLocation}") String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public LineIterator getFileLines() {
        try {
            log.info("Reading file from {}", this.fileLocation);
            this.inputStream = new FileInputStream(this.fileLocation);
            this.lineIterator = IOUtils.lineIterator(inputStream, "UTF-8");
        } catch (IOException e) {
            log.error("Unable to read file from {}", this.fileLocation);
            e.printStackTrace();
        }

        return this.lineIterator;
    }
}

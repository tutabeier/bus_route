package busroute.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Response {
    @JsonProperty("dep_sid")
    private int depsId;
    @JsonProperty("arr_sid")
    private int arrsId;
    @JsonProperty("direct_bus_route")
    private boolean directBusRoute;
}
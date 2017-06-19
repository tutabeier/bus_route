package busroute.controller;

import busroute.domain.Response;
import busroute.service.RouteSearcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/api/direct")
public class BusRouteCheckController {

    private RouteSearcherService service;

    @Autowired
    public BusRouteCheckController(RouteSearcherService service) {
        this.service = service;
    }

    @RequestMapping(method = GET)
    public @ResponseBody Response getRoute(
            @RequestParam(value="dep_sid") int depsId,
            @RequestParam(value="arr_sid") int arrsId) {
        boolean hasRoute = service.hasRouteBetween(depsId, arrsId);
        return new Response(depsId, arrsId, hasRoute);
    }
}
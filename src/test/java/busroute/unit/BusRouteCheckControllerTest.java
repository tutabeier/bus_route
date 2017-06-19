package busroute.unit;

import busroute.controller.BusRouteCheckController;
import busroute.service.RouteSearcherService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class BusRouteCheckControllerTest {
    private MockMvc mockMvc;
    @Mock
    private RouteSearcherService routeSearcherService;
    @InjectMocks
    private BusRouteCheckController checkController;

    @Before
    public void setUp() {
        mockMvc = standaloneSetup(checkController).build();
    }

    @Test
    public void shouldReturnCorrectJsonIfExistsARoute() throws Exception {
        when(routeSearcherService.hasRouteBetween(0, 8)).thenReturn(true);

        mockMvc.perform(get("/api/direct?dep_sid=0&arr_sid=8")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("dep_sid", is(0)))
                .andExpect(jsonPath("arr_sid", is(8)))
                .andExpect(jsonPath("direct_bus_route", is(true)));
    }

    @Test
    public void shouldReturnCorrectJsonIfDontExistsARoute() throws Exception {
        when(routeSearcherService.hasRouteBetween(0, 8)).thenReturn(false);

        mockMvc.perform(get("/api/direct?dep_sid=0&arr_sid=8")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("dep_sid", is(0)))
                .andExpect(jsonPath("arr_sid", is(8)))
                .andExpect(jsonPath("direct_bus_route", is(false)));
    }
}
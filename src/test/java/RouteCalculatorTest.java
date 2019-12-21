import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {
    StationIndex metro;
    RouteCalculator routeCalculator;

    @Override
    protected void setUp() throws Exception {
        metro = Parser.createStationIndex("src/main/resources/myMetro.json");
        routeCalculator = new RouteCalculator(metro);
    }

    public void testGetShortestRoute() {
        List<Station> actual = routeCalculator.getShortestRoute(metro.getStation("Мордовская"), metro.getStation("Питерская"));
        List<Station> expected = new ArrayList<>();
        expected.add(new Station("Мордовская",metro.getLine(1)));
        expected.add(new Station("Нижегородская",metro.getLine(1)));
        expected.add(new Station("Питерская",metro.getLine(1)));
        assertEquals(expected,actual);
    }

    public void testCalculateDurationOnLine() {
        List<Station> root = routeCalculator.getShortestRoute(metro.getStation("Казанская"), metro.getStation("Калининградская"));
        double actual = RouteCalculator.calculateDuration(root);
        double expected = 5.0;
        assertEquals(expected,actual);
    }
    public void testCalculateDurationWith1connection() {
        List<Station> root = routeCalculator.getShortestRoute(metro.getStation("Казанская"), metro.getStation("Кстовская"));
        double actual = RouteCalculator.calculateDuration(root);
        double expected = 8.5;
        assertEquals(expected,actual);
    }
    public void testCalculateDurationWith2connection() {
        List<Station> root = routeCalculator.getShortestRoute(metro.getStation("Казанская"), metro.getStation("Мордовская"));
        double actual = RouteCalculator.calculateDuration(root);
        double expected = 17.0;
        assertEquals(expected,actual);
    }

    @Override
    protected void tearDown() throws Exception {

    }
}

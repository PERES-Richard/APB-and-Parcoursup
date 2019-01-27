package statistics;

import commons.statistics.DistanceMetric;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class DistanceTest {
    @Test
    public void testDistance10() {
        assertEquals("0", (new DistanceMetric(new File("../testRessources/apbSolutions/solution_10.txt"), new File("../testRessources/apbSolutions/solution_10.txt"))).calculMetric().print());
        assertEquals("3", (new DistanceMetric(new File("../testRessources/apbSolutions/solution_10.txt"), new File("src/test/java/output10Bad.txt"))).calculMetric().print());
    }

    @Test
    public void testDistance100() {
        assertEquals("0", (new DistanceMetric(new File("../testRessources/apbSolutions/solution_100.txt"), new File("../testRessources/apbSolutions/solution_100.txt"))).calculMetric().print());
        assertEquals("7", (new DistanceMetric(new File("../testRessources/apbSolutions/solution_100.txt"), new File("src/test/java/output100Bad.txt"))).calculMetric().print());
    }

    @Test
    public void testDistance1000() {
        assertEquals("0", (new DistanceMetric(new File("../testRessources/apbSolutions/solution_1_000.txt"), new File("../testRessources/apbSolutions/solution_1_000.txt"))).calculMetric().print());
        assertEquals("9", (new DistanceMetric(new File("../testRessources/apbSolutions/solution_1_000.txt"), new File("src/test/java/solution_1_000Bad.txt"))).calculMetric().print());
    }
}

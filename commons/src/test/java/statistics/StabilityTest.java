package statistics;

import commons.parser.ParserFile;
import commons.statistics.StabilityMetric;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class StabilityTest {

    @Test
    public void stability10APBTest() {
        ParserFile parserFile = new ParserFile("../testRessources/samples/sample_10.txt");
        parserFile.parse();

        assertEquals("0\n" +
                "0", new StabilityMetric(parserFile.getSchools(), parserFile.getStudents(), new File("../testRessources/apbSolutions/solution_10.txt")).calculMetric().print());
    }

    @Test
    public void stability100APBTest() {
        ParserFile parserFile = new ParserFile("../testRessources/samples/sample_100.txt");
        parserFile.parse();

        assertEquals("0\n" +
                "0", new StabilityMetric(parserFile.getSchools(), parserFile.getStudents(), new File("../testRessources/apbSolutions/solution_100.txt")).calculMetric().print());
    }

    @Test
    public void stability1000APBTest() {
        ParserFile parserFile = new ParserFile("../testRessources/samples/sample_1_000.txt");
        parserFile.parse();

        assertEquals("0\n" +
                "0", new StabilityMetric(parserFile.getSchools(), parserFile.getStudents(), new File("../testRessources/apbSolutions/solution_1_000.txt")).calculMetric().print());
    }

    @Test
    public void stability10000APBTest() {
        ParserFile parserFile = new ParserFile("../testRessources/samples/sample_10_000.txt");
        parserFile.parse();

        assertEquals("0\n" +
                "0", new StabilityMetric(parserFile.getSchools(), parserFile.getStudents(), new File("../testRessources/apbSolutions/solution_10_000.txt")).calculMetric().print());
    }

    @Test
    public void stability100PlanBTest() {
        ParserFile parserFile = new ParserFile("../testRessources/samples/sample_100.txt");
        parserFile.parse();

        assertEquals("15\n" +
                "4", new StabilityMetric(parserFile.getSchools(), parserFile.getStudents(), new File("../testRessources/studentFirstSolutions/solutionB_100.txt")).calculMetric().print());
    }

    @Test
    public void stability1000PlanBTest() {
        ParserFile parserFile = new ParserFile("../testRessources/samples/sample_1_000.txt");
        parserFile.parse();

        assertEquals("316\n" +
                "126", new StabilityMetric(parserFile.getSchools(), parserFile.getStudents(), new File("../testRessources/studentFirstSolutions/solutionB_1_000.txt")).calculMetric().print());
    }

    @Test
    public void stability10000PlanBTest() {
        ParserFile parserFile = new ParserFile("../testRessources/samples/sample_10_000.txt");
        parserFile.parse();

        assertEquals("8400\n" +
                "2196", new StabilityMetric(parserFile.getSchools(), parserFile.getStudents(), new File("../testRessources/studentFirstSolutions/solutionB_10_000.txt")).calculMetric().print());
    }
}

package statistics;

import commons.parser.ParserFile;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class FillingTest {

    @Test
    public void testCase10() {
        ParserFile parserFile = new ParserFile("../testRessources/samples/sample_10.txt");
        parserFile.parse();

        assertEquals("5 0 0", (new FillinMetric(parserFile.getSchools(), parserFile.getStudents(), new File("../testRessources/apbSolutions/solution_10.txt"))).calculMetric().print());
    }

    @Test
    public void testCase10Other() {
        ParserFile parserFile = new ParserFile("../testRessources/samples/sample_10.txt");
        parserFile.parse();

        assertEquals("3 1 1", (new FillinMetric(parserFile.getSchools(), parserFile.getStudents(), new File("src/test/java/solution_10Other.txt"))).calculMetric().print());
    }

    @Test
    public void testCase10Other2() {
        ParserFile parserFile = new ParserFile("../testRessources/samples/sample_10.txt");
        parserFile.parse();

        assertEquals("2 2 1", (new FillinMetric(parserFile.getSchools(), parserFile.getStudents(), new File("src/test/java/solution_10Other2.txt"))).calculMetric().print());
    }
}

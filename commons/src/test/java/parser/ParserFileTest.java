package parser;

import commons.parser.ParserFile;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParserFileTest {

    @Test
    public void test10() {
        ParserFile parserFile = new ParserFile("../testRessources/samples/sample_10.txt");
        parserFile.parse();

        assertEquals(10, parserFile.getStudents().size());
        assertEquals(5, parserFile.getSchools().size());
    }

    @Test
    public void testStudents100() {
        ParserFile parserFile = new ParserFile("../testRessources/samples/sample_100.txt");
        parserFile.parse();

        assertEquals(100, parserFile.getStudents().size());
        assertEquals(23, parserFile.getSchools().size());
    }

    @Test
    public void testStudents1000() {
        ParserFile parserFile = new ParserFile("../testRessources/samples/sample_1_000.txt");
        parserFile.parse();

        assertEquals(1000, parserFile.getStudents().size());
        assertEquals(54, parserFile.getSchools().size());
    }

    @Test
    public void testStudents10000() {
        ParserFile parserFile = new ParserFile("../testRessources/samples/sample_10_000.txt");
        parserFile.parse();

        assertEquals(10000, parserFile.getStudents().size());
        assertEquals(123, parserFile.getSchools().size());
    }

    @Test
    public void testStudents100000() {
        ParserFile parserFile = new ParserFile("../testRessources/samples/sample_100_000.txt");
        parserFile.parse();

        assertEquals(100000, parserFile.getStudents().size());
        assertEquals(191, parserFile.getSchools().size());
    }
}

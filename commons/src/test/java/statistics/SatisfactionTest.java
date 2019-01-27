package statistics;

import commons.parser.ParserFile;
import commons.statistics.SatisfactionMetric;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class SatisfactionTest {
    @Test
    public void testSatisfactionAPB10() {
        ParserFile parserFile = new ParserFile("../testRessources/samples/sample_10.txt");
        parserFile.parse();

        assertEquals("0 1 2 X\n" +
                "8 1 1 0\n" +
                "1.30\n" +
                "2.30", new SatisfactionMetric(parserFile.getSchools(), parserFile.getStudents(), new File("../testRessources/apbSolutions/solution_10.txt")).calculMetric().print());
    }

    @Test
    public void testSatisfactionAPB100() {
        ParserFile parserFile = new ParserFile("../testRessources/samples/sample_100.txt");
        parserFile.parse();

        assertEquals("0 1 2 3 4 5 6 X\n" +
                "70 12 6 2 3 6 0 1\n" +
                "1.79\n" +
                "22.57", new SatisfactionMetric(parserFile.getSchools(), parserFile.getStudents(), new File("../testRessources/apbSolutions/solution_100.txt")).calculMetric().print());
    }

    @Test
    public void testSatisfactionAPB1000() {
        ParserFile parserFile = new ParserFile("../testRessources/samples/sample_1_000.txt");
        parserFile.parse();

        assertEquals("0 1 2 3 4 5 6 7 8 9 X\n" +
                "587 134 61 36 17 38 53 21 21 9 23\n" +
                "2.57\n" +
                "168.85", new SatisfactionMetric(parserFile.getSchools(), parserFile.getStudents(), new File("../testRessources/apbSolutions/solution_1_000.txt")).calculMetric().print());
    }

    @Test
    public void testSatisfactionAPB10000() {
        ParserFile parserFile = new ParserFile("../testRessources/samples/sample_10_000.txt");
        parserFile.parse();

        assertEquals("0 1 2 3 4 5 6 7 8 9 10 11 12 13 X\n" +
                "5081 958 368 330 384 364 206 276 316 319 429 229 250 54 436\n" +
                "4.12\n" +
                "1171.65", new SatisfactionMetric(parserFile.getSchools(), parserFile.getStudents(), new File("../testRessources/apbSolutions/solution_10_000.txt")).calculMetric().print());
    }

    @Test
    public void testSatisfactionAPB100000() {
        ParserFile parserFile = new ParserFile("../testRessources/samples/sample_100_000.txt");
        parserFile.parse();

        assertEquals("0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 X\n" +
                "54453 9148 5043 3273 2279 2308 1059 838 1219 519 529 1072 3542 2251 1717 1349 1117 8284\n" +
                "4.68\n" +
                "8054.45", new SatisfactionMetric(parserFile.getSchools(), parserFile.getStudents(), new File("../testRessources/apbSolutions/solution_100_000.txt")).calculMetric().print());
    }

    @Test
    public void testSatisfactionStudentFirst10() {
        ParserFile parserFile = new ParserFile("../testRessources/samples/sample_10.txt");
        parserFile.parse();

        assertEquals("0 1 2 X\n" +
                "9 0 0 1\n" +
                "1.30\n" +
                "2.22", new SatisfactionMetric(parserFile.getSchools(), parserFile.getStudents(), new File("../testRessources/studentFirstSolutions/solutionB_10.txt")).calculMetric().print());
    }

    @Test
    public void testSatisfactionStudentFirst100() {
        ParserFile parserFile = new ParserFile("../testRessources/samples/sample_100.txt");
        parserFile.parse();

        assertEquals("0 1 2 3 4 5 6 X\n" +
                "78 12 0 0 3 5 1 1\n" +
                "1.62\n" +
                "24.12", new SatisfactionMetric(parserFile.getSchools(), parserFile.getStudents(), new File("../testRessources/studentFirstSolutions/solutionB_100.txt")).calculMetric().print());
    }

    @Test
    public void testSatisfactionStudentFirst1000() {
        ParserFile parserFile = new ParserFile("../testRessources/samples/sample_1_000.txt");
        parserFile.parse();

        assertEquals("0 1 2 3 4 5 6 7 8 9 X\n" +
                "718 105 27 29 17 35 10 3 23 1 32\n" +
                "2.08\n" +
                "184.92", new SatisfactionMetric(parserFile.getSchools(), parserFile.getStudents(), new File("../testRessources/studentFirstSolutions/solutionB_1_000.txt")).calculMetric().print());
    }

    @Test
    public void testSatisfactionStudentFirst10000() {
        ParserFile parserFile = new ParserFile("../testRessources/samples/sample_10_000.txt");
        parserFile.parse();

        assertEquals("0 1 2 3 4 5 6 7 8 9 10 11 12 13 X\n" +
                "6606 541 95 58 329 406 44 180 278 369 76 159 323 42 494\n" +
                "3.52\n" +
                "1274.01", new SatisfactionMetric(parserFile.getSchools(), parserFile.getStudents(), new File("../testRessources/studentFirstSolutions/solutionB_10_000.txt")).calculMetric().print());
    }

    @Test
    public void testSatisfactionStudentFirst100000() {
        ParserFile parserFile = new ParserFile("../testRessources/samples/sample_100_000.txt");
        parserFile.parse();

        assertEquals("0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 X\n" +
                "70086 6729 1923 242 1488 2013 225 65 1430 133 231 215 1356 1595 1753 1408 311 8797\n" +
                "3.84\n" +
                "9154.07", new SatisfactionMetric(parserFile.getSchools(), parserFile.getStudents(), new File("../testRessources/studentFirstSolutions/solutionB_100_000.txt")).calculMetric().print());
    }
}

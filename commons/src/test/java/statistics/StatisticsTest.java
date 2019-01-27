package statistics;

import commons.parser.ParserFile;
import commons.statistics.SatisfactionMetric;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class StatisticsTest {

    @Test
    public void getRankOfStudent() {
        ParserFile parserFile = new ParserFile("../testRessources/samples/sample_10.txt");
        parserFile.parse();

        SatisfactionMetric satisfactionMetric = new SatisfactionMetric(parserFile.getSchools(), parserFile.getStudents(), new File("../testRessources/apbSolutions/solution_10.txt"));
        satisfactionMetric.addSchoolToStudent(parserFile.getSchools(), parserFile.getStudents(), new File("../testRessources/apbSolutions/solution_10.txt"));

        assertEquals(5, satisfactionMetric.getRankOfStudentInAcceptedSchool(parserFile.getStudents().get(2)));
    }

    @Test
    public void addSchoolToStudentsTest() {
        ParserFile parserFile = new ParserFile("../testRessources/samples/sample_10.txt");
        parserFile.parse();

        SatisfactionMetric satisfactionMetric = new SatisfactionMetric(parserFile.getSchools(), parserFile.getStudents(), new File("../testRessources/apbSolutions/solution_10.txt"));
        satisfactionMetric.addSchoolToStudent(parserFile.getSchools(), parserFile.getStudents(), new File("../testRessources/apbSolutions/solution_10.txt"));

        assertEquals(1, parserFile.getStudents().get(0).getInSchool().getId());
        assertEquals(2, parserFile.getStudents().get(1).getInSchool().getId());
    }

}
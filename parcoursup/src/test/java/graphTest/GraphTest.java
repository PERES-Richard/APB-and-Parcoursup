package graphTest;

import org.junit.Ignore;
import org.junit.Test;
import si3.ps5.parcoursup.commons_ext.result.ResultGraph;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GraphTest {

    @Ignore
    @Test
    public void graphTest() {
        File testImage = new File("testImage.png");
        if (testImage.exists())
            testImage.delete();

        //not working on windows
        List<Integer> studentsWithSchoolPerDay = new ArrayList<>();
        studentsWithSchoolPerDay.add(3); //1er jours 3 eleves ont une école
        studentsWithSchoolPerDay.add(5); //2eme jours 5 eleves ont une école
        studentsWithSchoolPerDay.add(8); //3eme jours 8 eleves ont une ecole
        studentsWithSchoolPerDay.add(10); //4eme jours 10 eleves ont une ecole
        ResultGraph resultGraph = new ResultGraph(studentsWithSchoolPerDay, "testImage", 10);
        resultGraph.print();

        assertTrue(testImage.exists());
    }
}

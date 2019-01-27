package parser;

import commons.entity.School;
import commons.entity.Student;
import org.junit.Test;
import si3.ps5.parcoursup.commons_ext.entity_ext.school.SchoolOpenDoors;
import si3.ps5.parcoursup.commons_ext.entity_ext.school.SchoolRanked;
import si3.ps5.parcoursup.commons_ext.entity_ext.school.SchoolWithProfile;
import si3.ps5.parcoursup.commons_ext.entity_ext.student.StudentCaution;
import si3.ps5.parcoursup.commons_ext.entity_ext.student.StudentCurious;
import si3.ps5.parcoursup.commons_ext.entity_ext.student.StudentWithProfile;
import si3.ps5.parcoursup.commons_ext.parser_ext.ParserFileWithProfile;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ParserFileProfileTest {
    @Test
    public void test10() {
        Map<StudentWithProfile.StudentProfile, Integer> studentsPercents = new HashMap<>();
        studentsPercents.put(StudentWithProfile.StudentProfile.curious, 60);
        studentsPercents.put(StudentWithProfile.StudentProfile.caution, 40);

        Map<SchoolWithProfile.SchoolProfile, Integer> schoolsPercrents = new HashMap<>();
        schoolsPercrents.put(SchoolWithProfile.SchoolProfile.open_doors, 20);
        schoolsPercrents.put(SchoolWithProfile.SchoolProfile.ranked, 80);

        ParserFileWithProfile parserFileWithProfile = new ParserFileWithProfile("../testRessources/samples/sample_10.txt", studentsPercents, schoolsPercrents);
        parserFileWithProfile.parse();


        int numberCaution = 0;
        int numberCurious = 0;
        for (Student studentWithProfile : parserFileWithProfile.getStudents()) {
            if (studentWithProfile instanceof StudentCaution) {
                numberCaution++;
            } else if (studentWithProfile instanceof StudentCurious) {
                numberCurious++;
            }
        }

        assertTrue((numberCurious == 4 && numberCaution == 6) || (numberCurious == 6 && numberCaution == 4));

        int numberDoors = 0;
        int numberRanked = 0;
        for (School school : parserFileWithProfile.getSchools()) {
            if (school instanceof SchoolOpenDoors) {
                numberDoors++;
            } else if (school instanceof SchoolRanked) {
                numberRanked++;
            }
        }

        assertTrue((numberDoors == 4 && numberRanked == 1) || (numberDoors == 1 && numberRanked == 4));
    }

    @Test
    public void test10Seed() {
        Map<StudentWithProfile.StudentProfile, Integer> studentsPercents = new HashMap<>();
        studentsPercents.put(StudentWithProfile.StudentProfile.curious, 60);
        studentsPercents.put(StudentWithProfile.StudentProfile.caution, 40);

        Map<SchoolWithProfile.SchoolProfile, Integer> schoolsPercrents = new HashMap<>();
        schoolsPercrents.put(SchoolWithProfile.SchoolProfile.open_doors, 20);
        schoolsPercrents.put(SchoolWithProfile.SchoolProfile.ranked, 80);

        ParserFileWithProfile parserFileWithProfile = new ParserFileWithProfile("../testRessources/samples/sample_10.txt", studentsPercents, schoolsPercrents, 150);
        parserFileWithProfile.parse();


        int numberCaution = 0;
        int numberCurious = 0;
        for (Student studentWithProfile : parserFileWithProfile.getStudents()) {
            if (studentWithProfile instanceof StudentCaution) {
                numberCaution++;
            } else if (studentWithProfile instanceof StudentCurious) {
                numberCurious++;
            }
        }

        assertTrue((numberCurious == 4 && numberCaution == 6) || (numberCurious == 6 && numberCaution == 4));

        int numberDoors = 0;
        int numberRanked = 0;
        for (School school : parserFileWithProfile.getSchools()) {
            if (school instanceof SchoolOpenDoors) {
                numberDoors++;
            } else if (school instanceof SchoolRanked) {
                numberRanked++;
            }
        }

        assertTrue((numberDoors == 4 && numberRanked == 1) || (numberDoors == 1 && numberRanked == 4));
    }
}

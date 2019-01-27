package commons.statistics;

import commons.entity.School;
import commons.entity.Student;
import commons.result.ResultMetric;
import commons.result.ResultMetricSatisfaction;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class SatisfactionMetric implements Metric {
    private final List<School> schools;
    private final List<Student> students;
    private final File solution;

    public SatisfactionMetric(List<School> schools, List<Student> students, File solution) {
        this.schools = schools;
        this.students = students;
        this.solution = solution;
    }

    @Override
    public ResultMetric calculMetric() {
        addSchoolToStudent(schools, students, solution);
        return new ResultMetricSatisfaction(this, getStudentSatisfactionList(), getSatisfactionStudentsAverage(), getSatisfcationSchools());
    }

    private double getSatisfcationSchools() {
        double[] schoolsAverage = new double[schools.size()];
        double average = 0;
        int studentsHaveAffectation = 0;

        for (int i = 0; i < schools.size(); i++) {
            School school = schools.get(i);
            for (Student student : students) {
                if (student.getInSchool() != null && student.getInSchool().equals(school)) { //si l'eleve a eu une affectation
                    studentsHaveAffectation++;
                    //on recupere le rang que l'ecole avait donné a l'eleve
                    int rankOfStudentThatSchoolGaveHim = getRankOfStudentInPriority(school, student);
                    //on compte le nombre d'eleves pris dans l'ecole ayant un meilleur rang
                    int numberStudentsBefore = getNumberOfStudentsBeforeOther(school, rankOfStudentThatSchoolGaveHim);
                    //System.out.println("school id : "+school.getId()+" - "+rankOfStudentThatSchoolGaveHim + " - " +numberStudentsBefore);
                    schoolsAverage[i] += rankOfStudentThatSchoolGaveHim - (numberStudentsBefore);
                }
            }

            average += schoolsAverage[i]; //on ajoute au nombre total
        }
        average /= studentsHaveAffectation;

        return average;
    }

    private float getSatisfactionStudentsAverage() {
        int[] obtainedTheirsChoices = getStudentSatisfactionList();

        float averageStudent = 0;
        for (int i = 0; i < obtainedTheirsChoices.length; i++) {
            averageStudent += obtainedTheirsChoices[i] * (i + 1);
        }
        return averageStudent / students.size();
    }

    private int[] getStudentSatisfactionList() {
        int numberChoices = students.get(0).getPrioritySchools().size();
        //+1 for the X
        int[] obtainedTheirsChoices = new int[numberChoices + 1];
        Arrays.fill(obtainedTheirsChoices, 0);

        for (Student theStudent : students) {
            if (theStudent.getInSchool() == null)
                obtainedTheirsChoices[numberChoices]++;
            else {
                for (int j = 0; j < theStudent.getPrioritySchools().size(); j++) {
                    if (theStudent.getPrioritySchools().get(j).equals(theStudent.getInSchool()))
                        obtainedTheirsChoices[j]++;
                }
            }
        }

        return obtainedTheirsChoices;
    }

    /**
     * Get the number of students in a school before an other student
     *
     * @param school        the school to get priority students list
     * @param rankOfStudent the rank of the student
     * @return the number of students before the rank of student
     */
    private int getNumberOfStudentsBeforeOther(School school, int rankOfStudent) {
        int numberStudents = 0;
        int i = 0;
        while (i < rankOfStudent) {
            if (school.getPrioryStutends().get(i).getInSchool() != null && school.getPrioryStutends().get(i).getInSchool().equals(school))
                numberStudents++;
            i++;
        }
        return numberStudents;
    }
}

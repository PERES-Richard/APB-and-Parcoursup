package commons.statistics;

import commons.entity.School;
import commons.entity.Student;
import commons.result.ResultMetric;
import commons.result.ResultMetricStability;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StabilityMetric implements Metric {
    private final List<School> schools;
    private final List<Student> students;
    private final File solution;

    public StabilityMetric(List<School> schools, List<Student> students, File solution) {
        this.schools = schools;
        this.students = students;
        this.solution = solution;
    }

    @Override
    public ResultMetric calculMetric() {
        addSchoolToStudent(schools, students, solution);

        int[] lastStudentRankInSchool = new int[schools.size()];
        Arrays.fill(lastStudentRankInSchool, -1);
        List<Integer> studentsInTrouble = new ArrayList<>();
        int numberCoupleProblem = 0;

        //to the last rank of each schools
        for (int i = 0; i < students.size(); i++) {
            School theStudentSchool = students.get(i).getInSchool();
            if (theStudentSchool != null) {
                for (int j = 0; j < theStudentSchool.getPrioryStutends().size(); j++) {
                    if (theStudentSchool.getPrioryStutends().get(j).getId() == i) {
                        if (lastStudentRankInSchool[theStudentSchool.getId()] < j + 1) {
                            lastStudentRankInSchool[theStudentSchool.getId()] = j + 1; //+1 because rank start at 1, not 0
                        }
                    }
                }
            }
        }

        for (Student theActualStudent : students) {
            //si student n'a pas eu son premier choix mais qu'il a été pris dans une ecole
            if (theActualStudent.getInSchool() != null && !theActualStudent.getPrioritySchools().get(0).equals(theActualStudent.getInSchool())) {
                List<School> theActualStudentPrioritySchools = theActualStudent.getPrioritySchools();
                int j = 0;
                //parcours les choix de l'eleve actuel
                while (!theActualStudentPrioritySchools.get(j).equals(theActualStudent.getInSchool())) {
                    int schoolId = theActualStudent.getPrioritySchools().get(j).getId();
                    int rankOfTheLastStudentInSchool = lastStudentRankInSchool[schoolId];
                    int baseRankOfTheCurrentStudent = getRankOfStudentInPriority(schools.get(schoolId), theActualStudent);

                    if (baseRankOfTheCurrentStudent < rankOfTheLastStudentInSchool) {
                        if (!studentsInTrouble.contains(theActualStudent.getId()))
                            studentsInTrouble.add(theActualStudent.getId());
                        numberCoupleProblem++;
                    }
                    j++;
                }
            }
        }

        return new ResultMetricStability(this, numberCoupleProblem, studentsInTrouble.size());
    }
}

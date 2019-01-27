package si3.ps5.parcoursup.commons_ext.result;

import commons.entity.School;
import commons.entity.Student;
import commons.result.ResultAffectation;

import java.util.List;

public class ResultAffectationPS extends ResultAffectation {
    private final List<Integer> studentsPerDay;
    private final int numberStudents;

    public ResultAffectationPS(List<? extends Student> students, List<? extends School> schools, List<Integer> studentsPerDay) {
        super(students, schools);
        this.studentsPerDay = studentsPerDay;
        numberStudents = students.size();
    }

    public List<Integer> getStudentsPerDay() {
        return studentsPerDay;
    }

    public int getNumberStudents() {
        return numberStudents;
    }
}

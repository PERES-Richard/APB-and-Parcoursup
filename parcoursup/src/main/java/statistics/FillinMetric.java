package statistics;

import commons.entity.School;
import commons.entity.Student;
import si3.ps5.parcoursup.commons_ext.result.ResultFilling;
import commons.result.ResultMetric;
import commons.statistics.Metric;

import java.io.File;
import java.util.List;

public class FillinMetric implements Metric {
    private final List<School> schools;
    private final List<Student> students;
    private final File solution;

    public FillinMetric(List<School> schools, List<Student> students, File solution) {
        this.schools = schools;
        this.students = students;
        this.solution = solution;
    }

    @Override
    public ResultMetric calculMetric() {
        addSchoolToStudent(schools, students, solution);
        return new ResultFilling(this, getSchoolsNumberRecruitedTheRight(), getSchoolsNumberNotRecruitedEnought(), getSchoolsNumberRecruitedMoreThanCapacity());
    }

    private int getSchoolsNumberRecruitedTheRight() {
        int[] numberRecruited = new int[schools.size()];
        int result = 0;
        for (Student student : students) {
            if (student.getInSchool() != null) {
                numberRecruited[student.getInSchool().getId()]++;
            }
        }
        for (int i = 0; i < schools.size(); i++) {
            if (numberRecruited[i] == schools.get(i).getCapacity())
                result++;
        }
        return result;
    }

    private int getSchoolsNumberNotRecruitedEnought() {
        int[] numberRecruited = new int[schools.size()];
        int result = 0;
        for (Student student : students) {
            if (student.getInSchool() != null) {
                numberRecruited[student.getInSchool().getId()]++;
            }
        }
        for (int i = 0; i < schools.size(); i++) {
            if (numberRecruited[i] < schools.get(i).getCapacity())
                result++;
        }
        return result;
    }

    private int getSchoolsNumberRecruitedMoreThanCapacity() {
        int[] numberRecruited = new int[schools.size()];
        int result = 0;
        for (Student student : students) {
            if (student.getInSchool() != null) {
                numberRecruited[student.getInSchool().getId()]++;
            }
        }
        for (int i = 0; i < schools.size(); i++) {
            if (numberRecruited[i] > schools.get(i).getCapacity())
                result++;
        }
        return result;
    }
}

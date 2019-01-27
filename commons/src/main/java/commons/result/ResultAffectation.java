package commons.result;

import commons.entity.School;
import commons.entity.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class ResultAffectation extends Result {

    private final List<Student> students;
    private final List<School> schools;
    private File output;

    public ResultAffectation(List<? extends Student> students, List<? extends School> schools) {
        this.students = (List<Student>) students;
        this.schools = (List<School>) schools;
    }

    @Override
    public String print() {
        if (students.size() == 0)
            return "";

        StringBuilder res = new StringBuilder();
        for (Student s : students) {
            if (s.getInSchool() != null)
                res.append(s.getInSchool().getId()).append(" ");
            else
                res.append("X ");
        }

        if (output != null) {
            PrintWriter writer;
            try {
                writer = new PrintWriter(output);
                writer.print(res);
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return res.toString().substring(0, res.length() - 1);
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<School> getSchools() {
        return schools;
    }

    public void setOutput(File output) {
        this.output = output;
    }
}

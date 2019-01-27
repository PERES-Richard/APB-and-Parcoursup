package si3.ps5.sf;

import commons.algo.AlgoInterface;
import commons.entity.School;
import commons.entity.Student;
import commons.result.ResultAffectation;

import java.util.List;

public class AffectationStudentFirst implements AlgoInterface {

    /**
     * Run the StudentFirst algorithm
     *
     * @return the output string
     */
    public ResultAffectation affecter(List<School> schools, List<Student> students) {

        int wishNumber = students.get(0).getPrioritySchools().size();

        for (int i = 0; i < wishNumber; i++) {
            for (School scho : schools) {
                List<Student> prioryStutends = scho.getPrioryStutends();
                for (Student currentStudent : prioryStutends) {
                    if (currentStudent.getInSchool() == null) {
                        if (scho.getCapacity() > 0) {
                            if (currentStudent.getPrioritySchools().get(i).getId() == scho.getId()) {
                                currentStudent.setInSchool(scho);
                                scho.decCapacity();
                            }
                        }
                    }
                }
            }
        }
        return new ResultAffectation(students, schools);
    }
}

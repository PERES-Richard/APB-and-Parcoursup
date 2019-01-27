package si3.ps5.parcoursup.commons_ext.entity_ext.school;

import commons.entity.Student;
import si3.ps5.parcoursup.commons_ext.entity_ext.student.StudentWithProfile;

import java.util.List;

public class SchoolPicky extends SchoolWithProfile {
    public SchoolPicky(int id, int capacity, List<Student> prioryStutends, String ville) {
        super(id, capacity, prioryStutends, ville);
    }

    @Override
    public void affectProfile() {
        for (StudentWithProfile stu : getStudentPriorityList()) {
            if (getRestPlaceNumber() > 0) {
                if (stu.getPrioritySchools().contains(this)) {
                    getDecisionList().put(stu, 0);            //OUI
                    decreaseRestPlaceNumber();
                } else {
                    getDecisionList().put(stu, 1);            //NON
                }
            } else {
                getDecisionList().put(stu, 1);               //NON
            }
        }
    }
}

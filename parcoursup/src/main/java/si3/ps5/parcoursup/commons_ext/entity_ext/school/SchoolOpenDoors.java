package si3.ps5.parcoursup.commons_ext.entity_ext.school;

import commons.entity.Student;
import si3.ps5.parcoursup.commons_ext.entity_ext.student.StudentWithProfile;

import java.util.List;

public class SchoolOpenDoors extends SchoolWithProfile {
    public SchoolOpenDoors(int id, int capacity, List<Student> prioryStutends, String ville) {
        super(id, capacity, prioryStutends, ville);
    }

    @Override
    public void affectProfile() {
        setRestPlaceNumber(getStudentPriorityList().size());
        for (StudentWithProfile stu : getStudentPriorityList()) {
            if (stu.getPrioritySchools().contains(this)) {
                getDecisionList().put(stu, 0);               //OUI
                decreaseRestPlaceNumber();

            }
        }
    }
}

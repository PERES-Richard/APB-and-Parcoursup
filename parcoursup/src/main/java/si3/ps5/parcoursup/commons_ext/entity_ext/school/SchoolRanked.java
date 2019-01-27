package si3.ps5.parcoursup.commons_ext.entity_ext.school;

import commons.entity.Student;
import si3.ps5.parcoursup.commons_ext.entity_ext.student.StudentWithProfile;

import java.util.ArrayList;
import java.util.List;

public class SchoolRanked extends SchoolWithProfile {
    public SchoolRanked(int id, int capacity, List<Student> prioryStutends, String ville) {
        super(id, capacity, prioryStutends, ville);
    }

    @Override
    public void affectProfile() {
        int overbooking = (int) (getCapacity() * 0.25);
        for (StudentWithProfile stuWP : getStudentPriorityList()) {
            if (getRestPlaceNumber() > 0) {
                if (stuWP.getSchoolPriorityList().contains(this)) {
                    getDecisionList().put(stuWP, 0);         //OUI
                    decreaseRestPlaceNumber();
                }
            } else {
                if (getStudentPriorityList().size() <= overbooking) {
                    List<Integer> schoolIdList = new ArrayList<>();
                    for (SchoolWithProfile school : stuWP.getSchoolPriorityList()) {
                        schoolIdList.add(school.getId());
                    }
                    if (schoolIdList.contains(this.getId())) {
                        getStudentPriorityList().add(stuWP);
                        getDecisionList().put(stuWP, 2);      //WAIT
                    } else {
                        getDecisionList().put(stuWP, 1);      //NON
                    }
                } else {
                    getDecisionList().put(stuWP, 1);          //NON
                }
                break;
            }
        }
    }
}

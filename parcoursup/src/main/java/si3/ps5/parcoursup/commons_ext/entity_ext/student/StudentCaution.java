package si3.ps5.parcoursup.commons_ext.entity_ext.student;

import si3.ps5.parcoursup.commons_ext.entity_ext.school.SchoolWithProfile;

import java.util.Map;

public class StudentCaution extends StudentWithProfile {
    public StudentCaution(int id, String ville) {
        super(id, ville);
    }

    @Override
    public void affectProfile(SchoolWithProfile schoolwp) {
        for (Map.Entry entry : schoolwp.getDecisionList().entrySet()) {
            StudentWithProfile currentStuWp = (StudentWithProfile) entry.getKey();
            int decisionOfSchool = (int) entry.getValue();
            if (currentStuWp.getId() == this.getId()) {
                if (decisionOfSchool == 0) {            //School say yes
                    getDecisionList().put(schoolwp, 3);          //Student do nothing for now
                    getSchoolAcceptedList().add(schoolwp);
                }
            }
        }
    }
}

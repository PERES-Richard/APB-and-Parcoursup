package si3.ps5.parcoursup.commons_ext.entity_ext.student;

import si3.ps5.parcoursup.commons_ext.entity_ext.school.SchoolWithProfile;

import java.util.Map;

public class StudentTop5 extends StudentWithProfile {
    public StudentTop5(int id, String ville) {
        super(id, ville);
    }

    @Override
    public void affectProfile(SchoolWithProfile schoolwp) {
        for (Map.Entry entry : schoolwp.getDecisionList().entrySet()) {
            StudentWithProfile currentStuWp = (StudentWithProfile) entry.getKey();
            int decisionOfSchool = (int) entry.getValue();
            if (currentStuWp.getId() == this.getId()) {
                if (decisionOfSchool == 0) {
                    if (this.getPrioritySchools().indexOf(schoolwp) <= 4) {     //include 5 first wishes
                        getDecisionList().put(schoolwp, 3);
                        getSchoolAcceptedList().add(schoolwp);
                    } else {
                        getDecisionList().put(schoolwp, 1);
                    }
                }
            }
        }
    }
}

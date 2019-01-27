package si3.ps5.parcoursup.commons_ext.result;

import commons.result.Result;
import si3.ps5.parcoursup.commons_ext.entity_ext.school.SchoolWithProfile;
import si3.ps5.parcoursup.commons_ext.entity_ext.student.StudentWithProfile;

public class ResultProfiles extends Result {


    @Override
    public String print() {
        StringBuilder str = new StringBuilder();
        int nbProfileStudent = StudentWithProfile.StudentProfile.values().length;

        String sornot = (nbProfileStudent == 1) ? "" : "s";
        str.append("Candidate: (").append(nbProfileStudent).append(" profile").append(sornot).append(" available)\n");
        str.append("\t- ").append(StudentWithProfile.StudentProfile.values()[0]).append(" (default)\n");

        for (int i = 1; i < nbProfileStudent; i++)
            str.append("\t- ").append(StudentWithProfile.StudentProfile.values()[i]).append("\n");

        int nbProfileSchool = SchoolWithProfile.SchoolProfile.values().length;
        sornot = (nbProfileSchool == 1) ? "" : "s";
        str.append("School : (").append(nbProfileSchool).append(" profile").append(sornot).append(" available)\n");
        str.append("\t- ").append(SchoolWithProfile.SchoolProfile.values()[0]).append("(default)\n");

        for (int i = 1; i < nbProfileSchool; i++)
            str.append("\t- ").append(SchoolWithProfile.SchoolProfile.values()[i]).append("\n");

        return str.toString();
    }
}

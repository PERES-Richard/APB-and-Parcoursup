package si3.ps5.parcoursup.commons_ext.entity_ext.student;

import si3.ps5.parcoursup.commons_ext.entity_ext.school.SchoolWithProfile;

public class StudentCurious extends StudentWithProfile {
    public StudentCurious(int id, String ville) {
        super(id, ville);
    }

    @Override
    public void affectProfile(SchoolWithProfile schoolWp) {
        //par manque de temps nous n'avons pas pu faire cette méthode
    }
}

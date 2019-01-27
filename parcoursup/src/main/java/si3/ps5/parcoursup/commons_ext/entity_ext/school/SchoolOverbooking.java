package si3.ps5.parcoursup.commons_ext.entity_ext.school;

import commons.entity.Student;

import java.util.List;

public class SchoolOverbooking extends SchoolWithProfile {
    public SchoolOverbooking(int id, int capacity, List<Student> prioryStutends, String ville) {
        super(id, capacity, prioryStutends, ville);
    }

    @Override
    public void affectProfile() {

    }
}

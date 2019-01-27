package commons.entity;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private final int id;
    private List<School> prioritySchools;
    private School inSchool; //null if the student doesn't have school

    public Student(int id) {
        this.id = id;
        this.prioritySchools = new ArrayList<>();
        inSchool = null;
    }

    public void setPrioritySchools(List<School> prioritySchools) {
        this.prioritySchools = prioritySchools;
    }

    public List<School> getPrioritySchools() {
        return prioritySchools;
    }

    public School getInSchool() {
        return inSchool;
    }

    public int getId() {
        return this.id;
    }

    public void setInSchool(School i) {
        this.inSchool = i;
    }

}

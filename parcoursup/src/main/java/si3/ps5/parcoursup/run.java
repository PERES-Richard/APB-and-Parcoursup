package si3.ps5.parcoursup;

import commons.entity.School;
import commons.entity.Student;
import si3.ps5.parcoursup.commons_ext.entity_ext.school.SchoolRanked;
import si3.ps5.parcoursup.commons_ext.entity_ext.student.*;
import si3.ps5.parcoursup.commons_ext.result.ResultAffectationPS;

import java.util.ArrayList;
import java.util.List;

class run {
    public static void main(String[] args) {
        //a main to launch easily
        StudentStubborn student0 = new StudentStubborn(0, "Nice");
        StudentStubborn student1 = new StudentStubborn(1, "Nice");
        StudentCaution student2 = new StudentCaution(2, "Nice");
        StudentTop5 student3 = new StudentTop5(3, "Nice");
        StudentCurious student4 = new StudentCurious(4, "Nice");
        StudentCurious student5 = new StudentCurious(5, "Nice");
        StudentCurious student6 = new StudentCurious(6, "Nice");


        //Priority Student
        List<Student> priorityStudent0 = new ArrayList<>();
        List<Student> priorityStudent1 = new ArrayList<>();
        List<Student> priorityStudent2 = new ArrayList<>();
        List<Student> priorityStudent3 = new ArrayList<>();
        List<Student> priorityStudent4 = new ArrayList<>();
        //0
        priorityStudent0.add(student1);
        priorityStudent0.add(student3);
        priorityStudent0.add(student4);
        priorityStudent0.add(student5);
        priorityStudent0.add(student6);
        //1
        priorityStudent1.add(student2);
        priorityStudent1.add(student5);
        priorityStudent1.add(student3);
        priorityStudent1.add(student6);
        priorityStudent1.add(student4);
        //2
        priorityStudent2.add(student0);
        priorityStudent2.add(student1);
        priorityStudent2.add(student2);
        priorityStudent2.add(student3);
        priorityStudent2.add(student4);
        //3
        priorityStudent3.add(student3);
        priorityStudent3.add(student6);
        priorityStudent3.add(student0);
        priorityStudent3.add(student1);
        priorityStudent3.add(student2);
        //4
        priorityStudent4.add(student6);
        priorityStudent4.add(student5);
        priorityStudent4.add(student4);
        priorityStudent4.add(student3);
        priorityStudent4.add(student2);


        SchoolRanked school0 = new SchoolRanked(0, 1, priorityStudent0, "Nice");
        SchoolRanked school1 = new SchoolRanked(1, 3, priorityStudent1, "Nice");
        SchoolRanked school2 = new SchoolRanked(2, 1, priorityStudent2, "Nice");
        SchoolRanked school3 = new SchoolRanked(3, 1, priorityStudent3, "Nice");
        SchoolRanked school4 = new SchoolRanked(4, 1, priorityStudent4, "Nice");

        //Priority School
        List<School> prioritySchools0 = new ArrayList<>();
        List<School> prioritySchools1 = new ArrayList<>();
        List<School> prioritySchools2 = new ArrayList<>();
        List<School> prioritySchools3 = new ArrayList<>();
        List<School> prioritySchools4 = new ArrayList<>();
        List<School> prioritySchools5 = new ArrayList<>();
        List<School> prioritySchools6 = new ArrayList<>();
        //0
        prioritySchools0.add(school0);
        prioritySchools0.add(school1);
        prioritySchools0.add(school2);
        prioritySchools0.add(school3);
        //1
        prioritySchools1.add(school3);
        prioritySchools1.add(school2);
        //2
        prioritySchools2.add(school1);
        prioritySchools2.add(school3);
        //3
        prioritySchools3.add(school0);
        prioritySchools3.add(school2);
        prioritySchools3.add(school3);
        //4
        prioritySchools4.add(school0);
        prioritySchools4.add(school1);
        prioritySchools4.add(school3);
        //5
        prioritySchools5.add(school0);
        prioritySchools5.add(school1);
        prioritySchools5.add(school2);
        //6
        prioritySchools6.add(school4);


        student0.setSchoolPriorityListProfile(prioritySchools0);
        student1.setSchoolPriorityListProfile(prioritySchools1);
        student2.setSchoolPriorityListProfile(prioritySchools2);
        student3.setSchoolPriorityListProfile(prioritySchools3);
        student4.setSchoolPriorityListProfile(prioritySchools4);
        student5.setSchoolPriorityListProfile(prioritySchools5);
        student6.setSchoolPriorityListProfile(prioritySchools6);

        List<School> schools = new ArrayList<>();
        schools.add(school0);
        schools.add(school1);
        schools.add(school2);
        schools.add(school3);
        schools.add(school4);
        List<Student> students = new ArrayList<>();
        students.add(student0);
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
        students.add(student6);


        AffectationParcourSup affect = new AffectationParcourSup();
        affect.setOptions(123);
        ResultAffectationPS result = affect.affecter(schools, students);

        System.out.println(result.print());

    }
}

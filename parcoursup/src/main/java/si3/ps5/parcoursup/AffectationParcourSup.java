package si3.ps5.parcoursup;

import commons.algo.AlgoInterface;
import commons.entity.School;
import commons.entity.Student;
import si3.ps5.parcoursup.commons_ext.entity_ext.school.SchoolWithProfile;
import si3.ps5.parcoursup.commons_ext.entity_ext.student.StudentWithProfile;
import si3.ps5.parcoursup.commons_ext.result.ResultAffectationPS;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AffectationParcourSup implements AlgoInterface {
    private int days;

    public ResultAffectationPS affecter(List<School> schools, List<Student> students) {
        List<SchoolWithProfile> schoolWithProfiles = initSchoolWithProfile(schools);
        List<StudentWithProfile> studentWithProfiles = initStudentWithProfile(students);
        List<Integer> studentAdmisPerDayList = new ArrayList<>();

        //School start to init decision list and waitingStudent list
        for (SchoolWithProfile schoolwp : schoolWithProfiles) {
            schoolwp.affectProfile();
        }
        //Start the Period
        for (int i = 0; i < days; i++) {
            for (SchoolWithProfile schoolwp : schoolWithProfiles) {
                for (Map.Entry entry : schoolwp.getDecisionList().entrySet()) {
                    StudentWithProfile currentStudent = (StudentWithProfile) entry.getKey();
                    currentStudent.affectProfile(schoolwp);       //Student receive the propostions and start init decision list and add all the school accepted him in schoolAcceptedList
                }
            }

            //Student start to decide which school he want to take and mettre à jour ses listes
            for (StudentWithProfile stuwp : studentWithProfiles) {
                stuwp.adjustListNoWait();
            }

            //School receive student's answer then start to adjust decision list and waitingStudent list
            for (SchoolWithProfile schoolwp : schoolWithProfiles) {
                schoolwp.adjustList();
            }

            //Adjust studentAdmisPerDay List - calculate total students admitted each day
            int totalStudentAdmis = 0;
            for (Student stu : students) {
                if (stu.getInSchool() != null) {
                    totalStudentAdmis++;
                }
            }
            studentAdmisPerDayList.add(totalStudentAdmis);
        }

        return new ResultAffectationPS(students, schools, studentAdmisPerDayList);
    }

    private List<StudentWithProfile> initStudentWithProfile(List<Student> students) {
        List<StudentWithProfile> studentWithProfiles = new ArrayList<>();
        for (Student stu : students) {
            if (stu instanceof StudentWithProfile) {
                StudentWithProfile stuwp = (StudentWithProfile) stu;
                studentWithProfiles.add(stuwp);
            }
        }
        return studentWithProfiles;
    }

    private List<SchoolWithProfile> initSchoolWithProfile(List<School> schools) {
        List<SchoolWithProfile> schoolWithProfiles = new ArrayList<>();
        for (School school : schools) {
            if (school instanceof SchoolWithProfile) {
                SchoolWithProfile schoolwp = (SchoolWithProfile) school;
                schoolWithProfiles.add(schoolwp);
            }
        }
        return schoolWithProfiles;

        //return null;
    }

    public void setOptions(int days) {
        this.days = days;
    }
}

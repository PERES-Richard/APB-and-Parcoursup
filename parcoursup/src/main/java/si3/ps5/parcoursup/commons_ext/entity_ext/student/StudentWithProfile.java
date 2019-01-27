package si3.ps5.parcoursup.commons_ext.entity_ext.student;

import commons.entity.School;
import commons.entity.Student;
import si3.ps5.parcoursup.commons_ext.entity_ext.school.SchoolWithProfile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class StudentWithProfile extends Student {
    private final String ville;
    private int waitCount;

    public enum StudentProfile {
        caution, curious, top5, stubborn, lowering_expectation
    }

    private final List<SchoolWithProfile> schoolPriorityList;

    private final HashMap<SchoolWithProfile, Integer> decisionList;       // à chaque étudiant on associe une decisionList qui contient ses propositions comme clé et sa décision comme valeur (0, 1 ou 2).
    private final HashMap<SchoolWithProfile, Integer> timeToLiveList;     // à chaque étudiant on associe une decisionList qui contient ses propositions comme clé et nombre de jour passé pour répodre la proposition comme valeur .
    private final List<SchoolWithProfile> schoolAcceptedList;

    StudentWithProfile(int id, String ville) {
        super(id);
        this.ville = ville;

        decisionList = new HashMap<>();
        timeToLiveList = new HashMap<>();
        schoolAcceptedList = new ArrayList<>();
        schoolPriorityList = new ArrayList<>();
    }

    /*GETTER*/
    public String getVille() {
        return ville;
    }

    public int getWaitCount() {
        return waitCount;
    }

    public List<SchoolWithProfile> getSchoolPriorityList() {
        return schoolPriorityList;
    }

    public HashMap<SchoolWithProfile, Integer> getDecisionList() {
        return decisionList;
    }

    public HashMap<SchoolWithProfile, Integer> getTimeToLiveList() {
        return timeToLiveList;
    }

    List<SchoolWithProfile> getSchoolAcceptedList() {
        return schoolAcceptedList;
    }

    /*SETTER*/
    public void setSchoolPriorityListProfile(List<School> schools) {
        for (School school : schools) {
            SchoolWithProfile swp = (SchoolWithProfile) school;
            schoolPriorityList.add(swp);
        }
    }

    /*Incrémenter nombre du jour passé TTL correspond avec school passé en paramètre*/
    public void accelerateDayPassedNumber(SchoolWithProfile swp) {
        for (Map.Entry entry : timeToLiveList.entrySet()) {
            SchoolWithProfile currentSchoolwp = (SchoolWithProfile) entry.getKey();
            int currentDayPassedNb = (int) entry.getValue();
            if (currentSchoolwp.getId() == swp.getId()) {
                timeToLiveList.replace(currentSchoolwp, currentDayPassedNb, currentDayPassedNb + 1);
            }
        }
    }

    /*Get the day passed number of schoolwp*/
    public int getDayPassedNumber(SchoolWithProfile schoolwp) {
        int res = 0;
        for (Map.Entry decisionSchool : timeToLiveList.entrySet()) {
            SchoolWithProfile currentSchoolwp = (SchoolWithProfile) decisionSchool.getKey();
            int currentDayPassedNumber = (int) decisionSchool.getValue();
            if (schoolwp.getId() == currentSchoolwp.getId()) {
                res = currentDayPassedNumber;
            }
        }
        return res;
    }

    /*Ajuster decisionList et timeToLiveList de profile Caution*/
    public void adjustListNoWait() {
        if (!schoolAcceptedList.isEmpty()) {        //Parcourir tous les school ont accepté
            SchoolWithProfile schoolTaken = null;
            //Find school that student will accept
            for (SchoolWithProfile schoolPriority : schoolPriorityList) {
                if (schoolAcceptedList.contains(schoolPriority)) {      //Take the firstone school in wish list
                    schoolTaken = schoolPriority;
                    decisionList.put(schoolTaken, 0);       //Student say yes
                    this.setInSchool(schoolTaken);
                    break;
                }
            }
            //Modif decisionList
            for (SchoolWithProfile otherSchoolAccepted : schoolAcceptedList) {
                if (schoolTaken != null && otherSchoolAccepted.getId() != schoolTaken.getId()) {
                    decisionList.put(otherSchoolAccepted, 1);   //Student say no with the others
                }
            }
        }
    }


    public abstract void affectProfile(SchoolWithProfile schoolWp);
}

package si3.ps5.parcoursup.commons_ext.entity_ext.school;

import commons.entity.School;
import commons.entity.Student;
import si3.ps5.parcoursup.commons_ext.entity_ext.student.StudentWithProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class SchoolWithProfile extends School {
    public static int TTL = 7;
    private int restPlaceNumber;
    private final String ville;

    public enum SchoolProfile {
        ranked, open_doors, picky, overbookingn
    }

    private final List<StudentWithProfile> studentPriorityList;
    private final ArrayList<StudentWithProfile> studentTakenList;
    private final ArrayList<StudentWithProfile> studentWaitingList;
    private final Map<StudentWithProfile, Integer> decisionList;


    SchoolWithProfile(int id, int capacity, List<Student> prioryStutends, String ville) {
        super(id, capacity, prioryStutends);
        restPlaceNumber = capacity;
        this.ville = ville;
        studentTakenList = new ArrayList<>();
        studentWaitingList = new ArrayList<>();
        decisionList = new ConcurrentHashMap<>();
        studentPriorityList = new ArrayList<>();

        for (Student stu : prioryStutends) {
            StudentWithProfile stuwp = (StudentWithProfile) stu;
            studentPriorityList.add(stuwp);
        }
    }

    /*GETTER*/
    public static int getTTL() {
        return TTL;
    }

    int getRestPlaceNumber() {
        return restPlaceNumber;
    }

    public void decrementRestPlaceNumber() {
        restPlaceNumber--;
    }

    public String getVille() {
        return ville;
    }

    List<StudentWithProfile> getStudentPriorityList() {
        return studentPriorityList;
    }

    public ArrayList<StudentWithProfile> getStudentTakenList() {
        return studentTakenList;
    }

    public ArrayList<StudentWithProfile> getStudentWaitingList() {
        return studentWaitingList;
    }

    public Map<StudentWithProfile, Integer> getDecisionList() {
        return decisionList;
    }

    /*SETTER*/

    void setRestPlaceNumber(int restPlaceNumber) {
        this.restPlaceNumber = restPlaceNumber;
    }

    public void incrementRestPlaceNumber() {
        this.restPlaceNumber++;
    }

    void decreaseRestPlaceNumber() {
        this.restPlaceNumber--;
    }

    /*
     * Remove the student stw in accepted list and replace by the first student in waiting list
     * */
    private void chaseStudent(StudentWithProfile stw) {
        removeFromDecisionList(stw);
        if (studentWaitingList.size() > 0) {
            StudentWithProfile stuWPReplace = studentWaitingList.get(0);
            decisionList.put(stuWPReplace, 0);
            removeFromDecisionList(stuWPReplace);
        }
    }

    private void removeFromStudentWaitingList(StudentWithProfile stu) {
        for (StudentWithProfile currentstu : studentWaitingList) {
            if (currentstu.getId() == stu.getId()) {
                studentWaitingList.remove(currentstu);
            }
        }
    }

    public int getPlaceNumberAcceptedDecisionList() {
        int counter = 0;
        for (Map.Entry entry : decisionList.entrySet()) {
            int yes = (int) entry.getValue();
            if (yes == 0) counter++;
        }
        return counter;
    }

    private void removeFromDecisionList(StudentWithProfile stu) {
        for (Map.Entry decisionOfSchool : decisionList.entrySet()) {
            StudentWithProfile currentStudent = (StudentWithProfile) decisionOfSchool.getKey();
            int currentdecision = (int) decisionOfSchool.getValue();
            if (stu.getId() == currentStudent.getId()) {
                decisionList.remove(currentStudent);
            }
        }
    }

    /*Get answer of studentwp*/
    private int getAnswer(StudentWithProfile studentwp) {
        int answer = -1;
        for (Map.Entry decisionOfStudent : studentwp.getDecisionList().entrySet()) {
            SchoolWithProfile currentSchool = (SchoolWithProfile) decisionOfStudent.getKey();
            int currentAnswer = (int) decisionOfStudent.getValue();
            if (currentSchool.getId() == this.getId()) {
                answer = currentAnswer;
            }
        }
        return answer;
    }

    public void endOfPeriod() {
        for (StudentWithProfile stu : studentWaitingList) {
            removeFromStudentWaitingList(stu);
        }
    }

    //Ajuster studentTakenList, decisionList et studentWaitingList
    public void adjustList() {
        if (restPlaceNumber > 0) {     //Si il reste des places libres
            if (decisionList.size() > 0) {                  //Si il reste encore des étudiants dans la liste décision (les étudiants n'ont pas encore décidé)
                for (Map.Entry decisionSchool : this.getDecisionList().entrySet()) {
                    StudentWithProfile currentStuWP = (StudentWithProfile) decisionSchool.getKey();        //Student in Decision list
                    int decisionOfSchool = (int) decisionSchool.getValue();         //School Decision affected on student 0, 1 or 2

                    //Nombre du jour passé
                    int currentDayPassedNumber = currentStuWP.getDayPassedNumber(this);        //Nombre du jour d'attente la réponse courante de l"étudiant
                    //La réponse de l'étudiant
                    int currentAnswerOfStudent = this.getAnswer(currentStuWP);


                    if (decisionOfSchool == 0) {       //School say Yes
                        if (currentStuWP.getInSchool() != null) {      //But student is allredeady taken by another school
                            //chaseStudent(currentStuWP);
                            decisionList.remove(currentStuWP);
                        } else {
                            if (currentAnswerOfStudent == 3) {              //Student do nothing
                                if (currentDayPassedNumber > TTL) {             //Day passed number pass date limite TTL
                                    chaseStudent(currentStuWP);
                                }
                            } else if (currentAnswerOfStudent == 0) {       //Student say yes
                                studentTakenList.add(currentStuWP);
                                restPlaceNumber--;
                                removeFromDecisionList(currentStuWP);
                            } else if (currentAnswerOfStudent == 1) {       //Student say no
                                chaseStudent(currentStuWP);
                            } else if (currentAnswerOfStudent == 2) {
                                if (currentStuWP.getWaitCount() == 0) {     //if student dont have any school in waiting list
                                    studentWaitingList.add(currentStuWP);
                                }
                            }
                        }
                    } else if (decisionOfSchool == 1) {   //NON
                        removeFromDecisionList(currentStuWP);
                    } else if (decisionOfSchool == 2) {   //WAIT
                        studentWaitingList.add(currentStuWP);
                    }
                }
            }
        }
    }

    public abstract void affectProfile();

}

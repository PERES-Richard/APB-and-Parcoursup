package si3.ps5.apb;


import commons.algo.AlgoInterface;
import commons.entity.School;
import commons.entity.Student;
import commons.result.ResultAffectation;

import java.util.*;

public class AffectationAPB implements AlgoInterface {

    /**
     * Run the APB algorithm
     *
     * @return the output string
     */
    public ResultAffectation affecter(List<School> schools, List<Student> students) {
        ArrayList<ArrayList<Student>> maybes = new ArrayList<>(); //ArrayList contenant des arrayList associées à chaque école. Chaque ArrayList d'une école contient les potentiels étudiants qui vont rejoindre cette école.
        ArrayList<Student> studentsNotYetChoice = new ArrayList<>(students); //ArrayList contenant les étudiants non assignés à une école
        HashMap<Integer, Integer> wishList = new HashMap<>(); // à chaque étudiant on associe une wishList qui contient ses voeux.

        for (int i = 0; i < schools.size(); i++) {
            maybes.add(new ArrayList<>());
        }

        for (int i = 0; i < studentsNotYetChoice.size(); i++) {   //on associe tous les voeux au premier voeu ( numéro 0)
            wishList.put(i, 0);
        }

        while (studentsNotYetChoice.size() > 0) {
            for (Student stu : students) {
                int nextWish = wishList.get(stu.getId());   // on saisi le voeu de l'étudiant
                if (nextWish > stu.getPrioritySchools().size() - 1) {   // si c'est supérieur au nombre maximal de voeux cela veut dire que l'étudiant a été refusé par toutes les écoles. Il ne sera donc jamais affecté.
                    studentsNotYetChoice.remove(stu);
                } else {
                    School idSchool_voeux = stu.getPrioritySchools().get(wishList.get(stu.getId()));
                    if (stu.getInSchool() == null) {   // s'il n'est pas refusé par toute les écoles et qu'il est encore non affecté.
                        if (idSchool_voeux.getPrioryStutends().contains(stu)) {  //on regarde si l'école accepte de le prendre temporairement.
                            maybes.get(idSchool_voeux.getId()).add(stu);
                            stu.setInSchool(idSchool_voeux);
                            studentsNotYetChoice.remove(stu);
                            wishList.replace(stu.getId(), nextWish, nextWish + 1);  //s'il est pris pour l'instant, on incrémente son voeu, car il se peut
                        }
                    }
                }
            }

            for (ArrayList<Student> maybe : maybes) { // On parcourt la liste des étudiants potentiels pour chaque école pour regarder si l'acole à assez de place pour tout le monde
                School school_current = schools.get(maybes.indexOf(maybe));
                int capacitySchool_current = school_current.getCapacity(); // nombre de place
                if (maybe.size() > capacitySchool_current) {  // s'il y a trop d'étudiant
                    int diff = Math.abs(maybe.size() - capacitySchool_current);
                    int compteur = 0;
                    for (int j = school_current.getPrioryStutends().size() - 1; j >= 0; j--) { // on supprime les étudiants en trop et qui sont moins préférés aux autres
                        if (compteur < diff) {
                            if (maybe.contains(school_current.getPrioryStutends().get(j))) {
                                //Delete extra student
                                Student stu_deleted = students.get(school_current.getPrioryStutends().get(j).getId());
                                maybe.remove(stu_deleted);
                                stu_deleted.setInSchool(null);
                                studentsNotYetChoice.add(stu_deleted); // chaque étudiant supprimé est remis dans la liste des étudiants non affectés.
                                compteur++;
                            }
                        }
                    }
                }

            }
        }
        return new ResultAffectation(students, schools);
    }

}

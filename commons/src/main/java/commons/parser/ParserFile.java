package commons.parser;

import commons.entity.School;
import commons.entity.Student;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Damien Montoya
 */

public class ParserFile {
    protected final List<School> schools;
    protected final List<Student> students;
    private final String inputFile;
    protected int numberStudents;
    protected int numberSchools;
    protected int numberStudentChoices;

    public ParserFile(String inputFile) {
        schools = new ArrayList<>();
        students = new ArrayList<>();
        this.inputFile = inputFile;
    }

    /**
     * The method will launch readStudent or readSchool by reading all lines in the file
     */
    public void parse() {
        try {
            BufferedReader buff = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(inputFile)));

            int lineNumber = 0;
            int studentIdNew = 0;
            int schoolIdNew = 0;
            String line;
            List<List<Integer>> schoolsChoicesOfStudents = new ArrayList<>();
            //on en peux pas attribuer a la création les écoles aux éleves
            //car les écoles sont créer apres, on stock donc les id des écoles que l'on remplis apres
            while ((line = buff.readLine()) != null) {
                if (lineNumber == 0)
                    readFirstLine(line);
                else if (lineNumber < numberStudents + 1) {
                    readStudent(schoolsChoicesOfStudents, line, studentIdNew);
                    studentIdNew++;
                } else if (lineNumber < numberSchools + numberStudents + 1) {
                    readSchool(line, schoolIdNew);
                    schoolIdNew++;
                }
                lineNumber++;
            }
            addStudentWish(schoolsChoicesOfStudents);

            if (numberStudents != students.size()) {
                System.out.println("Il n'y a pas autant d'eleves que prévu");
                System.exit(1);
            }

            if (numberSchools != schools.size()) {
                System.err.println("Il n'y a pas autant d'écoles que prévu");
                System.exit(1);
            }


            if (lineNumber > numberSchools + numberStudents + 1) {
                System.err.println("Il y a plus de lignes dans le fichier qu'il faut");
                System.exit(1);
            }

            for (School school : schools) {
                for (Student studentId : school.getPrioryStutends()) {
                    if (studentId.getId() > students.size() || studentId.getId() < 0) {
                        System.err.println("un eleve n'existe pas");
                        System.exit(1);
                    }
                }
            }

            for (Student student : students) {
                for (School schooldId : student.getPrioritySchools()) {
                    if (schooldId.getId() > schools.size() || schooldId.getId() < 0) {
                        System.err.println("Un élève se trouve dans une école qui n'existe pas");
                        System.exit(1);
                    }
                }
            }

            for (School school : schools) {
                for (Student studentId : school.getPrioryStutends()) {
                    if (studentId.getId() > students.size() || studentId.getId() < 0) {
                        System.err.println("Une école a pris un élève qui n'existe pas");
                        System.exit(1);
                    }
                }
            }

            buff.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(2);
        }
    }

    /**
     * To parse the first line of the file, containing the number of students, the number of schools and the number of students choices
     *
     * @param line the line to inspect
     */
    protected void readFirstLine(String line) {
        String[] numbers = line.split("[ ]+");

        if (numbers.length > 3) {
            System.err.println("la premiere ligne ne contient pas de nombre ou que les indices sont faux -> bad input file");
            System.exit(1);
        }
        try {
            numberStudents = Integer.parseInt(numbers[0]);
            numberSchools = Integer.parseInt(numbers[1]);
            numberStudentChoices = Integer.parseInt(numbers[2]);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.err.println("la premiere ligne ne contient pas de nombre ou que les indices sont faux -> bad input file");
            System.exit(1);
        }
    }

    /**
     * To build a student
     *
     * @param student the line to analyse
     * @param id      the id that student will have
     */
    protected void readStudent(List<List<Integer>> schoolsChoicesOfStudents, String student, int id) {
        String[] studentSplitted = student.split("[ ]+");

        List<Integer> theChoicesOfTheStudent = new ArrayList<>();

        if (studentSplitted.length > 5 + numberStudentChoices) {
            System.err.println("Les élèves ont mis plus de choix que prévu");
            System.exit(1);
        }
        stockStudentsSchoolsChoices(theChoicesOfTheStudent, studentSplitted, 5);
        schoolsChoicesOfStudents.add(theChoicesOfTheStudent);
        students.add(new Student(id));
    }

    /**
     * Pour stocker les id des écoles que l'éleve veux pour les associer après aux écoles
     *
     * @param theChoicesOfTheStudent les id des écoles
     * @param lineSplitted           la ligne contenant les informations de l'éleve
     * @param offset                 le nombre d'informations que contient un eleve et ou commencerà récuperer ses choix
     */
    protected void stockStudentsSchoolsChoices(List<Integer> theChoicesOfTheStudent, String[] lineSplitted, int offset) {
        for (int i = 0; i < numberStudentChoices; i++) {
            //si la premiere ligne ne contient pas de nombre ou que les indices sont faux -> bad input file
            try {
                theChoicesOfTheStudent.add(Integer.parseInt(lineSplitted[offset + i]));
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.err.println("Un élève n'est pas bon");
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    /**
     * Add to the list the wish of the student
     *
     * @param schoolsChoosen the list that contain the id of the school
     */
    private void addStudentWish(List<List<Integer>> schoolsChoosen) {
        for (int i = 0; i < schoolsChoosen.size(); i++) {
            List<School> schoolsOfTheStudent = new ArrayList<>();
            for (int j = 0; j < schoolsChoosen.get(i).size(); j++) {
                schoolsOfTheStudent.add(schools.get(schoolsChoosen.get(i).get(j)));
            }
            students.get(i).setPrioritySchools(schoolsOfTheStudent);
        }
    }

    /**
     * To build a school
     *
     * @param school the line to analyse
     * @param id     the id of the school
     */
    protected void readSchool(String school, int id) {
        String[] studentSplitted = school.split("[ ]+");

        int capacityOfSchool = Integer.parseInt(studentSplitted[1]);
        List<Student> studentsChoosen = new ArrayList<>();
        int numberTheyChoose = Integer.parseInt(studentSplitted[3]);

        if (studentSplitted.length > 4 + numberTheyChoose) {
            System.err.println("L'ecole n'a pas le bon nombre d'élèves qu'il veulent");
            System.exit(1);
        }

        addSchoolWish(studentsChoosen, studentSplitted, 4, numberTheyChoose);
        schools.add(new School(id, capacityOfSchool, studentsChoosen));
    }

    /**
     * Add the rank of the students they want to take
     *
     * @param studentsChoosen  the list to add students id
     * @param studentSplitted  the line to analyse
     * @param offset           the offeset to begin
     *                         ex : CPGE_Lycée_Bellevue          1 Toulouse                  5 2 9 1 7 4
     *                         -> offset est 4 car la liste des souhaits commence au 2
     * @param numberTheyChoose the number of student they take
     */
    protected void addSchoolWish(List<Student> studentsChoosen, String[] studentSplitted, int offset, int numberTheyChoose) {
        for (int i = 0; i < numberTheyChoose; i++) {
            //si la premiere ligne ne contient pas de nombre ou que les indices sont faux -> bad input file
            try {
                studentsChoosen.add(students.get(Integer.parseInt(studentSplitted[offset + i])));
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    public List<School> getSchools() {
        return schools;
    }

    public List<Student> getStudents() {
        return students;
    }

    public String getInputFile() {
        return inputFile;
    }
}

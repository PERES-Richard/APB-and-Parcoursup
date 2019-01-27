package si3.ps5.parcoursup.commons_ext.parser_ext;

import commons.entity.Student;
import commons.parser.ParserFile;
import si3.ps5.parcoursup.commons_ext.entity_ext.school.*;
import si3.ps5.parcoursup.commons_ext.entity_ext.student.*;

import java.util.*;

public class ParserFileWithProfile extends ParserFile {
    private final Map<StudentWithProfile.StudentProfile, Integer> studentsProfilList;
    private final Map<SchoolWithProfile.SchoolProfile, Integer> schoolsProfilList;

    private long seed;
    private boolean asSeed = false;

    /**
     * ces attribut permet de savoir combien de profils différents nous avons pour les étudiants et écoles
     */
    private PercentStudentProfile[] studentsProfilesToAttribute;
    private PercentSchoolProfile[] schoolsProfilesToAttribute;

    /**
     * This parser is used with ParcoursSup because parcours sup have more parameters to count
     *
     * @param inputFile          the file to get schools and students
     * @param studentsProfilList the students that will be affected by parse
     * @param schoolsProfilList  the schools that will be affected by parse
     */
    public ParserFileWithProfile(String inputFile, Map<StudentWithProfile.StudentProfile, Integer> studentsProfilList, Map<SchoolWithProfile.SchoolProfile, Integer> schoolsProfilList) {
        super(inputFile);
        this.studentsProfilList = studentsProfilList;
        this.schoolsProfilList = schoolsProfilList;
    }

    /**
     * This parser is used with ParcoursSup because parcours sup have more parameters to count with seed)
     *
     * @param inputFile          the file to get schools and students
     * @param studentsProfilList the students that will be affected by parse
     * @param schoolsProfilList  the schools that will be affected by parse
     * @param seed               the seed of the simulation
     */
    public ParserFileWithProfile(String inputFile, Map<StudentWithProfile.StudentProfile, Integer> studentsProfilList, Map<SchoolWithProfile.SchoolProfile, Integer> schoolsProfilList, long seed) {
        super(inputFile);
        this.studentsProfilList = studentsProfilList;
        this.schoolsProfilList = schoolsProfilList;

        asSeed = true;
        this.seed = seed;
    }

    /**
     * count the number of students that have a certain profile
     */
    private void initPercentsOfStudentProfiles() {
        studentsProfilesToAttribute = new PercentStudentProfile[studentsProfilList.size()];

        int i = 0;
        for (Map.Entry<StudentWithProfile.StudentProfile, Integer> entry : studentsProfilList.entrySet()) {
            studentsProfilesToAttribute[i] = new PercentStudentProfile((int) Math.ceil(numberStudents * (entry.getValue() / 100.0)), entry.getKey());
            i++;
        }
    }

    /**
     * count the number of schools that have a certain profile
     */
    private void initPercentsOfSchoolProfiles() {
        schoolsProfilesToAttribute = new PercentSchoolProfile[schoolsProfilList.size()];

        int i = 0;
        for (Map.Entry<SchoolWithProfile.SchoolProfile, Integer> entry : schoolsProfilList.entrySet()) {
            schoolsProfilesToAttribute[i] = new PercentSchoolProfile((int) Math.ceil(numberSchools * (entry.getValue() / 100.0)), entry.getKey());
            i++;
        }
    }

    /**
     * after reading the first line wi can init arrays to calculate percents
     *
     * @param line the line to inspect
     */
    @Override
    protected void readFirstLine(String line) {
        super.readFirstLine(line);
        initPercentsOfStudentProfiles();
        initPercentsOfSchoolProfiles();
    }

    /**
     * override the read student to add also the city and the profile
     *
     * @param student the line to analyse
     * @param id      the id that student will have
     */
    @Override
    protected void readStudent(List<List<Integer>> schoolsChoicesOfStudents, String student, int id) {
        String[] studentSplitted = student.split("[ ]+");
        List<Integer> theChoicesOfTheStudent = new ArrayList<>();

        String studentCity = studentSplitted[4];

        if (studentSplitted.length > 5 + numberStudentChoices) {
            System.err.println("Les élèves ont mis plus de choix que prévu");
            System.exit(1);
        }

        //attribute the type of student
        StudentWithProfile.StudentProfile profileToAttribute;
        if (studentsProfilList.size() != 0) {
            if (!asSeed) {
                int i = 0;
                while (studentsProfilesToAttribute[i].getNumberOfStudentsThatHaveThisProfile() < 1) {
                    i++;
                }
                studentsProfilesToAttribute[i].decrementNumber();
                profileToAttribute = studentsProfilesToAttribute[i].getTheProfileToAttribute();
            } else {
                Random random = new Random(seed);
                int rand;
                do {
                    rand = random.nextInt(studentsProfilesToAttribute.length);
                } while (studentsProfilesToAttribute[rand].getNumberOfStudentsThatHaveThisProfile() < 1);
                studentsProfilesToAttribute[rand].decrementNumber();
                profileToAttribute = studentsProfilesToAttribute[rand].getTheProfileToAttribute();
            }
        } else {
            profileToAttribute = StudentWithProfile.StudentProfile.caution;
        }

        stockStudentsSchoolsChoices(theChoicesOfTheStudent, studentSplitted, 5);
        schoolsChoicesOfStudents.add(theChoicesOfTheStudent);

        switch (profileToAttribute) {
            case top5:
                students.add(new StudentTop5(id, studentCity));
                break;
            case curious:
                students.add(new StudentCurious(id, studentCity));
                break;
            case stubborn:
                students.add(new StudentStubborn(id, studentCity));
                break;
            case lowering_expectation:
                students.add(new StudentLoveringExpectation(id, studentCity));
                break;
            default:
                students.add(new StudentCaution(id, studentCity));
        }
    }

    /**
     * To build a school
     *
     * @param school the line to analyse
     * @param id     the id of the school
     */
    @Override
    protected void readSchool(String school, int id) {
        String[] studentSplitted = school.split("[ ]+");

        String schoolCity = studentSplitted[2];

        int capacityOfSchool = Integer.parseInt(studentSplitted[1]);
        List<Student> studentsChoosen = new ArrayList<>();
        int numberTheyChoose = Integer.parseInt(studentSplitted[3]);

        if (studentSplitted.length > 4 + numberTheyChoose) {
            System.err.println("L'ecole n'a pas le bon nombre d'élèves qu'il veulent");
            System.exit(1);
        }

        //attribute the type of school
        SchoolWithProfile.SchoolProfile profileToAttribute;
        if (schoolsProfilList.size() != 0) {
            if (!asSeed) {
                int i = 0;
                while (schoolsProfilesToAttribute[i].getNumberOfSchoolsThatHaveThisProfile() < 1) {
                    i++;
                }
                schoolsProfilesToAttribute[i].decrementNumber();
                profileToAttribute = schoolsProfilesToAttribute[i].getTheProfileToAttribute();
            } else {
                Random random = new Random(seed);
                int rand;
                do {
                    rand = random.nextInt(schoolsProfilesToAttribute.length);
                } while (schoolsProfilesToAttribute[rand].getNumberOfSchoolsThatHaveThisProfile() < 1);
                schoolsProfilesToAttribute[rand].decrementNumber();
                profileToAttribute = schoolsProfilesToAttribute[rand].getTheProfileToAttribute();
            }
        } else {
            profileToAttribute = SchoolWithProfile.SchoolProfile.ranked;
        }


        addSchoolWish(studentsChoosen, studentSplitted, 4, capacityOfSchool);
        switch (profileToAttribute) {
            case picky:
                schools.add(new SchoolPicky(id, capacityOfSchool, studentsChoosen, schoolCity));
                break;
            case overbookingn:
                schools.add(new SchoolOverbooking(id, capacityOfSchool, studentsChoosen, schoolCity));
                break;
            case open_doors:
                schools.add(new SchoolOpenDoors(id, capacityOfSchool, studentsChoosen, schoolCity));
                break;
            default:
                schools.add(new SchoolRanked(id, capacityOfSchool, studentsChoosen, schoolCity));
        }
    }
}

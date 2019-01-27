package commons.statistics;

import commons.entity.School;
import commons.entity.Student;
import commons.result.ResultMetric;

import java.io.*;
import java.util.List;

public interface Metric {
    ResultMetric calculMetric();

    /**
     * To get students school accepted from a solution file
     *
     * @param students the students to put the school id
     * @param solution the students to put the school id
     */
    default void addSchoolToStudent(List<School> schools, List<Student> students, File solution) {
        try {
            BufferedReader buff = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(solution)));
            String line = buff.readLine();
            String[] numbers = line.split("[ ]+");
            for (int i = 0; i < numbers.length; i++) {
                School number;
                if (numbers[i].equals("X") || numbers[i].equals("x"))
                    number = null;
                else
                    number = schools.get(Integer.parseInt(numbers[i]));
                students.get(i).setInSchool(number);
            }

            buff.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(2);
        }

    }

    /**
     * Return the rank of a student that a school gave him before accepted him
     *
     * @param school  the school to search
     * @param student the student to search
     * @return the rank -1 if student not in school
     */
    default int getRankOfStudentInPriority(School school, Student student) {
        int result = school.getPrioryStutends().indexOf(student);
        if (result == -1)
            return -1;
        return result + 1; //rank begin at 1 not 0
    }

    /**
     * to get a student rank in the school that accepted him
     *
     * @param student the student to get rank
     * @return the rank or -1 if there is no student
     */
    default int getRankOfStudentInAcceptedSchool(Student student) {
        return student.getInSchool().getPrioryStutends().indexOf(student) + 1;
    }
}
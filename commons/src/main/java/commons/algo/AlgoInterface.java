package commons.algo;

import commons.entity.School;
import commons.entity.Student;
import commons.result.ResultAffectation;

import java.util.List;

public interface AlgoInterface {

    ResultAffectation affecter(List<School> schools, List<Student> students);

}

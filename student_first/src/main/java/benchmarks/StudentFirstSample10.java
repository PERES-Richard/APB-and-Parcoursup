package benchmarks;

import commons.parser.ParserFile;
import si3.ps5.sf.AffectationStudentFirst;

public class StudentFirstSample10 {
    public static void main(String[] args) {

        long begin = System.currentTimeMillis();
        ParserFile parser = new ParserFile("testRessources/samples/sample_10.txt");
        parser.parse();
        long parserEnd = System.currentTimeMillis() - begin;

        long affecationBegin = System.currentTimeMillis();
        AffectationStudentFirst affectationAPB = new AffectationStudentFirst();
        affectationAPB.affecter(parser.getSchools(), parser.getStudents());
        long affecationEnd = System.currentTimeMillis() - affecationBegin;

        long end = System.currentTimeMillis();

        System.out.println("Temps parser (ms) : " + parserEnd);
        System.out.println("Temps algo (ms) : " + affecationEnd);
        System.out.println("Temps total (ms) : " + (end - begin));

    }
}

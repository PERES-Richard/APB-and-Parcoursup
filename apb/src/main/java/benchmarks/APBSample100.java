package benchmarks;

import commons.parser.ParserFile;
import si3.ps5.apb.AffectationAPB;

public class APBSample100 {
    public static void main(String[] args) {

        long begin = System.currentTimeMillis();
        ParserFile parser = new ParserFile("testRessources/samples/sample_100.txt");
        parser.parse();
        long parserEnd = System.currentTimeMillis() - begin;

        long affecationBegin = System.currentTimeMillis();
        AffectationAPB affectationAPB = new AffectationAPB();
        affectationAPB.affecter(parser.getSchools(), parser.getStudents());
        long affecationEnd = System.currentTimeMillis() - affecationBegin;

        long end = System.currentTimeMillis();

        System.out.println("Temps parser (ms) : " + parserEnd);
        System.out.println("Temps algo (ms) : " + affecationEnd);
        System.out.println("Temps total (ms) : " + (end - begin));

    }
}

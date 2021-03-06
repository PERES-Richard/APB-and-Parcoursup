package benchmarks;

import commons.parser.ParserFile;
import si3.ps5.apb.AffectationAPB;

public class APBSample1000000 {
    public static void main(String[] args) {

        long begin = System.currentTimeMillis();
        ParserFile parser = new ParserFile("dataset/samples/sample_1_000_000.txt");
        parser.parse();
        long parserEnd = System.currentTimeMillis() - begin;

        long affecationBegin = System.currentTimeMillis();
        AffectationAPB affectationAPB = new AffectationAPB();
        affectationAPB.affecter(parser.getSchools(), parser.getStudents());
        long affecationEnd = System.currentTimeMillis() - affecationBegin;

        long end = System.currentTimeMillis();

        System.out.println("Temps parser (s) : " + parserEnd / 1000);
        System.out.println("Temps algo (s) : " + affecationEnd / 1000);
        System.out.println("Temps total (s) : " + ((end - begin) / 1000));

    }
}

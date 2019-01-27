package si3.ps5.apb.affectation;

import commons.entity.Student;
import commons.parser.ParserFile;
import commons.statistics.SatisfactionMetric;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import si3.ps5.apb.AffectationAPB;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AffectationAPBTest {

    private ParserFile sample10, sample100, sample1_000, sample10_000, sample100_000;
    private AffectationAPB affectation;

    @BeforeEach
    void setUp() {
        //Sample test case
        sample10 = new ParserFile("../testRessources/samples/sample_10.txt");
        sample100 = new ParserFile("../testRessources/samples/sample_100.txt");
        sample1_000 = new ParserFile("../testRessources/samples/sample_1_000.txt");
        sample10_000 = new ParserFile("../testRessources/samples/sample_10_000.txt");
        sample100_000 = new ParserFile("../testRessources/samples/sample_100_000.txt");
    }

    @Test
    void testCaseSample10() {
        setUp();
        sample10.parse();
        affectation = new AffectationAPB();
        String currentRes = affectation.affecter(sample10.getSchools(), sample10.getStudents()).print();
        String expectedRes = "1 2 1 1 2 1 4 0 3 2";
        assertEquals(expectedRes, currentRes);
        tearDown();
    }

    @Test
    void testCaseSample100() {
        setUp();
        sample100.parse();
        affectation = new AffectationAPB();
        String currentRes = affectation.affecter(sample100.getSchools(), sample100.getStudents()).print();
        String expectedRes = "0 6 5 4 0 6 21 0 10 7 13 15 8 5 12 17 4 10 9 5 10 18 8 5 5 3 7 2 8 5 5 9 19 8 18 8 X 0 8 15 5 4 4 5 5 4 9 1 1 9 5 9 4 17 0 5 5 1 9 22 2 6 3 0 7 7 8 9 7 5 10 19 14 9 9 5 11 5 10 10 10 8 7 8 8 10 13 5 3 8 5 3 5 4 8 11 5 6 10 8";
        assertEquals(expectedRes, currentRes);
        tearDown();
    }

    @Test
    void testCaseSample1_000() {
        setUp();
        sample1_000.parse();
        affectation = new AffectationAPB();
        String currentRes = affectation.affecter(sample1_000.getSchools(), sample1_000.getStudents()).print();
        String expectedRes = "5 25 26 28 15 33 24 13 24 16 27 X 29 53 24 24 26 40 18 49 16 14 23 15 16 17 24 28 22 28 53 17 51 16 18 28 22 16 19 25 16 14 2 32 25 9 28 18 33 22 25 16 25 18 1 28 14 18 30 9 18 24 20 36 14 28 X 27 31 7 X 24 22 51 28 28 18 17 45 27 22 22 18 21 28 21 33 26 14 26 18 14 14 18 28 4 18 30 24 X 18 18 28 28 26 16 24 7 52 23 43 25 39 11 16 18 0 13 21 17 22 28 24 24 28 24 17 24 28 28 28 19 X 18 6 35 17 43 28 19 25 24 20 15 5 39 19 18 16 9 25 28 5 17 24 18 12 37 28 27 38 28 38 16 22 10 35 25 48 28 17 10 42 18 23 20 4 0 40 23 41 43 27 40 39 24 27 26 25 20 19 X 48 3 14 27 24 22 31 28 26 12 6 22 29 28 38 28 24 28 28 17 27 15 5 15 17 44 36 24 25 29 25 27 28 29 26 15 52 37 27 20 28 20 17 26 18 40 22 15 17 15 25 27 13 18 50 19 28 14 51 4 26 28 24 28 28 17 24 24 18 20 23 24 22 11 24 28 22 14 28 16 25 28 27 28 40 14 33 25 24 X 22 52 18 16 14 25 X 27 14 7 6 28 30 43 28 31 41 29 20 10 27 11 X 28 28 51 22 39 14 22 24 25 35 16 16 17 15 28 36 42 22 22 24 11 22 17 17 15 53 15 19 30 47 28 16 36 50 14 24 28 28 52 28 35 14 28 19 23 24 24 15 52 26 14 24 X 20 22 49 5 16 51 16 28 29 45 13 32 1 28 25 28 24 3 9 16 18 32 26 28 28 28 24 42 18 14 28 25 28 17 4 17 24 18 5 17 20 25 24 27 24 0 53 28 6 3 X 18 4 15 28 25 27 25 6 24 17 19 24 16 24 26 28 17 18 17 28 5 24 25 45 18 12 25 14 25 28 25 41 15 14 0 24 25 17 24 28 24 25 26 25 52 28 53 22 28 28 14 28 28 27 28 22 18 17 17 17 24 23 18 15 6 18 18 12 18 20 38 17 3 45 X 33 28 28 27 18 31 22 5 26 16 28 8 24 28 29 17 29 11 16 2 15 15 18 22 18 20 2 22 17 37 24 X 19 20 14 25 16 6 17 27 28 28 17 29 17 2 14 25 46 17 24 35 27 29 17 24 17 39 29 20 52 25 24 12 29 19 19 18 15 27 24 34 16 14 28 14 27 12 22 15 28 28 22 17 24 14 28 X 22 53 16 21 36 2 32 43 17 23 15 16 27 29 28 14 31 22 16 28 23 27 17 53 22 13 17 24 4 29 14 24 22 24 18 34 15 28 47 6 9 6 16 22 28 47 25 44 11 40 25 24 29 42 X 32 41 35 14 16 24 22 28 24 23 29 29 24 22 26 X 22 24 39 24 24 27 36 28 27 17 24 17 15 25 29 23 17 33 28 18 22 17 4 25 6 24 28 14 3 18 52 15 24 22 9 28 28 25 24 24 27 14 18 52 X 24 19 12 41 12 14 16 39 49 19 17 16 16 18 41 28 23 27 22 27 15 26 34 6 21 16 5 18 24 17 49 17 23 14 28 18 29 12 35 24 15 28 24 22 22 25 17 27 22 27 33 45 24 40 14 28 25 50 16 18 X 29 28 22 17 16 6 8 3 28 25 18 26 35 25 21 1 25 14 28 35 28 10 22 28 14 38 13 24 7 28 50 16 25 10 18 28 24 23 0 37 0 39 22 28 8 18 28 25 2 24 49 14 28 15 22 17 25 14 15 25 18 5 15 17 47 45 18 36 33 33 15 1 28 X 26 19 35 28 15 27 49 27 18 7 X 25 43 22 25 25 24 22 34 26 28 18 2 24 13 14 1 20 42 9 20 18 29 30 33 52 26 29 12 32 16 21 40 16 20 52 18 22 6 18 3 48 6 0 37 X 15 24 42 21 23 28 24 47 8 14 27 22 28 42 5 25 14 40 3 22 25 30 25 22 X 14 45 29 24 38 25 7 47 28 15 22 25 29 17 4 21 42 26 22 22 18 18 18 45 17 6 18 25 17 14 26 24 20 24 14 18 16 19 18 28 22 53 28 8 8 12 19 24 14 29 22 19 17 25 23 22 17 16 21 17 52 14 42 18 24 27 14 48 49 15 24 29 28 17 17 35 16 28 15 25 23 25 53 28 28 26 32 35 24 19 35 48 27 19 0 X";
        assertEquals(expectedRes, currentRes);
        tearDown();
    }

    @Test
    void testCaseSample10_000() {
        setUp();
        sample10_000.parse();
        affectation = new AffectationAPB();
        String currentRes = affectation.affecter(sample10_000.getSchools(), sample10_000.getStudents()).print();
        String expectedRes = "77 56 68 52 58 X 70 67 62 67 92 52 82 67 64 67 102 61 74 70 60 74 56 44 52 66 63 61 63 55 51 76 87 110 62 50 52 60 68 63 66 4 56 116 61 61 73 50 X 118 58 X 51 65 56 51 57 66 10 X 64 60 86 52 59 63 66 58 67 50 71 95 66 57 19 109 59 56 X 116 113 63 58 X 56 55 91 66 54 61 77 75 56 51 95 52 73 52 64 9 58 66 55 53 84 58 71 X 6 72 60 65 50 104 51 38 68 52 63 50 51 X 46 52 64 X 88 75 40 53 66 97 X 51 119 66 55 64 75 17 38 63 66 98 69 84 96 55 59 55 50 51 52 86 55 94 55 72 67 59 50 107 51 52 77 55 120 86 108 67 56 19 61 101 60 73 85 64 52 58 76 105 40 84 52 52 113 71 57 75 66 38 54 19 73 65 43 50 52 45 45 71 64 63 56 50 60 73 34 64 61 71 64 50 50 66 30 59 30 66 20 73 55 63 55 55 92 28 113 52 X 63 77 63 51 70 52 51 9 50 117 51 X 64 50 120 23 58 66 X 52 86 68 55 58 45 60 X 54 74 52 65 91 52 54 68 58 52 66 99 64 64 60 81 53 119 73 74 56 54 14 80 68 56 52 50 4 57 X 52 76 50 X 52 52 53 75 68 60 58 32 51 64 34 74 51 114 50 4 89 110 74 52 26 63 58 63 69 51 55 52 84 60 58 72 72 113 53 55 51 57 X 56 55 53 65 4 104 56 63 74 67 51 40 110 103 64 50 87 66 51 X 50 66 66 112 67 77 52 99 112 77 45 74 77 100 73 103 66 50 51 54 61 57 55 64 65 51 119 55 60 66 77 74 115 66 65 74 X 60 61 108 60 113 72 52 57 113 66 66 71 X 65 83 53 66 66 72 55 0 92 81 70 66 35 X 65 58 89 77 51 51 73 55 119 122 69 12 X X 51 78 117 4 58 81 70 X 46 3 100 81 84 67 69 84 97 8 51 50 38 63 108 73 64 74 59 68 58 111 51 67 41 0 92 61 47 106 63 108 106 52 119 56 70 71 65 57 77 62 76 52 52 77 51 58 60 58 6 67 66 8 83 74 58 73 58 51 50 55 120 56 70 70 120 68 77 72 114 73 43 63 110 7 113 73 52 65 38 73 60 15 X 121 31 50 52 70 52 61 51 77 52 75 74 X 65 55 54 58 85 74 55 51 X 74 X 52 64 74 61 56 121 52 51 51 52 63 50 52 59 5 73 104 30 59 55 20 52 55 73 59 52 65 74 50 19 59 56 108 68 51 86 63 50 74 53 97 66 67 28 55 50 74 59 52 70 71 73 20 50 64 54 67 52 52 59 76 51 58 55 111 96 58 14 113 72 54 X X 50 17 37 9 60 65 63 58 30 58 50 51 20 31 54 109 70 52 119 51 51 98 108 51 57 51 52 54 0 60 63 58 59 53 76 69 23 56 68 120 69 73 73 82 18 77 10 56 55 52 73 X 60 3 60 67 52 103 59 68 52 55 54 63 66 52 73 54 52 X 80 89 59 91 60 56 65 62 50 66 55 58 24 54 59 54 93 116 59 65 52 64 64 100 76 55 65 52 58 50 54 80 74 68 X 60 59 52 5 62 63 57 121 66 110 114 74 55 61 60 60 23 51 66 60 27 65 75 52 X 55 74 74 59 53 76 95 78 66 8 X 64 67 70 73 64 59 51 101 52 57 56 78 64 84 75 101 60 52 52 98 X 56 64 95 74 59 54 34 73 51 112 65 X 70 59 80 40 56 2 66 51 61 63 36 60 52 63 52 73 X 66 80 62 57 51 85 114 69 51 72 64 69 67 74 58 105 57 32 51 73 35 2 66 X 47 50 73 61 73 63 32 52 83 64 94 72 11 66 60 67 69 52 66 55 52 58 7 76 54 53 94 62 78 70 57 42 77 55 73 58 78 39 73 66 60 61 52 X 78 52 40 54 87 51 64 69 51 52 66 X 66 52 58 67 52 X 45 52 56 64 58 63 96 67 112 73 66 59 65 15 107 92 68 X 50 62 83 58 58 57 61 58 65 44 73 110 50 51 77 73 52 60 58 61 68 15 53 111 74 51 60 72 66 50 X 52 115 66 51 52 18 75 58 114 103 16 6 73 87 63 97 54 X 14 53 51 111 76 23 68 60 63 50 75 36 75 58 52 65 63 59 52 93 80 X 58 63 79 53 7 53 73 74 86 58 52 59 70 X 44 58 60 70 71 64 68 74 66 73 67 49 60 50 5 52 81 90 29 56 75 62 114 16 73 81 70 57 61 56 108 58 52 74 52 80 51 56 63 67 111 60 67 77 69 86 50 112 5 54 63 75 50 40 65 66 X 63 52 86 64 59 69 100 X 60 117 74 59 X 113 70 30 53 50 X 77 X X 51 54 70 73 47 15 52 20 63 54 73 47 55 40 68 73 70 52 65 61 52 X 51 75 41 52 63 93 52 35 62 76 22 55 24 52 51 55 X 22 114 100 110 73 107 5 97 67 60 121 59 1 73 66 99 55 75 52 66 70 51 63 60 24 69 58 74 68 9 58 69 97 53 81 66 76 122 51 93 24 62 58 54 4 X 58 104 57 97 87 56 70 X 99 49 63 50 66 64 63 75 82 43 54 75 63 64 15 100 74 75 40 68 23 45 86 74 61 52 64 108 68 63 X 63 58 56 59 49 19 74 X 59 58 52 58 63 65 52 121 73 109 81 75 52 95 73 58 54 77 73 65 106 67 66 96 51 60 41 61 63 13 52 58 67 60 113 51 35 2 61 98 63 80 X 50 60 69 66 74 67 48 51 64 68 26 68 38 52 50 74 54 80 70 58 51 52 52 41 73 86 73 108 X 39 51 52 60 66 74 119 54 68 51 63 57 74 68 X 73 39 73 110 60 X 68 64 112 67 67 38 34 56 73 63 50 97 110 74 52 66 62 64 60 73 65 25 49 55 64 59 60 65 52 64 63 86 51 87 121 66 63 63 112 73 68 108 9 X 64 99 51 52 85 63 60 12 74 60 51 107 66 X 55 63 4 74 56 X 78 53 54 52 51 65 75 67 49 64 74 50 50 17 62 63 33 69 63 47 55 118 64 50 87 76 X 66 64 63 54 52 74 63 77 25 72 67 86 5 54 59 71 58 111 52 66 116 61 11 63 78 56 73 74 42 115 96 50 65 75 71 5 75 63 58 67 10 65 52 63 50 77 67 74 55 62 X X 63 50 59 20 74 73 67 62 116 66 74 101 70 37 67 74 68 77 51 55 73 50 50 10 20 92 109 56 105 63 60 52 68 55 X 59 4 52 83 105 59 103 110 54 63 53 66 54 31 51 63 37 66 X 24 44 64 62 50 58 51 52 52 66 55 61 58 111 64 116 50 57 66 66 54 65 0 50 56 58 119 68 62 51 99 60 72 67 57 52 58 70 58 66 61 60 51 X 54 4 61 64 70 54 62 60 87 73 44 67 63 77 108 X 77 73 55 69 X 51 65 113 66 90 11 58 52 61 87 52 52 14 52 60 19 54 58 54 63 59 31 53 X 64 82 54 55 77 10 X 66 52 X X 62 X 75 67 65 59 53 70 73 73 59 58 62 116 52 52 55 68 52 55 58 X 55 X 70 104 5 116 52 78 52 51 X 60 52 59 60 57 70 64 52 0 26 52 55 75 56 63 69 60 55 63 32 56 64 56 112 58 83 66 50 65 56 77 43 109 51 96 51 57 68 52 56 44 94 X 33 53 80 51 72 122 68 51 51 61 83 52 57 63 55 55 64 51 63 74 96 77 55 87 66 110 63 6 X 86 50 74 73 28 10 50 52 58 45 73 56 74 106 52 69 51 50 54 63 52 51 63 16 67 52 55 109 42 X 52 55 65 52 70 6 62 58 51 51 52 59 63 84 65 62 16 58 52 50 34 54 82 60 74 80 71 56 41 52 60 108 51 67 60 23 87 65 53 118 55 X 98 74 80 46 X 59 75 58 51 51 106 73 67 58 52 68 52 63 55 27 64 77 X 23 54 52 50 5 108 73 89 58 79 64 57 110 30 112 77 54 74 52 53 55 51 53 86 50 63 63 64 122 71 12 21 77 100 72 102 63 73 X 19 60 74 64 51 66 40 66 60 88 89 63 73 50 69 86 75 52 38 71 72 73 50 90 51 56 54 73 59 52 8 54 65 34 69 72 51 51 70 59 112 69 73 69 69 55 74 50 50 66 52 76 74 X 67 69 110 55 90 109 X 65 9 76 74 7 65 52 83 52 72 52 67 23 51 101 5 66 58 70 73 73 66 67 51 50 55 72 55 51 109 51 19 88 73 58 86 53 68 X 62 67 72 68 97 51 70 51 60 63 50 19 56 85 108 X 66 75 69 60 74 54 X 56 61 52 38 51 61 69 74 54 108 58 82 56 64 52 X 21 67 56 52 58 68 51 74 81 65 55 67 59 34 94 65 55 58 60 92 54 55 117 73 105 60 52 72 45 58 67 64 72 92 49 74 68 5 66 56 52 70 55 77 112 71 58 52 12 42 109 55 103 120 37 61 50 73 50 66 69 52 52 77 67 87 77 27 22 64 58 53 55 58 52 64 63 X X X 58 32 50 63 73 68 60 66 54 71 71 114 77 X 59 52 108 51 50 54 116 73 12 66 52 58 110 74 52 96 58 113 67 55 109 7 43 75 58 59 51 65 50 13 66 51 52 10 70 25 95 55 52 52 92 57 60 51 65 64 53 55 32 73 53 29 44 60 77 51 83 55 51 58 58 74 68 47 55 63 55 59 12 67 75 19 72 64 93 89 68 55 95 60 50 69 68 52 53 51 1 64 52 64 62 69 70 63 58 77 54 55 54 101 54 54 59 74 82 88 85 87 81 77 42 X 92 112 66 61 62 44 58 X 88 53 38 64 64 X 55 54 81 58 95 66 67 93 58 52 121 32 58 65 67 63 73 108 54 71 2 98 121 52 55 44 50 60 73 52 80 78 66 52 64 51 50 81 65 65 3 91 69 66 X 58 61 80 74 66 58 52 70 87 64 92 56 33 58 67 2 57 95 95 55 67 66 65 55 75 55 74 74 54 61 66 52 54 74 78 56 119 74 75 59 62 51 62 70 71 60 73 54 60 X 50 55 56 50 120 56 53 55 55 69 55 121 63 60 66 X 67 64 65 19 69 64 58 107 65 52 31 112 52 77 63 73 59 105 104 52 67 76 50 62 62 66 51 120 52 51 81 55 56 45 85 61 66 60 56 81 60 60 51 98 52 17 72 X 108 66 68 46 68 18 95 X 109 56 51 68 53 87 83 59 58 58 62 63 56 59 3 68 16 65 99 70 59 73 X 52 0 74 52 69 57 51 61 53 54 52 122 105 56 52 56 54 92 106 70 85 64 60 117 66 67 104 26 66 73 52 52 10 69 89 70 63 78 X 5 116 73 75 49 52 44 51 18 61 14 60 54 56 52 X 28 82 55 102 116 69 55 50 65 59 112 50 61 69 61 72 64 41 56 109 46 74 60 52 107 52 51 73 51 51 67 63 54 74 73 74 55 51 73 66 50 96 96 38 X 53 104 12 59 0 X 69 66 0 64 74 73 9 82 60 77 104 73 99 64 73 57 52 50 63 57 67 54 60 40 93 69 66 52 58 52 70 62 78 52 91 51 66 54 51 54 54 52 73 52 64 59 17 53 52 113 68 61 52 64 63 72 67 116 73 59 63 64 64 73 55 14 52 75 63 73 53 51 67 55 65 61 64 18 X 74 72 52 30 24 54 74 58 119 50 X 51 64 58 55 93 94 103 60 58 73 44 3 55 55 32 73 53 65 X 112 91 73 86 55 64 X 63 2 55 X 52 22 78 54 103 77 55 X 116 64 96 66 55 98 53 52 56 55 116 14 66 66 86 60 81 68 52 67 69 66 40 113 63 51 74 64 50 56 52 61 61 89 X 50 64 64 55 52 77 20 67 105 52 80 42 23 61 55 73 68 51 63 52 55 9 63 61 73 112 36 87 51 52 49 57 92 52 52 19 52 70 60 55 58 91 51 89 70 96 73 73 75 X 71 60 73 73 51 X 60 66 60 50 58 50 52 68 52 71 X 68 52 65 95 52 72 60 16 92 111 58 59 50 65 52 99 52 50 50 69 63 55 9 52 53 61 103 93 73 52 52 51 93 X 50 58 99 53 21 70 66 74 60 76 X 72 52 107 53 8 50 105 70 69 58 50 X 68 67 56 52 58 52 58 X 62 63 49 80 70 52 116 40 87 52 51 51 45 52 59 67 116 77 45 115 60 50 11 52 110 73 60 75 61 78 60 52 73 15 69 52 73 53 110 67 65 58 39 56 50 66 51 66 52 76 X 59 X 52 65 96 68 36 61 74 52 77 51 38 55 74 50 65 62 55 66 105 66 54 44 52 58 91 60 60 61 51 101 77 66 X 62 73 43 68 74 99 116 52 4 122 60 28 51 40 57 58 17 55 7 40 69 73 57 63 59 65 54 56 34 63 50 53 69 71 60 72 78 64 103 14 X 50 6 52 72 53 57 53 56 73 91 12 67 71 64 96 95 64 67 108 68 55 33 X 34 52 63 108 112 57 74 60 63 X 61 61 52 113 60 50 66 109 83 63 58 87 55 73 67 114 66 50 X 52 55 61 27 55 64 57 69 55 15 97 36 52 50 121 56 113 66 58 60 20 113 107 74 X 0 44 55 51 61 51 X 52 56 70 31 60 61 51 X 69 81 97 50 50 66 60 73 73 57 65 70 83 57 53 53 67 73 51 60 88 63 41 21 58 51 X 52 116 4 X 61 77 66 66 71 40 50 72 49 74 78 31 53 99 63 52 69 55 50 67 66 29 58 51 14 59 87 115 52 78 95 88 56 85 60 73 122 51 X 75 30 74 49 80 63 91 67 122 66 64 X 55 64 67 55 67 51 64 80 58 65 61 66 66 87 55 81 77 40 86 103 111 73 51 118 59 100 73 65 76 65 X 119 67 120 58 120 80 50 74 52 70 56 52 69 113 63 60 59 67 X 52 81 75 51 78 83 53 X 52 67 73 63 67 88 105 52 68 51 52 64 73 21 67 60 88 50 40 70 52 66 68 69 58 55 73 52 58 61 100 52 74 52 73 69 60 9 52 79 61 63 58 116 67 58 118 52 55 64 55 74 55 91 60 53 64 77 65 58 51 92 63 2 115 X 72 69 60 113 116 57 52 74 110 56 51 4 58 44 59 55 X 104 19 X 66 65 55 109 61 64 50 63 X 52 51 75 66 73 63 75 62 22 52 17 62 122 58 60 X 74 94 64 113 72 61 59 52 73 66 61 9 X 50 52 50 73 101 58 72 63 26 X 92 52 86 76 32 68 42 66 52 56 83 63 63 64 64 75 102 68 50 66 74 73 46 54 60 35 73 108 62 64 69 51 74 72 66 39 55 63 119 74 65 54 4 12 51 47 50 55 54 78 54 108 25 55 64 23 74 32 94 66 X 51 103 110 120 50 3 112 64 93 73 58 66 100 104 58 74 87 56 72 55 77 58 58 51 110 X 53 X 63 57 50 67 55 70 52 66 84 76 X 64 56 30 50 38 63 70 58 61 64 74 61 28 63 58 55 55 50 108 87 107 51 51 66 102 52 68 53 39 60 X 55 76 51 72 50 60 45 73 74 68 69 6 52 54 113 35 103 65 55 60 X 67 55 63 68 79 25 43 51 58 75 68 66 114 X X 51 81 78 65 53 108 64 54 94 1 61 X 51 X 51 75 72 52 58 50 77 66 54 6 68 70 58 X 54 22 76 58 58 54 82 60 28 61 30 7 74 58 54 101 35 67 64 58 84 80 51 X 80 60 63 38 63 60 X 0 60 52 68 81 73 5 15 51 4 74 81 55 62 X 11 52 64 63 109 116 66 105 53 50 55 64 2 28 86 94 55 55 95 60 X 50 55 50 69 27 60 0 42 51 65 60 52 63 72 91 73 61 58 54 X X 66 55 79 74 51 37 52 98 52 55 80 60 121 51 52 60 80 51 60 55 52 54 58 50 52 108 55 55 109 52 55 69 63 81 72 X 58 88 72 29 57 X 62 21 63 20 114 118 52 51 74 77 49 52 91 66 60 60 15 55 49 81 66 53 37 50 52 74 74 104 77 60 66 17 51 119 97 104 51 120 67 50 52 74 46 63 80 77 110 73 84 73 5 69 57 59 73 73 72 50 106 29 X 54 101 112 0 71 71 66 73 52 44 58 51 49 62 54 51 61 67 56 51 X 52 24 X 60 50 77 120 56 X 57 36 80 60 120 50 70 61 X 74 40 73 58 86 55 74 93 103 72 51 53 53 68 75 52 67 55 74 57 66 57 64 70 61 60 61 68 66 90 55 73 61 66 62 51 69 57 66 69 60 71 75 51 83 122 58 64 52 69 69 66 52 54 79 21 114 118 76 62 41 55 67 76 17 59 91 74 65 59 54 39 67 60 X 86 52 63 52 79 56 71 118 X 63 17 89 X 52 51 65 63 55 10 69 33 115 65 46 89 63 113 X 117 55 66 51 105 52 50 108 59 63 67 87 63 54 73 60 108 68 104 66 51 13 119 55 89 60 8 56 52 63 54 69 52 94 X 50 19 50 107 61 66 43 63 53 73 52 107 X 72 65 51 78 52 22 52 55 66 64 X 50 91 64 55 91 17 60 60 X 51 17 59 50 106 6 52 56 62 72 68 66 X 50 99 58 52 58 51 56 52 67 X 35 73 73 52 51 20 101 58 28 72 73 69 55 55 68 62 44 55 59 96 50 120 71 60 67 54 60 76 51 52 97 67 60 50 61 98 54 X 75 77 52 106 X 108 66 58 119 95 72 55 80 50 56 60 70 52 73 1 68 68 101 24 80 58 52 67 50 65 79 61 5 52 51 X 62 74 51 52 63 23 X 61 66 115 70 56 69 72 112 52 65 83 50 74 93 57 X 57 51 61 52 60 102 25 52 61 X 31 48 63 X X 52 120 55 52 51 63 39 52 107 68 52 51 66 52 75 60 52 52 44 52 52 104 52 49 74 37 107 121 53 70 92 61 60 71 50 52 54 51 53 63 X 67 18 54 87 50 86 87 58 52 52 63 109 54 52 119 50 108 9 50 61 70 69 57 74 45 113 42 118 52 67 93 52 55 51 60 0 55 51 61 52 65 63 30 75 66 63 115 122 52 60 58 1 50 74 69 46 77 67 75 52 78 81 104 53 59 52 66 53 61 66 75 74 22 58 56 61 54 76 55 73 73 51 113 65 58 117 80 52 65 55 10 61 70 51 61 82 109 67 73 75 97 68 67 64 52 73 115 54 119 7 52 60 X 58 77 110 65 74 50 75 62 61 74 52 54 40 54 63 54 52 13 58 59 X 11 70 18 29 3 56 63 50 104 67 52 118 37 67 103 52 52 54 51 67 66 55 52 87 24 72 X 68 58 51 73 94 74 63 63 X 63 74 52 66 51 67 65 55 51 72 4 75 62 73 63 51 51 66 114 74 68 73 60 63 54 116 54 52 60 101 67 108 77 117 52 50 52 51 61 30 54 54 94 87 55 38 17 1 77 58 59 60 74 52 62 65 121 51 73 58 75 52 91 21 63 67 X 7 61 60 57 50 53 60 63 50 64 63 58 74 115 52 68 55 121 X 65 68 114 26 63 70 58 67 45 122 51 71 60 102 61 71 54 57 50 X X 55 X 66 63 71 73 51 91 74 68 55 73 100 118 52 12 59 91 56 52 60 52 X 51 55 60 50 92 56 65 120 81 67 X 57 90 50 54 X 57 60 54 35 68 87 102 98 62 66 52 50 25 73 74 80 115 54 51 108 56 52 60 64 65 66 65 52 53 16 58 51 116 59 51 106 100 52 73 52 59 119 58 70 36 59 60 52 72 58 54 63 96 119 74 52 107 58 81 58 20 38 63 59 X 70 55 51 52 72 66 91 18 74 75 105 73 51 51 65 52 58 64 52 55 30 66 97 95 117 73 X 23 68 36 X 51 77 51 X 63 55 71 51 107 65 118 61 63 60 2 58 17 52 77 51 63 108 51 54 69 75 76 52 51 100 2 88 50 9 58 77 61 66 51 52 88 77 109 64 100 67 88 50 55 50 58 52 98 99 56 X 53 64 50 52 44 51 55 116 52 67 73 52 75 68 56 56 112 58 51 86 68 74 73 60 51 67 73 60 74 54 55 89 58 69 67 52 27 51 71 115 59 59 63 X 51 85 52 55 65 47 77 56 109 108 X 54 52 56 73 74 55 49 X 99 74 X 55 62 65 64 55 53 33 52 72 55 68 75 31 36 54 52 68 13 110 60 75 58 61 50 70 73 109 4 5 73 110 65 60 66 91 55 74 50 112 106 33 56 68 114 50 52 63 0 119 58 63 115 82 121 116 52 73 64 50 9 51 68 100 51 95 67 52 69 63 60 66 92 X 58 65 X 1 81 50 76 63 21 52 52 109 64 63 64 50 58 70 75 51 52 66 61 88 50 60 52 102 77 63 52 110 69 74 67 13 15 X 78 121 50 55 54 75 X 59 108 88 54 67 52 52 76 78 94 6 106 52 86 38 X 57 103 65 49 62 50 87 59 49 74 51 65 68 102 55 73 50 X 51 9 93 X 52 50 39 52 53 63 52 66 57 50 36 111 61 96 59 98 54 75 51 X 67 70 42 52 52 63 74 102 73 51 107 66 81 83 115 87 94 17 X X 50 X 81 90 60 82 66 59 57 X 54 122 52 68 52 60 67 60 60 55 73 84 55 81 54 54 50 55 67 55 77 52 64 53 80 72 55 54 73 117 55 52 60 66 59 76 52 73 66 58 52 103 120 33 X 52 77 66 52 43 55 68 63 55 69 69 56 59 72 X 72 82 71 52 74 52 54 50 52 58 65 98 67 52 61 87 52 63 78 10 74 51 97 60 68 66 52 67 54 52 66 52 58 84 55 52 63 50 X 52 70 59 66 52 86 61 63 72 78 110 73 63 77 51 57 60 105 63 35 54 54 73 99 61 50 108 X 66 X 65 63 52 52 119 52 52 51 77 77 74 122 114 63 26 73 62 55 50 56 58 74 61 52 55 61 14 33 54 13 16 60 52 50 63 85 55 52 68 70 56 74 56 55 52 64 37 74 64 X 65 55 62 77 52 109 57 54 72 52 67 X 5 70 56 72 40 52 X 51 99 54 X 94 58 52 40 55 67 60 60 77 105 74 84 50 41 63 51 79 52 41 75 75 31 5 52 63 63 45 58 51 51 61 78 58 85 52 112 73 52 54 51 7 6 94 61 56 63 42 63 52 52 55 69 71 109 61 66 79 98 54 92 51 66 55 51 58 53 52 68 113 76 33 52 54 77 66 95 77 51 55 58 54 73 107 62 50 68 65 50 111 55 68 62 50 63 66 89 61 64 58 54 74 70 66 111 73 51 63 61 54 52 65 118 74 11 55 52 70 50 52 52 60 60 52 52 29 68 52 X 54 84 52 83 77 52 63 73 63 85 80 61 67 57 75 44 58 103 52 73 60 52 88 58 73 52 51 50 56 87 57 51 92 52 29 74 64 78 X 50 59 88 73 52 71 96 74 58 X 63 55 50 51 66 55 121 X 57 63 119 51 59 73 5 52 52 60 X 80 60 60 66 55 68 73 66 51 64 118 72 63 83 51 35 107 53 66 52 77 16 64 X 116 73 28 116 52 61 81 50 60 55 51 64 66 52 75 51 44 71 112 52 68 X 58 48 42 69 62 103 50 69 56 4 20 65 36 40 61 74 59 55 50 112 55 74 42 55 68 52 73 75 63 98 54 25 94 73 56 56 52 54 58 8 X 109 74 68 74 73 121 74 9 X 113 73 51 66 27 34 106 121 54 73 X X 100 106 51 18 54 60 27 59 74 55 55 52 94 1 64 50 73 103 121 62 52 54 66 73 X 88 52 0 87 55 55 55 114 5 90 50 85 52 64 33 X 31 35 117 51 79 100 23 54 59 64 51 51 19 51 67 X 64 52 63 67 51 52 61 51 73 63 8 65 61 X X 66 105 50 52 51 67 52 X 55 77 68 29 66 64 50 51 55 59 38 55 52 49 67 51 4 58 64 52 62 57 57 69 52 52 18 96 60 68 121 50 122 53 62 101 55 63 52 52 73 65 74 75 87 94 67 107 96 29 73 64 52 61 50 74 51 121 73 67 50 60 2 72 50 52 63 32 77 X 52 63 55 57 5 58 70 60 71 18 85 57 50 34 20 78 56 108 53 120 12 55 51 66 55 60 60 63 60 51 52 51 72 62 55 33 55 54 68 58 67 55 109 55 62 105 68 55 40 29 52 77 50 74 74 71 68 X 34 50 47 119 62 58 53 73 110 51 52 68 8 57 89 92 60 66 64 58 122 54 83 77 56 59 52 65 55 67 53 50 64 58 60 108 X 51 X 44 51 109 63 X 64 63 50 108 2 X 60 97 54 63 66 13 121 59 122 50 75 73 67 51 52 60 70 68 X 69 64 50 91 52 58 66 55 67 105 56 47 51 61 62 91 60 61 55 98 63 77 52 58 46 59 89 52 66 10 58 104 53 104 52 75 66 73 30 80 52 60 68 31 72 50 69 116 51 50 67 54 75 72 59 52 108 51 67 55 55 78 81 111 20 56 63 56 75 64 X 51 58 81 51 19 52 67 56 52 77 66 73 64 64 52 83 105 65 X 55 52 67 83 56 80 73 74 41 104 X 51 94 52 67 65 50 47 108 116 74 52 66 86 25 59 66 28 73 85 70 55 52 52 61 113 X 68 106 28 55 66 17 58 42 67 51 62 67 53 55 12 55 59 X 68 58 60 61 55 60 59 54 74 62 13 55 60 63 76 95 X 59 55 54 67 51 17 73 57 71 54 63 53 68 70 64 55 6 51 51 13 72 72 50 104 52 55 58 89 66 60 82 73 52 52 73 74 52 42 64 60 X 74 55 77 68 21 58 55 107 52 82 55 91 73 65 55 92 68 73 63 89 71 60 70 68 51 50 28 X 64 52 56 X 72 77 54 102 33 53 52 63 54 58 17 67 120 68 52 42 31 10 74 58 63 56 X 4 52 68 63 57 72 88 58 51 26 50 81 98 14 50 114 72 63 66 56 101 68 61 52 52 52 75 100 38 67 87 76 52 66 114 81 63 70 75 67 66 56 76 4 59 1 74 X 66 60 67 74 52 70 88 46 65 57 58 62 60 50 25 116 X 64 109 51 10 51 52 66 31 50 51 2 52 71 50 65 58 77 58 50 100 73 63 74 59 29 74 61 51 63 81 70 51 49 98 31 67 56 50 64 62 51 59 88 73 43 21 61 11 6 59 120 49 56 71 56 X 80 104 51 60 58 46 10 51 31 54 51 52 25 X 56 65 60 61 99 29 57 89 52 111 X 73 118 74 63 13 42 50 119 47 99 X 52 71 19 20 64 52 55 51 33 119 50 60 66 91 28 58 40 X 50 58 66 67 81 104 52 58 111 92 54 39 63 61 73 70 63 52 X 71 66 93 54 58 55 58 74 58 64 117 63 74 58 65 33 66 67 63 38 67 64 24 52 112 33 65 111 63 79 98 44 52 58 52 12 115 57 X 5 69 75 64 56 72 52 66 75 56 110 73 60 39 63 109 52 73 61 42 52 60 45 70 74 62 50 63 81 X 19 49 51 51 98 51 60 51 59 51 X 64 105 55 90 73 65 101 50 51 111 56 65 63 37 52 59 37 79 53 74 52 74 55 50 X 15 14 59 54 52 95 50 68 36 58 63 63 87 X 107 73 52 70 64 55 74 75 77 X 57 52 110 8 66 1 67 98 55 64 63 8 23 79 23 52 74 61 95 73 58 107 61 58 73 65 55 28 54 3 52 60 29 52 52 89 51 68 58 56 54 73 61 58 70 52 69 51 70 56 108 55 58 6 51 61 52 51 28 52 103 88 60 110 52 54 63 60 56 95 51 66 109 73 51 50 55 63 73 74 67 55 56 57 52 57 61 16 53 52 55 50 73 51 9 52 111 120 53 74 X 86 74 31 53 66 52 45 59 18 73 53 52 52 39 73 52 52 55 51 61 106 3 60 80 61 18 52 73 33 113 106 50 116 60 69 51 55 34 52 72 69 5 35 52 55 52 X 90 58 103 76 13 52 53 110 65 64 X 61 119 112 73 9 75 51 63 71 63 51 51 58 61 57 73 62 45 X 58 60 85 55 55 58 52 52 59 68 55 52 75 10 80 66 77 56 52 X 55 61 60 70 58 55 23 58 69 64 X 67 55 73 59 63 X 75 52 52 39 23 58 56 73 46 63 66 63 58 4 31 64 63 60 52 52 X 52 66 63 55 52 63 55 61 108 63 72 52 61 63 67 61 55 58 61 87 67 65 55 94 51 77 52 50 64 28 104 86 67 58 61 54 55 70 8 68 52 X 73 63 50 122 50 64 66 65 51 67 56 58 X 45 121 62 67 99 52 62 69 52 51 X 68 113 119 63 115 58 73 80 66 38 77 64 100 66 75 111 114 31 57 13 61 53 64 79 63 121 18 74 64 52 52 X 70 74 64 61 52 44 80 12 77 69 51 75 51 56 52 68 73 96 79 62 59 54 59 70 36 69 52 79 75 77 90 52 60 62 57 38 102 119 58 57 27 51 56 26 67 98 60 103 109 101 51 70 0 70 70 55 43 106 120 73 66 X 83 10 102 40 35 58 X X 64 51 63 100 X 70 66 52 50 8 58 100 101 59 65 81 4 52 11 66 111 53 45 2 93 28 27 51 78 X 53 75 55 52 112 58 X 65 67 72 66 64 20 56 58 69 25 63 81 73 104 59 52 107 55 51 112 27 57 108 63 52 63 121 80 38 114 69 50 63 X 63 55 97 55 56 92 42 68 54 X 55 52 54 114 52 52 118 X 61 50 87 72 67 67 74 65 41 52 5 63 97 121 60 66 63 52 50 73 X 58 61 52 14 75 52 61 59 43 52 51 56 X 121 62 66 74 51 67 112 80 63 66 72 108 68 83 109 52 30 77 63 80 60 52 20 74 63 52 64 63 64 57 52 54 119 7 14 28 77 30 77 89 73 86 55 58 43 60 105 X 74 64 60 70 54 52 52 74 50 52 59 83 51 95 68 60 101 53 54 71 93 5 45 51 51 81 52 67 50 69 61 51 58 58 57 58 58 51 104 58 73 83 52 73 60 70 106 58 52 74 52 54 66 73 73 63 109 4 74 70 50 68 37 64 60 122 77 21 77 90 54 52 51 56 50 56 41 98 59 72 67 52 X 56 50 63 90 52 X 51 105 52 73 87 69 55 61 4 51 X 20 56 57 65 64 68 X 74 77 63 87 50 66 102 61 70 56 66 31 52 106 115 58 44 96 51 108 X 118 45 60 52 57 74 53 56 X 66 89 92 52 67 101 19 67 66 99 24 62 73 49 50 43 73 54 53 63 8 110 X 34 52 55 67 64 51 102 96 31 70 67 58 102 53 52 110 X 51 16 58 8 62 59 51 52 15 56 X 68 50 63 74 29 52 92 67 58 64 64 52 55 72 73 95 70 38 52 76 74 52 50 70 61 22 21 63 8 94 50 65 52 X 70 65 72 58 73 52 77 67 74 6 75 52 52 58 51 77 52 1 32 52 74 74 65 117 73 93 52 56 58 64 52 104 73 65 35 61 110 57 50 70 122 61 57 45 3 22 70 47 66 13 52 74 50 78 75 52 51 68 70 52 74 60 9 68 60 38 61 78 114 11 50 12 52 105 88 55 52 45 14 73 55 68 50 58 29 56 68 55 X 73 51 63 34 90 55 53 53 80 52 67 40 63 65 68 52 X 1 74 69 55 53 55 52 62 72 52 52 108 121 114 92 108 59 66 94 55 69 66 52 64 60 14 55 11 60 60 64 81 50 108 67 83 59 109 115 109 10 57 52 80 59 65 52 52 52 81 70 52 59 52 80 75 X 67 85 68 102 17 52 X 24 4 46 61 81 66 61 104 63 61 51 19 67 56 60 55 51 52 58 59 51 94 72 55 88 70 64 59 76 64 91 X 55 92 55 64 52 73 114 93 67 X 55 70 80 8 61 52 52 52 27 86 52 95 X 17 0 56 68 71 103 63 63 94 54 74 95 63 54 74 61 74 80 60 56 74 50 54 51 51 47 57 58 51 77 67 50 53 50 77 51 35 54 63 73 65 X 54 51 66 66 69 94 65 58 58 52 50 67 59 52 93 63 96 20 73 73 66 66 X 99 23 50 8 63 103 66 63 31 69 59 69 114 66 55 56 52 62 32 51 101 82 X 74 34 49 50 62 51 44 61 80 55 54 52 80 52 52 74 107 55 73 96 78 52 67 52 66 55 57 55 52 79 67 50 54 55 63 20 118 63 X 57 40 66 61 52 91 55 64 55 51 X 65 36 73 69 6 X 95 60 121 3 61 65 59 67 101 88 81 51 119 55 68 53 68 44 56 52 45 91 107 82 88 60 51 68 50 74 58 50 52 96 50 60 58 56 58 60 108 74 52 X 33 22 74 52 54 92 73 55 50 28 31 X 106 52 2 68 102 52 37 77 52 55 99 55 84 55 85 117 4 100 73 109 64 79 58 100 70 58 X 51 X 50 61 38 101 64 54 56 71 120 65 76 74 56 64 105 56 58 X 29 63 77 54 50 63 61 52 73 33 56 35 99 67 68 63 52 74 100 63 111 99 63 53 80 63 65 71 97 49 117 61 12 32 64 52 X 72 82 58 73 77 99 58 68 52 52 66 70 101 69 77 63 54 48 67 72 68 51 62 61 67 56 92 X 90 71 51 67 38 70 64 X 65 89 50 63 68 59 55 67 65 37 74 120 60 X 67 65 66 73 51 51 51 72 52 55 55 116 50 51 74 51 17 52 52 60 49 78 65 X 55 9 60 72 41 60 X 66 57 36 78 52 12 52 52 115 63 72 52 71 51 50 81 52 92 77 X 52 45 77 68 58 70 119 83 61 74 58 57 52 52 41 60 45 X 69 74 60 57 89 59 50 112 59 65 50 54 52 70 110 52 67 67 62 57 64 112 X 114 52 72 74 97 72 52 47 60 60 93 104 67 66 74 55 94 54 66 69 59 58 54 11 98 66 54 50 65 74 X 6 96 74 69 50 66 X X 64 64 56 71 54 X 67 92 71 67 52 44 50 50 70 59 60 92 52 80 58 35 55 115 27 51 55 52 40 35 52 55 46 52 106 66 107 68 16 66 72 74 44 94 66 10 62 113 56 74 52 40 109 52 71 45 65 89 66 52 104 30 114 52 66 83 61 27 50 63 107 56 50 66 114 107 73 70 99 59 73 66 37 77 52 65 66 52 118 4 55 68 33 77 X 52 X 51 51 67 91 1 52 77 56 57 73 68 50 32 55 70 68 61 108 94 68 52 66 88 96 42 103 63 63 50 52 55 47 51 23 65 69 58 52 65 60 50 68 49 51 X 69 52 52 74 106 52 58 84 77 10 52 93 X 74 72 50 73 56 50 52 60 35 21 74 78 68 50 52 74 66 60 116 64 90 69 60 51 51 61 54 53 77 59 77 52 58 79 52 77 76 74 73 53 83 87 82 61 62 52 66 60 54 114 50 54 64 102 74 80 101 67 25 61 97 66 41 66 80 52 103 60 114 51 33 107 51 68 111 70 74 69 74 64 68 79 51 34 67 61 75 77 74 51 51 73 15 58 60 51 52 58 74 60 64 77 51 68 67 118 58 90 61 65 54 64 X 57 64 64 55 58 88 73 65 98 60 66 109 119 109 70 58 52 56 X 122 59 4 114 X 50 X 46 78 122 77 50 58 54 37 59 62 67 69 52 24 70 25 103 52 62 95 63 117 78 51 82 73 30 100 86 93 68 59 50 73 59 77 59 103 56 74 93 58 58 64 50 X 65 63 74 52 58 62 76 77 63 88 64 63 61 66 54 119 112 95 52 61 108 X 55 118 78 X 66 66 67 93 111 57 74 63 59 61 28 52 73 67 86 98 74 117 51 64 68 52 66 74 66 5 52 45 34 110 51 51 X X 64 4 102 60 67 108 X 66 87 76 50 120 96 60 68 52 88 109 121 56 60 52 59 78 49 39 56 66 56 50 70 63 96 52 64 54 74 117 52 60 52 70 51 74 76 3 50 100 61 86 80 18 82 X 51 74 47 85 75 51 92 54 94 77 63 51 70 74 X 108 74 122 64 52 75 67 94 54 51 73 89 X 55 73 72 51 13 52 83 69 63 66 69 68 47 93 67 89 52 67 60 67 72 23 64 68 56 64 77 52 64 99 4 50 67 58 67 52 52 77 18 55 87 63 67 26 X 51 66 115 50 79 60 27 76 51 X 95 22 114 73 58 115 X 54 67 74 52 52 58 87 52 62 73 121 X 87 55 52 54 51 79 74 51 50 64 74 66 59 0 64 61 94 65 63 99 73 X 51 114 6 55 106 90 66 60 68 74 104 68 56 52 67 19 60 52 94 56 74 81 63 67 1 52 74 52 61 64 55 50 56 4 52 116 67 65 51 110 20 62 20 67 73 51 56 73 54 66 59 64 59 X 65 15 59 59 17 65 63 74 63 34 70 X 64 70 34 67 27 X 87 61 60 52 107 74 72 27 X 54 57 60 55 58 63 121 94 51 66 65 73 67 54 85 70 75 52 67 55 67 76 74 73 67 67 73 116 82 105 74 120 67 55 55 X 58 60 66 60 63 54 61 60 66 52 28 91 67 70 74 89 67 55 50 55 96 109 60 67 58 67 122 61 108 79 39 66 60 8 65 77 54 53 67 77 52 60 80 X 38 X 88 50 52 66 77 121 52 96 34 118 52 77 50 56 71 55 52 110 50 54 56 70 50 108 10 108 57 56 56 68 50 61 95 109 99 52 74 63 52 24 22 59 64 64 56 47 51 X X 63 66 54 0 55 19 76 43 55 52 65 65 66 72 90 68 115 68 110 51 60 68 83 64 58 52 55 73 55 112 58 58 66 20 35 59 84 55 80 51 76 72 64 67 63 67 52 63 73 54 58 63 52 57 87 102 68 X 57 54 51 77 19 69 65 50 52 66 66 103 63 65 55 X 73 79 31 97 83 28 52 58 68 29 38 X X 53 73 45 70 X X 51 78 100 X 60 59 74 52 X 120 68 66 51 20 79 59 103 65 8 60 109 63 X 111 X 51 64 71 121 83 94 X 73 59 66 50 78 51 52 92 52 56 21 73 52 21 68 74 115 51 78 50 65 76 30 53 56 66 9 105 52 50 46 100 52 65 74 17 51 58 51 68 61 105 55 50 67 77 66 74 78 52 67 33 94 57 77 88 59 56 73 52 68 90 67 50 40 52 50 67 74 103 77 109 50 117 46 58 86 52 52 57 73 74 54 65 74 50 51 50 33 48 73 51 112 51 80 52 63 74 52 38 113 20 50 74 68 61 53 64 10 107 51 X 107 68 64 X 52 58 60 50 52 75 110 52 52 70 54 5 74 55 116 63 50 52 55 55 58 64 9 52 52 77 51 77 52 55 60 76 40 X 52 50 68 52 51 51 56 75 X 54 5 73 71 63 65 107 73 55 X 82 89 107 74 25 108 82 X 67 63 26 97 74 80 110 52 71 51 60 58 64 106 83 75 90 63 116 24 73 53 113 38 60 X X 81 52 51 88 63 66 X 51 63 52 83 93 52 6 26 51 50 73 52 66 65 66 76 66 59 104 52 96 54 52 62 52 55 74 74 66 X 61 73 53 78 X 56 96 109 67 60 60 50 18 77 63 44 68 63 50 86 10 56 83 32 68 54 75 75 74 60 64 58 89 107 67 57 95 52 66 52 80 60 72 96 74 63 52 50 56 50 104 73 64 63 31 97 57 52 66 58 77 60 66 56 51 108 51 16 87 52 52 51 74 39 73 58 79 55 118 64 58 94 50 54 54 112 43 64 52 43 50 59 104 70 51 51 50 104 76 53 109 66 75 X 34 77 63 73 58 65 9 58 74 61 66 41 64 59 63 114 71 21 60 50 78 24 61 101 53 51 61 65 94 70 55 X 27 94 50 55 108 66 52 86 70 58 64 121 50 50 63 67 41 66 66 68 110 6 X 91 71 66 63 56 68 63 73 89 55 20 107 53 69 66 46 8 55 91 55 91 96 62 51 116 55 65 72 55 68 8 100 2 50 83 7 122 4 60 50 41 50 5 117 50 51 65 61 59 70 55 52 66 29 68 33 61 47 47 67 114 51 65 69 52 67 62 67 66 72 61 108 51 59 56 56 100 74 57 67 98 40 52 58 52 114 7 63 55 60 66 50 80 95 74 106 61 78 109 87 52 X 18 68 51 35 84 0 52 51 57 X 86 X 66 118 66 76 77 X 56 78 92 55 73 116 64 66 91 58 52 69 53 52 63 120 73 69 72 60 66 112 73 121 62 67 78 63 98 55 67 58 X 55 69 54 52 102 21 51 77 94 114 72 61 109 67 83 58 55 39 87 81 62 66 52 52 17 X 51 77 54 59 24 54 76 57 60 10 107 68 119 62 60 66 23 52 69 52 58 121 120 54 65 54 51 29 66 99 60 74 58 65 88 98 55 66 74 50 X 66 17 66 113 73 52 65 65 70 65 60 110 63 89 91 56 51 63 78 7 54 51 106 60 81 73 55 103 71 55 66 55 58 X 55 64 54 52 41 55 66 67 52 93 4 56 37 51 15 39 102 29 58 71 52 34 55 19 66 91 101 117 57 X 121 66 52 74 57 50 66 50 52 45 52 122 58 5 52 44 59 67 64 68 55 60 56 73 73 63 106 52 52 X 58 63 73 34 61 52 65 14 52 64 70 50 56 120 73 56 74 61 36 51 31 61 73 27 52 52 67 69 116 96 85 73 2 77 63 113 26 57 51 61 73 65 27 58 72 52 69 66 56 58 52 97 54 51 33 101 65 66 57 69 82 66 51 64 61 8 61 60 15 52 53 65 75 50 73 52 59 34 102 113 54 67 54 62 116 63 55 74 73 36 74 66 60 55 66 56 50 21 66 69 52 61 X 52 52 51 63 77 113 70 63 69 36 60 97 52 58 52 66 51 73 55 94 60 51 X 64 5 45 52 64 28 52 58 65 60 19 59 52 82 68 63 71 75 73 114 56 64 X 51 X 67 50 91 73 59 63 52 77 98 20 52 57 101 81 51 73 73 67 3 83 63 53 52 58 74 64 58 57 52 72 73 61 62 52 103 87 50 64 28 50 X 5 61 51 55 83 118 104 52 66 7 X 21 56 75 5 15 73 76 66 62 117 50 55 56 61 52 50 64 61 60 52 95 59 119 5 71 101 36 66 51 73 57 72 85 63 52 52 49 52 74 80 74 68 52 73 63 105 81 58 66 70 55 60 86 54 113 52 X 52 80 54 51 109 5 58 52 51 66 12 63 50 86 106 82 42 77 65 119 52 73 69 54 120 56 52 111 112 63 35 58 66 66 54 8 74 52 63 90 57 54 53 118 59 68 0 54 55 54 76 52 40 52 54 97 63 89 73 74 120 X 103 78 39 58 43 95 52 62 63 66 77 51 64 63 76 78 91 52 67 97 55 23 1 34 43 62 61 101 59 92 53 64 61 108 58 1 66 73 3 70 26 51 62 65 24 15 11 67 59 89 88 74 70 60 73 120 103 99 55 66 95 51 X 62 74 17 52 53 69 55 52 73 9 67 50 66 55 X 75 117 73 51 59 61 58 97 63 72 50 52 4 60 70 57 51 66 4 83 111 52 75 59 110 51 74 60 60 52 54 58 58 0 109 60 121 64 101 51 52 60 63 51 50 118 29 52 58 74 108 14 58 74 51 54 56 77 52 52 53 X 3 46 65 21 54 54 52 39 73 103 50 60 60 55 15 52 115 51 52 72 53 85 63 80 66 88 111 X 121 93 59 86 66 110 76 67 85 52 60 73 73 34 56 64 80 59 56 58 52 67 56 52 77 60 22 60 55 52 51 25 90 113 28 117 67 60 X 51 72 70 54 56 52 64 52 105 51 58 52 51 X 73 74 X 50 98 67 64 58 51 4 89 56 66 4 85 54 51 51 56 52 68 52 51 119 66 60 52 49 73 52 77 68 65 30 63 4 57 38 X 64 68 117 58 63 50 52 51 54 18 101 108 15 62 56 64 62 34 74 31 50 57 54 58 67 X 108 54 52 110 90 64 66 50 8 97 113 76 75 61 52 55 90 60 80 60 18 51 58 119 40 67 73 71 73 71 58 53 95 84 111 82 86 101 60 52 73 56 7 58 83 63 53 67 52 60 50 52 67 90 105 78 9 66 57 50 53 51 95 X X 51 52 64 51 52 102 25 61 67 68 58 52 50 15 52 52 62 63 51 21 53 87 63 71 52 70 54 50 72 51 52 91 45 55 55 102 74 30 77 X X 38 50 61 63 X 55 52 40 117 51 74";
        assertEquals(expectedRes, currentRes);
        tearDown();
    }

    @Test
    void testCaseSample100_000() {
        setUp();
        sample100_000.parse();
        affectation = new AffectationAPB();
        List<Student> studentsSolution = new ArrayList<>(sample100_000.getStudents());

        SatisfactionMetric satisfactionMetric = new SatisfactionMetric(sample100_000.getSchools(), sample100_000.getStudents(), new File("../testRessources/apbSolutions/solution_100_000.txt"));
        satisfactionMetric.addSchoolToStudent(sample100_000.getSchools(), studentsSolution, new File("../testRessources/apbSolutions/solution_100_000.txt"));

        for (int i = 0; i < studentsSolution.size(); i++) {
            assertEquals(studentsSolution.get(i).getInSchool(), sample100_000.getStudents().get(i).getInSchool());
        }

        tearDown();
    }


    @AfterEach
    void tearDown() {
        sample10 = sample100 = sample1_000 = sample10_000 = null;
        affectation = null;
    }

}
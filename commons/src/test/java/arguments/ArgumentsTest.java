package arguments;

import commons.arguments.Argument;
import commons.arguments.ArgumentMetric;
import commons.arguments.ArgumentValue;
import commons.main.MainUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ArgumentsTest {

    private final String sample10 = "../testRessources/samples/sample_10.txt";
    private final String output = "src/test/java/output.txt";
    private final String outputEmpty = "src/test/java/outputE.txt";
    private final String output10 = "src/test/java/output10.txt";

    private final String[] stabilityOk = {"-i", sample10, "-o", output10, "--stability"};
    private final String[] satisfactionOk = {"-i", sample10, "-o", output10, "--satisfaction"};
    private final String[] distanceOk = {"-b", sample10, "-o", output10, "--distance"};

    private final String[] argsOKNoFile = {"-i", sample10, "-o", output};
    private final String[] argsOKFileEmpty = {"-i", sample10, "-o", outputEmpty};
    private final String[] argsErr2Metric = {"-i", sample10, "-o", outputEmpty, "--stability", "--satisfaction"};
    private final String[] argsErr2MetricDistance = {"-o", output10, "-b", sample10, "--satisfaction"};

    private final String[] argOErr2O = {"-i", sample10, "-o", output, "-o"};
    private final String[] argOErrFileNotEmpty = {"-i", sample10, "-o", output10};

    private final String[] argB2BErr = {"-b", sample10, "-o", output10, "--distance", "-b"};
    private final String[] argBNoDistance = {"-b", sample10, "-o", output10};
    private final String[] argBNoB = {"-o", output10, "--distance", sample10};
    private final String[] argBNoFile = {"-b", output, "-o", output10, "--distance"};

    private final String[] argI2I = {"-i", sample10, "-o", output, "-o", "-i"};
    private final String[] argIErrDistance = {"-i", sample10, "-b", sample10, "-o", output10, "--distance"};

    private final String[] argErrSameFiles = {"-i", sample10, "-o", sample10};
    private final String[] argOkSameFilesMetric = {"-i", sample10, "-o", sample10, "--satisfaction"};
    private final String[] argOkSameFilesDistance = {"-b", sample10, "-o", sample10, "--distance"};

    private MainUtil argTest;
    private List<Argument> listArgs;

    @Before
    public void setUp() {
        if (new File(output).exists()) {
            try {
                Files.delete(Paths.get(output));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        listArgs = new ArrayList<>();
        listArgs.addAll(ArgumentValue.getCommonValueArgs());
        listArgs.addAll(ArgumentMetric.getCommonMetricArgs());
    }

    @Test
    public void initArgsTest() {
        // Test Ok with no output file
        try {
            argTest = new MainUtil(argsOKNoFile, null, listArgs);
            Assertions.assertTrue(argTest.getOutput().exists());
            Assertions.assertEquals(0, argTest.getOutput().length());
        } catch (IOException e) {
            e.printStackTrace();
            fail("argOOKNoFile IO");
        } catch (InstantiationException e) {
            e.printStackTrace();
            fail("argOOKNoFile Inst");
        }

        // Test Ok with empty output file
        try {
            argTest = new MainUtil(argsOKFileEmpty, null, listArgs);
            Assertions.assertTrue(argTest.getOutput().exists());
            Assertions.assertEquals(0, argTest.getOutput().length());
        } catch (IOException e) {
            e.printStackTrace();
            fail("argOOKFileEmpty IO");
        } catch (InstantiationException e) {
            e.printStackTrace();
            System.out.println("now");

            fail("argOOKFileEmpty Inst");
        }

        // Test error 2 metric
        try {
            argTest = new MainUtil(argsErr2Metric, null, listArgs);
        } catch (IOException e) {
            e.printStackTrace();
            fail("argsErr2Metric IO");
        } catch (InstantiationException ignored) {
        }

        // Test error 2 metric (with distance)
        try {
            argTest = new MainUtil(argsErr2MetricDistance, null, listArgs);
        } catch (IOException e) {
            e.printStackTrace();
            fail("argsErr2MetricDistance IO");
        } catch (InstantiationException ignored) {
        }
    }

    @Test
    public void testInitArg0() {

        // Test InstatiationException with an non empty file (no longer my responsabilities)
//        try {
//            argTest = new MainUtil(argOErrFileNotEmpty,null, listArgs);
//            fail("InstantiationException supposed");
//        } catch (IOException e) {
//            e.printStackTrace();
//            fail("argOErrFileNotEmpty IO");
//        } catch (InstantiationException e) {
//        }

        // Test InstatiationException with 2 -o
        try {
            argTest = new MainUtil(argOErr2O, null, listArgs);
            fail("InstantiationException supposed");
        } catch (IOException e) {
            e.printStackTrace();
            fail("argOErrFileNotEmpty IO");
        } catch (InstantiationException e) {
            e.printStackTrace();
            fail("InstantiationException IO");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void testInitArgB() {
//        // Test 2 -b
//        try {
//            argTest = new MainUtil(argB2BErr, null, listArgs);
//            fail("InstantiationException supposed");
//        } catch (IOException e) {
//            e.printStackTrace();
//            fail("argB2BErr IO");
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//            fail("argB2BErr InstantiationException");
//        } catch (IllegalArgumentException e) {
//
//        }

        // Test no reference file
//        try {
//            argTest = new MainUtil(argBNoFile,null, listArgs);
//            fail("IOException supposed");
//        } catch (IOException e) {
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//            fail("argBNoFile Inst");
//        }

        // Test -b without --distance
        try {
            argTest = new MainUtil(argBNoDistance, null, listArgs);
            fail("InstantiationException supposed");
        } catch (IOException e) {
            e.printStackTrace();
            fail("argBNoB IO");
        } catch (InstantiationException ignored) {
        }

        // Test --distance without -b
        try {
            argTest = new MainUtil(argBNoB, null, listArgs);
            fail("InstantiationException supposed");
        } catch (IOException e) {
            e.printStackTrace();
            fail("argBNoB IO");
        } catch (InstantiationException e) {
            e.printStackTrace();
            fail("argBNoB InstantiationException");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void testInitArgI() {
        // Errror 2 -i
        try {
            argTest = new MainUtil(argI2I, null, listArgs);
            fail("InstantiationException supposed");
        } catch (IOException e) {
            e.printStackTrace();
            fail("argOOKNoFile IO");
        } catch (InstantiationException e) {
            e.printStackTrace();
            fail("argOOKNoFile InstantiationException");
        } catch (IllegalArgumentException ignored) {

        }

        // Errror no -i if distance
        try {
            argTest = new MainUtil(argIErrDistance, null, listArgs);
            fail("InstantiationException supposed");
        } catch (IOException e) {
            e.printStackTrace();
            fail("argIErrDistance IO");
        } catch (InstantiationException ignored) {
        }
    }

    @Test
    public void testMetric() {
        // Test stability
        try {
            argTest = new MainUtil(stabilityOk, null, listArgs);
            Assertions.assertTrue(argTest.isStability());
        } catch (IOException e) {
            e.printStackTrace();
            fail("Metric IO");
        } catch (InstantiationException e) {
            e.printStackTrace();
            fail("Metric Inst");
        }

        setUp();

        // Test distance
        try {
            argTest = new MainUtil(distanceOk, null, listArgs);
            Assertions.assertTrue(argTest.isDistance());
        } catch (IOException e) {
            e.printStackTrace();
            fail("Metric IO");
        } catch (InstantiationException e) {
            e.printStackTrace();
            fail("Metric Inst");
        }

        setUp();
        // Test satisfaction
        try {
            argTest = new MainUtil(satisfactionOk, null, listArgs);
            Assertions.assertTrue(argTest.isSatisfaction());
        } catch (IOException e) {
            e.printStackTrace();
            fail("Metric IO");
        } catch (InstantiationException e) {
            e.printStackTrace();
            fail("Metric Inst");
        }
    }

    @Test
    public void testCheckDuplicate() {
        // Test Ok 2 same files for distance
        try {
            argTest = new MainUtil(argOkSameFilesDistance, null, listArgs);
        } catch (IOException e) {
            e.printStackTrace();
            fail("argErrSameFiles IO");
        } catch (InstantiationException e) {
            e.printStackTrace();
            fail("argErrSameFiles InstantiationException");
        }

        setUp();
        // Test Ok 2 same files for metric
        try {
            argTest = new MainUtil(argOkSameFilesMetric, null, listArgs);
        } catch (IOException e) {
            e.printStackTrace();
            fail("argErrSameFiles IO");
        } catch (InstantiationException e) {
            e.printStackTrace();
            fail("InstantiationException IO");
        }

        setUp();

        // Test Error 2 same files for calcul
        try {
            argTest = new MainUtil(argErrSameFiles, null, listArgs);
            fail("InstantiationException supposed");
        } catch (IOException e) {
            e.printStackTrace();
            fail("argErrSameFiles IO");
        } catch (InstantiationException ignored) {
        }
    }
}
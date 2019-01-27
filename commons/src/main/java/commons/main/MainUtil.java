package commons.main;

import commons.algo.AlgoInterface;
import commons.arguments.Argument;
import commons.arguments.default_args.metric_args.ArgDistance;
import commons.arguments.default_args.metric_args.ArgSatisfaction;
import commons.arguments.default_args.metric_args.ArgStability;
import commons.arguments.default_args.value_args.ArgB;
import commons.arguments.default_args.value_args.ArgI;
import commons.arguments.default_args.value_args.ArgO;
import commons.result.ResultAffectation;
import commons.statistics.*;
import commons.parser.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainUtil {
    private boolean distance, satisfaction, stability;

    private ParserFile parserFile;
    private File output;
    private File reference;

    private List<String> remainingArgs;
    private List<Argument> remainingArgsObj;

    private final AlgoInterface algo;

    public MainUtil(String[] args, AlgoInterface algo, List<Argument> listArgs) throws IOException, InstantiationException, IllegalArgumentException {
        this.algo = algo;
        initialize(args, listArgs);
    }

    /**
     * Init with args
     */
    protected void initialize(String[] args, List<Argument> listArgs) throws IOException, InstantiationException, IllegalArgumentException {
        remainingArgs = new ArrayList<>(Arrays.asList(args));

        if (remainingArgs.size() < 4)
            throw new IllegalArgumentException("Not enough arguments.");

        for (Argument a : listArgs) {
            remainingArgs = a.init(remainingArgs);
        }

        for (Argument a : listArgs) {
            a.check(listArgs);
        }

        setDistance(listArgs);
        setSatisfaction(listArgs);
        setStability(listArgs);

        setParserFile(listArgs);
        setOutput(listArgs);
        setReference(listArgs);

        if (remainingArgs.size() != 0) {
            StringBuilder str = new StringBuilder();
            for (String s : remainingArgs)
                str.append("Invalid argument : ").append(s).append("\n");

            throw new IllegalArgumentException(str.toString());
        }
    }

    private void setSatisfaction(List<Argument> listArgs) {
        satisfaction = ((ArgSatisfaction) listArgs.get(listArgs.indexOf(new ArgSatisfaction()))).isAsked();
    }

    private void setDistance(List<Argument> listArgs) {
        distance = ((ArgDistance) listArgs.get(listArgs.indexOf(new ArgDistance()))).isAsked();
    }

    private void setStability(List<Argument> listArgs) {
        stability = ((ArgStability) listArgs.get(listArgs.indexOf(new ArgStability()))).isAsked();
    }

    private void setParserFile(List<Argument> listArgs) {
        if (((ArgI) listArgs.get(listArgs.indexOf(new ArgI()))).asValue())
            parserFile = new ParserFile(((ArgI) listArgs.get(listArgs.indexOf(new ArgI()))).getValue());
        else parserFile = null;
    }

    private void setOutput(List<Argument> listArgs) {
        output = new File(((ArgO) listArgs.get(listArgs.indexOf(new ArgO()))).getValue());
    }

    private void setReference(List<Argument> listArgs) {
        if (((ArgB) listArgs.get(listArgs.indexOf(new ArgB()))).asValue())
            reference = new File(((ArgB) listArgs.get(listArgs.indexOf(new ArgB()))).getValue());
        else reference = null;
    }

    /**
     * Start affectation process and stats (if asked)
     */
    public ResultAffectation process() {

        if (distance) {
            System.out.println(new DistanceMetric(reference, output).calculMetric().print());
            System.exit(0);
        }

        parserFile.parse();

        if (stability) {
            System.out.println(new StabilityMetric(parserFile.getSchools(), parserFile.getStudents(), output).calculMetric().print());
            System.exit(0);
        } else if (satisfaction) {
            System.out.println(new SatisfactionMetric(parserFile.getSchools(), parserFile.getStudents(), output).calculMetric().print());
            System.exit(0);
        }

        ResultAffectation res = algo.affecter(parserFile.getSchools(), parserFile.getStudents());
        res.setOutput(output);
        res.print();
        return res;
//            System.out.println("Process done. Result in " + output.getAbsolutePath() + ".");
//        System.exit(0);
    }

    public boolean isDistance() {
        return distance;
    }

    public boolean isSatisfaction() {
        return satisfaction;
    }

    public boolean isStability() {
        return stability;
    }

    protected ParserFile getParserFile() {
        return parserFile;
    }

    public File getOutput() {
        return output;
    }

    public File getReference() {
        return reference;
    }

    protected AlgoInterface getAlgo() {
        return algo;
    }

    protected List<String> getRemainingArgs() {
        return remainingArgs;
    }

    public List<Argument> getRemainingArgsList() {
        return remainingArgsObj;
    }
}
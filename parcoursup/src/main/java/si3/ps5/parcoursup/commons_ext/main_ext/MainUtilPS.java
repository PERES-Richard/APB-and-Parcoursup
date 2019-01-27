package si3.ps5.parcoursup.commons_ext.main_ext;

import commons.algo.AlgoInterface;
import commons.arguments.Argument;
import commons.main.MainUtil;
import commons.result.ResultAffectation;
import si3.ps5.parcoursup.AffectationParcourSup;
import si3.ps5.parcoursup.commons_ext.arguments_ext.*;
import si3.ps5.parcoursup.commons_ext.entity_ext.school.SchoolWithProfile;
import si3.ps5.parcoursup.commons_ext.entity_ext.student.StudentWithProfile;
import si3.ps5.parcoursup.commons_ext.parser_ext.ParserFileWithProfile;
import si3.ps5.parcoursup.commons_ext.result.ResultAffectationPS;
import si3.ps5.parcoursup.commons_ext.result.ResultGraph;
import si3.ps5.parcoursup.commons_ext.result.ResultProfiles;
import statistics.FillinMetric;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * --profiles: lists all available profiles, identifying the default ones for school and students;
 * -c profile:percentage: specifies that a given percentage of candidates follow a given profile. By default, all the candidates follow the default candidate profile;
 * -s profile:percentage: specifies that a given percentage of schools follow a given profile. By default, all the schools follow the default school profile.
 * -g image: generate the recruitment graph into the image PNG file. If not provided, you do not have to generate an image;
 * --ttl i: change the default TTL to i;
 * --days i: specify i as the number of days available for this simulation (default is 123).
 * --seed i: specify a seed for the random generator to make an experiment reproducible.
 * --filling: compute the filling metric.
 */
public class MainUtilPS extends MainUtil {

    private static final int DEFAULT_TTL = 7;
    private static final int DEFAULT_DAYS = 123;

    private boolean profiles;
    private boolean asSeed;
    private boolean asTtl;
    private boolean filling;
    private boolean asGraph;

    private String graph;

    private long seed;

    private int days;
    private int ttl;

    private Map<StudentWithProfile.StudentProfile, Integer> studentsProfilList;
    private Map<SchoolWithProfile.SchoolProfile, Integer> schoolsProfilList;

    public MainUtilPS(String[] args, AlgoInterface algo, List<Argument> listArgs) throws IOException, InstantiationException {
        super(args, algo, listArgs);
    }

    @Override
    protected void initialize(String[] args, List<Argument> listArgs) throws IOException, InstantiationException, IllegalArgumentException {

        try {
            super.initialize(args, listArgs);
        } catch (IOException | InstantiationException e) {
//            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
//            System.err.println(e.getMessage());
        }

        List<String> remainArgs = getRemainingArgs();

        for (Argument a : listArgs) {
            remainArgs = a.init(remainArgs);
        }

        for (Argument a : listArgs) {
            a.check(listArgs);
        }

        studentsProfilList = new HashMap<>();
        schoolsProfilList = new HashMap<>();

        setProfilesAffect(listArgs);
        setSeed(listArgs);
        setDays(listArgs);
        setProfiles(listArgs);
        setTTL(listArgs);
        setFilling(listArgs);
        setGraph(listArgs);

    }

    private void setProfilesAffect(List<Argument> listArgs) {
        for (Argument arg : listArgs) {
            if (arg instanceof ArgC && ((ArgC) arg).asValue() && ((ArgC) arg).asValue() && ((ArgC) arg).isCorrect()) {
                studentsProfilList.put(((ArgC) arg).getProfile(), ((ArgC) arg).getPercent());
            } else if (arg instanceof ArgS && ((ArgS) arg).asValue() && ((ArgS) arg).asValue() && ((ArgS) arg).isCorrect()) {
                schoolsProfilList.put(((ArgS) arg).getProfile(), ((ArgS) arg).getPercent());
            }
        }
    }

    private void setGraph(List<Argument> listArgs) {
        asGraph = ((ArgG) listArgs.get(listArgs.indexOf(new ArgG()))).asValue();
        if (asGraph)
            graph = ((ArgG) listArgs.get(listArgs.indexOf(new ArgG()))).getValue();
    }

    private void setFilling(List<Argument> listArgs) {
        filling = ((ArgFilling) listArgs.get(listArgs.indexOf(new ArgFilling()))).isAsked();
    }

    private void setTTL(List<Argument> listArgs) {
        asTtl = ((ArgTTL) listArgs.get(listArgs.indexOf(new ArgTTL()))).asValue();
        if (asTtl)
            ttl = Integer.parseInt(((ArgTTL) listArgs.get(listArgs.indexOf(new ArgTTL()))).getValue());
        else
            ttl = DEFAULT_TTL;
    }

    private void setProfiles(List<Argument> listArgs) {
        profiles = ((ArgProfiles) listArgs.get(listArgs.indexOf(new ArgProfiles()))).isAsked();
    }

    private void setDays(List<Argument> listArgs) {
        if (((ArgDays) listArgs.get(listArgs.indexOf(new ArgDays()))).asValue())
            days = Integer.parseInt(((ArgDays) listArgs.get(listArgs.indexOf(new ArgDays()))).getValue());
        else
            days = DEFAULT_DAYS;
    }

    private void setSeed(List<Argument> listArgs) {
        asSeed = ((ArgSeed) listArgs.get(listArgs.indexOf(new ArgSeed()))).asValue();
        if (asSeed)
            seed = Long.parseLong(((ArgSeed) listArgs.get(listArgs.indexOf(new ArgSeed()))).getValue());
    }


    @Override
    public ResultAffectation process() {

        if (profiles) {
            ResultProfiles res = new ResultProfiles();
            System.out.println(res.print());
            System.exit(0);
        }

        if (asTtl)
            SchoolWithProfile.TTL = ttl;
        else SchoolWithProfile.TTL = DEFAULT_TTL;


        ParserFileWithProfile parser;
        if (asSeed)
            parser = new ParserFileWithProfile(getParserFile().getInputFile(), studentsProfilList, schoolsProfilList, seed);
        else
            parser = new ParserFileWithProfile(getParserFile().getInputFile(), studentsProfilList, schoolsProfilList);

        if (filling) {
            new FillinMetric(parser.getSchools(), parser.getStudents(), getOutput()).calculMetric().print();
            System.exit(0);
        }

        parser.parse();

        AffectationParcourSup affect = (AffectationParcourSup) getAlgo();
        affect.setOptions(days);

        ResultAffectationPS res = affect.affecter(parser.getSchools(), parser.getStudents());
        res.setOutput(getOutput());
        res.print();

        if (asGraph) {
            new ResultGraph(res.getStudentsPerDay(), graph, res.getNumberStudents()).print();
        }

        return res;
    }
}

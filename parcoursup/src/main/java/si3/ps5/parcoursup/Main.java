package si3.ps5.parcoursup;

import commons.arguments.Argument;
import commons.arguments.ArgumentMetric;
import commons.arguments.ArgumentValue;
import si3.ps5.parcoursup.commons_ext.arguments_ext.*;
import si3.ps5.parcoursup.commons_ext.entity_ext.school.SchoolWithProfile;
import si3.ps5.parcoursup.commons_ext.entity_ext.student.StudentWithProfile;
import si3.ps5.parcoursup.commons_ext.main_ext.MainUtilPS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
class Main {

    public static void main(String[] args) {

//        args = new String[]{"--profiles"};
//        args = new String[]{"-i" , "./dataset/samples/sample_10.txt" , "-o",  "output.txt"};
        //args = new String[]{"-i" , "./dataset/samples/sample_10.txt" , "-o",  "outputtest.txt", "--seed", "12", "-c", "caution:30", "-s", "ranked:70", "--days", "123", "--ttl", "4", "-c", "stubborn:70", "-s", "open_doors:30"};

        List<Argument> listArgs = new ArrayList<>();

        listArgs.addAll(ArgumentValue.getCommonValueArgs());
        listArgs.addAll(ArgumentMetric.getCommonMetricArgs());
        listArgs.addAll(getPSArgs());

        try {
            new MainUtilPS(args, new AffectationParcourSup(), listArgs).process();
        } catch (IOException | InstantiationException | IllegalArgumentException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.exit(0);
    }

    private static List<Argument> getPSArgs() {
        List<Argument> args = new ArrayList<>(Arrays.asList(new ArgDays(), new ArgFilling(), new ArgG(), new ArgSeed(), new ArgTTL(), new ArgProfiles()));

        for (int i = 0; i < StudentWithProfile.StudentProfile.values().length; i++)
            args.add(new ArgC());

        for (int i = 0; i < SchoolWithProfile.SchoolProfile.values().length; i++)
            args.add(new ArgS());

        return args;
    }

}

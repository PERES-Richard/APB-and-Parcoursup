package si3.ps5.apb;

import commons.arguments.Argument;
import commons.arguments.ArgumentMetric;
import commons.arguments.ArgumentValue;
import commons.main.MainUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Main {

    public static void main(String[] args) {

        List<Argument> listArgs = new ArrayList<>();

        listArgs.addAll(ArgumentValue.getCommonValueArgs());
        listArgs.addAll(ArgumentMetric.getCommonMetricArgs());

        try {
            new MainUtil(args, new AffectationAPB(), listArgs).process();
        } catch (IOException | InstantiationException e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.exit(0);
    }
}
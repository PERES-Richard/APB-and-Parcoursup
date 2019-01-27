package si3.ps5.parcoursup.commons_ext.arguments_ext;

import commons.arguments.Argument;
import commons.arguments.ArgumentMetric;

import java.io.IOException;
import java.util.List;

public class ArgProfiles extends ArgumentMetric {

    public ArgProfiles() {
        super("--profiles");
    }

    @Override
    public void check(List<Argument> args) {
    }
}

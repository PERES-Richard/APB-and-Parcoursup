package commons.arguments;

import commons.arguments.default_args.metric_args.ArgDistance;
import commons.arguments.default_args.metric_args.ArgSatisfaction;
import commons.arguments.default_args.metric_args.ArgStability;

import java.util.Arrays;
import java.util.List;

public abstract class ArgumentMetric extends Argument {
    private boolean isAsked;

    protected ArgumentMetric(String name) {
        super(name);
        isAsked = false;
    }

    public List<String> init(List<String> args) {
        int i = 0;
        while (i < args.size() && !args.get(i).equals(getName()))
            i++;

        if (i != args.size()) {
            isAsked = true;
            args.remove(i);
        }

        return args;
    }

    public static List<ArgumentMetric> getCommonMetricArgs() {
        return Arrays.asList(new ArgDistance(), new ArgSatisfaction(), new ArgStability());
    }

    public boolean isAsked() {
        return isAsked;
    }

    @Override
    public String toString() {
        return super.toString() + " => isAsked =  " + isAsked;
    }
}

package commons.arguments.default_args.metric_args;

import commons.arguments.Argument;
import commons.arguments.ArgumentMetric;
import commons.arguments.default_args.value_args.ArgB;
import commons.arguments.default_args.value_args.ArgI;
import commons.arguments.default_args.value_args.ArgO;

import java.util.List;

public class ArgDistance extends ArgumentMetric {

    public ArgDistance() {
        super("--distance");
    }

    @Override
    public void check(List<Argument> args) throws InstantiationException {

        if (isAsked()) {
            boolean asI = false, asO = false, asB = false;

            int i = 0;
            while (i < args.size() && !(asI && asO && asB)) {
                if (args.get(i) instanceof ArgO) {
                    if (((ArgO) args.get(i)).isCreatedOutput())
                        throw new InstantiationException("Invalid --distance : cannot have an non-existing file if metric asked");
                    if (((ArgO) args.get(i)).asValue())
                        asO = true;
                } else if (args.get(i) instanceof ArgI && !(((ArgI) args.get(i)).asValue()))
                    asI = true;
                else if (args.get(i) instanceof ArgB)
                    if (((ArgB) args.get(i)).asValue())
                        asB = true;
                i++;
            }

            if (i == args.size())
                throw new InstantiationException("Invalid --distance : no -o and/or no -i and/or no -b" + asI + " " + asB + " " + asO);
        }
    }
}

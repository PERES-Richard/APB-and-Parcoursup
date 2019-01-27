package commons.arguments.default_args.metric_args;

import commons.arguments.Argument;
import commons.arguments.ArgumentMetric;
import commons.arguments.default_args.value_args.ArgI;
import commons.arguments.default_args.value_args.ArgO;

import java.util.List;

public class ArgStability extends ArgumentMetric {
    public ArgStability() {
        super("--stability");
    }

    @Override
    public void check(List<Argument> args) throws InstantiationException {
        if (isAsked()) {
            boolean asI = false, asO = false;

            int i = 0;
            while (i < args.size() && !(asI && asO)) {
                if (args.get(i) instanceof ArgO) {
                    if (((ArgO) args.get(i)).isCreatedOutput())
                        throw new InstantiationException("Invalid --stability : cannot have an non-existing file if metric asked");
                    asO = true;
                } else if (args.get(i) instanceof ArgI)
                    asI = true;
                i++;
            }

            if (i == args.size())
                throw new InstantiationException("Invalid --stability : no -o and/or no -i");
        }
    }
}

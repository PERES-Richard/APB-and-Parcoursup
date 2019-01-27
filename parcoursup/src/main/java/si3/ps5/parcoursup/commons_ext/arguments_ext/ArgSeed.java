package si3.ps5.parcoursup.commons_ext.arguments_ext;

import commons.arguments.Argument;
import commons.arguments.ArgumentValue;
import commons.arguments.default_args.value_args.ArgI;
import commons.arguments.default_args.value_args.ArgO;

import java.util.List;

public class ArgSeed extends ArgumentValue {
    private long seed;

    public ArgSeed() {
        super("--seed");
    }

    @Override
    public void check(List<Argument> args) throws InstantiationException {
        if (asValue()) {

            for (Argument arg : args) {
                if (arg instanceof ArgO && !((ArgO) arg).asValue())
                    throw new InstantiationException("Invalid --seed : -o option needed");
                if (arg instanceof ArgI && !((ArgI) arg).asValue())
                    throw new InstantiationException("Invalid --seed : -i option needed");
            }

            try {
                seed = Long.parseLong(getValue());
            } catch (NumberFormatException e) {
                throw new InstantiationException("Invalid --seed : value is not a long.");
            }
        }
    }

    public long getSeed() {
        return seed;
    }
}

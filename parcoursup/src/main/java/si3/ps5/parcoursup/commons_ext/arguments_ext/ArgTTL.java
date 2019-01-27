package si3.ps5.parcoursup.commons_ext.arguments_ext;

import commons.arguments.Argument;
import commons.arguments.ArgumentValue;
import commons.arguments.default_args.value_args.ArgI;
import commons.arguments.default_args.value_args.ArgO;

import java.util.List;

public class ArgTTL extends ArgumentValue {

    private int ttl;

    public ArgTTL() {
        super("--ttl");
    }

    @Override
    public void check(List<Argument> args) throws InstantiationException {
        if (asValue()) {

            for (Argument arg : args) {
                if (arg instanceof ArgO && !((ArgO) arg).asValue())
                    throw new InstantiationException("Invalid --ttl : -o option needed");
                if (arg instanceof ArgI && !((ArgI) arg).asValue())
                    throw new InstantiationException("Invalid --ttl : -i option needed");
            }

            try {
                ttl = Integer.parseInt(getValue());
            } catch (NumberFormatException e) {
                throw new InstantiationException("Invalid --ttl : value is not a long.");
            }
        }
    }

    public int getTtl() {
        return ttl;
    }
}

package si3.ps5.parcoursup.commons_ext.arguments_ext;

import commons.arguments.Argument;
import commons.arguments.ArgumentMetric;
import commons.arguments.default_args.value_args.ArgI;
import commons.arguments.default_args.value_args.ArgO;

import java.util.List;

public class ArgFilling extends ArgumentMetric {
    public ArgFilling() {
        super("--filling");
    }

    @Override
    public void check(List<Argument> args) throws InstantiationException {
        if (isAsked()) {

            for (Argument arg : args) {
                if (arg instanceof ArgO && !((ArgO) arg).asValue())
                    if (((ArgO) arg).isCreatedOutput())
                        throw new InstantiationException("Invalid --filling : -o need an existing valid file if using metric");
                    else
                        throw new InstantiationException("Invalid --filling : -o option needed");
                if (arg instanceof ArgI && !((ArgI) arg).asValue())
                    throw new InstantiationException("Invalid --filling : -i option needed");
            }
        }
    }
}

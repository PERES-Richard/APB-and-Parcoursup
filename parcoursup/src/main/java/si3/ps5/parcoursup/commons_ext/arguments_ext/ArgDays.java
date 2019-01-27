package si3.ps5.parcoursup.commons_ext.arguments_ext;

import commons.arguments.Argument;
import commons.arguments.ArgumentValue;
import commons.arguments.default_args.value_args.ArgI;
import commons.arguments.default_args.value_args.ArgO;

import java.util.List;

public class ArgDays extends ArgumentValue {

    private int days;

    public ArgDays() {
        super("--days");
    }

    @Override
    public void check(List<Argument> args) throws InstantiationException {
        if (asValue()) {

            for (Argument arg : args) {
                if (arg instanceof ArgO && !((ArgO) arg).asValue())
                    throw new InstantiationException("Invalid --days : -o option needed");
                if (arg instanceof ArgI && !((ArgI) arg).asValue())
                    throw new InstantiationException("Invalid --days : -i option needed");
            }

            try {
                days = Integer.parseInt(getValue());
            } catch (NumberFormatException e) {
                throw new InstantiationException("Invalid --days : value is not an int.");
            }
        }
    }

    public int getDays() {
        return days;
    }
}

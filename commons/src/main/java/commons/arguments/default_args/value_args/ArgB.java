package commons.arguments.default_args.value_args;

import commons.arguments.Argument;
import commons.arguments.ArgumentValue;
import commons.arguments.default_args.metric_args.ArgDistance;

import java.io.File;
import java.util.List;

public class ArgB extends ArgumentValue {

    public ArgB() {
        super("-b");
    }

    @Override
    public void check(List<Argument> args) throws InstantiationException {

        if (asValue()) {

            // Check if there is a -i
            for (Argument arg : args) {
                if (arg instanceof ArgI && ((ArgI) arg).asValue())
                    throw new InstantiationException("Invalid -b : cannot have a -i if distance metric asked (-b / --distance).");

                // Check if there is a --distance
                if (arg instanceof ArgDistance && !(((ArgDistance) arg).isAsked()))
                    throw new InstantiationException("Invalid -b option : --ditance needed if -b");

                if (arg instanceof ArgO && !(((ArgO) arg).asValue()))
                    throw new InstantiationException("Invalid -b option : -o needed");
            }

            // Check if file exist
            if (!new File(getValue()).exists())
                throw new InstantiationException("Invalid -b option : reference file doesn't exist");

        }
    }
}

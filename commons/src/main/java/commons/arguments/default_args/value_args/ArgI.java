package commons.arguments.default_args.value_args;

import commons.arguments.Argument;
import commons.arguments.ArgumentValue;

import java.io.File;
import java.util.List;

public class ArgI extends ArgumentValue {

    public ArgI() {
        super("-i");
    }

    @Override
    public void check(List<Argument> args) throws InstantiationException {

        if (asValue()) {

            // Check if file exist
            if (!new File(getValue()).exists())
                throw new InstantiationException("Invalide -i option : inputfile doesn't exist" + getValue());

            // Check if there is a -o
            int i = 0;
            while (i < args.size() && !(args.get(i) instanceof ArgO))
                i++;

            if (i == args.size() || !((ArgO) args.get(i)).asValue())
                throw new InstantiationException("Invalid -i option : -o needed");
        }
    }
}

package si3.ps5.parcoursup.commons_ext.arguments_ext;

import commons.arguments.Argument;
import commons.arguments.ArgumentValue;
import commons.arguments.default_args.value_args.ArgI;
import commons.arguments.default_args.value_args.ArgO;

import java.io.File;
import java.util.List;

public class ArgG extends ArgumentValue {
    public ArgG() {
        super("-g");
    }

    @Override
    public void check(List<Argument> args) throws InstantiationException {
        if (asValue()) {

            for (Argument arg : args) {
                if (arg instanceof ArgO && !((ArgO) arg).asValue())
                    throw new InstantiationException("Invalid -g : -o option needed");
                if (arg instanceof ArgI && !((ArgI) arg).asValue())
                    throw new InstantiationException("Invalid -g : -i option needed");
            }

            if (new File(getValue()).exists())
                throw new InstantiationException("Invalid -g : file need to be non existent.");

        }
    }
}

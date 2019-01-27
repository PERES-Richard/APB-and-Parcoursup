package commons.arguments.default_args.value_args;

import commons.arguments.Argument;
import commons.arguments.ArgumentValue;
import commons.arguments.default_args.metric_args.ArgDistance;
import commons.arguments.default_args.metric_args.ArgSatisfaction;
import commons.arguments.default_args.metric_args.ArgStability;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ArgO extends ArgumentValue {

    private boolean createdOutput;

    public ArgO() {
        super("-o");
    }

    @Override
    public void check(List<Argument> args) throws IOException, InstantiationException {
        if (asValue()) {
            // Create file if doesnt exist
            createdOutput = new File(getValue()).createNewFile();

            int metricAsked = 0;

            for (Argument arg : args) {
                if ((arg instanceof ArgB && ((ArgB) arg).asValue()) || (arg instanceof ArgDistance && ((ArgDistance) arg).isAsked()))
                    if (createdOutput)
                        throw new InstantiationException("Invalid -o : cannot have a created file if metric asked (-b / --distance).");
                    else
                        metricAsked++;

                if (arg instanceof ArgSatisfaction && ((ArgSatisfaction) arg).isAsked())
                    if (createdOutput)
                        throw new InstantiationException("Invalid -o : cannot have a created file if metric asked (--satisfaction).");
                    else
                        metricAsked++;

                if ((arg instanceof ArgStability && ((ArgStability) arg).isAsked()))
                    if (createdOutput)
                        throw new InstantiationException("Invalid -o : cannot have a created file if metric asked (--stability).");
                    else
                        metricAsked++;
            }

            if (metricAsked == 0)
                if (!createdOutput)
                    if (new File(getValue()).length() > 0)
                        throw new InstantiationException("Invalid -o : if no metric, file must be empty or non-existent");
        }
    }

    public boolean isCreatedOutput() {
        return createdOutput;
    }
}

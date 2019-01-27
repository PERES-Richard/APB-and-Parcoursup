package commons.arguments;

import commons.arguments.default_args.value_args.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public abstract class ArgumentValue extends Argument {

    private String value;
    private boolean asValue;

    protected ArgumentValue(String name) {
        super(name);
        asValue = false;
    }

    public String getValue() {
        return value;
    }

    public boolean asValue() {
        return asValue;
    }

    private void setValue(String value) {
        this.value = value;
        asValue = true;
    }

    public abstract void check(List<Argument> args) throws InstantiationException, IOException;

    public List<String> init(List<String> args) {
        int i = 0;

        while (i < args.size() && !args.get(i).equals(getName()))
            i++;

        if (i < args.size() && args.get(i).equals(getName())) {
            setValue(args.get(i + 1));
            args.remove(i);
            args.remove(i);
        }

        return args;
    }

    public static List<ArgumentValue> getCommonValueArgs() {
        return Arrays.asList(new ArgI(), new ArgO(), new ArgB());
    }

    @Override
    public String toString() {
        return super.toString() + " ==> asValue=" + asValue;
    }
}

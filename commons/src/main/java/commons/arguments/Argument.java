package commons.arguments;

import java.io.IOException;
import java.util.List;

public abstract class Argument {

    private final String name;

    Argument(String name) {
        this.name = name;
    }

    public abstract List<String> init(List<String> args);

    public abstract void check(List<Argument> args) throws InstantiationException, IOException;

    String getName() {
        return name;
    }

    @Override
    public boolean equals(Object arg2) {
        if (!(arg2 instanceof Argument))
            return false;
        return hashCode() == arg2.hashCode();
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Arg " + name;
    }
}

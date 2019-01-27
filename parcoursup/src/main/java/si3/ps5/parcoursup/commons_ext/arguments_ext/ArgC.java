package si3.ps5.parcoursup.commons_ext.arguments_ext;

import commons.arguments.Argument;
import commons.arguments.ArgumentValue;
import commons.arguments.default_args.value_args.ArgI;
import commons.arguments.default_args.value_args.ArgO;
import si3.ps5.parcoursup.commons_ext.entity_ext.student.StudentWithProfile;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ArgC extends ArgumentValue {

    private StudentWithProfile.StudentProfile profile;
    private int percent;

    private final String id = UUID.randomUUID().toString();

    private boolean isCorrect = false;

    public ArgC() {
        super("-c");
    }

    @Override
    public void check(List<Argument> args) throws InstantiationException {
        if (asValue()) {
            List<String> values = Arrays.asList(getValue().split(":"));

            if (values.size() != 2)
                throw new InstantiationException("Invalid -c : incorrect values \"" + values + "\".");

            try {
                profile = StudentWithProfile.StudentProfile.valueOf(values.get(0));
                percent = Integer.parseInt(values.get(1));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                throw new InstantiationException("Invalid -c : values  \"" + values.get(1) + "\" isnt a correct int value.");
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                throw new InstantiationException("Invalid -c : profiles \"" + values.get(0) + "\" doesnt exist.");
            }

            if (percent < 0 || percent > 100)
                throw new InstantiationException("Invalid -c : percent " + percent + " invalid. Values between 0 and 100 accepted.");

            int totalP = percent;

            for (Argument arg : args) {
                if (arg instanceof ArgO && !((ArgO) arg).asValue())
                    throw new InstantiationException("Invalid -c : -o option needed");
                if (arg instanceof ArgI && !((ArgI) arg).asValue())
                    throw new InstantiationException("Invalid -c : -i option needed");

                if (arg instanceof ArgC && ((ArgC) arg).asValue() && ((ArgC) arg).isCorrect() && !((ArgC) arg).getId().equals(id)) {
                    if (((ArgC) arg).getProfile().name().equals(profile.name()))
                        throw new InstantiationException("Invalid -c : cannot have 2 times same profiles. (" + profile.name() + " & " + ((ArgC) arg).getProfile().name() + ").");

                    totalP += ((ArgC) arg).getPercent();
                    if (totalP > 100)
                        throw new InstantiationException("Invalid -c : Total percent of profiles > 100%.");
                }
            }

            isCorrect = true;
        }
    }

    public StudentWithProfile.StudentProfile getProfile() {
        return profile;
    }

    public int getPercent() {
        return percent;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    private String getId() {
        return id;
    }
}

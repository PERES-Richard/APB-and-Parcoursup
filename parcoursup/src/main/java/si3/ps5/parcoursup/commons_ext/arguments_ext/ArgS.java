package si3.ps5.parcoursup.commons_ext.arguments_ext;

import commons.arguments.Argument;
import commons.arguments.ArgumentValue;
import commons.arguments.default_args.value_args.ArgI;
import commons.arguments.default_args.value_args.ArgO;
import si3.ps5.parcoursup.commons_ext.entity_ext.school.SchoolWithProfile;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ArgS extends ArgumentValue {

    private SchoolWithProfile.SchoolProfile profile;
    private int percent = -1;
    private boolean isCorrect = false;

    private final String id = UUID.randomUUID().toString();

    public ArgS() {
        super("-s");
    }

    @Override
    public void check(List<Argument> args) throws InstantiationException {
        if (asValue()) {

            List<String> values = Arrays.asList(getValue().split(":"));

            if (values.size() != 2)
                throw new InstantiationException("Invalid -s : incorrect values \"" + values + "\".");

            try {
                profile = SchoolWithProfile.SchoolProfile.valueOf(values.get(0));
                percent = Integer.parseInt(values.get(1));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                throw new InstantiationException("Invalid -s : values  \"" + values.get(1) + "\" isnt a correct int value.");
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                throw new InstantiationException("Invalid -s : profiles \"" + values.get(0) + "\" doesnt exist.");
            }

            if (percent < 0 || percent > 100)
                throw new InstantiationException("Invalid -s : percent " + percent + " invalid. Values between 0 and 100 accepted.");

            int totalP = percent;

            for (Argument arg : args) {
                if (arg instanceof ArgO && !((ArgO) arg).asValue())
                    throw new InstantiationException("Invalid -s : -o option needed");
                if (arg instanceof ArgI && !((ArgI) arg).asValue())
                    throw new InstantiationException("Invalid -s : -i option needed");

                if (arg instanceof ArgS && ((ArgS) arg).asValue() && ((ArgS) arg).isCorrect() && !((ArgS) arg).getId().equals(id)) {
                    if (((ArgS) arg).getProfile().name().equals(profile.name()))
                        throw new InstantiationException("Invalid -s : cannot have 2 times same profiles. (" + profile.name() + ").");

                    totalP += ((ArgS) arg).getPercent();
                    if (totalP > 100)
                        throw new InstantiationException("Invalid -s : Total percent of profiles > 100%.");
                }
            }

            isCorrect = true;
        }

    }

    public SchoolWithProfile.SchoolProfile getProfile() {
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

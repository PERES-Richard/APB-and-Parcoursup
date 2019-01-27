package si3.ps5.parcoursup.commons_ext.entity_ext.school;

public class PercentSchoolProfile {
    private int numberOfSchoolsThatHaveThisProfile;
    private final SchoolWithProfile.SchoolProfile theProfileToAttribute;

    public PercentSchoolProfile(int numberOfSchoolsThatHaveThisProfile, SchoolWithProfile.SchoolProfile theProfileToAttribute) {
        this.numberOfSchoolsThatHaveThisProfile = numberOfSchoolsThatHaveThisProfile;
        this.theProfileToAttribute = theProfileToAttribute;
    }

    public int getNumberOfSchoolsThatHaveThisProfile() {
        return numberOfSchoolsThatHaveThisProfile;
    }

    public SchoolWithProfile.SchoolProfile getTheProfileToAttribute() {
        return theProfileToAttribute;
    }

    public void decrementNumber() {
        numberOfSchoolsThatHaveThisProfile--;
    }
}

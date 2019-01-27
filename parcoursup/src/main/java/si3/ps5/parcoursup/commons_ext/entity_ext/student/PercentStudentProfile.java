package si3.ps5.parcoursup.commons_ext.entity_ext.student;

public class PercentStudentProfile {
    private int numberOfStudentsThatHaveThisProfile;
    private final StudentWithProfile.StudentProfile theProfileToAttribute;

    public PercentStudentProfile(int numberOfStudentsThatHaveThisProfile, StudentWithProfile.StudentProfile theProfileToAttribute) {
        this.numberOfStudentsThatHaveThisProfile = numberOfStudentsThatHaveThisProfile;
        this.theProfileToAttribute = theProfileToAttribute;
    }

    public int getNumberOfStudentsThatHaveThisProfile() {
        return numberOfStudentsThatHaveThisProfile;
    }

    public StudentWithProfile.StudentProfile getTheProfileToAttribute() {
        return theProfileToAttribute;
    }

    public void decrementNumber() {
        numberOfStudentsThatHaveThisProfile--;
    }
}

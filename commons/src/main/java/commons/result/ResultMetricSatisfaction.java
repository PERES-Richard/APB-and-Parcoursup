package commons.result;

import commons.statistics.Metric;

import java.util.Arrays;

public class ResultMetricSatisfaction extends ResultMetric {

    private final int[] affectationDependingOnWish;
    private final float averageStudentSatisfaction;
    private final double averageSchoolSatisfaction;

    public ResultMetricSatisfaction(Metric metricInterface, int[] affectationDependingOnWish, float averageStudentSatisfaction, double averageSchoolSatisfaction) {
        super(metricInterface);
        this.affectationDependingOnWish = affectationDependingOnWish;
        this.averageStudentSatisfaction = averageStudentSatisfaction;
        this.averageSchoolSatisfaction = averageSchoolSatisfaction;
    }

    @Override
    public String print() {

        String str = printNbWish() + "\n" +
                Arrays.toString(affectationDependingOnWish).replaceAll("[\\[,\\]]", "") + "\n" +
                String.format("%.2f", averageStudentSatisfaction).replace(",", ".") + "\n" +
                String.format("%.2f", averageSchoolSatisfaction).replace(",", ".");
        return str;
    }


    private String printNbWish() {
        StringBuilder toReturn = new StringBuilder();

        int numberChoices = affectationDependingOnWish.length;

        for (int i = 0; i < numberChoices - 1; i++) {
            toReturn.append(i).append(" ");
        }
        return toReturn.append("X").toString();
    }

}

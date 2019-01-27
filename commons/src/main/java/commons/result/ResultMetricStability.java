package commons.result;

import commons.statistics.Metric;

public class ResultMetricStability extends ResultMetric {

    private final int couple;
    private final int student;

    public ResultMetricStability(Metric metricInterface, int couple, int student) {
        super(metricInterface);
        this.couple = couple;
        this.student = student;
    }

    public int getCouple() {
        return couple;
    }

    public int getStudent() {
        return student;
    }

    @Override
    public String print() {
        return couple + "\n" + student;
    }
}

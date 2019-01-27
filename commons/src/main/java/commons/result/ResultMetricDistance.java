package commons.result;

import commons.statistics.Metric;

public class ResultMetricDistance extends ResultMetric {

    private final int value;

    public ResultMetricDistance(Metric metricInterface, int value) {
        super(metricInterface);
        this.value = value;
    }

    @Override
    public String print() {
        return String.valueOf(value);
    }

    public int getValue() {
        return value;
    }
}

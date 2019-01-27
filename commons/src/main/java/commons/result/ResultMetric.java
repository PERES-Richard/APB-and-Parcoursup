package commons.result;

import commons.statistics.Metric;

public class ResultMetric extends Result {

    private final Metric metricInterface;

    protected ResultMetric(Metric metricInterface) {
        this.metricInterface = metricInterface;
    }

    @Override
    public String print() {
        return null;
    }

    public Metric getMetricInterface() {
        return metricInterface;
    }
}

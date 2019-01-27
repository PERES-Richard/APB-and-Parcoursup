package si3.ps5.parcoursup.commons_ext.result;

import commons.result.ResultMetric;
import commons.statistics.Metric;

public class ResultFilling extends ResultMetric {
    private final int numberSchoolRecruitedRightNumber;
    private final int numberSchoolNotRecruitedEnough;
    private final int numberSchoolRecruitedMore;

    public ResultFilling(Metric metricInterface, int numberSchoolRecruitedRightNumber, int numberSchoolNotRecruitedEnough, int numberSchoolRecruitedMore) {
        super(metricInterface);
        this.numberSchoolRecruitedRightNumber = numberSchoolRecruitedRightNumber;
        this.numberSchoolNotRecruitedEnough = numberSchoolNotRecruitedEnough;
        this.numberSchoolRecruitedMore = numberSchoolRecruitedMore;
    }

    @Override
    public String print() {
        return numberSchoolRecruitedRightNumber + " " + numberSchoolNotRecruitedEnough + " " + numberSchoolRecruitedMore;
    }
}

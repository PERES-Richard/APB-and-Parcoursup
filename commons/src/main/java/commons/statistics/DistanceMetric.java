package commons.statistics;

import commons.result.ResultMetric;
import commons.result.ResultMetricDistance;

import java.io.*;

public class DistanceMetric implements Metric {
    private final File firstFile;
    private final File secondFile;

    public DistanceMetric(File firstFile, File secondFile) {
        this.firstFile = firstFile;
        this.secondFile = secondFile;
    }

    /**
     * Calcul the distance metric
     *
     * @return the output to display
     */
    @Override
    public ResultMetric calculMetric() {
        int distance = 0;

        try {
            BufferedReader buff = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(firstFile)));

            BufferedReader buff2 = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(secondFile)));

            String line = buff.readLine();
            String line2 = buff2.readLine();
            if (line == null || line2 == null)
                System.exit(1);
            else {
                String[] numbers = line.split("[ ]+");
                String[] numbers2 = line2.split("[ ]+");
                if (numbers.length != numbers2.length) {
                    System.out.println("The files doesn't have the same number of students");
                    System.exit(1);
                } else {
                    for (int i = 0; i < numbers.length; i++) {
                        if (!numbers[i].equals(numbers2[i]))
                            distance++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(2);
        }
        return new ResultMetricDistance(this, distance);
    }
}

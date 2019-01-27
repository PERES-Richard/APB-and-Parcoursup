package si3.ps5.parcoursup.commons_ext.result;

import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.style.NamedPlotColor;
import com.panayotis.gnuplot.style.PlotStyle;
import com.panayotis.gnuplot.style.Style;
import com.panayotis.gnuplot.terminal.ImageTerminal;
import commons.result.Result;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class ResultGraph extends Result {
    private final List<Integer> studentsPerDay;
    private String pathToSavePicture;
    private final int numberStudents;

    public ResultGraph(List<Integer> studentsPerDay, String pathToSavePicture, int numberStudents) {
        this.studentsPerDay = studentsPerDay;
        this.pathToSavePicture = pathToSavePicture;
        this.numberStudents = numberStudents;
    }

    @Override
    public String print() {
        int[][] studentsWithoutSchools = new int[studentsPerDay.size()][numberStudents];
        for (int i = 0; i < studentsPerDay.size(); i++) {
            int[] tab = new int[1];
            tab[0] = numberStudents - studentsPerDay.get(i);
            studentsWithoutSchools[i] = tab;
        }

        PlotStyle styleExist = new PlotStyle();
        styleExist.setStyle(Style.LINES);
        styleExist.setLineType(NamedPlotColor.GREEN);

        DataSetPlot setExist = new DataSetPlot(studentsWithoutSchools);
        setExist.setPlotStyle(styleExist);
        setExist.setTitle("Etudiants sans école");

        if (!pathToSavePicture.contains(".png"))
            pathToSavePicture += ".png";

        ImageTerminal png = new ImageTerminal();
        File file = new File(pathToSavePicture);
        try {
            file.createNewFile();
            png.processOutput(new FileInputStream(file));
        } catch (IOException ex) {
            ex.printStackTrace();
            return "error";
        }

        JavaPlot p = new JavaPlot();
        p.setPersist(false);
        p.setTerminal(png);
        p.getAxis("x").setLabel("Jours");
        p.getAxis("y").setLabel("Etudiants sans école");
        p.getAxis("x").setBoundaries(0.0, studentsPerDay.size() - 1);
        p.getAxis("y").setBoundaries(0.0, numberStudents);
        p.addPlot(setExist);
        p.setTitle("Nombre etudiants sans affectation par jour");
        p.plot();

        try {
            ImageIO.write(png.getImage(), "png", file);
        } catch (IOException ex) {
            ex.printStackTrace();
            return "error";
        }
        return "ok";
    }
}

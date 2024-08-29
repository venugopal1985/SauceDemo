import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GenerateMasterThoughtReport
{
    private static final String outputDir = "./target/my-cucumber-reports";
    public static void main(String[] args)
    throws Exception
    {
        GenerateMasterThoughtReport app = new GenerateMasterThoughtReport();
        app.run();

    }

    public void run()
    throws Exception
    {
        File reportOutputDirectory = new File(outputDir);

        File jsonDir = new File("target/cucumber-report");
        List<String> findAllJSONFiles = new ArrayList<>();
        findAllJSONFiles.add("target/cucumber-report/cucumber.json");

        String projectName = "Automated Testing for Saucedemo site";
        Configuration config = new Configuration(reportOutputDirectory, projectName);

        // additional metadata presented on main page
        config.addClassifications("Platform", "Windows 11");
        config.addClassifications("Browser", "Edge");

        // optionally add metadata presented on main page via properties file
        List<String> classificationFiles = new ArrayList<>();
        classificationFiles.add("properties-1.properties");
        config.addClassificationFiles(classificationFiles);

        config.setTrendsStatsFile(new File("cucumber-trends.json"));
        ReportBuilder reportBuilder = new ReportBuilder(findAllJSONFiles, config);
        reportBuilder.generateReports();
    }

    private List<String> findAllJSONFiles(File file)
    {
        List<String> ret = new ArrayList<String>();
        if (file.isDirectory())
        {
            File[] subFiles = file.listFiles();
            for (File childFile : subFiles)
            {
                ret.addAll(findAllJSONFiles(childFile));
            }
        }
        else if (file.getName().toLowerCase().endsWith(".json"))
        {
            ret.add(file.getAbsolutePath());
        }
        return ret;
    }
}

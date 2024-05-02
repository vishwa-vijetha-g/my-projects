package qtriptest;

import java.io.File;
import com.relevantcodes.extentreports.ExtentReports;

public class ReportSingleton {

    private ReportSingleton(){

    }

    private static ExtentReports extentReportsVar = null;

    public static ExtentReports getExtentReports(){
        if(extentReportsVar==null){
            extentReportsVar =  new ExtentReports("/home/crio-user/workspace/vishwavijethag-ME_QTRIP_QA_V2/app/qtripExtentReport.html");
            extentReportsVar.loadConfig(new File("/home/crio-user/workspace/vishwavijethag-ME_QTRIP_QA_V2/app/extent_customization_configs.xml"));
        }
        return extentReportsVar;
    }
}
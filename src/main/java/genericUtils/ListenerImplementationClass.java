package genericUtils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplementationClass implements ITestListener, ISuiteListener {

	@Override
	public void onStart(ISuite suite) {
		//configuring the ui
		ExtentSparkReporter htmlreport = new ExtentSparkReporter(
				".\\ExtentReport\\report" + new JavaUtils().sysDate() + ".html");
		htmlreport.config().setDocumentTitle("TP-30_VTiger");
		htmlreport.config().setTheme(Theme.STANDARD);
		htmlreport.config().setReportName("VTiger");

		report = new ExtentReports();
		report.attachReporter(htmlreport);
		report.setSystemInfo("base_platform", "windows");
		report.setSystemInfo("base_browser", "chrome");
		report.setSystemInfo("base_url", "http://localhost:8888");
		report.setSystemInfo("Reporter_Name", "Arthi");
	}

	@Override
	public void onFinish(ISuite suite) {
		report.flush();

		extent.remove();
	}

	//public static ExtentReports report;
	// private static ExtentTest test;
	ExtentReports report;
	ExtentTest test;

	// used to support parallel execution safety
	public static ThreadLocal<ExtentTest> extent = new ThreadLocal<>();//<threadID in suite file,webdriver id/that specific extent test obj - create contact>
	//threadlocal class - static - it can hold multiple address - respictive to diff thread
	//driver instance of diff 
	//div the memory and store all

	@Override
	public void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		//String methodName = result.getName();
		test = report.createTest(methodName);
		// test = report.createTest(methodName);
		// test.log(Status.INFO, methodName+" execution starts");
		extent.set(test);
		extent.get().log(Status.INFO, methodName + " execution starts");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		extent.get().log(Status.PASS, "<b>"+methodName+" --> Passed</b>"); //<b> </b>
	}

	@Override
	public void onTestFailure(ITestResult result) {
		//ExtentTest test = extent.get();
		String methodName = result.getMethod().getMethodName();
		//String fileName = methodName + new JavaUtils().sysDate();
		try {
//		String filepath = ".\\screenshot\\"+fileName+".png";
//		
//		TakesScreenshot ts = (TakesScreenshot) BaseClass.sdriver;
//		File src = ts.getScreenshotAs(OutputType.FILE);
//		File dst = new File(filepath);
//		
//			FileUtils.copyFile(src, dst);
//			String path = dst.getAbsolutePath();
//			
//			//test.addScreenCaptureFromPath(path);//attaching failed screenshot into extent report
//			extent.get().addScreenCaptureFromPath(path);

			TakesScreenshot ts = (TakesScreenshot) BaseClass.wdriver.get();

			// Base64 screenshot
			String base64 = ts.getScreenshotAs(OutputType.BASE64);

			// attach to report
			extent.get().addScreenCaptureFromBase64String(base64, methodName);

			// optional: save locally
//			byte[] bytes = Base64.getDecoder().decode(base64);
//			File file = new File("./screenshot/" + methodName + "_" + new JavaUtils().sysDate() + ".png");
//			file.getParentFile().mkdirs();
//			FileUtils.writeByteArrayToFile(file, bytes);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			extent.get().log(Status.WARNING, "Screenshot failed: " + e.getMessage());
		}
		extent.get().log(Status.FAIL, "<i>"+methodName + " --> Failed</i>");
		extent.get().log(Status.FAIL, result.getThrowable());
		// extent.get().log(Status.FAIL, result.getThrowable());
		// extent.get().log(Status.FAIL, methodName+" --> Failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		// test.log(Status.SKIP, methodName+" --> Skipped");
		extent.get().log(Status.SKIP, methodName + " --> Skipped");
	}

//	@Override
//	public void onStart(ITestContext context) {
//
//		ExtentSparkReporter htmlreport = new ExtentSparkReporter(
//				".\\ExtentReport\\report" + new JavaUtils().sysDate() + ".html");
//		htmlreport.config().setDocumentTitle("TP-30_VTiger");
//		htmlreport.config().setTheme(Theme.STANDARD);
//		htmlreport.config().setReportName("VTiger");
//
//		report = new ExtentReports();
//		report.attachReporter(htmlreport);
//		report.setSystemInfo("base_platform", "windows");
//		report.setSystemInfo("base_browser", "chrome");
//		report.setSystemInfo("base_url", "http://localhost:8888");
//		report.setSystemInfo("Reporter_Name", "Arthi");
//	}

//	@Override
//	public void onFinish(ITestContext context) {
//		report.flush();
//
//		extent.remove();
//	}

	///////

//	private static ExtentReports report;
//	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
//
//	@Override
//	public void onStart(ISuite suite) {
//// create physical rep of our report - extSpart
//		ExtentSparkReporter spark = new ExtentSparkReporter(
//				".\\ExtentReport\\VTiger_" + new JavaUtils().sysDate() + ".html");
//
//		spark.config().setTheme(Theme.STANDARD);
//		spark.config().setDocumentTitle("VTiger Report");
//		spark.config().setReportName("Parallel Execution");
//
//		report = new ExtentReports();//to generate only 1 physical report - for 1 suite - for entire suite file
//		//where to store the info
//		report.attachReporter(spark);
//	}
//
//	@Override
//	public void onTestStart(ITestResult result) {
//		//
//		ExtentTest test = report.createTest(result.getMethod().getMethodName());
//		extentTest.set(test);
//		extentTest.get().log(Status.INFO, result.getMethod().getMethodName() + " execution starts");
//	}
//
//	@Override
//	public void onTestSuccess(ITestResult result) {
//		//extentTest.get().pass("Test Passed");
//		extentTest.get().log(Status.PASS, result.getMethod().getMethodName() + " --> Passed");
//	}
//
//	@Override
//	public void onTestFailure(ITestResult result) {
//
//		ExtentTest test = extentTest.get();
//
//		try {
//			TakesScreenshot ts = (TakesScreenshot) BaseClass.sdriver;
//			String base64 = ts.getScreenshotAs(OutputType.BASE64);
//
//			test.addScreenCaptureFromBase64String(base64);
//
//			byte[] bytes = Base64.getDecoder().decode(base64);
//			FileUtils.writeByteArrayToFile(new File(
//					".\\screenshot\\" + result.getMethod().getMethodName() + "_" + new JavaUtils().sysDate() + ".png"),
//					bytes);
//
//		} catch (Exception e) {
//			test.log(Status.WARNING, "Screenshot failed: " + e.getMessage());
//			e.printStackTrace();
//		}
//
//		//test.fail(result.getThrowable());
//		test.log(Status.FAIL, result.getMethod().getMethodName() + " --> Failed");
//        test.log(Status.FAIL, result.getThrowable());
//	}
//
//	@Override
//	public void onTestSkipped(ITestResult result) {
//		//extentTest.get().skip("Test Skipped");
//		extentTest.get().log(Status.SKIP, result.getMethod().getMethodName() + " --> Skipped");
//	}
//
//	@Override
//	public void onFinish(ISuite suite) {
//		report.flush();
//		extentTest.remove();
//	}

}

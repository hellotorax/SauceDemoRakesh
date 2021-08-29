package utilities;

import org.apache.log4j.Logger;
import org.testng.*;

/**
 * Created by Rakesh Kumar Padhiary.
 */
public class LogUtil extends TestListenerAdapter implements ITestListener {

    private static Logger log = Logger.getLogger(ConfigReader.get("logger.file"));

    public static void info(String message){
        log.info(message);
    }

    public static void warn(String message){
        log.warn(message);
    }

    public static void error(String message){
        log.error(message);
    }

    public static void fatal(String message){
        log.fatal(message);
    }

    public static void debug(String message){
        log.debug(message);
    }

    public static void startTestCase(String sTestCaseName) {

        log.info(
                "\n***************************************************************************************");

        log.info(
                "****************************************************************************************");

        log.info(
                "$$$$$$$$$$$$$$$$$$$$$                 "
                        + sTestCaseName
                        + "       $$$$$$$$$$$$$$$$$$$$$$$$$");

        log.info(
                "****************************************************************************************");

        log.info(
                "****************************************************************************************");
    }

    public static void endTestCase(String sTestCaseName) {

        log.info(
                "XXXXXXXXXXXXXXXXXXXXXXX             "
                        + "-E---N---D-"
                        + "             XXXXXXXXXXXXXXXXXXXXXX");

        log.info("X");

        log.info("X");

        log.info("X\n");
    }

    @Override
    public void onStart(ITestContext context){
        log.info(context.getName()+"--Test Started->");
    }

    @Override
    public void onFinish(ITestContext context) {

        log.info(context.getName() + "--Test Finish->\n\n");
    }


    @Override
    public void onTestStart(ITestResult result) {

        log.info(result.getTestClass() + ":" + result.getName() + "--Test Started->");
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        log.info("Test '" + result.getName() + "'  PASSED  ");

        // This will print the class name in which the method is present
        //log.info(""+result.getTestClass());

        // This will print the priority of the method.
        // If the priority is not defined it will print the default priority as
        // 'o'

        log.info("Priority of this method is " + result.getMethod().getPriority() + "\n\n");

        log.info("Priority of this method is " + result.getMethod().getPriority());
        log.info("### ### ### ### ### ### ### ### #\n");
    }



    @Override
    public void onTestSkipped(ITestResult result) {
        log.error(result.getName() + "--Test Skipped");
        log.warn("Test '" + result.getName() + "' SKIPPED\n");
        System.out.println(".....");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        log.error("Test '" + result.getName() + "' FAILED");
        log.error(Reporter.getCurrentTestResult());
        log.error(result.getThrowable());

        log.error("Priority of this method is " + result.getMethod().getPriority() + "\n\n");

        log.error("Priority of this method is " + result.getMethod().getPriority());
        log.info("###### ###### ###### ###### ###### ######\n");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        log.error(result.getName() + "--Test Failed with success percentage");
        log.error("Priority of this method is " + result.getMethod().getPriority() + "\n");
    }


}

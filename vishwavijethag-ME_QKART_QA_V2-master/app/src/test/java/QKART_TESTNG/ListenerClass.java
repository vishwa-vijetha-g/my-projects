package QKART_TESTNG;

import org.testng.ITestListener;
import org.testng.ITestResult;


public class ListenerClass implements ITestListener {
    @Override
    public void onTestStart(ITestResult result){
        QKART_Tests.takeScreenshot("TestStart", result.getName());
    }
    @Override
    public void onTestSuccess(ITestResult result){
        QKART_Tests.takeScreenshot("TestSuccess", result.getName());
    }
    @Override
    public void onTestFailure(ITestResult result){
        QKART_Tests.takeScreenshot("TestFailure", result.getName());
    }
   
}
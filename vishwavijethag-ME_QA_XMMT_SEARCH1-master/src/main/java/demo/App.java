package demo;
import java.net.MalformedURLException;


public class App {
    public void getGreeting() throws InterruptedException, MalformedURLException {
        
        // This is to remove unnecessary warnings from your console
        System.setProperty("java.util.logging.config.file", "logging.properties");
        
        TestCases tests = new TestCases(); // Initialize your test class

        //TODO: call your test case functions one after other here

        tests.testCase01();
        tests.testCase02();
        tests.testCase03();
        tests.testCase04();

        //END Tests


        tests.endTest(); // End your test by clearning connections and closing browser

        System.out.println("Flight Price: " + tests.flightPrice);
        System.out.println("Train Price: " + tests.trainPrice);

    }

    public static void main(String[] args) throws InterruptedException, MalformedURLException {
        new App().getGreeting();
    }
}

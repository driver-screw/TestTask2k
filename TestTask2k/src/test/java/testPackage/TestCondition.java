package testPackage;


import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.Log4Test;

/**
 * Created by anatolii on 09/04/16.
 */
public class TestCondition {

    public static WebDriver driver;

    @BeforeSuite
    public void Before(){
        driver= new FirefoxDriver();
        driver.get("https://todomvc4tasj.herokuapp.com/");
        driver.manage().window().maximize();
        Log4Test.info("URL opened, window maximised. Start suit. \n");

    }

//    @Test
//    public void Testit(){
//        Log4Test.info("Star Test1 \n");
//        CommonActions doit = new CommonActions(driver);
//        doit.createNewTask("Task1");
//        doit.createNewTask("Task2");
//        doit.createNewTask("Task3");
//        doit.createNewTask("Task4");
//        doit.deleteTask("Task2");
//        doit.editTask("Task3");
//        doit.markTaskAsComplited("Task4");
//        doit.clearComplited();
//        doit.markAllAsComlited();
//        doit.clearComplited();
//    }

    @AfterSuite
    public void After(){
        if (driver!=null){
            driver.quit();
            Log4Test.info("End Suite");
        }
    }
}

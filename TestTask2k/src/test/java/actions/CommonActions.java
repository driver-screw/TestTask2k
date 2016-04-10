package actions;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import utils.Log4Test;

/**
 * Created by anatolii on 09/04/16.
 */
public class CommonActions  {

    public WebDriver driver;

    public CommonActions (WebDriver driver) {
        this.driver=driver;
      //  Log4Test.info(".");


    }

    public void createNewTask(String S){
        driver.findElement(By.xpath("//*[@id='new-todo']")).sendKeys(S, Keys.ENTER);
        Log4Test.info("Creating task "+S);
        try {
        WebElement element = driver.findElement(By.xpath("/html/body/section/section/ul/li[last()]/div/label"));
        String elementval = element.getText();
        Assert.assertEquals(elementval,S,Log4Test.fail("New task "+S+" wasn't created"));

        } catch (NoSuchElementException e) {
            Log4Test.error("There are no tasks");

        }

    }

    public void deleteTask(String S){
        Log4Test.info("Deleting task "+S);
        Actions builder = new Actions(driver);
        try {
            builder.moveToElement(driver.findElement(By.xpath("//label[.='" + S + "']"))).perform();
            driver.findElement(By.xpath("//label[.='" + S + "']/following-sibling::button[@class='destroy']")).click();
        } catch (NoSuchElementException e) {
            Log4Test.error("There is no task "+S);

        }
    }


    public boolean checkforTaskPresence (String S){

        try {
            driver.findElement(By.xpath("//label[.='" + S + "']"));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void editTask(String S1, String S2){
        Actions builder = new Actions(driver);
        builder.moveToElement(driver.findElement(By.xpath("//label[.='"+S1+"']"))).doubleClick().perform();
        builder.moveToElement(driver.findElement(By.xpath("//input[@class='edit']"))).perform();
        builder.sendKeys(Keys.chord(Keys.CONTROL, "a"),S2,Keys.ENTER).perform();
        Log4Test.info(S1+" task was edited ");
    }

    public void markTaskAsCompleted(String S){
        Log4Test.info(S+" mark as complited ");
        try {
            driver.findElement(By.xpath("//label[.='" + S + "']/preceding-sibling::input[@type='checkbox']")).click();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            Log4Test.error("There is no task "+S);
        }

    }

    public boolean checkTaskIsCompleted(String S){
        try {
            WebElement element = driver.findElement(By.xpath("//label[.='" + S + "']/../.."));
            String elparam=element.getAttribute("class");
            return elparam.equals("completed");
        } catch (org.openqa.selenium.NoSuchElementException e) {
            Log4Test.error("There is no task "+S);
            return false;
        }
    }

    public void clearCompleted(){
        Log4Test.info("Clearing completed tasks ");
        driver.findElement(By.xpath("//*[@id=\"clear-completed\"]")).click();
        try{
            driver.findElement(By.xpath("//li[@class='completed']"));
            Log4Test.error("There are still completed tasks" );
        } catch (org.openqa.selenium.NoSuchElementException e) {
            Log4Test.info("Clearing completed tasks successful");

        }

    }
    public  void toggleAll(){
        Log4Test.info("Toggle all ");
        try {
            driver.findElement(By.xpath("//*[@id=\"toggle-all\"]")).click();
        }catch (org.openqa.selenium.NoSuchElementException e){
            Log4Test.error("There are no tasks or toggle-all checkox.");
        }

    }
    public boolean checkActivePresence(){
        try {
            driver.findElement(By.xpath("//li[@class='active']"));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
           return false;
        }
    }

}

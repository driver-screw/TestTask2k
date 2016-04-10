package testPackage;

import actions.CommonActions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Log4Test;

/**
 * Created by anatolii on 09/04/16.
 */
public class Test1 extends TestCondition {

    @Test
    public void Testit(){
        Log4Test.info("Star Test1 \n");
        CommonActions doit = new CommonActions(driver);
        doit.createNewTask("Task1");
        doit.createNewTask("Task2");
        doit.createNewTask("Task3");
        doit.createNewTask("Task4");
        doit.deleteTask("Task2");
        Assert.assertFalse(doit.checkforTaskPresence("Task2"), Log4Test.fail("Task2"+" task wasn't deleted."));
        doit.editTask("Task3","Task03");
        Assert.assertTrue(doit.checkforTaskPresence("Task03"), Log4Test.fail("Task3"+" task wasn't edited to "+"Task03"));
        doit.markTaskAsCompleted("Task4");
        Assert.assertTrue(doit.checkTaskIsCompleted("Task4"),Log4Test.fail("Task "+"Task4"+" is not completed"));
        doit.clearCompleted();
        //Mark all as completed
        doit.toggleAll();
        Assert.assertFalse(doit.checkActivePresence(),Log4Test.fail("Not all tasks completed"));
        doit.clearCompleted();
    }

}

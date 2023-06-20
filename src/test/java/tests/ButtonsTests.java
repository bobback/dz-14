package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ButtonsTests {

    SoftAssert softAssert = new SoftAssert();

    private WebDriver driver;
    private Actions actions;


    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        actions = new Actions(driver);
        driver.get("https://demoqa.com/elements");
        driver.findElement(By.xpath("//*[@id=\"item-4\"]")).click();

    }

    @Test
    public void testDoubleClickButton(){
        actions.doubleClick(driver.findElement(By.xpath("//*[@id=\"doubleClickBtn\"]"))).perform();

        softAssert.assertEquals(driver.findElement(By.xpath("//*[@id=\"doubleClickMessage\"]"))
                .getText(), "You have done a double click", "Double click has not passed");
        driver.close();

}

    @Test
    public void testRightClickButton(){
        actions.contextClick(driver.findElement(By.xpath("//*[@id=\"rightClickBtn\"]"))).perform();

        softAssert.assertEquals(driver.findElement(By.xpath("//*[@id=\"rightClickMessage\"]"))
                .getText(), "You have done a right click", "Right click has not passed");
        driver.close();

    }

    @Test
    public void testClickMeButton(){
        actions.click(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div[3]/button")))
                .perform();

        softAssert.assertEquals(driver.findElement(By.xpath("//*[@id=\"dynamicClickMessage\"]"))
                .getText(), "You have done a dynamic click", "Dynamic click (Click Me button) has not passed");
        driver.quit();

    }

}

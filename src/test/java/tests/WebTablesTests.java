package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class WebTablesTests {
    SoftAssert softAssert = new SoftAssert();

    private WebDriver driver;


    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://demoqa.com/webtables");

    }

    @Test
    public void addRowTest(){
        driver.findElement(By.xpath("//*[@id=\"addNewRecordButton\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"firstName\"]")).sendKeys("John");
        driver.findElement(By.xpath("//*[@id=\"lastName\"]")).sendKeys("Doe");
        driver.findElement(By.xpath("//*[@id=\"userEmail\"]")).sendKeys("johndoe2023@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"age\"]")).sendKeys("23");
        driver.findElement(By.xpath("//*[@id=\"salary\"]")).sendKeys("5000");
        driver.findElement(By.xpath("//*[@id=\"department\"]")).sendKeys("QA");
        driver.findElement(By.xpath("//*[@id=\"submit\"]")).click();

        softAssert.assertEquals(driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/div[3]/div[1]/div[2]/div[4]/div/div[4]"))
                .getText(), "johndoe2023@gmail.com", "The row wasn't added");

        driver.close();

    }

    @Test
    public void editRowTest(){
        driver.findElement(By.xpath("//*[@id=\"edit-record-1\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"salary\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"salary\"]")).sendKeys("2500");
        driver.findElement(By.xpath("//*[@id=\"department\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"department\"]")).sendKeys("IT");
        driver.findElement(By.xpath("//*[@id=\"submit\"]")).click();

        softAssert.assertEquals(driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/div[3]/div[1]/div[2]/div[1]/div/div[5]"))
                .getText(), "2500", "Salary wasn't edited");
        softAssert.assertEquals(driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/div[3]/div[1]/div[2]/div[1]/div/div[6]"))
                .getText(), "IT", "Department wasn't edited");

        driver.close();

    }

    @Test
    public void deleteRowTest(){
        driver.findElement(By.xpath("//*[@id=\"delete-record-3\"]")).click();

        softAssert.assertNull(driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/div[3]/div[1]/div[2]/div[3]"))
                .getText(), "The row wasn't deleted");

        driver.quit();

    }
}

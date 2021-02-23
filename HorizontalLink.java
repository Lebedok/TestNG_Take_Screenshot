package Selenium.Screenshot;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class HorizontalLink {

    WebDriver driver;
    Actions actions;



    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        actions = new Actions(driver);


    }

    @Test
    public void Herokuapp1(){
        driver.get("http://the-internet.herokuapp.com/");
        takeScreenshot("herokuappHomePage");
        WebElement horizontalSlider = driver.findElement(By.linkText("Horizontal Slider"));
        actions.click(horizontalSlider).perform();
        takeScreenshot("horizontalSliderPage");
        WebElement slider = driver.findElement(By.tagName("input"));
        actions.clickAndHold(slider).moveByOffset(-25,0).release().perform();
        takeScreenshot("horizontalSliderMoved");
        WebElement range = driver.findElement(By.id("range"));
        Assert.assertEquals(range.getText(),"1.5");

    }


    public void takeScreenshot(String screenshotName) {
        // takes screenshot
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotDirectory = "screenshot/";

        // copy taken screenshot to screenshot directory
        try {
            FileUtils.copyFile(screenshot,
                    new File(screenshotDirectory + screenshotName + "-" + System.currentTimeMillis() + ".png"));
        } catch (IOException ex) {
            System.out.println("Screenshot was not taken");
            ex.printStackTrace();
        }
    }


    }




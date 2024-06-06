package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class NestedFrames {

    public static void main(String[] args) {
        // Set the path to your ChromeDriver executable
        System.setProperty("web driver.chrome.driver", "/path/to/chromedriver");

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        try {
            // Maximize the browser window
            driver.manage().window().maximize();

            // Navigate to the URL
            driver.get("https://the-internet.herokuapp.com/nested_frames");

            // Wait until the frames are loaded
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//frame[@name='frame-top']")));

            // Switch to the top frame using XPath
            driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-top']")));

            // Verify that there are three frames in the top frame
            List<WebElement> framesInTopFrame = driver.findElements(By.xpath("//frame"));
            if (framesInTopFrame.size() == 3) {
                System.out.println("There are three frames in the top frame.");
            } else {
                System.out.println("The number of frames in the top frame is incorrect.");
            }

            // Switch to the left frame
            driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-left']")));

            // Verify that the left frame has the text "LEFT"
            WebElement leftTextElement = driver.findElement(By.xpath("//body"));
            if (leftTextElement.getText().contains("LEFT")) {
                System.out.println("The left frame contains the text 'LEFT'.");
            } else {
                System.out.println("The left frame does not contain the text 'LEFT'.");
            }

            // Switch back to the top frame
            driver.switchTo().parentFrame();

            // Switch to the middle frame
            driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-middle']")));

            // Verify that the middle frame has the text "MIDDLE"
            WebElement middleTextElement = driver.findElement(By.xpath("//div"));
            if (middleTextElement.getText().contains("MIDDLE")) {
                System.out.println("The middle frame contains the text 'MIDDLE'.");
            } else {
                System.out.println("The middle frame does not contain the text 'MIDDLE'.");
            }

            // Switch back to the top frame
            driver.switchTo().parentFrame();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser instance
            driver.quit();
        }
    }
}

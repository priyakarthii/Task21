package tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Set;

public class WindowHandling {
    public static void main(String[] args) {

        // Set the path to your ChromeDriver executable
        System.setProperty("web driver.chrome.driver", "/path/to/chromedriver");

        // Initialize the Chrome driver
        WebDriver driver = new ChromeDriver();



        try {
            // Maximize the browser window
            driver.manage().window().maximize();

            // Navigate to the URL
            driver.get("https://the-internet.herokuapp.com/windows");

            // Wait until the "Click Here" link is visible and clickable
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement clickHereLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Click Here")));

            // Click the "Click Here" link to open a new window
            clickHereLink.click();

            // Get the handle of the original window

            String originalWindow = driver.getWindowHandle();

            // Wait for the new window to be opened and get the handle of all open windows
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            Set<String> allWindows = driver.getWindowHandles();

            // Switch to the new window
            for (String windowHandle : allWindows) {
                if (!windowHandle.equals(originalWindow)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }

            // Verify that the "New Window" text is present on the page
            WebElement newWindowText = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h3")));
            if (newWindowText.getText().equals("New Window")) {
                System.out.println("New window opened successfully.");
            } else {
                System.out.println("New window verification failed.");
            }

            // Close the new window
            driver.close();

            // Switch back to the original window
            driver.switchTo().window(originalWindow);

            // Verify that the original window is active
            if (driver.getWindowHandle().equals(originalWindow)) {
                System.out.println("Switched back to the original window successfully.");
            } else {
                System.out.println("Failed to switch back to the original window.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser instance
            driver.quit();
        }
    }















}

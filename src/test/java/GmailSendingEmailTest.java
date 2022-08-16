import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class GmailSendingEmailTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }

    /**
     *
     */
    @Test
    public void testGoogleImageSearchResultsAreImages() {
        driver.get("https://www.gmail.com/");

    }
}
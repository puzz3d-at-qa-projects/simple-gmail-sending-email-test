import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class GmailSendingEmailTest {

    static final String GMAIL_URL = "https://www.gmail.com/";
    static final String EMAIL = "private.static.final.long@gmail.com";
    static final String PASSWORD = "_8pZgRS2wA";

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
     * Authorizes given user at Gmail, sends a letter, and verifies that the sent box contains the letter
     * with the sent subject.
     */
    @Test
    public void testGmailLogInAndEmailSending() {
        String recipientAddress = "t.model101@yahoo.com";

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(GMAIL_URL);
        driver.findElement(By.id("identifierId")).sendKeys(EMAIL + Keys.ENTER);
        driver.findElement(By.name("password")).sendKeys(PASSWORD + Keys.ENTER);
        driver.findElement(By.xpath("//div[@gh='cm']")).click();
        String coolSubj = emailSubjectCompose();
        driver.findElement(By.xpath("//div[@role='listbox']/div/input")).sendKeys(recipientAddress);
        driver.findElement(By.name("subjectbox")).sendKeys(coolSubj);
        driver.findElement(By.cssSelector("div.aoO")).click();
        driver.findElement(By.xpath("//a[contains(@href,'#sent')]")).click();
        List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + coolSubj + "')]"));
        Assert.assertTrue( list.size() > 0, "Subject not found!");
    }

    /**
     * Build unique (well sort of) string
     * @return very hot subject for 2022.
     */
    private String emailSubjectCompose() {
        var rnd = new Random();
        return new StringBuilder()
                .append("Forward this message to ")
                .append(rnd.nextInt(6666-666) + 666)
                .append(" friends, or Putin will die in ")
                .append(rnd.nextInt(99-9)+9)
                .append(" days!")
                .toString();
    }
}
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;

import java.sql.Driver;

public class Main {
    static WebDriver driver;

    @FindBy(name = "jid02c628")
    private WebElement principalForm;
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().browserVersion("100.0.4896.60").setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("enable-automation");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-browser-side-navigation");
        options.addArguments("--disable-gpu");
        options.addArguments("--allowed-ips");
        driver = new ChromeDriver(options);
        driver.get("https://www.rnpdigital.com/shopping/login.jspx");
        PrincipalPage element = new PrincipalPage(driver);
        LoginRNacional(element);
        GetFreeRequest();


        //driver.close();
       // driver.manage().window().fullscreen();
    }

    static void LoginRNacional( PrincipalPage pageElements) throws InterruptedException {
        pageElements.SystemLogin("email","password");
    }

    static void GetFreeRequest() throws InterruptedException {
        PrincipalPage element = new PrincipalPage(driver);
        element.GetFreeRequest();
    }
}



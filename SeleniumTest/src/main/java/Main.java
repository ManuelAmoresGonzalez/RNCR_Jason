import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;


public class Main {
    static WebDriver driver;

    @FindBy(name = "jid02c628")
    private WebElement principalForm;
    public static void main(String[] args) {

        System.setProperties("webdriver.chrome.driver");

    }

    static void LoginRNacional( PrincipalPage pageElements) throws InterruptedException {
        pageElements.SystemLogin("email","password");
    }

    static void GetFreeRequest() throws InterruptedException {
        PrincipalPage element = new PrincipalPage(driver);
        element.GetFreeRequest();
    }
}



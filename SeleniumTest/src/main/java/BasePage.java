import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;


public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,65);
    }

    public void moveComponent(WebElement dragAccept, int x, int y){
        Actions action= new Actions(driver);
        action.dragAndDropBy(dragAccept,x,y).perform();
    }

    public void scrollPage(){
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(686, 661)");
    }


    public void findElement(String xpathExpression){
        clickOnElement(driver.findElement(By.xpath(xpathExpression)));
    }

    public boolean verifyStatusOfPage(String urlPage){
        JavascriptExecutor j = (JavascriptExecutor) driver;
        if((j.executeScript("return document.readyState").toString()).equals("complete")) {
            String pageURL = driver.getCurrentUrl();
            if (pageURL.equals(urlPage)) {
                System.out.println("Page loaded correctly: " + driver.getTitle());
                System.out.println("And the URL is: " + pageURL);

                return true;
            }
        }
        return false;
    }

    public boolean switchTabsAndVerifyStatusOfPage(){
        List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
        //switch to new tab
        driver.switchTo().window(browserTabs .get(1));
        String urlSecondPage = driver.getCurrentUrl();
        return verifyStatusOfPage(urlSecondPage);
    }

    public boolean typeOnElement(WebElement element, String text){
        if (waitForElementToBeEnabled(element)){
            element.sendKeys(text);
            return true;
        }
        System.out.println("Type on element: Element is not enabled");
        return false;
    }
    public boolean clickDay(WebElement element){
        if(wait.until(ExpectedConditions.visibilityOfAllElements(element)).get(0) != null ) {
            List<WebElement> days = element.findElements(By.tagName("div"));
            for(WebElement day: days) {
                    day.click();
                    return true;
                }
            }

        return false;
    }

    public void doubleClickOnElement(WebElement element){
        Actions actionClick = new Actions(driver);
        actionClick.doubleClick(element).perform();
    }

    public void rightClickOnElement(WebElement element){
        Actions actionClick = new Actions(driver);
        actionClick.contextClick(element).perform();
    }

    public void clickOnElement(WebElement element){
        if(waitForElementToBeEnabled(element)){
            element.click();
        } else {
            System.out.println("Al " + element.getText() + " no se le puede hacer click");
        }
    }
    public String getText(WebElement element){
        if(waitForElementToBeVisibled(element)){
            return element.getText();
        }
        return "";
    }
    public String getElementClass(WebElement element){
        if(waitForElementToBeVisibled(element)){
            return element.getAttribute("class");
        }

        return "";
    }

    public boolean waitForElementToBeEnabled(WebElement element){
        return wait.until(ExpectedConditions.elementToBeClickable(element)) != null;
    }

    public boolean waitForElementToBeVisibled(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element)) != null;
    }

    public void waitElement() throws InterruptedException {
        Thread.sleep(1000);
    }





}

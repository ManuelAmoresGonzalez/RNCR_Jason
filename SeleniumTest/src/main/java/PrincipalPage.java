import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PrincipalPage extends BasePage{

    @FindBy(tagName = "form")
    private List<WebElement> container_Form;


    public PrincipalPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public void SystemLogin(String email, String password) throws InterruptedException {
        WebElement form = (container_Form.get(2));
        typeOnElement(form.findElements(By.tagName("input")).get(2), email);
        typeOnElement(form.findElements(By.tagName("input")).get(3), password);
        clickOnElement(form);
        waitElement();
        clickOnElement(form.findElements(By.tagName("input")).get(4));
    }

    public void GetFreeRequest() throws InterruptedException {
        driver.findElement(By.id("mainmenu"));
        List<WebElement> formsInElement = driver.findElement(By.id("mainmenu")).findElements(By.tagName("form"));
        WebElement buttonRequest = formsInElement.get(0).findElements(By.tagName("li")).get(3);
        clickOnElement(buttonRequest);
        waitElement();
        WebElement buttonInformationCar = driver.findElement(By.id("printerDiv"));
        GetFormCarWithID(buttonInformationCar);
    }

    public void GetFormCarWithID(WebElement buttonInformationCar) throws InterruptedException {
        WebElement ulElements = buttonInformationCar.findElements(By.tagName("ul")).get(1);
        clickOnElement(ulElements.findElements(By.tagName("a")).get(5));
        waitElement();
        typeOnElement(driver.findElement(By.id("carNumber")), "323505");
        WebElement formConsult = driver.findElement(By.id("params"));
        clickOnElement(formConsult.findElements(By.tagName("a")).get(0));
        GetInformationSpecificCar();
    }

    public void GetInformationSpecificCar(){
        WebElement buttonInformationCar = driver.findElement(By.id("printerDiv"));
        WebElement listInComponent = buttonInformationCar.findElements(By.tagName("table")).get(0);
        WebElement principalTable = listInComponent.findElements(By.tagName("table")).get(1);
        WebElement ownerCar = listInComponent.findElements(By.tagName("table")).get(4);
        String owner = getText(ownerCar.findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(3));
        WebElement brandStyle = principalTable.findElements(By.tagName("tr")).get(0);
        WebElement carValue = principalTable.findElements(By.tagName("tr")).get(5);
        List<WebElement> carBrand = brandStyle.findElements(By.tagName("td"));
        List<WebElement> valueCar = carValue.findElements(By.tagName("td"));
        String marca = getText(carBrand.get(1));
        String style = getText(carBrand.get(3));
        String value = getText(valueCar.get(3));
        InformationCar information = new InformationCar(marca, style,value, owner );

        System.out.println("Datos del auto de placa 323505");
        System.out.println("Marca del carro: "+information.marca);
        System.out.println("Estilo del carro: "+information.style);
        System.out.println("Valor del carro: "+information.valorHacienda);
        System.out.println("Propietario del carro: "+information.propietario);
        System.out.println();
    }

}

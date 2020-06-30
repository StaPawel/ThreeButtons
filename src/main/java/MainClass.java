import com.thoughtworks.selenium.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class MainClass {
    public static void main (String [] arg){
        //implementacja sciezki do chromedrivera
        String driverPath = "C:\\chromedriver.exe";
        //implementacja adresu strony
        String urlPath = "https://antycaptcha.amberteam.pl/exercises/exercise1?seed=9bfde5a0-8c20-48e0-a8be-329cd3ffde21";
        //implementacja drivera
        WebDriver driver;
        //inicjalizacja drivera
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.get(urlPath);
        //tworzenie obiektu wait typu WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver,2);
        //oczekiwana odpowiedz po poprawnie zakonczonym tescie
        String expectedAnswer = "OK. Good answer";

        //inicjalizacja elementow
        WebElement b1Button = driver.findElement(By.id("btnButton1"));
        WebElement b2Button = driver.findElement(By.id("btnButton2"));
        WebElement checkButton = driver.findElement(By.id("solution"));
        WebElement answer = driver.findElement(By.id("trail"));

        //zaczekaj na widoczny przycisk B1 a nastepnie kliknij w niego
        wait.until(ExpectedConditions.visibilityOf(b1Button));
        b1Button.click();

        //zaczekaj na widoczny przycisk B2 a nastepnie kliknij w niego 2 razy
        wait.until(ExpectedConditions.visibilityOf(b2Button));
        for (int i = 0; i <= 2; i++){
            b2Button.click();
        }

        //zaczekaj na widoczny przycisk CHECK SOLUTION i kliknij w niego
        wait.until(ExpectedConditions.visibilityOf(checkButton));
        checkButton.click();

        //pobierz odpowiedz i przypisz ja do zmiennej answerString
        String answerString = answer.getText();

        //jesli odpowiedz nie zawiera frazy OK. to pobierz odpowiedz jeszcze raz
        while (!answerString.contains("OK.")){
            answerString = answer.getText();
        }

        //pobierz docelowa odpowiedz
        answerString = answer.getText();

        //porownaj czy oczekiwana odpowiedz i aktualna odpowiedz sa takie same
        Assert.assertEquals(answerString,expectedAnswer);

    }
}

package qa_old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;


public class LetsBeTestersTest extends BaseTest {

    WebDriverWait wait;

    @BeforeMethod
    private void before() {
        wait = new WebDriverWait(getDriver(), 10);
    }

    private void openWebSite(String url) {
        getDriver().get(url);
    }

    @Test
    public void testCountOfSectionButtons() {

        openWebSite("https://www.nokia.com/");

        getDriver().findElement(By.id("modalAcceptAllBtn")).click();

        List<WebElement> carousel = getDriver().findElements(By.xpath("//div[contains(@id, 'tns1-item')]//h2"));

        Assert.assertEquals(carousel.size(), 9);
    }

    @Test
    public void testCheckTablet() {

        openWebSite("https://www.nokia.com/");

        getDriver().findElement(By.id("modalAcceptAllBtn")).click();

        Actions action = new Actions(getDriver());

        List<WebElement> menu = getDriver().findElements(By.xpath("//li[contains(@class, 'dropdown-menu')]/a"));
        WebElement forConsumers = menu.stream().filter(el2 -> el2.getText().equals("For consumers")).findFirst().orElse(null);

        action.moveToElement(forConsumers).build().perform();

        getDriver().findElement(By.xpath("//li[@class='dropdown-submenu-item']/a[text()='Phones']")).click();
        getDriver().findElement(By.xpath("//a[@data-gtm-cta='tablets']")).click();
        String actualResult = getDriver().findElement(By.xpath("//li[contains(@class, 'h5')]")).getText();

        Assert.assertEquals(actualResult, "Nokia T20");
    }

    @Test
    public void testKICheckAddress() {

        openWebSite("https://davinagaz.by/");

        getDriver().findElement(By.xpath("//b[text()= ' Полесская 14']")).click();

        String address = "Полесская 14";

        WebElement actualResult = getDriver().findElement(By.cssSelector(".name-office"));

        Assert.assertTrue(actualResult.getText().contains(address));
    }

    @Test
    public void testKICheckHeader() throws InterruptedException {

        openWebSite("https://davinagaz.by/");

        getDriver().findElement(By.xpath("//b[text()= ' Полесская 14']")).click();

        String expectedResult = "Аккумулятор";

        WebElement menu = getDriver().findElement(By.xpath("//a[text()='ТО и фильтра']"));

        Actions action = new Actions(getDriver());
        action.moveToElement(menu).perform();
        Thread.sleep(1000);
        getDriver().findElement(By.xpath("//a[text()='Аккумулятор']")).click();
        Thread.sleep(1000);

        WebElement actualResult = getDriver().findElement(By.tagName("h1"));

        Assert.assertEquals(actualResult.getText(), expectedResult.toUpperCase());
    }

    @Test
    public void testElementsTextBox() {

        openWebSite("https://demoqa.com/");

        getDriver().findElement(By.xpath("//h5[text()='Elements']")).click();

        String[] testData = {"Maksim", "test@test.com", "Sankt-Peterburg"};

        getDriver().findElement(By.id("item-0")).click();

        getDriver().findElement(By.id("userName")).sendKeys(testData[0]);
        getDriver().findElement(By.id("userEmail")).sendKeys(testData[1]);
        getDriver().findElement(By.id("currentAddress")).sendKeys(testData[2]);

        getDriver().findElement(By.id("submit")).click();

        String[] actualData = new String[3];
        actualData[0] = getDriver().findElement(By.id("name")).getText().replace("Name:", "");
        actualData[1] = getDriver().findElement(By.id("email")).getText().replace("Email:", "");
        actualData[2] = getDriver().findElement(By.xpath("//*[@class='mb-1'][@id='currentAddress']"))
                .getText().replace("Current Address :", "");

        System.out.println(getDriver().findElement(By.id("currentAddress")).getText());

        Assert.assertEquals(actualData, testData);
    }

    @Test
    public void testElementsRadioButton() {

        openWebSite("https://demoqa.com/");

        getDriver().findElement(By.xpath("//h5[text()='Elements']")).click();

        getDriver().findElement(By.id("item-2")).click();

        getDriver().findElement(By.xpath("//*[@for='impressiveRadio']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//*[@class='text-success']")).getText(), "Impressive");
    }

    @Test
    public void testFlagmaMainPageOpening() {

        openWebSite("https://flagma.si/");

        getDriver().findElement(By.xpath("//a[@id='uc-lang-ru']")).click();

        WebElement goodAndServicesTitle = getDriver().findElement(By.xpath("//h1"));

        String actualResult = goodAndServicesTitle.getText();
        String expectedResult = "Товары и услуги в Словении";

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testOfNavigationToCoffeeSection() {

        openWebSite("https://flagma.si/");

        getDriver().findElement(By.xpath("//a[@id='uc-lang-ru']")).click();

        Actions actions = new Actions(getDriver());

        WebElement sideBarMenu = getDriver().findElement(By.xpath("//div[@class='toggle-cats']"));
        sideBarMenu.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='cat-746']")));
        WebElement foodMenuItem = getDriver().findElement(By.xpath("//a[@class='cat-746']"));
        actions.moveToElement(foodMenuItem).perform();

        WebElement groupOfDrinksItem = getDriver().findElement(By.xpath("//span[text()='Вода, напитки, соки']"));
        actions.moveToElement(groupOfDrinksItem).perform();

        WebElement coffeeTeaCacaoItem = getDriver().findElement(By.xpath("//span[text()='Чай, кофе, какао']"));
        actions.moveToElement(coffeeTeaCacaoItem).perform();

        WebElement coffeeItem = getDriver().findElement(By.xpath("//a[@href='https://flagma.si/ru/products/kofe/']/span[text()='Кофе']"));
        actions.moveToElement(coffeeItem).click().perform();

        WebElement titleOfCoffeeSection = getDriver().findElement(By.xpath("//h1"));

        String actualResult = titleOfCoffeeSection.getText();
        String expectedResult = "Кофе в Словении";

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testCheckSmartphoneIphoneSelection() {

        openWebSite("https://flagma.si/");

        getDriver().findElement(By.xpath("//a[@id='uc-lang-ru']")).click();

        WebElement inputSearch = getDriver().findElement(By.xpath("//div[@id='search-input']//input[@name='q']"));
        inputSearch.sendKeys("iphone\n");

        WebElement searchResultTitle = getDriver().findElement(By.xpath("//div[@class='title']"));

        String actualResult = searchResultTitle.getText();
        String expectedResult = "Найдено по запросу iphone в Словении";

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Ignore
    @Test
    public void testRegistration() {

        openWebSite("https://cloud.swivl.com/register/");

        WebElement NamePlaceholderElement = getDriver().findElement(By.id("swivl_registration_firstName"));
        WebElement LastNamePlaceholderElement = getDriver().findElement(By.id("swivl_registration_lastName"));
        WebElement EmailPlaceholderElement = getDriver().findElement(By.id("swivl_registration_email"));
        WebElement PasswordPlaceholderElement = getDriver().findElement(By.id("swivl_registration_plainPassword_first"));
        WebElement ConfirmPasswordPlaceholderElement = getDriver().findElement(By.id("swivl_registration_plainPassword_second"));
        Select AgeDropDownPlaceholder = new Select(getDriver().findElement(By.id("swivl_registration_age")));
        Select CountryDropDownPlaceholder = new Select(getDriver().findElement(By.id("swivl_registration_country")));
        Select RoleDropDownPlaceholder = new Select(getDriver().findElement(By.id("swivl_registration_role_rolePreset")));

        NamePlaceholderElement.sendKeys("John");
        LastNamePlaceholderElement.sendKeys("Smith");
        EmailPlaceholderElement.sendKeys("fmvmug@midiharmonica.com");
        PasswordPlaceholderElement.sendKeys("fmvmug@midiharmonica.com");
        ConfirmPasswordPlaceholderElement.sendKeys("fmvmug@midiharmonica.com");
        AgeDropDownPlaceholder.selectByIndex(2);
        CountryDropDownPlaceholder.selectByVisibleText("Japan");
        RoleDropDownPlaceholder.selectByVisibleText("IT");

        getDriver().findElement(By.xpath("//button[@id = 'formSubmit']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class='control__error']/a")).getText(), "Sign in");
    }

    @Test
    public void testEbayFindProduct() {

        openWebSite("https://www.ebay.com/");

        WebElement searchRow = getDriver().findElement(By.xpath("//input[@class = 'gh-tb ui-autocomplete-input']"));
        searchRow.click();
        searchRow.sendKeys("Ipad");
        WebElement buttonSearch = getDriver().findElement(By.id("gh-btn"));
        buttonSearch.click();
        WebElement actualResult = getDriver().findElement(By.xpath("//h1[@class = 'srp-controls__count-heading']/span[@class = 'BOLD'][2]"));

        Assert.assertEquals(actualResult.getText(), "ipad");
    }

    @Ignore
    @Test
    public void changeShipCountry(){

        openWebSite("https://www.ebay.com/");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@id = 'gh-shipto-click']")));
        getDriver().findElement(By.xpath("//li[@id = 'gh-shipto-click']")).click();

        getDriver().findElement(By.xpath("//button[@class = 'expand-btn expand-btn--default menu-button__button']")).click();
        getDriver().findElement(By.xpath("//span[text()='Afghanistan']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='flag-wrapper']/span[@data-country='AFG|AF']")));

        getDriver().findElement(By.xpath("//button[@class = 'shipto__close-btn']")).click();

        wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//i[contains(@class, 'flaaf')]"))));
        WebElement flag = getDriver().findElement(By.xpath("//i[contains(@class, 'flaaf')]"));
        Assert.assertTrue(flag.isDisplayed());
    }
}

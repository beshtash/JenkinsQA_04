import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class TestAddDescription extends BaseTest {
    private final String PROJECT_NAME = "Some_Project";

    protected void addDescription() {
        getDriver().findElement(By.cssSelector("span[class='task-link-text']")).click();
        WebElement entryField = getDriver().findElement(By.id("name"));
        entryField.sendKeys(PROJECT_NAME);
        getDriver().findElement(By.cssSelector("li[tabindex='0'][class='hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();
        getDriver().findElement(By.name("description")).sendKeys("This is a sample project.");
        getDriver().findElement(By.id("yui-gen25-button")).click();
    }
    protected void deleteProject() {
        getDriver().findElement(By.cssSelector("img#jenkins-head-icon")).click();
        getDriver().findElement(By.xpath("//a[contains(@href, 'Some_Project')]")).click();
        getDriver().findElement(By.cssSelector("a[data-post='true']")).click();
        getDriver().switchTo().alert().accept();
    }

    @Test
    public void descriptionTest() {
        final String expectedText = "This is a sample project.";
        addDescription();
        String actualText = getDriver().findElement(By.xpath("//*[@id=\"description\"]/div[1]")).getText();
        Assert.assertEquals(actualText, expectedText);
        deleteProject();
    }
}

package org.icechamps.mvctdd.page;

import com.google.common.base.Predicate;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

@Data
public class HomePage {
    @FindBy(css = "h1.title")
    private WebElement greetingText;
    private WebElement name;
    private WebElement submit;
    private WebElement ajax;
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public <T> T submitForm(Class<T> resultPage, String name) {
        this.name.sendKeys(name);
        this.submit.click();
        return PageFactory.initElements(driver, resultPage);
    }

    public void ajaxSubmitForm(String name) {
        waitForJSandJQueryToLoad();

        this.name.sendKeys(name);
        this.ajax.click();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(new Predicate<WebDriver>() {
            @Override
            public boolean apply(WebDriver input) {
                return input.findElement(By.cssSelector(".title")).isDisplayed();
            }
        });
    }

    public static HomePage to(WebDriver driver) {
        driver.get("/");
        return PageFactory.initElements(driver, HomePage.class);
    }

    private boolean waitForJSandJQueryToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = d -> {
            try {
                return ((Long) ((JavascriptExecutor) getDriver()).executeScript("return jQuery.active") == 0);
            } catch (Exception e) {
                // no jQuery present
                return true;
            }
        };

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = d -> ((JavascriptExecutor) getDriver()).executeScript("return document.readyState")
                .toString().equals("complete");

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }
}

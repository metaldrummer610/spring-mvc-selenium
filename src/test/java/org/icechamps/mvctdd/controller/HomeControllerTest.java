package org.icechamps.mvctdd.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)
public class HomeControllerTest {
    @Autowired
    private WebDriver webDriver;

    @Test(expected = NoSuchElementException.class)
    public void index() throws Exception {
        this.webDriver.get("/");
        webDriver.findElement(By.tagName("greetingText"));
    }

    @Test
    public void greeting() throws Exception {
        webDriver.get("/");
        WebElement nameInput = webDriver.findElement(By.cssSelector("input[name='name']"));
        nameInput.sendKeys("asdf");

        webDriver.findElement(By.tagName("form")).submit();

        assertThat(webDriver.getCurrentUrl()).endsWith("/");
        assertThat(webDriver.findElement(By.tagName("greetingText")).getText()).isEqualToIgnoringCase("Hey asdf");
    }

}
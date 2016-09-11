package org.icechamps.mvctdd.controller;

import org.icechamps.mvctdd.page.HomePage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {HomeController.class, AjaxController.class})
public class HomeControllerWebDriverTest {
    @Autowired
    private WebDriver webDriver;

    @Test(expected = NoSuchElementException.class)
    public void testPageRender() {
        HomePage home = HomePage.to(webDriver);
        assertThat(home.getGreetingText().getText());
    }

    @Test
    public void testFormSubmit() {
        HomePage home = HomePage.to(webDriver);
        HomePage formSubmitted = home.submitForm(HomePage.class, "asdf");

        assertThat(formSubmitted.getGreetingText().getText()).isEqualToIgnoringCase("Hey asdf");
    }

    @Test
    public void testAjaxFormSubmit() {
        HomePage home = HomePage.to(webDriver);
        home.ajaxSubmitForm("asdf2");

        assertThat(home.getGreetingText().getText()).isEqualToIgnoringCase("Hey asdf2");
    }
}

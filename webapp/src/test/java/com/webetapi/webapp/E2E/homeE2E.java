package com.webetapi.webapp.E2E;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class homeE2E {

    @LocalServerPort
    private Integer port;
    private WebDriver webDriver = null;
    private String baseUrl;

    @BeforeAll
    public static void setUpFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    public void setUpWebDriver() {
        webDriver = new FirefoxDriver();
        baseUrl = "http://localhost:" + port + "/home";
    }

    @AfterEach
    public void quitWebDriver() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }


    @Test
    public void watchlist() {

        // GIVEN
        webDriver.get(baseUrl);
        final WebElement submitButton = webDriver.findElement(By.id("Supprimer"));

        // WHEN
        submitButton.click();

        // THEN

    }

}

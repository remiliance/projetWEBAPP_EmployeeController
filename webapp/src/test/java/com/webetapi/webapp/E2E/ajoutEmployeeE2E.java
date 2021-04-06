package com.webetapi.webapp.E2E;


import com.webetapi.webapp.CustomProperties;
import com.webetapi.webapp.model.Employee;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ajoutEmployeeE2E {

    @LocalServerPort
    private Integer port;
    private WebDriver webDriver = null;
    private String baseUrl;

    @Autowired
    private CustomProperties props;


    @BeforeAll
    public static void setUpFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    public void setUpWebDriver() {
        webDriver = new FirefoxDriver();
        baseUrl = "http://localhost:" + port + "/createEmployee";

    }

    @AfterEach
    public void quitWebDriver() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }


    @Test
    public void AjoutUnEmployee() {

        // GIVEN
        webDriver.get(baseUrl);
        final WebElement NomField = webDriver.findElement(By.id("firstNameInput"));
        final WebElement PrenomField = webDriver.findElement(By.id("lastNameInput"));
        final WebElement EmailField = webDriver.findElement(By.id("mailInput"));
        final WebElement PasswordField = webDriver.findElement(By.id("passwordInput"));
        final WebElement submitButton = webDriver.findElement(By.id("Submit"));


        // WHEN
        NomField.sendKeys("Tapis");
        PrenomField.sendKeys("Bernard");
        EmailField.sendKeys("Bernard@tapis.com");
        PasswordField.sendKeys("BerBer");
        submitButton.click();

        // THEN
//appelle de l'API rest et check dans la liste retour des employees
      final WebDriverWait waiter = new WebDriverWait(webDriver, 5);

        String baseApiUrl = props.getApiUrl();
        String getEmployeesUrl = baseApiUrl + "/employees";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Employee>> response = restTemplate.exchange(
                getEmployeesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Employee>>(){});

        Employee rechercheBernard = response.getBody().stream().filter(Employee -> "Bernard@tapis.com".equals(Employee.getMail()))
                .findAny()
                .orElse(null);

        assertThat(rechercheBernard.getFirstName()).isEqualTo("Tapis");

    }



}

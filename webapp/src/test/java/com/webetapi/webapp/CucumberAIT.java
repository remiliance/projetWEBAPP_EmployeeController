package com.webetapi.webapp;

import com.webetapi.webapp.model.Employee;
import com.webetapi.webapp.repository.EmployeeProxy;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", plugin = { "pretty", "html:target/html-cucumber-report" })
public class CucumberAIT {


    @Given("un élève utilise le Calculateur")
    public void un_élève_utilise_le_Calculateur() {
        throw new cucumber.api.PendingException();
    }


    @When("{int} et {int} sont additionnés")
    public void et_sont_additionnés(Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
      throw new cucumber.api.PendingException();
    }

    @Then("on montre {int} à l'élève")
    public void on_montre_à_l_élève(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }
}

package com.tim.app;

import cucumber.api.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;

import static org.junit.Assert.*;

public class Stepdefs {
    private String today;
    private String actualAnswer;

    @Given("^today is \"([^\"]*)\"$")
    public void today_is(String today){
        this.today = today;
    }

    @When("I ask whether its Friday yet")
    public void i_ask_whether_its_friday_yet(){
        actualAnswer = this.today.equals("Friday") ? "Yes!" : "Nope";
    }

    @Then("^I should be told \"([^\"]*)\"$")
    public void i_should_be_told(String answer){
        assertEquals(answer, actualAnswer);
    }
}

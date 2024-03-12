package dtu.travel.steps;

import dtu.travel.ResponseMessage;
import dtu.travel.Station;
import dtu.travel.TravelCard;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class checkinSteps {
    TravelCard card = new TravelCard();
    Station station = null;
    ResponseMessage response= null;

    @Given("A travel card with balance of {int}")
    public void aTravelCardWithBalanceOf(int balance) {
        card.setBalance(balance);
    }

    @And("Check in status false")
    public void checkInStatusFalse() {
        card.setCheckinStatus(false);
    }

    @And("Check in machine at {string}")
    public void checkInMachineAt(String stationName) {
        this.station = new Station(stationName);
    }

    @When("Check in")
    public void checkIn() {
        this.response = station.checkin(card);
    }

    @Then("Check in machine should return a response message that the card is checked in.")
    public void checkInMachineShouldReturnAResponseMessageThatTheCardIsCheckedIn() {
        assertEquals("checked in", response.getMessage());
    }

    @And("Check in status true")
    public void checkInStatusTrue() {
        card.setCheckinStatus(true);
    }

    @Then("Check in machine should return a response message that the card is already checked in.")
    public void checkInMachineShouldReturnAResponseMessageThatTheCardIsAlreadyCheckedIn() {
        assertEquals("already checked in", response.getMessage());
    }

    @Then("Check in machine should return a response message that the balance is not enough.")
    public void checkInMachineShouldReturnAResponseMessageThatTheBalanceIsNotEnough() {
        assertEquals("the balance is not enough", response.getMessage());
    }
}

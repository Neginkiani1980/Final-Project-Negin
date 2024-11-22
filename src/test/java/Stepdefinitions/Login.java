package Stepdefinitions;
import Utilities.ConfigurationReader;
import Utilities.Driver;
import Utilities.SeleniumUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import Pages.LoginPage;

public class Login {

    LoginPage loginpage = new LoginPage();

    @Given("I am an external user of the {string},")
    public void i_am_an_external_user_of_the(String string) throws InterruptedException {

        Driver.getDriver().get(ConfigurationReader.getPropertyValue("craterUrl"));
        SeleniumUtils.sendkeysWithActionsClass(loginpage.emailInput,"entityadmin@primetechschool.com");
        Thread.sleep(2000);
        SeleniumUtils.sendkeysWithActionsClass(loginpage.passwordInput,"primetech@school");
        Thread.sleep(2000);
        loginpage.loginButton.click();
        Thread.sleep(2000);
    }

    @When("I have logged in to the application,")
    public void i_have_logged_in_to_the_application() {
    }

    @And("I click on the {string} Menu Link,")
    public void i_click_on_the_menu_link(String string) {
    }

    @And("I click on {string},")
    public void i_click_on(String string) {
    }

    @Then("I enter the details for {string}, {string}, {string}, {string}.")
    public void i_enter_the_details_for(String string, String string2, String string3, String string4) {
    }


}

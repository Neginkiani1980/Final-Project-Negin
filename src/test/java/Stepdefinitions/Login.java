package Stepdefinitions;
import Pages.itemsPage;
import Utilities.DataReader;
import Utilities.DatabaseUtils;
import Utilities.Driver;
import Utilities.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import Pages.LoginPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class Login {
    String BaseURL= "http://crater.primetech-apps.com/";
    WebDriver driver= Driver.getDriver();
    LoginPage LoginPage = new LoginPage();
    itemsPage itemsPage= new itemsPage();


    String name1= Utils.product();
    String price1= "17.99";
    String query="select * from CraterDBS.items order by created_at desc;";
    String description= Utils.randomLongtxt(120);
    String unit="ct";

    LoginPage loginpage = new LoginPage();

    @Given("I am an external user of the {string},")
    public void i_am_an_external_user_of_the(String string) throws InterruptedException {

        Driver.getDriver().get(DataReader.getPropertyValue("craterUrl"));
        Utils.sendkeysWithActionsClass(loginpage.EmailInput,"entityadmin@primetechschool.com");
        Thread.sleep(2000);
        Utils.sendkeysWithActionsClass(loginpage.loginPassword,"primetech@school");
        Thread.sleep(2000);
        loginpage.loginButton.click();
        Thread.sleep(2000);
    }

    @Given("I am an external user of the Prime Tech Invoice Application,")
    public void i_am_an_external_user_of_the_prime_tech_invoice_application() throws InterruptedException {
        driver.get(DataReader.getPropertyValue("craterURL"));
        String actualURL=driver.getCurrentUrl();
        driver.getCurrentUrl();

        Assert.assertEquals(BaseURL,actualURL);
        Assert.assertTrue(LoginPage.loginButton.isDisplayed());

        LoginPage.EmailInput.sendKeys(DataReader.getPropertyValue("userName"));
        LoginPage.loginPassword.sendKeys(DataReader.getPropertyValue("userPassword"));
        LoginPage.loginButton.click();
        Thread.sleep(2000);
    }
    @When("I have logged in to the application,")
    public void i_have_logged_in_to_the_application() {
        Assert.assertEquals( (BaseURL+"admin/dashboard"), driver.getCurrentUrl());
        Assert.assertTrue(LoginPage.dashTab.isDisplayed());
    }



    @When("I click on the ‘Items’ Menu Link,")
    public void i_click_on_the_items_menu_link() throws InterruptedException {
        LoginPage.itemTab.click();
        Thread.sleep(1000);
    }
    @When("I click on + Add Item,")
    public void i_click_on_add_item() throws InterruptedException {
        itemsPage.addItemBtn.click();
        Thread.sleep(1000);

    }
    @Then("I enter the details for name, pc, price, description.")
    public void i_enter_the_details_for_name_pc_price_description() throws InterruptedException {
        Assert.assertEquals(BaseURL+"admin/items/create", driver.getCurrentUrl());
        Assert.assertTrue(itemsPage.itemsNewItemLbl.isDisplayed());
        Thread.sleep(2000);

        itemsPage.itemsNameInput.sendKeys(name1);
        itemsPage.itemsPrice.sendKeys(price1);
        itemsPage.itemsDescription.sendKeys(description);
        Thread.sleep(1000);
        itemsPage.itemsUnit.sendKeys(unit + Keys.ENTER);
        itemsPage.itemsSaveBtn.click();
        Thread.sleep(3000);
        Assert.assertEquals(BaseURL+"admin/items", driver.getCurrentUrl());
        Assert.assertTrue(itemsPage.itemsMessageSuccess.isDisplayed());
        Assert.assertEquals("Success!",itemsPage.itemsMessageSuccess.getText());
        Assert.assertTrue(itemsPage.itemsMessage2.isDisplayed());
        Assert.assertEquals("Item created successfully",itemsPage.itemsMessage2.getText());

        Assert.assertTrue(driver.findElement(By.xpath("//td[.='"+name1+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'"+price1+"')]")).isDisplayed());
        String actualName= DatabaseUtils.selectRecord(query, "name");
        Assert.assertEquals(name1,actualName);

        String actualDescription= DatabaseUtils.selectRecord(query, "description");
        Assert.assertEquals(description,actualDescription);

    }

}

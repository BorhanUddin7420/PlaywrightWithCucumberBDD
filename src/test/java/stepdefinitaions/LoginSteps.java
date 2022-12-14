package stepdefinitaions;

import com.pages.LoginPage;
import com.qa.managers.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginSteps {

    private LoginPage loginPage = new LoginPage(DriverFactory.getPage());

    @Given("customer is on login page")
    public void customer_is_on_login_page() {
        loginPage.gotoHomePage();
        loginPage.gotoLoginPage();
    }

    @And("Verify login page title should be {string}")
    public void verifyLoginPageTitleShouldBe(String exPageTitle) {
        String acPageTitle = loginPage.getLoginPageTitle();
        Assert.assertEquals(exPageTitle, acPageTitle);
    }


    @When("customer input {string} and {string} in login page")
    public void customerInputAndInLoginPage(String email, String password) {
        loginPage.inputLoginPageEmail(email);
        loginPage.inputLoginPagePassword(password);
    }

    @And("customer click login button in login page")
    public void customerClickLoginButtonInLoginPage() {
        loginPage.clickLoginPageLoginButton();
    }

    @Then("Verify customer login successfully")
    public void verifyCustomerLoginSuccessfully() {
        loginPage.clickHomePageMyAccountMenu();
        String acPageTitle = loginPage.getMyAccountPageHeaderTitle();
        String exPageTitle = "My account - Customer info test";
        Assert.assertEquals(acPageTitle, exPageTitle);
    }
}

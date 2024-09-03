package steps;

import com.codeborne.selenide.Condition;
import common.PageFactory;
import common.PageManager;
import driver.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.interactions.Actions;
import test.BaseTest;

import java.util.List;
import java.util.Map;

public class WebTablesSteps extends BaseTest {

    PageManager pageManager=new PageManager();

    @Before
    public void before(){
        PageFactory.buildWebTables();
    }

    @Given("Click addbtn button")
    public void addButtonClick(){
        new Actions(DriverFactory.currentDriver()).scrollByAmount(0, 200).perform();
        pageManager.webTables.addbtn.click();
    }

   @And("Fill in the form and submit")
   public void fillTheFormAndSubmit(DataTable dataTable) {
       String firstName = null;
       String lastName = null;
       String email = null;
       String age = null;
       String salary = null;
       String department = null;
       List<Map<String, String>> data=dataTable.asMaps(String.class, String.class);
       for(Map<String, String>form : data){
          firstName = form.get(firstName);
          lastName = form.get(lastName);
          email = form.get(email);
          age = form.get(age);
          salary = form.get(salary);
          department = form.get(department);
       }
       pageManager.webTables.fillTheForm("Ayse", "Yilmaz", "xxx@mail.co", "25","30000", "IT");
   }

    @When("Click edit button")
    public void clickEditButton() {
        pageManager.webTables.clickEditBtn();
    }

    @And("New row's first name edited")
    public void editNameInWebTablesPage(){
        pageManager.webTables.editName("Merve");
    }

    @Then("New row's first column should seen new name")
    public void editedRowInTheWebTablesPage() {
        pageManager.webTables.editedRow.should(Condition.visible);
        Assertions.assertThat(pageManager.webTables.editedRow.getText()).isEqualTo("Merve");
    }
}

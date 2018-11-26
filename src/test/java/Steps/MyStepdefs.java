package Steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

public class MyStepdefs {


    @Given("^i sout sth$")
    public void iSoutSth() throws Throwable {
        System.out.println("alo alo11");
        System.out.println("alo alo12");
    }

    @When("^i hsout sth$")
    public void iHsoutSth() throws Throwable {
        System.out.println("alo alo11");
        System.out.println("alo alo12");
    }

    @Then("^i sjhout sth$")
    public void iSjhoutSth() throws Throwable {
        System.out.println("alo alo11");
        System.out.println("alo alo12");
    }

    @Given("^i sout(\\d+) sth(\\d+)$")
    public void iSoutSth(int arg0, int arg1) throws Throwable {
        System.out.println("alo alo12");
        System.out.println("alo alo2");
    }

    @When("^i hsout$")
    public void iHsout(DataTable table) throws Throwable {

        List<List<String>> data = table.raw();
        System.out.println(data.get(0).get(0));
        System.out.println(data.get(0).get(1));
        System.out.println(data.get(1).get(0));
        System.out.println(data.get(1).get(1));
    }


    @Then("^i sjhout sth(\\d+)$")
    public void iSjhoutSth(int arg0) throws Throwable {
        System.out.println("alo alo12");
        System.out.println("alo alo2");
    }

}

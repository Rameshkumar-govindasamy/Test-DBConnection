package org.test.dbconnection.steps;
 
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.test.dbconnection.*;
import java.sql.*;

public class DBConnection_StepDef {

	DBConnection dbc = new DBConnection();

@Given("^a database (.*) with stored procedures$")
public void a_database_with_stored_procedures(String dbName) {
	dbc.getConnection(dbName);
}

@When("^making a request against a stored procedure (.*) with (.*)$")
public void making_a_request_against_a_stored_procedure(String spName,String params) {
	dbc.runStoredProcedure(spName, params);
	
}

@Then("^I will get data back using (.*)$")
public void i_will_get_data_back(String params) throws SQLException {
	dbc.printResult(params);
	
	}
	
}

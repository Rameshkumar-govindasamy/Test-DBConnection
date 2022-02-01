package org.test.dbconnection.steps;
 
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.test.dbconnection.*;
import java.sql.*;

public class DBConnection_StepDef {

	DBConnection dbc = new DBConnection();

@Given("a database with stored procedures")
public void a_database_with_stored_procedures() {
    // Write code here that turns the phrase above into concrete actions
//    throw new io.cucumber.java.PendingException();
	System.out.println("a database with stored procedures");
	dbc.getConnection();
}

@When("making a request against a stored procedure")
public void making_a_request_against_a_stored_procedure() {
    // Write code here that turns the phrase above into concrete actions
//    throw new io.cucumber.java.PendingException();
	dbc.getCustomerLevel();
	
}

@Then("I will get data back.")
public void i_will_get_data_back() {
    // Write code here that turns the phrase above into concrete actions
//    throw new io.cucumber.java.PendingException();
	try {
		ResultSet rs1 = dbc.getResult();
		while(rs1.next()) {
			System.out.println(rs1.getInt("level"));
		}
	}
	catch(Exception e) {
		
	}
}
	
}

#Author: rameshkumar.govindasamy@gmail.com
@DB-Test
Feature: Feature to test Sample DB Connection

  Scenario Outline: Sample DB Connection
    Given a database <DBNAME> with stored procedures
    When making a request against a stored procedure <SPNAME> with <PARAMS>
    Then I will get data back using <PARAMS>

    Examples: 
      | DBNAME     | SPNAME                | PARAMS         |
      | db_tuscany | GetCustomerLevel      | 141,@level     |
      | db_tuscany | GetOrderCountByStatus | shipped,@total |
      | db_tuscany | GetOfficeByCountry    | USA            |

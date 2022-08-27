Feature: Create project
  Scenario: Create project
    Given Create project with valid values
    And Check created project with valid values
    And Delete created project
    And Check that deleted project is not avaliable
    And Create project with empty title
    And Create project with empty code

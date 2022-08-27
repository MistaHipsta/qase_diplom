Feature: Create project
  @smoke
  Scenario: Create project scenario
    Given open project page

    And click on create button

    And set next value on Project name field:
      | Project name | Test project name |
    And set next value on Project code field:
      | Project code | Test |
    And set next value on Description field:
      | Description | Some test description |
    And set public project access type
    Then click on Create project button

    And go to project page

    And check project name equal to created project

    When go to project page

    And click dropdown menu on created project
    And click delete button
    And click delete project button
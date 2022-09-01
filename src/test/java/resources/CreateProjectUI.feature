Feature: Create project
  @smoke
  Scenario: CRUD project scenario
    Given open login page and log in with next valid params:
      | username | suvorov.evgenii2727@gmail.com |
      | password | .#mYZV.v4*iV6zH               |

    And click on create project button and set next value on Project create field:
      | Project name        | Test project name     |
      | Project code        | Test                  |
      | Description         | Some test description |
      | Project access type | public                |

    And open project page

    And wait 4 second

    And check project name equal to created project

    When click dropdown menu on created project
    And click on settings button

    When click update settings button
    And check alert is not visible

    When click on input project name field and set next value:
      | Project name new | Change name |
      | Project code new | Change code |
      | Description new  | Description new|
    And wait 2 second
    And click update settings button
    ##And check expected alert message
    And open project page
    And check project name equal to created project
   ##When click delete button on project page
   ##And click delete project submit button
    When click dropdown menu on updated project
   And click delete button
   And click delete project submit button
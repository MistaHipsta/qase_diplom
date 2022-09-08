Feature: Negative
  Scenario: CRUD project negative scenario
    Given open login page and log in with next valid params:
      | username | suvorov.evgenii2727@gmail.com |
      | password | .#mYZV.v4*iV6zH               |
    And click on create project button and set next value on Project create field:
      | Project name        | Test project name     |
      | Project code        | Test code             |
      | Description         | Some test description |
      | Project access type | public                |
    And check error message code text

    When open project page

    And click on create project button and set next value on Project create field:
      | Project name        | Test project nameTest project nameTest project nameTest project nameTest project nameTest project nameTest project nameTest project nameTest project nameTest project nameTest project nameTest project nameTest project nameTest project nameTest project nameW |
      | Project code        | Test                                                                                                                                                                                                                                                             |
      | Description         | Some test description                                                                                                                                                                                                                                            |
      | Project access type | public                                                                                                                                                                                                                                                           |
    And check error message title text

    When open project page

    And click on create project button and set next value on Project create field:
      | Project name        | Test project name     |
      | Project code        | TestCodeToLarge       |
      | Description         | Some test description |
      | Project access type | public                |

    And wait 3 second
    And check code name
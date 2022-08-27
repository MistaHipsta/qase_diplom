Feature: Login test
  @smoke
  Scenario: Login

    Given open login page and log in with next valid params:
      | username | suvorov.evgenii2727@gmail.com |
      | password | .#mYZV.v4*iV6zH               |
    And logOut
    And wait 2 second

    Given open login page and log in with next not valid params:
      | username | suvorov@adf.com |
      | password | qqq             |


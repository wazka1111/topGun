Feature: Studio - check existing projects

  @project
  Scenario Outline: Get list of existing projects
    Given I save data "<Params>"
    When Authorized user navigate to main page
    Then Authorized user is able to see all existing projects
    And Authorized user has been logged out

    Examples:
      | Params                    |
      | project=Sartorius_Project |


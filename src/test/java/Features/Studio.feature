Feature: Studio - check existing projects

  @project
  Scenario Outline: Get list of existing projects
    Given I save data "<Params>"
    When Authorized user navigate to main page
    Then Authorized user is able to see all existing projects

    Examples:
      | Params                                                                            |
      | gid=1201464292043272,name=Selenium_training_project,resource_type=project, age=19 |


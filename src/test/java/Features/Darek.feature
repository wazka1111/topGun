Feature: Darek test feature

  Scenario Outline: Darek test scenario
    Given I have cos "<Params>"
    When Mam cos innego
    Then Robie asercje

    Examples:
      | Params                                                                            |
      | gid=1201464292043272,name=Selenium_training_project,resource_type=project, age=19 |
Feature: UserController feature
  Background: create a user
    Given user details

  Scenario: verify if user resources can be added
    When creating a user
    Then user must be created

    Scenario: verify the user can be added with no marks
      When creating a user
      Then user must be created


          Scenario: verify that user has to be updated
            When updating a user
            Then user is updated

            Scenario: verify the username can be updated
              When update user name
              Then display error name is required

              Scenario: verify that user address can be updated
                When update the user address
                Then display error address is required

                Scenario: verify that user marks can be updated
                  When update the user marks
                  Then display error marks is required

                Scenario: verify the user is deleted
                  When the user is deleted
                  Then user is deleted

                  Scenario: verify that the particular user in the list is displayed
                    When creating a user
                    Then the user with particular id is displayed

                    Scenario: verify invalid user is deleted
                      When the user is deleted
                      Then internal server error is displayed

                      Scenario:Verify if user multiple resources can be added
                        When creating a multiple users
                        Then  multiple users are created





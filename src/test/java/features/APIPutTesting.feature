Feature: Testing PUT method
  Scenario:Update post with ID 1 and validate success response
    Given User updates post with ID 1 using title "updated title", body "updated body", and userId 1
    Then The response status code should be 200
    And The response give id 1
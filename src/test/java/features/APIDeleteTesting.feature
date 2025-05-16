Feature: Testing DELETE method
Scenario: Delete post with id 1 and validate status code
  Given User Sends DELETE request to posts with ID 1
  Then The response status code should be 200

Feature:Testing Method GET
  Scenario: Verify if API returns 100 posts
    Given User sends GET request to posts
    Then response should contain 100 posts
    And Status code should be 200

   Scenario:Verify title from posts with ID 1

     Given User sends GET request to posts with ID1
     Then the title of the post should be "sunt aut facere repellat provident occaecati excepturi optio reprehenderit"



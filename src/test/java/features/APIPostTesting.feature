Feature: Testing Method Post
  Scenario Outline: Create a new post anda validate the returned ID
    Given User creates a new post with the "<title>" ,"<body>" and ID 11
    Then The response status code should be 201
    And The response should contain a post with ID
    Examples:
    |title|body|
    |Hello|Bye |

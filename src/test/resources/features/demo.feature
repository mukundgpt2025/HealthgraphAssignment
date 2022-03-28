@test
Feature: Check Search Functionality and Description Tag

  Scenario Outline: Verify search functionality for the multiple locality and description tag for the property
    Given User visits nobroker web application selecting Buy option
    When User selects city and types sub location in search box <RowNumber>
    When User selects 2 sub locations
    When User checks BHK checkboxes and click search button
    When User scroll down and click 4th property
    Then User scroll down 4th property information and check description
    Examples:
      | RowNumber |
      | 1 |


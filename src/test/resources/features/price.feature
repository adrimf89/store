Feature: Store price API

  Scenario Outline: Request product price bad request when effective date is empty
    Given the API is running
    When I request the effective price for productId 1, brandId 1 and null date
    Then the response status should be 400
    Then the error response detail should match field "effectiveDate" is missing

  Scenario Outline: Request product price not found <name>
    Given the API is running
    When I request the effective price for productId <productId>, brandId <brandId> at <effectiveDate>
    Then the response status should be 404
    Then the error response detail should match product <productId>, brand <brandId> and date <effectiveDate>
    Examples:
      | name                              | productId | brandId | effectiveDate      |
      | when product doesn't exist        | 99999     | 1       | "2020-06-14T10:00" |
      | when brand doesn't exist          | 35455     | 99      | "2020-06-14T10:00" |
      | when effective date out of range  | 35455     | 1       | "2020-06-13T23:59" |

  Scenario Outline: Request product price is <price> when search criteria is <productId>, <brandId> at <effectiveDate>
    Given the API is running
    When I request the effective price for productId <productId>, brandId <brandId> at <effectiveDate>
    Then the response status should be 200
    Then the price in the response should be <price>
    Examples:
        | productId | brandId | effectiveDate      | price  |
        | 35455     | 1       | "2020-06-14T10:00" | 35.50  |
        | 35455     | 1       | "2020-06-14T16:00" | 25.45  |
        | 35455     | 1       | "2020-06-14T21:00" | 35.50  |
        | 35455     | 1       | "2020-06-15T10:00" | 30.50  |
        | 35455     | 1       | "2020-06-16T21:00" | 38.95  |
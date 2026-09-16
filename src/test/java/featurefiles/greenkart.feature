Feature: Greenkart site.

Scenario: To verify checkout journey of greenkart site.
Given user is on greenkart site
When user add items in basket
And do checkout journey
Then successful checkout screenshould be dispalyed and screenshot should be capture.


Narrative:
Testing jbehave and selenide to open google

Scenario: open google and search

Given Open url 'http://google.com/ncr'
When find 'selenide'
Then check result size is 10
Then check text 'selenide.org' is present
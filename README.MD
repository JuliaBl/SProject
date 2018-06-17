Test Project for UI tests

Test Suite includes tests for login tests and loan tests.

In order to run  with local webdriver - mvn clean test.
For remote webdriver - mvn clean test -Dis_remote=true -Dremote_url=http://localhost:4444/wd/hub

For reporting - Allure has been selected.
In order to run allure report - mvn allure:serve

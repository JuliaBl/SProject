Test Project for UI tests

Test Suite includes tests for login tests and loan tests.

In order to run  with local webdriver - mvn clean test.
For remote webdriver - mvn clean test -Dis_remote=true -Dremote_url=http://localhost:4444/wd/hub

In order to run TestSuite there is need to commit in pom.xml in plugin - maven-surefire-plugin

                    <dependency>
                        <groupId>org.junit.platform</groupId>
                        <artifactId>junit-platform-surefire-provider</artifactId>
                        <version>${junit.platform.version}</version>
                    </dependency>

 TestSuite is not supported by JUnit5 for this reason junit-platform-runner has been added.
 For running TestSuite from commandline - mvn clean test -Dtest=TestSuite

For reporting - Allure has been selected.
In order to run allure report - mvn allure:serve

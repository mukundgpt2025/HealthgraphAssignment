package propertyFinder.runner;

import cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@Cucumber.Options(format = {"pretty", "html:target/cucumber"},
        features = {"features"},
        glue = {"propertyFinder.stepDef"},
        monochrome = true,
        tags = {"@test"})

public class TestRunner {

}

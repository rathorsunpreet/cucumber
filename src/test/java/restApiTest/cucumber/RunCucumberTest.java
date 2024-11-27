package restApiTest.cucumber;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
//@SelectClasspathResource("restApiTest/cucumber")
@SelectClasspathResource("restApiTest")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "restApiTest")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, json:target/jsonReports/cucumber.json")
public class RunCucumberTest {
}

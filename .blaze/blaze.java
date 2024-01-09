import static com.fizzed.blaze.Systems.exec;

// Adding JUnit5Engines and plugin settings increases execution speed
// These two can be omitted and test execution would not be affected
public class blaze {
String mvnCommand;

	public blaze() {
		// Get OS here: Windows / Linux / Mac
		// Then setup mvnCommand to be either mvnw or mvnw.cmd
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("windows")) {
			mvnCommand = "mvnw.cmd";
		} else {
			mvnCommand = "bash mvnw";
		}
	}
	
	public void testAll() {
		exec(mvnCommand, "test").run();
	}
	
	public void clean() {
		exec(mvnCommand, "clean").run();
	}
	
	public void resources() {
		exec(mvnCommand, "test",
			 "-Dsurefire.includeJUnit5Engines=cucumber",
			 "-Dcucumber.plugin=pretty",
			 "-Dcucumber.features=src/test/resources/restApiTest/cucumber/resources.feature").run();
	}
	
	public void users() {
		exec(mvnCommand, "test",
			 "-Dsurefire.includeJUnit5Engines=cucumber",
			 "-Dcucumber.plugin=pretty",
			 "-Dcucumber.features=src/test/resources/restApiTest/cucumber/users.feature").run();
	}
	
	public void register() {
		exec(mvnCommand, "test",
			 "-Dsurefire.includeJUnit5Engines=cucumber",
			 "-Dcucumber.plugin=pretty",
			 "-Dcucumber.features=src/test/resources/restApiTest/cucumber/register.feature").run();
	}
	
	public void login() {
		exec(mvnCommand, "test",
			 "-Dsurefire.includeJUnit5Engines=cucumber",
			 "-Dcucumber.plugin=pretty",
			 "-Dcucumber.features=src/test/resources/restApiTest/cucumber/login.feature").run();
	}
	
	public void loginHTML() {
		exec(mvnCommand, "test",
			 "-Dsurefire.includeJUnit5Engines=cucumber",
			 "-Dcucumber.plugin=pretty, html:target/cucumber-reports/Login.html",
			 "-Dcucumber.features=src/test/resources/restApiTest/cucumber/login.feature").run();
	}
	
	public void resourcesHTML() {
		exec(mvnCommand, "test",
			 "-Dsurefire.includeJUnit5Engines=cucumber",
			 "-Dcucumber.plugin=pretty, html:target/cucumber-reports/Resources.html",
			 "-Dcucumber.features=src/test/resources/restApiTest/cucumber/resources.feature").run();
	}
	
	public void usersHTML() {
		exec(mvnCommand, "test",
			 "-Dsurefire.includeJUnit5Engines=cucumber",
			 "-Dcucumber.plugin=pretty, html:target/cucumber-reports/Users.html",
			 "-Dcucumber.features=src/test/resources/restApiTest/cucumber/users.feature").run();
	}
	
	public void registerHTML() {
		exec(mvnCommand, "test",
			 "-Dsurefire.includeJUnit5Engines=cucumber",
			 "-Dcucumber.plugin=pretty, html:target/cucumber-reports/Register.html",
			 "-Dcucumber.features=src/test/resources/restApiTest/cucumber/register.feature").run();
	}
	
	public void testAllHTML() {
		exec(mvnCommand, "verify").run();
	}
}
package selenium

/**
 * Created by JM on 05/11/2015.
 */


import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver

class TestBackendIT extends GroovyTestCase{

    WebDriver webDriver = new FirefoxDriver();

    protected void tearDown() {
        webDriver.close();
    }

    void testSayingPage(){

        webDriver.get("localhost:18080/saying")

        def source = webDriver.getPageSource()

        assertTrue(source.contains("Hello"))

    }

    void testSayingPageWithParam(){
        def name = "Bob"

        webDriver.get("localhost:18080/saying?name=" + name)

        def source = webDriver.getPageSource()

        assertTrue(source.contains("Bob"))
    }
}

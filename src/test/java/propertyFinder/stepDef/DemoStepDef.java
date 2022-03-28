package propertyFinder.stepDef;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import propertyFinder.ReadExcelData;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DemoStepDef {

    public static WebDriver driver = null;
    public static int rowNumber;
    public List<String> propertyDetails;
    static JavascriptExecutor js;

    static {
        String path = System.getProperty("user.dir") + "\\src\\test\\resources\\driver\\chromedriver.exe";
        System.out.println(path);
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
    }

    @After
    public void cleanUp() {
        driver.close();
    }

    @Given("^User visits nobroker web application selecting Buy option$")
    public void visit_web_application_and_select_buy_option() throws InterruptedException {
        driver.get("https://www.nobroker.in/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[@class='nb__17yFj'][1]")).click();
        Thread.sleep(500);
    }

    @When("^User selects city and types sub location in search box ([^\"]*)$")
    public void select_city_and_type_sub_location(String rowNum) throws IOException, InterruptedException, AWTException {
        rowNumber = Integer.parseInt(rowNum);
        propertyDetails = ReadExcelData.readFile(rowNumber);
        Thread.sleep(500);
        driver.findElement(By.id("searchCity")).click();
        WebElement parentCityDropDownElement = driver.findElement(By.xpath("//div[@class='css-kj6f9i-menu nb-select__menu'][1]"));
        List<WebElement> listOfChildElement = parentCityDropDownElement.findElements(By.xpath("./child::*"));
        WebElement cityDropDownElement = listOfChildElement.get(0);
        listOfChildElement = cityDropDownElement.findElements(By.xpath("./child::*"));
        for (WebElement cityNameWebElement : listOfChildElement) {
            String text = cityNameWebElement.getText();
            if (text.contains(propertyDetails.get(1))) {
                System.out.println("Selected:" + cityNameWebElement.getText());
                cityNameWebElement.click();
                break;
            }
        }
    }

    @When("User selects 2 sub locations")
    public void select_sub_locations() throws AWTException, InterruptedException {
        Robot robot = new Robot();
        driver.findElement(By.id("listPageSearchLocality")).click();
        driver.findElement(By.id("listPageSearchLocality")).sendKeys(propertyDetails.get(3));
        Thread.sleep(2000);
        if (driver.findElement(By.className("autocomplete-dropdown-container")).isEnabled()) {
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            Thread.sleep(500);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        }
        Thread.sleep(2000);
        driver.findElement(By.id("listPageSearchLocality")).sendKeys(propertyDetails.get(4));
        Thread.sleep(2000);
        if (driver.findElement(By.className("autocomplete-dropdown-container")).isEnabled()) {
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            Thread.sleep(500);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        }
        Thread.sleep(2000);

    }

    @When("User checks BHK checkboxes and click search button")
    public void check_checkboxes_and_click_search_button() throws InterruptedException, AWTException {
        Robot robot = new Robot();
        List<String> BHK = new ArrayList<>(Arrays.asList(propertyDetails.get(2).split(",")));

        driver.findElement(By.xpath("//div[@class='nb-select__placeholder'][1]")).click();
        Thread.sleep(500);
        WebElement parentBHKDropDownElement = driver.findElement(By.xpath("//div[@class='css-kj6f9i-menu nb-select__menu'][1]"));
        List<WebElement> listOfChildElement = parentBHKDropDownElement.findElements(By.xpath("./child::*"));
        WebElement BHKDropDownElement = listOfChildElement.get(0);
        listOfChildElement = BHKDropDownElement.findElements(By.xpath("./child::*"));
        for (WebElement element : listOfChildElement) {
            boolean flag = false;
            for (String s : BHK) {
                if (element.getText().equalsIgnoreCase(s)) {
                    robot.keyPress(KeyEvent.VK_SPACE);
                    robot.keyRelease(KeyEvent.VK_SPACE);
                    BHK.remove(s);
                    robot.keyPress(KeyEvent.VK_DOWN);
                    robot.keyRelease(KeyEvent.VK_DOWN);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                robot.keyPress(KeyEvent.VK_DOWN);
                robot.keyRelease(KeyEvent.VK_DOWN);
            }
        }
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("button[class='prop-search-button btn btn-primary btn-lg']")).click();
        Thread.sleep(7000);
    }

    @When("User scroll down and click 4th property")
    public void scroll_down_and_click_fourth_property() throws InterruptedException, AWTException {
        Robot robot = new Robot();
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

        int x = (int) size.getWidth();
        int y = (int) size.getHeight();
        robot.mouseMove(x/2, y/2);
        Thread.sleep(1000);

        js.executeScript("window.scrollBy(0,1500)");
        Thread.sleep(2000);
        robot.mouseMove((int)(x*0.6), (int)(y*0.67));

        Thread.sleep(2000);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(5000);
    }

    @Then("User scroll down 4th property information and check description")
    public void scroll_down_and_check_description() throws AWTException, InterruptedException {
        Robot robot = new Robot();
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) size.getWidth();
        int y = (int) size.getHeight();
        robot.mouseMove(x/2, y/2);
        Thread.sleep(1000);
        for(int i=1;i<=4;i++){
            robot.mouseWheel(3);
            Thread.sleep(100);
        }
        Thread.sleep(5000);
        WebElement webElement = driver.findElement(By.id("description"));
        Thread.sleep(1000);
        if(webElement.getText() == null || Objects.equals(webElement.getText(), "")){
            System.out.println("Description does not exist, last test case failed");
            Assert.fail();
        }
        else{
            System.out.println("Description exists, all test cases passed successfully");
        }
    }
}

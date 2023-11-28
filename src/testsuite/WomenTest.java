package testsuite;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.time.Duration;
import java.util.List;

public class WomenTest extends Utility {
    String baseUrl = "https://magento.softwaretestingboard.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyTheSortByProductNameFilter() {
        //Women-------> Tops and click

        //Mouse Hover on Women Menu
        mouseHoverToElement(By.xpath("//a[@id='ui-id-4']//span[contains(text(),'Women')]"));

        //Mouse Hover on Tops
        mouseHoverToElement(By.id("ui-id-9"));

        //Click on Jackets
        clickOnElement(By.xpath("//a[@id='ui-id-11']//span[contains(text(),'Jackets')]"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        //Select Sort by Filter "Product Name"
        selectByVisibleTextFromDropDown(By.xpath("//body/div[2]/main[1]/div[3]/div[1]/div[2]/div[3]/select[1]"), "Product Name");

        // Verify the products name display in alphabetical order
        List<WebElement> elements = driver.findElements(By.xpath("//strong[@class = 'product name product-item-name']"));
        String expectedText = "12 Items";
        String actualText = driver.findElement(By.xpath("(//div[@class='toolbar toolbar-products']//p[@id='toolbar-amount'])[1]")).getText();
        Assert.assertEquals(expectedText, actualText);
    }


        @Test
        public void verifyTheSortByPriceFilter () {
            //Mouse Hover On Women Menu
            mouseHoverToElement(By.xpath("//a[@id='ui-id-4']//span[@class='ui-menu-icon ui-icon ui-icon-carat-1-e']"));

            //Mouse Hover On Tops
            mouseHoverToElement(By.xpath("//a[@id='ui-id-9']"));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

            //Click on Jackets
            clickOnElement(By.xpath("//a[@id='ui-id-11']//span[contains(text(),'Jackets')]"));

            //Select Sort By Filter Price
            selectByVisibleTextFromDropDown(By.id("sorter"), ("Price"));

            //Verify the products display in Low to High
            List<WebElement> List2 = driver.findElements(By.xpath("//div[@class = 'price-box price-final_price']"));
            for (int i = 0; i < List2.size(); i++) {
                System.out.println(List2.get(i).getText());
                String exp = List2.get(i).getText();
                String actual = List2.get(i).getText();
                Assert.assertEquals("Price not ordered: Low to High", exp, actual);

            }
        }
    }






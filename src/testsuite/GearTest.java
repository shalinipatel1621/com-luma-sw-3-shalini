package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class GearTest extends Utility {
    String baseUrl = "https://magento.softwaretestingboard.com/";

    @Before
    public void openBrowser() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldAddProductSuccessFullyToShoppingCart() throws InterruptedException {

        //Mouse Hover on Gear Menu
        mouseHoverToElement(By.xpath("//span[normalize-space()='Gear']"));

        //Click On Bags
        clickOnElement(By.xpath("//span[contains(text(),'Bags')]"));
        Thread.sleep(3000);


        //Click on Product Name 'Overnight Duffle'
        mouseHoverToElementAndClick(By.xpath("//a[@class='product-item-link'][normalize-space()='Overnight Duffle']"));

        //Verify the text 'Overnight Duffle'
        verifyTextFromElement(By.xpath("//span[@class='base']"), "Overnight Duffle");

        //Change QTY 3
        sendTextToElement(By.xpath("//input[@id='qty']"), Keys.DELETE + "3");

        //Click on 'Add To Cart' Option
        clickOnElement(By.xpath("//span[normalize-space()='Add to Cart']"));

        //Verify the text
        verifyTextFromElement(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']"), "You added Overnight Duffle to your shopping cart.");

        //Click on 'shopping cart' Link into message
        clickOnElement(By.xpath("//a[normalize-space()='shopping cart']"));


        // Verify the Qty is ‘3’
        Thread.sleep(3000);
        List<WebElement> qty3 = driver.findElements(By.xpath("//div[@class='control qty']"));
        for (WebElement c : qty3) {
            System.out.println(c.getText());


            //Verify the product name ‘Overnight Duffle’
            verifyTextFromElement(By.xpath("//td[@class='col item']//a[normalize-space()='Overnight Duffle']"), "Overnight Duffle");

            // Verify the product price ‘$135.00’
            String expectedPrice = "$135.00";
            String actualPrice = driver.findElement(By.xpath("//span[@class='cart-price']//span[contains(text(),'$135.00')]")).getText();
            Assert.assertEquals("Price do not match", expectedPrice, actualPrice);

            // Change Qty to ‘5’
            Thread.sleep(3000);
            driver.findElement(By.xpath("//input[@value='3']")).clear();
            sendTextToElement(By.xpath("//input[@value='3']"), "5");

            // Click on ‘Update Shopping Cart’ button
            Thread.sleep(3000);
            clickOnElement(By.xpath("//span[contains(text(),'Update Shopping Cart')]"));

            // Verify the product price ‘$225.00’;
            Thread.sleep(3000);
            clickOnElement(By.xpath("//tbody/tr[1]/td[4]/span[1]/span[1]/span[1]"));
        }
    }

    @After
    public void tearDown() {
        closeBrowser();

    }

}
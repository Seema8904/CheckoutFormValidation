
package com.checkout;

import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutFormTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();

        String filePath = Paths.get(
                "src", "test", "resources", "checkout.html"
        ).toAbsolutePath().toUri().toString();

        driver.get(filePath);
    }

    private boolean isFormValid() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        return (Boolean) js.executeScript(
                "return document.getElementById('checkoutForm').checkValidity();"
        );
    }

    private void fillValidDetails() {
        driver.findElement(By.id("name"))
                .sendKeys("Test User");

        driver.findElement(By.id("email"))
                .sendKeys("test@example.com");

        driver.findElement(By.id("phone"))
                .sendKeys("9876543210");

        driver.findElement(By.id("address"))
                .sendKeys("123 Test Street");

        driver.findElement(By.id("postal"))
                .sendKeys("583101");
    }

    // Test Case 1: Empty form validation
    @Test
    public void testEmptyForm() {
        Assert.assertFalse(
                isFormValid(),
                "Empty form should not be valid"
        );
    }

    // Test Case 2: Invalid email validation
    @Test
    public void testInvalidEmail() {
        fillValidDetails();

        WebElement email = driver.findElement(By.id("email"));
        email.clear();
        email.sendKeys("seema@@gmail.com");

        Assert.assertFalse(
                isFormValid(),
                "Invalid email should be rejected"
        );
    }

    // Test Case 3: Invalid phone validation
    @Test
    public void testInvalidPhone() {
        fillValidDetails();

        WebElement phone = driver.findElement(By.id("phone"));
        phone.clear();
        phone.sendKeys("12345");

        Assert.assertFalse(
                isFormValid(),
                "Invalid phone should be rejected"
        );
    }

    // Test Case 4: Invalid postal code validation
    @Test
    public void testInvalidPostalCode() {
        fillValidDetails();

        WebElement postal = driver.findElement(By.id("postal"));
        postal.clear();
        postal.sendKeys("123");

        Assert.assertFalse(
                isFormValid(),
                "Invalid postal code should be rejected"
        );
    }

    // Test Case 5: Valid checkout
    @Test
    public void testValidCheckout() {
        fillValidDetails();

        Assert.assertTrue(
                isFormValid(),
                "Valid details should pass validation"
        );

        driver.findElement(
                By.cssSelector("button[type='submit']")
        ).click();

        String actualMessage = driver.findElement(
                By.id("result")
        ).getText();

        Assert.assertEquals(
                actualMessage,
                "Order placed successfully!"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
       
    }
}

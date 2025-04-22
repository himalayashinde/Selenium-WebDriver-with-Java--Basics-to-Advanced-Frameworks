package test;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class ForgotPasswordTest extends BaseTest {

    @Test(priority = 1)
    public void testForgotPasswordFlow() {
        test = extent.createTest("Forgot Password Test", "Test the forgot password functionality");
        
        try {
            // Navigate to the URL
            test.log(Status.INFO, "Navigating to the login page");
            driver.get("https://rahulshettyacademy.com/locatorspractice/");
            
            // Initialize WebDriverWait
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            
            // Click on the "Forgot your password?" link
            test.log(Status.INFO, "Clicking on 'Forgot your password?' link");
            WebElement forgotPasswordLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.linkText("Forgot your password?"))
            );
            forgotPasswordLink.click();
            
            // Wait for the form to be visible after clicking the link
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form")));
            
            // Fill in the Name field
            test.log(Status.INFO, "Filling in the reset password form");
            WebElement nameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Name']"))
            );
            nameField.sendKeys("John Doe");
            
            // Fill in the Email field
            WebElement emailField = driver.findElement(By.cssSelector("input[placeholder='Email']"));
            emailField.sendKeys("john.doe@example.com");
            
            // Fill in the Phone Number field
            WebElement phoneField = driver.findElement(By.cssSelector("input[placeholder='Phone Number']"));
            phoneField.sendKeys("1234567890");
            
            // Click on the Reset Login button
            test.log(Status.INFO, "Clicking on Reset Login button");
            WebElement resetButton = driver.findElement(By.cssSelector(".reset-pwd-btn"));
            resetButton.click();
            
            // Wait for the success message to appear
            WebElement successMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".infoMsg"))
            );
            
            // Extract the temporary password from the message
            String messageText = successMessage.getText();
            test.log(Status.INFO, "Received message: " + messageText);
            
            String tempPassword = extractPassword(messageText);
            test.log(Status.INFO, "Extracted password: " + tempPassword);
            
            Assert.assertFalse(tempPassword.isEmpty(), "Password should not be empty");
            
            // Store username for login
            String username = "John Doe";
            
            // Click on Go to Login button
            test.log(Status.INFO, "Clicking on Go to Login button");
            WebElement goToLoginButton = driver.findElement(By.cssSelector(".go-to-login-btn"));
            goToLoginButton.click();
            
            // Perform login with the extracted password
            test.log(Status.INFO, "Performing login with extracted password");
            performLogin(username, tempPassword);
            
            // Validate the welcome message
            test.log(Status.INFO, "Validating welcome message");
            validateWelcomeMessage();
            
            test.log(Status.PASS, "Test completed successfully");
        } catch (Exception e) {
            test.log(Status.FAIL, "Test failed with exception: " + e.getMessage());
            test.addScreenCaptureFromBase64String(captureScreenshot(), "Failure Screenshot");
            Assert.fail("Test failed: " + e.getMessage());
        }
    }
    
    @Test (priority = 2)
    public void testDirectLogin() {
        test = extent.createTest("Direct Login Test", "Test direct login with known credentials");
        
        try {
            // Navigate to the URL
            test.log(Status.INFO, "Navigating to the login page");
            driver.get("https://rahulshettyacademy.com/locatorspractice/");
            
            // Perform login with known credentials
            test.log(Status.INFO, "Performing login with known credentials");
            performLogin("rahul", "rahulshettyacademy");
            
            // Validate the welcome message
            test.log(Status.INFO, "Validating welcome message");
            validateWelcomeMessage();
            
            test.log(Status.PASS, "Test completed successfully");
        } catch (Exception e) {
            test.log(Status.FAIL, "Test failed with exception: " + e.getMessage());
            test.addScreenCaptureFromBase64String(captureScreenshot(), "Failure Screenshot");
            Assert.fail("Test failed: " + e.getMessage());
        }
    }
    
    private void performLogin(String username, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // Wait for the login form to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
        
        // Fill in the username
        WebElement usernameField = driver.findElement(By.id("inputUsername"));
        usernameField.sendKeys(username);
        
        // Fill in the password
        WebElement passwordField = driver.findElement(By.name("inputPassword"));
        passwordField.sendKeys(password);
        
        // Check "Remember my username" checkbox
        WebElement rememberUsernameCheckbox = driver.findElement(By.id("chkboxOne"));
        rememberUsernameCheckbox.click();
        
        // Check "I agree to the terms and privacy policy" checkbox
        WebElement agreeTermsCheckbox = driver.findElement(By.id("chkboxTwo"));
        agreeTermsCheckbox.click();
        
        // Click on Sign In button
        WebElement signInButton = driver.findElement(By.className("signInBtn"));
        signInButton.click();
    }
    
    private void validateWelcomeMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // Wait for the welcome message to appear after successful login
        WebElement welcomeMessage = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.tagName("p"))
        );
        
        // Validate the welcome message
        String welcomeText = welcomeMessage.getText();
        test.log(Status.INFO, "Welcome message: " + welcomeText);
        
        Assert.assertTrue(welcomeText.contains("You are successfully logged in"), 
                         "Welcome message should indicate successful login");
    }
    
    // Helper method to extract password from the message
    private String extractPassword(String message) {
        // Extract text between single quotes
        int startIndex = message.indexOf("'") + 1;
        int endIndex = message.lastIndexOf("'");
        
        if (startIndex > 0 && endIndex > startIndex) {
            return message.substring(startIndex, endIndex);
        }
        
        return ""; // Return empty string if password not found
    }
    
    // Helper method to capture screenshot
    protected String captureScreenshot() {
        // This is a placeholder - in a real implementation, you would capture a screenshot
        // and return it as a Base64 string
        return "";
    }
}

//java.lang.AssertionError: Test failed: element click intercepted: Element <input type="checkbox" id="chkboxOne" name="chkboxOne" value="rmbrUsername"> is not clickable


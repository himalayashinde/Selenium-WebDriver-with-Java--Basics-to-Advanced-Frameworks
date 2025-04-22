package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordAutomation {

    public static void main(String[] args) {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/resources/chromedriver.exe");

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to the URL
            driver.get("https://rahulshettyacademy.com/locatorspractice/");

            // Maximize the browser window
            driver.manage().window().maximize();

            // Initialize WebDriverWait
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Wait for the "Forgot your password?" link to be clickable
            WebElement forgotPasswordLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.linkText("Forgot your password?"))
            );

            // Click on the "Forgot your password?" link
            forgotPasswordLink.click();
            
            // Wait for the form to be visible after clicking the link
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form")));
            
            // Fill in the Name field
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
            WebElement resetButton = driver.findElement(By.cssSelector(".reset-pwd-btn"));
            resetButton.click();
            
            // Wait for the success message to appear
            WebElement successMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".infoMsg"))
            );
            
            // Print the success message
            String messageText = successMessage.getText();
            System.out.println("Success message: " + messageText);
            
            // Extract the temporary password from the message
            // Message format: "Please use temporary password 'rahulshettyacademy' to Login."
            String tempPassword = extractPassword(messageText);
            System.out.println("Extracted password: " + tempPassword);
            
            // Store username for login
            String username = "John Doe";
            
            // Click on Go to Login button
            WebElement goToLoginButton = driver.findElement(By.cssSelector(".go-to-login-btn"));
            goToLoginButton.click();
            
            // Wait for the login form to be visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
            
            // Fill in the username
            WebElement usernameField = driver.findElement(By.id("inputUsername"));
            usernameField.sendKeys(username);
            
            // Fill in the password
            WebElement passwordField = driver.findElement(By.name("inputPassword"));
            passwordField.sendKeys(tempPassword);
            
            // Check "Remember my username" checkbox
            WebElement rememberUsernameCheckbox = driver.findElement(By.id("chkboxOne"));
            rememberUsernameCheckbox.click();
            
            // Check "I agree to the terms and privacy policy" checkbox
            WebElement agreeTermsCheckbox = driver.findElement(By.id("chkboxTwo"));
            agreeTermsCheckbox.click();
            
            // Click on Sign In button
            WebElement signInButton = driver.findElement(By.className("signInBtn"));
            signInButton.click();
            
            // Wait for the welcome message to appear after successful login
            WebElement welcomeMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.tagName("p"))
            );
            
            // Validate the welcome message
            String welcomeText = welcomeMessage.getText();
            System.out.println("Welcome message: " + welcomeText);
            
            if (welcomeText.contains("You are successfully logged in")) {
                System.out.println("Login successful! Validation passed.");
            } else {
                System.out.println("Login validation failed!");
            }

        } finally {
            // Wait a bit to see the results before closing
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            // Close the browser
            driver.quit();
        }
    }
    
    // Helper method to extract password from the message
    private static String extractPassword(String message) {
        // Extract text between single quotes
        int startIndex = message.indexOf("'") + 1;
        int endIndex = message.lastIndexOf("'");
        
        if (startIndex > 0 && endIndex > startIndex) {
            return message.substring(startIndex, endIndex);
        }
        
        return ""; // Return empty string if password not found
    }
}
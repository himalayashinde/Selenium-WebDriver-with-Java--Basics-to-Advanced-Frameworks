# Selenium WebDriver with Java - Automation Framework
This repository contains a comprehensive automation framework built with Selenium WebDriver and Java, demonstrating best practices for web application testing.

# 🚀 Features
Modular Framework Design: Well-structured code organization for maintainability
Page Object Model: Implementation of POM design pattern
TestNG Integration: For test execution, assertions, and reporting
Extent Reports: Detailed HTML test reports with screenshots
Forgot Password Automation: Complete end-to-end test flow
Wait Strategies: Proper implementation of explicit and implicit waits
Exception Handling: Robust error handling with try-catch blocks
📋 Prerequisites
Java JDK 8 or higher
Maven
Chrome browser
ChromeDriver (compatible with your Chrome version)
🛠️ Setup Instructions
# 1. Clone the repository

git clone https://github.com/himalayashinde/Selenium-WebDriver-with-Java--Basics-to-Advanced-Frameworks.git
cd Selenium-WebDriver-with-Java--Basics-to-Advanced-Frameworks


# 2. Install dependencies

mvn clean install

# 3. ChromeDriver Setup

Download the appropriate ChromeDriver for your Chrome version
Place it in the resources folder
The framework will automatically detect it using System.getProperty("user.dir") + "/resources/chromedriver.exe"
🧪 Running Tests
Via Maven

mvn clean test -DsuiteXmlFile=testng.xml

# Via TestNG in IDE

Right-click on testng.xml
Select "Run As" > "TestNG Suite"
📊 Test Reports
After test execution, find the detailed HTML reports at:
test-output/ExtentReport.html

# 📁 Project Structure

├── src
│   ├── main
│   │   └── java
│   └── test
│       └── java
│           └── test
│               ├── BaseTest.java
│               └── ForgotPasswordTest.java
├── resources
│   └── chromedriver.exe
├── test-output
├── testng.xml
└── pom.xml

# 🔍 Sample Test Case: Forgot Password Flow
The framework includes a complete test case that demonstrates:

Navigating to the login page
Clicking "Forgot your password?" link
Filling out the password reset form
Extracting the temporary password from the response
Logging in with the extracted credentials
Validating successful login with assertions
🛠️ Troubleshooting
ChromeDriver/Chrome Version Mismatch
If you encounter CDP-related warnings:

WARNING: Unable to find CDP implementation matching X

Add the appropriate DevTools dependency to your pom.xml:

<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-devtools-vXX</artifactId>
    <version>4.X.X</version>
</dependency>


# Connection Reset Issues

1. Ensure your ChromeDriver version matches your Chrome browser version
2. Try running with additional ChromeOptions:
ChromeOptions options = new ChromeOptions();
options.addArguments("--remote-allow-origins=*");
WebDriver driver = new ChromeDriver(options);

📚 Key Concepts Demonstrated
Explicit Waits: Using WebDriverWait for dynamic elements
Locator Strategies: CSS, XPath, ID, and other selector methods
Test Assertions: Validating expected outcomes
String Manipulation: Extracting data from text responses
Exception Handling: Graceful error management
Test Reporting: Detailed execution logs and results
👨‍💻 Author
Himalaya Shinde

📄 License
This project is licensed under the MIT License - see the LICENSE file for details.

Feel free to contribute to this project by submitting pull requests or creating issues for any bugs or feature requests!
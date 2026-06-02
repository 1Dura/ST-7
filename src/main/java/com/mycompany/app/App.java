package com.mycompany.app;

import java.nio.file.Paths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {
    private static final String PASSWORD_PAGE =
            "https://www.calculator.net/password-generator.html";

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver",
                Paths.get("chromedriver.exe").toAbsolutePath().toString());

        WebDriver driver = new ChromeDriver();
        try {
            System.out.println("Generated password: " + readGeneratedPassword(driver));
            System.out.println("Client IPv4: " + Task2.getIpAddress(driver));
            Task3.getWeatherForecast(driver);
        } catch (Exception ex) {
            System.out.println("Error");
            System.out.println(ex);
        } finally {
            driver.quit();
        }
    }

    static String readGeneratedPassword(WebDriver driver) {
        driver.get(PASSWORD_PAGE);
        WebElement password = driver.findElement(By.cssSelector(".verybigtext b"));
        return password.getText();
    }
}

package Utilities;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Random;

public class Utils {
    Faker faker;

    // Method to send keys using Actions class
    public static void sendkeysWithActionsClass(WebElement element, String text) {
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).click().sendKeys(text).build().perform();
    }

    public static String product() {
        Faker faker = new Faker();
        String productName = faker.commerce().productName();
        return productName;
    }

    public static String name() {
        Faker faker = new Faker();
        String name = faker.name().firstName();
        return name;
    }

    public static String email() {
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        return email;
    }

    public static String randomLongtxt(int length) {
        // Create a Random object
        Random random = new Random();
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        // Create a StringBuilder to store the random string
        StringBuilder sb = new StringBuilder();

        // Generate the random string of specified length
        for (int i = 0; i < length; i++) {
            // Get a random index to select a character from the pool
            int randomIndex = random.nextInt(CHARACTERS.length());

            // Append the randomly selected character to the string
            sb.append(CHARACTERS.charAt(randomIndex));
        }

        // Convert StringBuilder to String and return
        return sb.toString();
    }
}

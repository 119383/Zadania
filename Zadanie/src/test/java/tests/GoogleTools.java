/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author 119383
 */
public class GoogleTools {
    
    private static WebDriver driver;
    /**
     * Waits till results appear. Specifically div with "Około <number> wyników"
     */
    public static void waitForResultsPage() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("result-stats")));
    }

    public static void initializeBrowser(){
        WebDriverManager.firefoxdriver().setup();
        //System.setProperty("webdriver.gecko.driver", "C:\\SeleniumDrivers\\geckodriver32.exe");
        driver = new FirefoxDriver();        
    }
    
    public static void visitGoogle(){
        String baseUrl = "http://google.pl";
        driver.get(baseUrl);
    }
    
    
    public static void searchFor(String text){
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys(text);
        element.submit();
    }    

    static void turnOffWindow() {
        driver.close();
    }
    
    /**
     * Checks if text "Około <number> wyników" appeared on the screen.  
     * @return <number> extracted from aforementioned phrase
     */
    public String getPagesFoundNumberAsString(){
        WebElement results = driver.findElement(By.id("result-stats"));
        String pagesFound = "";
        String resultTxt = results.getText();
        String expectedResult = "Około ((\\d+\\s)+)wyników.+";
        Pattern p = Pattern.compile(expectedResult);
        Matcher m = p.matcher(resultTxt);
        if (!m.find()){
            System.out.println("Cannot find results message on the page");
            return null;
        }
        pagesFound = m.group(1);
        System.out.println("Number of found results: " + pagesFound);
        return pagesFound;
    }
   
    /**
     * Counts how many search results (links) are displayed on this specific page.  
     * @return number of links found int the form of integer. 
     */
    public int getNumberOfDisplayedResults(){
        List<WebElement> findElements = driver.findElements(By.xpath("(//div[@class='r']/a)"));
        System.out.println("Number of elements displayed: " + findElements.size());
        return findElements.size();
    }    
    
    public boolean isAValidNumber(String numberCandidate){
        String numberNoSpaces = numberCandidate.replaceAll(" ", "");
        return numberNoSpaces.chars().allMatch(Character::isDigit);
    }      

    boolean checkIfPageContainsText(String text) {
        boolean contains = driver.getPageSource().contains(text);
        if (contains){
            System.out.println("Found pictures");
        }
        else{
            System.out.println("Pictures not found");
        }
        return contains;
    }
}

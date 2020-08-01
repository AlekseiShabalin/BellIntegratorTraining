package ru.alekseishabalin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PageObjectGoogle {

    private String selectorSearchItems="//div[@class='g']";
    private String selectorURL = ".//div[@class='r']/a[@href]";
    private String selectorNamePage = ".//div[@class='r']/a[@href]";
    private String selectorDiscriprion = ".//div[@class='s']";

    private WebDriver driver;

    WebElement searchField;
    WebElement searchButton;
    List<WebElement> searchItems = new ArrayList<>();
    private List<Map<String, Object>> collectResults = new ArrayList<>();

    public List<WebElement> getSearchItems() {
        return driver.findElements(By.xpath(selectorSearchItems));
    }

    public List<Map<String, Object>> getCollectResults() {
        for(WebElement result : searchItems){
            collectResults.add(Map.of(
                    "WEB_ELEMENT", result,
                    "URL", result.findElement(By.xpath(selectorURL)).getAttribute("href"),
                    "NAME_PAGE", result.findElement(By.xpath(selectorNamePage)).getText(),
                    "DISCRIPTION", result.findElement(By.xpath(selectorDiscriprion)).getText()
            ));
        }
        return collectResults;
    }

    public PageObjectGoogle(WebDriver driver){
        this.driver = driver;
        searchItems = driver.findElements(By.xpath(selectorSearchItems));
    }

    public PageObjectGoogle(WebDriver driver, String search){
        this.driver = driver;
        this.driver.get("https://www.google.ru/search?q="+search);
        searchItems = driver.findElements(By.xpath(selectorSearchItems));
    }

}

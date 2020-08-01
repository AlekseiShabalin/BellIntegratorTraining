package ru.alekseishabalin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PageObjectGoogle {
    private WebDriver chromeDriver;
    WebElement searchField;
    WebElement searchButton;
    List<WebElement> results;

    public List<WebElement> getResults() {
        return chromeDriver.findElements(By.xpath("//*[@class=\"g\"]"));
    }

    PageObjectGoogle(WebDriver chromeDriver){
        this.chromeDriver=chromeDriver;
        searchField = chromeDriver.findElement(By.xpath("//*[@class=\"gLFyf gsfi\"]"));
        searchButton = chromeDriver.findElement(By.xpath("//*[@class=\"gNO89b\"]"));
    }

    public void find(String wordFind){
        searchField.click();
        searchField.sendKeys(wordFind);
        searchButton.click();
    }
}

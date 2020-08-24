package ru.aleksei;

import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class PageObjectYandexMarket {

    private String selectorSearchItems="//article[@class]//h3[@class]//span[@data-tid=\"ce80a508\"]";
    private String selectorSearchField="//input[@id=\"header-search\"]";
    private String selectorSearchButton="//button[@type='submit']";
    private String urlYandexMarket = "https://market.yandex.ru/";


    private WebDriver driver;
    WebElement searchField;
    WebElement searchButton;
    List<WebElement> searchItems = new ArrayList<>();

    public PageObjectYandexMarket(WebDriver driver){
        this.driver=driver;
        this.driver.get(urlYandexMarket);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void find(String keyWord){
        searchField = driver.findElement(By.xpath(selectorSearchField));
        searchField.click();
        searchField.sendKeys(keyWord);
        searchButton = driver.findElement(By.xpath(selectorSearchButton));
        searchButton.click();
    }

    public List<WebElement> getCollectResults() {
        searchItems = driver.findElements(By.xpath(selectorSearchItems));
        return searchItems;
    }

    public void clickButtonShowMore(String selectorButton){
        boolean staleElement = false;
        searchButton = driver.findElement(By.xpath(selectorButton));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        while (!staleElement) {
            try{
                executor.executeScript("arguments[0].click();", searchButton);
                driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
            } catch (StaleElementReferenceException e){
                staleElement = true;
            }
        }
    }

    public void filterByManufacturer(String nameManufacture){
        driver.findElement(By.xpath("//li[@class]//span[@class=\"NVoaOvqe58\" and text()=\""+ nameManufacture + "\"]/ancestor::*[2]")).click();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
    }
}

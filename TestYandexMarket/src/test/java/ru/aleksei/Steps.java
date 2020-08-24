package ru.aleksei;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class Steps {
    @Step("Шаг 1. Проверка открытия страницы Яндекс Маркета")
    public static void checkOpenYandexMarket(PageObjectYandexMarket yandexMarketPO){
        String title = yandexMarketPO.getDriver().getTitle();
        if(title.contains("Яндекс.Маркет — выбор и покупка товаров из проверенных интернет-магазинов")){
            Assertions.assertTrue(true);
        }else{
            CustomUtils.getScreen(yandexMarketPO.getDriver());
            Assertions.assertTrue(false,"Страница Яндекс Маркет не найдена");
        }
    }

    @Step("Шаг 2. Открытие страницы телефоны")
    public static void checkOpenSmartphonePage(PageObjectYandexMarket yandexMarketPO){
        yandexMarketPO.find("телефоны");
        String text = String.valueOf(yandexMarketPO.getDriver().findElement(By.xpath("//legend[@class=\"ShXb4FpS5R\" and contains(., \"Производитель\")]")));
        if (text.contains("Производитель")) {
            Assertions.assertTrue(true);
        }
        else {
            CustomUtils.getScreen(yandexMarketPO.getDriver());
            Assertions.assertTrue(false,"Страница \"Телефоны\" не найдена");
        }
    }

    @Step("Шаг 3. Применение фильтра Apple")
    public static void checkFilterApple(PageObjectYandexMarket yandexMarketPO){
        yandexMarketPO.filterByManufacturer("Apple");
        yandexMarketPO.clickButtonShowMore("//div[@data-apiary-widget-name]//div[@class=\"_8v6CFFrbuZ\"]/descendant::*[1]");
        yandexMarketPO.getCollectResults();
        System.out.println("Размер коллекции: " + yandexMarketPO.getCollectResults().size());
        Assertions.assertTrue(yandexMarketPO.getCollectResults().stream().anyMatch(x -> x.getText().contains("iPhone")));
    }
}

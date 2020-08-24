package ru.aleksei;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

public class Tests extends WebDriverSettings {

    @Test
    @Description(value = "Тест Яндекс Маркета по фильтру Apple")
    public void testCheckFilterApple(){
        PageObjectYandexMarket yandexMarketPO = new PageObjectYandexMarket(chromeDriver);
        Steps.checkOpenYandexMarket(yandexMarketPO);
        Steps.checkOpenSmartphonePage(yandexMarketPO);
        Steps.checkFilterApple(yandexMarketPO);
    }
}

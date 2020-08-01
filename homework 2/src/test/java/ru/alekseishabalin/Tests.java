package ru.alekseishabalin;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class Tests extends WebDriverSettings{

    @Test
    @Description(value = "Тест поиска по слову \"Гладиолус\"")
    public void testSearchWord(){
        PageObjectGoogle googleWithSearch = new PageObjectGoogle(chromeDriver, "гладиолус");
        List<Map<String,Object>> resultSearch = googleWithSearch.getCollectResults();
        Steps.checkContainsName(resultSearch,"Гладиолус", chromeDriver);
        Steps.checkNumberResults(resultSearch,"Гладиолус", chromeDriver);
        Steps.checkLink(resultSearch,"Гладиолус — Википедия", chromeDriver);
    }
}

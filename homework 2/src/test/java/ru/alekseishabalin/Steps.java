package ru.alekseishabalin;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

public class Steps {

    @Step("Шаг 1. Проверка наличия имени: {name}")
    public static void checkContainsName(List<Map<String,Object>> resultSearch, String name, WebDriver driver){
        if(resultSearch.stream().anyMatch(x->x.get("NAME_PAGE").toString().contains(name))){
            Assertions.assertTrue(true);
        }
        else {
            CustomUtils.getScreen(driver);
            Assertions.assertTrue(false,"Not found: "+ name);
        }
    }

    @Step("Шаг 2. Проверка количества результатов поиска по слову: {searchWord}")
    public static void checkNumberResults(List<Map<String,Object>> resultSearch, String searchWord, WebDriver driver){
        if (resultSearch.stream().anyMatch(x -> x.size() > 3)){
            Assertions.assertTrue(true);
        }
        else {
            CustomUtils.getScreen(driver);
            Assertions.assertTrue(false,"Less than 3 matches found for: "+ searchWord);

        }
    }

    @Step("Шаг 3. Проверка наличия ссылки \"Гладиолус - Википедия\"")
    public static void checkLink(List<Map<String,Object>> resultSearch, String name, WebDriver driver){
        if(resultSearch.stream().anyMatch(x->x.get("NAME_PAGE").toString().contains(name))){
            Assertions.assertTrue(true);
        }
        else {
            CustomUtils.getScreen(driver);
            Assertions.assertTrue(false,"No results found for \"Гладиолус - Википедия\"");
        }
    }



}

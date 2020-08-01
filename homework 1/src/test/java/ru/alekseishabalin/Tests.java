package ru.alekseishabalin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Tests extends WebDriverSettings{

    @Test
    public void checkNumberResult() {
        chromeDriver.get("https://www.google.com/");
        PageObjectGoogle googlePO = new PageObjectGoogle(chromeDriver);
        googlePO.find("гладиолус");
        Assertions.assertTrue(
                googlePO.getResults().size() > 3,
                "Less than three results found");
    }

    @Test
    public void checkWikipediaLink() {
        chromeDriver.get("https://www.google.com/");
        PageObjectGoogle googlePO = new PageObjectGoogle(chromeDriver);
        googlePO.find("гладиолус");
        Assertions.assertTrue(
                googlePO.getResults().stream().anyMatch(x -> x.getText().contains("Гладиолус - Википедия")),
                "No results found for \"Гладиолус - Википедия\" ");
    }


}

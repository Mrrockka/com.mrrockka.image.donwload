package service;

import lombok.SneakyThrows;
import model.UrlRequest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static utils.Const.IMG_PATTERN;

public class LinksAggregator {

    @SneakyThrows
    public static Set<URL> getLinks(UrlRequest request) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Set<URL> urls = Collections.emptySet();

        try {
            driver.get(request.getSiteUrl());
            JavascriptExecutor jsExec = (JavascriptExecutor) driver;
            if (jsExec.executeScript("return document.readyState").toString().equals("complete")) {
                urls = driver.findElements(By.tagName("img"))
                        .stream()
                        .map(img -> img.getAttribute("src"))
                        .filter(Objects::nonNull)
                        .filter(src -> IMG_PATTERN.matcher(src).matches())
                        .map(src -> {
                            try {
                                return new URL(src);
                            } catch (MalformedURLException e) {
                                return null;
                            }
                        })
                        .filter(Objects::nonNull)
                        .collect(Collectors.toSet());
            }
        } finally {
            driver.quit();
        }

        return urls;
    }
}

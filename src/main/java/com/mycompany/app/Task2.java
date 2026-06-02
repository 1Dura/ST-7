package com.mycompany.app;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Task2 {
    private static final String IPIFY_URL = "https://api.ipify.org/?format=json";
    private static final String RESPONSE_TAG = "pre";
    private static final String IP_FIELD = "ip";

    public static String getIpAddress(WebDriver driver) {
        driver.get(IPIFY_URL);
        String json = driver.findElement(By.tagName(RESPONSE_TAG)).getText();
        String ip = parseIp(json);
        System.out.println(ip);
        return ip;
    }

    static String parseIp(String json) {
        return new JSONObject(json).getString(IP_FIELD);
    }
}

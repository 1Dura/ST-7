package com.mycompany.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Task3 {
    private static final Path FORECAST_FILE = Paths.get("result", "forecast.txt");

    private static final String WEATHER_URL =
            "https://api.open-meteo.com/v1/forecast"
                    + "?latitude=56&longitude=44"
                    + "&hourly=temperature_2m,rain"
                    + "&current=cloud_cover"
                    + "&timezone=Europe%2FMoscow"
                    + "&forecast_days=1"
                    + "&wind_speed_unit=ms";

    public static String getWeatherForecast(WebDriver driver) throws IOException {
        driver.get(WEATHER_URL);
        String json = driver.findElement(By.tagName("pre")).getText();
        String table = formatForecast(json);
        Files.createDirectories(FORECAST_FILE.getParent());
        Files.writeString(FORECAST_FILE, table);
        System.out.print(table);
        return table;
    }

    static String formatForecast(String json) {
        JSONObject root = new JSONObject(json);
        JSONObject hourly = root.getJSONObject("hourly");
        JSONArray times = hourly.getJSONArray("time");
        JSONArray temperatures = hourly.getJSONArray("temperature_2m");
        JSONArray rain = hourly.getJSONArray("rain");

        StringBuilder table = new StringBuilder();
        table.append(String.format("%-3s | %-16s | %-11s | %-9s%n",
                "No", "Date/time", "Temperature", "Rain, mm"));
        table.append("----|------------------|-------------|----------")
                .append(System.lineSeparator());

        for (int i = 0; i < times.length(); i++) {
            appendForecastRow(table, i + 1, times.getString(i),
                    temperatures.getDouble(i), rain.getDouble(i));
        }
        return table.toString();
    }

    private static void appendForecastRow(StringBuilder table, int rowNumber,
            String time, double temperature, double rainMillimeters) {
        table.append(String.format("%-3d | %-16s | %-11.1f | %-9.2f%n",
                rowNumber, time, temperature, rainMillimeters));
    }
}

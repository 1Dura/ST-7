package com.mycompany.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AppTest {
    @Test
    public void parseIpReadsValueFromJson() {
        assertEquals("203.0.113.10", Task2.parseIp("{\"ip\":\"203.0.113.10\"}"));
    }

    @Test
    public void formatForecastBuildsRowsForHourlyData() {
        String json = "{"
                + "\"hourly\":{"
                + "\"time\":[\"2026-06-01T00:00\",\"2026-06-01T01:00\"],"
                + "\"temperature_2m\":[12.5,13.0],"
                + "\"rain\":[0.0,0.2]"
                + "}}";

        String table = Task3.formatForecast(json);

        assertTrue(table.contains("2026-06-01T00:00"));
        assertTrue(table.contains("12.5"));
        assertTrue(table.contains("0.20"));
        assertTrue(table.contains("2026-06-01T01:00"));
    }
}

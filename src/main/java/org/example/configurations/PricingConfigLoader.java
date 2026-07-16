package org.example.configurations;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PricingConfigLoader {
    private final Properties props = new Properties();

    public PricingConfigLoader(String resourceName) {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(resourceName)) {
            if (in == null) {
                throw new IllegalStateException("Config file not found on classpath: " + resourceName);
            }
            props.load(in);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load pricing config: " + resourceName, e);
        }
    }

    public BigDecimal getDecimal(String key) {
        String value = props.getProperty(key);
        if (value == null) {
            throw new IllegalArgumentException("Missing config key: " + key);
        }
        return new BigDecimal(value.trim());
    }

    public List<BigDecimal> getDecimalList(String key) {
        String value = props.getProperty(key);
        if (value == null) {
            throw new IllegalArgumentException("Missing config key: " + key);
        }
        List<BigDecimal> result = new ArrayList<>();
        for (String part : value.split(",")) {
            result.add(new BigDecimal(part.trim()));
        }
        return result;
    }
}

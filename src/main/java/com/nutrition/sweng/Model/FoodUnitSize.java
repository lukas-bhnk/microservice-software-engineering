package com.nutrition.sweng.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum FoodUnitSize {
    @JsonProperty("pro 100g essbarer Anteil")
    GRAMS("pro 100g essbarer Anteil"),
    @JsonProperty("pro 100 ml")
    MILLILITRE("pro 100 ml");

    private final String formatted;

    FoodUnitSize(String formatted) {
        this.formatted = formatted;
    }

    public String getUnitSize() {
        return formatted;
    }

    private static Map<String, FoodUnitSize> FORMAT_MAP = Stream
            .of(FoodUnitSize.values())
            .collect(Collectors.toMap(s -> s.formatted, Function.identity()));

    @JsonCreator
    public static FoodUnitSize fromString(String string) {
        return Optional
                .ofNullable(FORMAT_MAP.get(string))
                .orElseThrow(() -> new IllegalArgumentException(string));
    }
}

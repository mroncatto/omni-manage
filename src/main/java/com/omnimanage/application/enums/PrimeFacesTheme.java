package com.omnimanage.application.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum PrimeFacesTheme {

    SAGA("saga"),
    VELA("vela"),
    ARYA("arya");

    private final String value;

    public static PrimeFacesTheme getThemeByName(String theme) {
        Map<String, PrimeFacesTheme> list = new HashMap<>();
        list.put(SAGA.value, PrimeFacesTheme.SAGA);
        list.put(VELA.value, PrimeFacesTheme.VELA);
        list.put(ARYA.value, PrimeFacesTheme.ARYA);
        return list.getOrDefault(theme, PrimeFacesTheme.VELA);
    }
}

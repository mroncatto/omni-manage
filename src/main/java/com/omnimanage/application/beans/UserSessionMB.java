package com.omnimanage.application.beans;

import com.omnimanage.application.enums.PrimeFacesTheme;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Named(value = "userMB")
public class UserSessionMB implements Serializable {
    private PrimeFacesTheme theme = PrimeFacesTheme.SAGA;

    public void changeTheme(String theme) {
        this.theme = PrimeFacesTheme.getThemeByName(theme);
    }

    public String isCurrentTheme(String theme) {
        return this.theme.getValue().equals(theme) ? "on" : "off";
    }
}

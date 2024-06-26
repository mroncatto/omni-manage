package com.omnimanage.infrastructure.jsf.util;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

public final class FacesService {

    private FacesService(){}

    public static void abrirDialogo(String dialog) {
        PrimeFaces.current().executeScript("PF('" + dialog + "').show()");
    }

    public static void cerrarDialogo(String dialog) {
        PrimeFaces.current().executeScript("PF('" + dialog + "').hide()");
    }

    public static void actualizarElemento(String elemento) {
        PrimeFaces.current().ajax().update(elemento);
    }

    public static void mostrarInfo(String message) {
        showMessage(FacesMessage.SEVERITY_INFO, "Info",message);
    }

    public static void mostrarAlerta(String message) {
        showMessage(FacesMessage.SEVERITY_WARN, "Atencion",message);
    }

    public static void mostrarError(String message) {
        showMessage(FacesMessage.SEVERITY_ERROR, "Error",message);
    }

    private static void showMessage(FacesMessage.Severity severity, String summary, String message) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, message));
    }


}

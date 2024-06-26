package com.omnimanage.application.beans;

import com.omnimanage.application.usecase.cuentas.CreateCuentaUC;
import com.omnimanage.application.usecase.cuentas.DeleteCuentaUC;
import com.omnimanage.application.usecase.cuentas.UpdateCuentaUC;
import com.omnimanage.application.usecase.cuentas.facade.CuentaFacade;
import com.omnimanage.domain.cuentas.entity.Cuenta;
import com.omnimanage.infrastructure.jsf.util.FacesService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class CuentasMB implements Serializable {

    public static final String DIALOGO_NUEVA_CUENTA = "dialogoNuevaCuenta";

    @Inject
    private CuentaFacade cuentaFacade;

    @Getter
    private List<Cuenta> cuentas = new ArrayList<>();
    @Getter
    private Cuenta cuentaSeleccionada;
    @Getter
    @Setter
    private Cuenta nuevaCuenta;
    @Getter
    @Setter
    private String pickColor;
    @Getter
    private final List<String> palletColor = List.of("#264653", "#2a9d8f", "#e9c46a", "#f4a261", "#e76f51", "#d62828", "#023e8a", "#0077b6", "#0096c7", "#00b4d8", "#48cae4");


    @PostConstruct
    public void init() {
        cuentas = cuentaFacade.buscarCuentas().cuentas();
    }

    public void buscarCuentas() {
        cuentas = cuentaFacade.buscarCuentas().cuentas();
        FacesService.actualizarElemento("dt-cuentas");
    }

    public void abrirNuevaCuenta() {
        nuevaCuenta = new Cuenta();
        nuevaCuenta.setSaldoInicial(BigDecimal.ZERO);
        FacesService.abrirDialogo(DIALOGO_NUEVA_CUENTA);
    }

    public void salvarCuenta() {
        try {
            CreateCuentaUC.Input input = new CreateCuentaUC.Input(
                    nuevaCuenta.getDescripcion(),
                    pickColor,
                    nuevaCuenta.getSaldoInicial()
            );
            Cuenta cuenta = cuentaFacade.crearCuenta(input).cuenta();
            cuentas.add(0, cuenta);
            FacesService.actualizarElemento("dt-cuentas");
            FacesService.mostrarInfo("Nueva cuenta registrada!");
        } catch (Exception e) {
            FacesService.mostrarError(e.getMessage());
        }
    }

    public void alterarCuenta() {
        try {
            UpdateCuentaUC.Input input = new UpdateCuentaUC.Input(
                    cuentaSeleccionada.getId(),
                    cuentaSeleccionada.getDescripcion(),
                    pickColor,
                    cuentaSeleccionada.getSaldoInicial()
            );
            cuentaFacade.alterarCuenta(input);
            FacesService.mostrarInfo("Cuenta alterada!");
            FacesService.actualizarElemento("dt-cuentas");
        } catch (Exception e) {
            FacesService.mostrarError(e.getMessage());
        }
    }

    public void inactivarCuenta() {
        try {
            DeleteCuentaUC.Input input = new DeleteCuentaUC.Input(
                    cuentaSeleccionada.getId()
            );
            cuentaFacade.eliminarCuenta(input);
            cuentaSeleccionada.setActiva(false);
            FacesService.mostrarInfo("Cuenta inactivada!");
            FacesService.actualizarElemento("dt-cuentas");
        } catch (Exception e) {
            FacesService.mostrarError(e.getMessage());
        }
    }


    public void setCuentaSeleccionada(Cuenta cuentaSeleccionada) {
        this.cuentaSeleccionada = cuentaSeleccionada;
        this.pickColor = cuentaSeleccionada.getColor();
    }
}

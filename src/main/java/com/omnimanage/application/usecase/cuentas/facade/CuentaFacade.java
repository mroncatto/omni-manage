package com.omnimanage.application.usecase.cuentas.facade;

import com.omnimanage.application.usecase.cuentas.*;
import com.omnimanage.domain.cuentas.exception.CuentaCreateException;
import com.omnimanage.domain.cuentas.exception.CuentaNotFoundException;
import com.omnimanage.domain.cuentas.exception.CuentaUpdateException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class CuentaFacade {

    @Inject
    private CreateCuentaUC createCuentaUC;
    @Inject
    private GetCuentaUC getCuentaUC;
    @Inject
    private FindCuentasUC findCuentasUC;
    @Inject
    private DeleteCuentaUC deleteCuentaUC;
    @Inject
    private UpdateCuentaUC updateCuentaUC;

    public CreateCuentaUC.Output crearCuenta(CreateCuentaUC.Input input) throws CuentaCreateException {
        return createCuentaUC.execute(input);
    }

    public GetCuentaUC.Output obtenerCuentaPorId(GetCuentaUC.Input input) throws CuentaNotFoundException {
        return getCuentaUC.execute(input);
    }

    public FindCuentasUC.Output buscarCuentas() {
        return findCuentasUC.execute();
    }

    public void eliminarCuenta(DeleteCuentaUC.Input input) throws CuentaNotFoundException {
        deleteCuentaUC.execute(input);
    }

    public UpdateCuentaUC.Output alterarCuenta(UpdateCuentaUC.Input input) throws CuentaUpdateException {
        return updateCuentaUC.execute(input);
    }
}

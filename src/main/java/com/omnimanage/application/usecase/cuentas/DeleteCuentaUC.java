package com.omnimanage.application.usecase.cuentas;

import com.omnimanage.domain.cuentas.exception.CuentaNotFoundException;
import com.omnimanage.domain.cuentas.repository.CuentaRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class DeleteCuentaUC {
    @Inject
    private CuentaRepository repository;

    public void execute(Input input) throws CuentaNotFoundException {
        var cuenta = repository.obtenerCuentaPorId(input.id).orElseThrow(CuentaNotFoundException::new);
        if(cuenta.isActiva()) {
            cuenta.setActiva(false);
            repository.salvarCuenta(cuenta);
        } else {
            throw new CuentaNotFoundException();
        }

    }

    public record Input(Long id){}
}

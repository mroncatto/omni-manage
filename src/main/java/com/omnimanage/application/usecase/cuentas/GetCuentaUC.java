package com.omnimanage.application.usecase.cuentas;

import com.omnimanage.domain.cuentas.entity.Cuenta;
import com.omnimanage.domain.cuentas.exception.CuentaNotFoundException;
import com.omnimanage.domain.cuentas.repository.CuentaRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GetCuentaUC {

    @Inject
    private CuentaRepository repository;

    public Output execute(Input input) throws CuentaNotFoundException {
        var cuenta = repository.obtenerCuentaPorId(input.id).orElseThrow(CuentaNotFoundException::new);
        return new Output(cuenta);
    }

    public record Input(Long id){}
    public record Output(Cuenta cuenta) {}
}

package com.omnimanage.application.usecase.cuentas;

import com.omnimanage.domain.cuentas.entity.Cuenta;
import com.omnimanage.domain.cuentas.exception.CuentaCreateException;
import com.omnimanage.domain.cuentas.repository.CuentaRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.math.BigDecimal;

@Stateless
public class CreateCuentaUC {

    @Inject
    private CuentaRepository repository;

    public Output execute(Input input) throws CuentaCreateException {
        var cuenta = new Cuenta(null, input.descripcion, input.color, input.saldoInicial, true);
        var cuentaCreada = repository.salvarCuenta(cuenta).orElseThrow(CuentaCreateException::new);
        return new Output(cuentaCreada);
    }

    public record Input(
            String descripcion,
            String color,
            BigDecimal saldoInicial
    ) {
    }

    public record Output(Cuenta cuenta) {
    }
}

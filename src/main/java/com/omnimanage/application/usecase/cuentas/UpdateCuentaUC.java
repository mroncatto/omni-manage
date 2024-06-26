package com.omnimanage.application.usecase.cuentas;

import com.omnimanage.domain.cuentas.entity.Cuenta;
import com.omnimanage.domain.cuentas.exception.CuentaUpdateException;
import com.omnimanage.domain.cuentas.repository.CuentaRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.math.BigDecimal;

@Stateless
public class UpdateCuentaUC {

    @Inject
    private CuentaRepository repository;

    public Output execute(Input input) throws CuentaUpdateException {
        var cuenta = new Cuenta(input.id, input.descripcion, input.color, input.saldoInicial, true);
        var cuentaAlterada = repository.salvarCuenta(cuenta).orElseThrow(CuentaUpdateException::new);
        return new Output(cuentaAlterada);
    }

    public record Input(
            Long id,
            String descripcion,
            String color,
            BigDecimal saldoInicial
    ) {
    }

    public record Output(Cuenta cuenta) {
    }
}

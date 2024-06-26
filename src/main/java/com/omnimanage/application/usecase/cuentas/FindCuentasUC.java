package com.omnimanage.application.usecase.cuentas;

import com.omnimanage.domain.cuentas.entity.Cuenta;
import com.omnimanage.domain.cuentas.repository.CuentaRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class FindCuentasUC {

    @Inject
    private CuentaRepository repository;

    public Output execute() {
        var cuentas = repository.buscarCuentas();
        return new Output(cuentas);
    }

    public record Output(List<Cuenta> cuentas) {}
}

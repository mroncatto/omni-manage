package com.omnimanage.domain.cuentas.repository;

import com.omnimanage.domain.cuentas.entity.Cuenta;

import java.util.List;
import java.util.Optional;

public interface CuentaRepository {
    Optional<Cuenta> salvarCuenta(Cuenta cuenta);
    List<Cuenta> buscarCuentas();
    Optional<Cuenta> obtenerCuentaPorId(Long id);
}

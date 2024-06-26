package com.omnimanage.infrastructure.persistence.cuenta;

import com.omnimanage.domain.cuentas.entity.Cuenta;
import com.omnimanage.domain.cuentas.repository.CuentaRepository;
import com.omnimanage.infrastructure.config.AbstractRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class CuentaRepositoryImpl extends AbstractRepository<CuentaSchema, Long> implements CuentaRepository {

    @Inject
    private EntityManager em;

    public CuentaRepositoryImpl() {
        super(CuentaSchema.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Optional<Cuenta> salvarCuenta(Cuenta cuenta) {
        var cuentaCreada = save(cuenta.toSchema());
        return Optional.of(new Cuenta(cuentaCreada));
    }

    @Override
    public List<Cuenta> buscarCuentas() {
        return find()
                .stream()
                .map(Cuenta::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Optional<Cuenta> obtenerCuentaPorId(Long id) {
        var cuenta = new Cuenta(find(id));
        return Optional.of(cuenta);
    }
}

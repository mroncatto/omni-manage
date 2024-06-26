package com.omnimanage.application.usecase.cuentas;

import com.omnimanage.domain.cuentas.entity.Cuenta;
import com.omnimanage.domain.cuentas.exception.CuentaNotFoundException;
import com.omnimanage.domain.cuentas.repository.CuentaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class DeleteCuentaUCTest {

    @Mock
    private CuentaRepository repository;

    @InjectMocks
    private DeleteCuentaUC useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_debeRetornarError_cuandoLaCuentaYaEsInactiva() {
        Cuenta cuenta = new Cuenta(null, "Test Description", "#ffffff", new BigDecimal(10000), false);
        DeleteCuentaUC.Input input = new DeleteCuentaUC.Input(1L);
        when(repository.obtenerCuentaPorId(any(Long.class))).thenReturn(Optional.of(cuenta));

        // Act & Assert
        assertThrows(CuentaNotFoundException.class, () -> useCase.execute(input));
    }
}
package com.omnimanage.application.usecase.cuentas;

import com.omnimanage.domain.cuentas.entity.Cuenta;
import com.omnimanage.domain.cuentas.exception.CuentaCreateException;
import com.omnimanage.domain.cuentas.repository.CuentaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CreateCuentaUCTest {

    @Mock
    private CuentaRepository repository;

    @InjectMocks
    private CreateCuentaUC useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_debeRetornarOutput_cuandoLaCuentaEsCreada() throws CuentaCreateException {
        CreateCuentaUC.Input input = new CreateCuentaUC.Input("Test Description", "#ffffff", BigDecimal.valueOf(1000));
        Cuenta cuenta = new Cuenta(null, input.descripcion(), input.color(), input.saldoInicial(), true);
        when(repository.salvarCuenta(any(Cuenta.class))).thenReturn(Optional.of(cuenta));

        // Act
        var output = useCase.execute(input);

        // Assert
        assertEquals(cuenta, output.cuenta());
    }

    @Test
    void execute_debeLanzarException_cuandoLaCuentaNOesCreada() {
        CreateCuentaUC.Input input = new CreateCuentaUC.Input("Test Description", "#ffffff", BigDecimal.valueOf(1000));
        Cuenta cuenta = new Cuenta(null, "Test Description", "#ffffff", BigDecimal.valueOf(1000), true);
        when(repository.salvarCuenta(cuenta)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CuentaCreateException.class, () -> useCase.execute(input));
    }

}
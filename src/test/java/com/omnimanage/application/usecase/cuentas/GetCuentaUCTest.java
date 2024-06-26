package com.omnimanage.application.usecase.cuentas;

import com.omnimanage.domain.cuentas.exception.CuentaNotFoundException;
import com.omnimanage.domain.cuentas.repository.CuentaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class GetCuentaUCTest {

    @Mock
    private CuentaRepository repository;

    @InjectMocks
    private GetCuentaUC useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_debeRetornarError_cuandoLaCuentaNoSeEncuentra()  {
        GetCuentaUC.Input input = new GetCuentaUC.Input(1L);
        when(repository.obtenerCuentaPorId(any(Long.class))).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CuentaNotFoundException.class, () -> useCase.execute(input));
    }

}